/**
 * todo provide framework for time measurement
 * required to finish with a portions of requests
 * this is related to the whole project rather than to this
 * class
 * */

// complete

public class PageReplacementSolution {
    private RequestsFrontend requestsFrontend;
    private PageAllocator pageAllocator;
    private WorkingSetMaintainer workingSetMaintainer;

    PageReplacementSolution(int configurationConstant) {
        this.workingSetMaintainer = new WorkingSetMaintainer(configurationConstant);
        this.pageAllocator = new PageAllocator(configurationConstant + 1, workingSetMaintainer);
        this.requestsFrontend = new RequestsFrontend();
    }

    /**
     * This method is the entry point to the logic of the application;
     * */
    public void processNextPageAllocationRequest() {
        int nextPage = requestsFrontend.nextFakePage(); // change this stub method later
        workingSetMaintainer.registerNewPage(nextPage); // first we register the new page to get a fresh working set
        pageAllocator.allocatePage(nextPage); // then we process it
    }
}