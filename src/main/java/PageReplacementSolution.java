/**
 * todo what happens if we need to store the amount of elements
 * and then decide on which one to evict - maybe the one that is not currently in the working set
 * типа что если там будет мультисет, что если воркинг сет это мультисет, а не сет? по книжке это сет, но что будет,
 * если сделать его мультисетом?????
 * */

/**
 * todo provide framework for time measurement
 * required to finish with a portions of requests
 * this is related to the whole project rather than to this
 * class
 * */

/**
 * Todo try to store all elements in sorted order
 * you will need linear memory, but get constant
 * access time, i really do not know
 * what makes this solution not suitable, it looks good
 * when there is not much to store
 *
 * and the algorithm that involves factorization is not useful at all in this case
 * although we can employ LRU cahce to make use of temporal locality!!!!
 * */

// complete

public class PageReplacementSolution {
    private int configurationConstant;
    private RequestsFrontend requestsFrontend;
    private PageAllocator pageAllocator;
    private WorkingSetMaintainer workingSetMaintainer;

    /**
     * WorkingSetMaintainer and PageAllocator are independently from each other updated to reflect
     * the current state of the incoming requests; also WorkingSetMaintainer is fed to PageAllocator
     * via dependency injection
     * */
    PageReplacementSolution(int configurationConstant) {
        this.configurationConstant = configurationConstant;
        this.workingSetMaintainer = new WorkingSetMaintainer(configurationConstant);
        this.pageAllocator = new PageAllocator(configurationConstant + 1, workingSetMaintainer);
        requestsFrontend = new RequestsFrontend();
    }

    /**
     * This method is the entry point to the logic of the application;
     * */
    public void processNextPageAllocationRequest() {
        int nextPage = requestsFrontend.nextFakePage(); // change this stub method later
        workingSetMaintainer.registerNewPage(nextPage); // first we register the new page to get a fresh working set
        pageAllocator.allocatePage(nextPage); // then we process it
    }

    /* todo implement public void processNextPageFreeRequest() {}*/
}