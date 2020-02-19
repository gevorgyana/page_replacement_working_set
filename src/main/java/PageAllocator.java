import java.security.InvalidParameterException;
import java.util.logging.Logger;

/**
 * it is only allowed to add pages so far,
 * you cannot remove them - they can only go away it
 * eviction takes place
 * */

/**
 *
 * small assumption! nobody else apart from this class
 * uses logging facilities; this way we will see clear sequence
 * of actions performed on pages and it all will make sense;
 * internal tooling used to generate this sequence of logs is
 * not important for simulation purpose!
 * */

// done

public class PageAllocator {
    private WorkingSetMaintainer workingSetMaintainer; // we need this boy to tell us whom to evict
    private int maxSimultaneousPages;
    private int currentLoad = 0; // the amount of pages we currently keep in working set
    private int pageAllocatorHash = 1;

    PageAllocator(int maxSimultaneousPages, WorkingSetMaintainer workingSetMaintainer)
            throws InvalidParameterException
    {
        if (maxSimultaneousPages < 2) {
            throw new InvalidParameterException("maxSimultaneousPages must be >= 2!");
        }
        this.maxSimultaneousPages = maxSimultaneousPages;
        this.workingSetMaintainer = workingSetMaintainer;
    }

    void allocatePage(int page) {
        if (currentLoad > maxSimultaneousPages) {
            int pageToBeEvicted = workingSetMaintainer.getSomeoneNotFromWorkingSet(pageAllocatorHash);
            Logger.getAnonymousLogger().info(String.format("Page #%d has been evicted", pageToBeEvicted));
            pageAllocatorHash /= pageToBeEvicted;

            /**
             * at this point it is assumed that there the WorkingSetManager is informed that we
             * have evicted the page, but in real world todo anything could happen,
             * and the WorkingSetManager must provide us a way to confirm removal
             * */
        }
        ++currentLoad;
        pageAllocatorHash *= page;
        Logger.getAnonymousLogger().info(String.format("Page #%d has been allocated;", page));
    }

    /* todo implement
    void freePage(int page) {
        Logger.getAnonymousLogger().info(String.format("Page #d has been freed", page));
    }
     */
}