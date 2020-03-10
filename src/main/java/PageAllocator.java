import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.logging.Logger;

public class PageAllocator {

    // modification of this object is done one level up in the call stack,
    // we can only ask it to give us a page number that is currently not in
    // the working set, when we need to evict someone
    private WorkingSetMaintainer workingSetMaintainer;

    private int maxSimultaneousPages;
    private int currentLoad = 0; // the amount of pages we are currently
                                 // keeping allocated

    private HashSet<Integer> pagesAllocated = new HashSet<>();

    PageAllocator(int maxSimultaneousPages,
                  WorkingSetMaintainer workingSetMaintainer)
            throws InvalidParameterException {
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
        if (pagesAllocated.contains(page)) {
            return;
        }
        if (currentLoad + 1 > maxSimultaneousPages) {
            int pageToBeEvicted = workingSetMaintainer.removeSomeoneNotFromWorkingSet();
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