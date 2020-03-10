public class PageReplacementSolution {
    private RequestsFrontend requestsFrontend;
    private PageAllocator pageAllocator;
    private WorkingSetMaintainer workingSetMaintainer;

    PageReplacementSolution(int configurationConstant) {
        this.workingSetMaintainer = new WorkingSetMaintainer
                (configurationConstant);
        this.pageAllocator = new PageAllocator
                (configurationConstant + 1,
                workingSetMaintainer);
        this.requestsFrontend = new RequestsFrontend();
    }

    public void processNextPageAllocationRequest() {
        int nextPage = requestsFrontend.nextFakePage();
        pageAllocator.allocatePage(nextPage);
        workingSetMaintainer.registerNewPage(nextPage);
    }
}