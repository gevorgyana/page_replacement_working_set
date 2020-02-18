import java.security.InvalidParameterException;
import java.util.logging.Logger;

/**
 * it is only allowed to add pages so far,
 * you cannot remove them
 * */

/**
 *
 * small assumption! nobody else apart from this class
 * uses logging facilities; in this way we will see clear sequence
 * of actions performed on pages and it all will make sense;
 * internal tooling used to generate this sequence of logs is
 * not important for simulation purpose!
 * */

public class PageAllocator {
    private WorkingSetMaintainer workingSetMaintainer;
    private int maxSimultaneousPages;
    private int currentLoad = 0;

    PageAllocator(int maxSimultaneousPages) throws InvalidParameterException {
        if (maxSimultaneousPages < 2) {
            throw new InvalidParameterException("maxSimultaneousPages must be >= 2!");
        }
        this.workingSetMaintainer = new WorkingSetMaintainer(maxSimultaneousPages - 1);
        this.maxSimultaneousPages = maxSimultaneousPages;
    }

    void allocatePage(int page) {
        if (currentLoad > maxSimultaneousPages) {
            // oopps, we need to remove somebody from the set of pages that have been allocated so far;
            // for that, we will ask the WorkingSet manager to give us some page that is currently not
            // inside of the working set
            int pageToBeEvicted = workingSetMaintainer.getSomeoneNotFromWorkingSet();
            Logger.getAnonymousLogger().info(String.format("Page #%d has been evicted", pageToBeEvicted));
            /**
             * at this point it is assumed that there the workingsetmanager is informed that we
             * have avicted the page, but in real world todo anything could happen,
             * and the workingsetmanager must provide us a way to confirm removal
             * */
        }
        Logger.getAnonymousLogger().info(String.format("Page #%d has been allocated;", page));
    }

    void freePage(int page) {
        Logger.getAnonymousLogger().info(String.format("Page #d has been freed", page));
    }
}