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
        pageAllocator.allocatePage(nextPage); // process it
        // workingSetMaintainer.registerNewPage(nextPage); // register the new page to get a fresh working set
        // for future generations

        workingSetMaintainer.registerNewPage1(nextPage);
    }
}