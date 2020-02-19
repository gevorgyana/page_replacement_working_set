import sun.rmi.rmic.Main;

import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * questions
 *
 * - class initiaization - whatthefuck happended to the static {} code from primesRoutines?
 *
 * - how to restrict PageAllocator from modifying its workingSetMaintainer? How to employ const-correctness in Java
 * like in C++ (const T&) ???
 *
 * */

public class MainFixture {

    // @Done
    public void testWorkingSetMaintainerAddPage() {
        WorkingSetMaintainer workingSetMaintainer = new WorkingSetMaintainer(2);
        for (int i = 1; i <= 10; ++i) {
            workingSetMaintainer.registerNewPage(PrimeRoutines.pageNumber2PrimeRepresentation(i));
            for (int e : workingSetMaintainer.getTemporallyOrderedRequests()) {
                System.out.println(e);
            }
            System.out.println("----");
        }
    }

    // @Done
    public void testWorkingSetMaintainerEviction() {
        // we know it returns the first number that is not in the working set and is inside the pageallocationhash
        // so create a couple of working sets here manually, then check how they behave

        WorkingSetMaintainer maintainer = new WorkingSetMaintainer(2);
        maintainer.registerNewPage(PrimeRoutines.pageNumber2PrimeRepresentation(2));
        maintainer.registerNewPage(PrimeRoutines.pageNumber2PrimeRepresentation(3));

        for (int e : maintainer.getTemporallyOrderedRequests()) {
            assert (e == 5 || e == 3);
        }

        Logger.getAnonymousLogger().info("Current working set is expected");
        // suppose we have just received consecutive requests for 2, 3, 5
        int pageAllocatorHash = 2 * 3 * 5;
        // we take 5, as we still have free space;
        // now we get 7, working set looks like 3 * 5,
        // and we need to discard 2! let's check that

        assert (2 == maintainer.getSomeoneNotFromWorkingSet(pageAllocatorHash));

        Logger.getAnonymousLogger().info("Maintainer discarded the right page");
    }

    void testPageAllocator() {
        WorkingSetMaintainer workingSetMaintainer = new WorkingSetMaintainer(2);
        PageAllocator pageAllocator = new PageAllocator(3, workingSetMaintainer);
        pageAllocator.allocatePage(1);
        pageAllocator.allocatePage(2);
        pageAllocator.allocatePage(3);
        pageAllocator.allocatePage(4);
        assert (pageAllocator.getPageAllocatorHash() == 3 * 5 * 7);
    }

    public static void main(String args[]) {
        MainFixture fixture = new MainFixture();
        fixture.testPageAllocator();


        /*
        PageReplacementSolution pageReplacementSolution = new PageReplacementSolution(2);
        for (int i = 0; i < 10; ++i) {
            pageReplacementSolution.processNextPageAllocationRequest();
        }
         */
    }
}
