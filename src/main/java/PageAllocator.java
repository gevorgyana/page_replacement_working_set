import java.security.InvalidParameterException;
import java.util.logging.Logger;

public class PageAllocator {

    /**
     * we need this to tell us whom to evict, BUT we do not modify
     * the working set, its update is handled up the call stack!
     * */
    private WorkingSetMaintainer workingSetMaintainer;

    private int maxSimultaneousPages;
    private int currentLoad = 0; // the amount of pages we are currently keeping in working set

    /**
     * There can't be 2 or more same elements as this is set, not multiset,
     * in contrast to the working set, which is multiset actually
     * */
    private int pageAllocatorHash = 1;

    int getPageAllocatorHash() {
        return pageAllocatorHash;
    }

    PageAllocator(int maxSimultaneousPages, WorkingSetMaintainer workingSetMaintainer)
            throws InvalidParameterException
    {
        if (maxSimultaneousPages < 2) {
            throw new InvalidParameterException("maxSimultaneousPages must be >= 2! working set must be of " +
                    "non-negative size and page allocator must be of size at least 1 + size of working set;" +
                    "this is needed to guarantee that at any point in time there will be a page that is not in" +
                    "working set and is allocated (page to be evicted), given that there is need for eviction");
        }
        this.maxSimultaneousPages = maxSimultaneousPages;
        this.workingSetMaintainer = workingSetMaintainer;
    }

    void allocatePage(int page) {
        if (pageAllocatorHash % page == 0) {
            // already stored - the page is in memory now
            return;
        }
        if (currentLoad + 1 > maxSimultaneousPages) {
            int pageToBeEvicted = workingSetMaintainer.getSomeoneNotFromWorkingSet(pageAllocatorHash);
            pageAllocatorHash /= pageToBeEvicted;
            --currentLoad;
            Logger.getAnonymousLogger().info(String.format("Page #%d has been evicted",
                    PrimeRoutines.
                            primeRepresentation2PageNumber(pageToBeEvicted)));
        }
        ++currentLoad;
        pageAllocatorHash *= page;
        Logger.getAnonymousLogger().info(String.format("Page #%d has been allocated;",
                PrimeRoutines.primeRepresentation2PageNumber(page)));
    }
}