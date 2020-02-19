import java.security.InvalidParameterException;
import java.util.logging.Logger;

/**
 * small assumption! nobody else apart from this class uses logging facilities; this way we will see clear sequence
 * of actions performed on pages and it all will make sense; internal tooling used to generate this sequence of logs is
 * not important for simulation purpose!
 * */

public class PageAllocator {
    private WorkingSetMaintainer workingSetMaintainer; // we need this to tell us whom to evict
    private int maxSimultaneousPages;
    private int currentLoad = 0; // the amount of pages we currently keep in working set
    private int pageAllocatorHash = 1;

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
        if (currentLoad > maxSimultaneousPages) {
            int pageToBeEvicted = workingSetMaintainer.getSomeoneNotFromWorkingSet(pageAllocatorHash);
            Logger.getAnonymousLogger().info(String.format("Page #%d has been evicted",
                    PrimeRoutines.primeRepresentation2PageNumber(pageToBeEvicted)));
            pageAllocatorHash /= pageToBeEvicted;

            /**
             * at this point it is assumed that there the WorkingSetManager is informed that we
             * have evicted the page, but in real world todo anything could happen,
             * and the WorkingSetManager must provide us a way to confirm removal
             * */
        }
        ++currentLoad;
        pageAllocatorHash *= page;
        Logger.getAnonymousLogger().info(String.format("Page #%d has been allocated;",
                PrimeRoutines.primeRepresentation2PageNumber(page)));
    }
}