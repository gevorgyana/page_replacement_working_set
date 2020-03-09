import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.logging.Logger;


public class PageAllocator {

    // An instance of the class needs this to tell it whom to evict,
    // (modification of this object is done one level up in the call stack)
    private WorkingSetMaintainer workingSetMaintainer;

    private int maxSimultaneousPages;
    private int currentLoad = 0; // the amount of pages we are currently
                                 // keeping in working set

    private HashSet<Integer> pagesAllocated = new HashSet<>();

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

    boolean pageIsLoaded(int page) {
        return pagesAllocated.contains(page);
    }

    void allocatePage(int page) {
        if (pagesAllocated.contains(page)) {
            return;
        }
        if (currentLoad + 1 > maxSimultaneousPages) {
            int pageToBeEvicted = workingSetMaintainer.getSomeoneNotFromWorkingSet();
            pagesAllocated.remove(pageToBeEvicted);
            --currentLoad;
            Logger.getAnonymousLogger().info(String.format("Page #%d has been evicted",
                    pageToBeEvicted));
        }
        ++currentLoad;
        pagesAllocated.add(page);
        Logger.getAnonymousLogger().info(String.format("Page #%d has been allocated;",
                page));
    }
}