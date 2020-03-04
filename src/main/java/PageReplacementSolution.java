/**
 * update: todo rewrite this;
 * new algorithm;
 * maintain a counter for each page
 *
 * it must be similar to the following:
 * 1 2 3 = requests
 *
 * take 1, it is not allocated, cnt(1) = 1, alocate
 * take 2, it is not allocated, cnt(2) = 1, allocate
 * take 2, it is allocated, continue
 * take 3, it is not allocated, not enough space, eviction:
 *         take sb with lowest cnt : x = argmin cnt(.) - take 1
 *         evict 1, store 3: wscounter(3) = 1, wscounter(1) = 0 -> it reached 0 -> eviction
 *
 *
 * task: maintaint ws
 * interface:
 * take sb not from ws
 * check if x is in ws
 *
 * ws_maintainer:
 * interface:
 * serve(x)
 *   if (x loaded) return // btw it means it is in ws
 *   if (x not in ws) remove last element from queue, say y; then decrese(ws_counter(y)) -> if it reaches 0
 *   remove it from ws; add x to ws
 * */


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
        workingSetMaintainer.registerNewPage(nextPage); // register the new page to get a fresh working set
        // for future generations
    }
}