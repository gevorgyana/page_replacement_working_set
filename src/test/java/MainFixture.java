import java.util.NoSuchElementException;

public class MainFixture {

    public void simulateCyclicPageAccess() {
        PageReplacementSolution pageReplacementSolution = new PageReplacementSolution(2);
        for (int i = 0; i < 10; ++i) {
            pageReplacementSolution.processNextPageAllocationRequest();
        }
    }

    /*
    // this would be a useless test, but it is helpful to look at this code
    // and read the comments
    public void workingSetSizeStaysConstant()
    {
        WorkingSetMaintainer workingSetMaintainer = new WorkingSetMaintainer(2);
        workingSetMaintainer.registerNewPage(1);
        workingSetMaintainer.registerNewPage(1);
        // invariant - after the next line finished,
        // the size of working set is 2;
        // the size of working set is the sum of all counters
        // it contains

        workingSetMaintainer.registerNewPage(1);

        workingSetMaintainer = new WorkingSetMaintainer(2);
        workingSetMaintainer.registerNewPage(1);
        workingSetMaintainer.registerNewPage(2);
        // invariant - at any point in between
        // these lines the size of working set is 2;
        // the size of working set is the sum of all counters
        // it contains
        workingSetMaintainer.registerNewPage(3);
        workingSetMaintainer.registerNewPage(4);
        workingSetMaintainer.registerNewPage(1);
        workingSetMaintainer.registerNewPage(2);
    }
     */

    void testWorkingSet() {
        WorkingSet workingSet = new WorkingSet();
        workingSet.addElement(1);
        workingSet.addElement(2);
        workingSet.addElement(3);
        workingSet.addElement(4);

        boolean noSuchElementIsThrows = false;
        try {
            workingSet.removeFirstNotInWorkingSet();
        } catch (NoSuchElementException e) {
            noSuchElementIsThrows = true;
        }

        assert (noSuchElementIsThrows);
        noSuchElementIsThrows = false;

        workingSet.removeElement(1);
        int removed = workingSet.removeFirstNotInWorkingSet();

        assert (removed == 1);

        try {
            workingSet.removeFirstNotInWorkingSet();
        } catch (NoSuchElementException e) {
            noSuchElementIsThrows = true;
        }

        assert (noSuchElementIsThrows);
        noSuchElementIsThrows = false;

        workingSet.removeElement(2);
        workingSet.removeElement(3);

        assert (workingSet.removeFirstNotInWorkingSet() == 2
                && workingSet.removeFirstNotInWorkingSet() == 3);

        workingSet.removeElement(4);
        workingSet.removeFirstNotInWorkingSet();

        try {
            workingSet.removeElement(1);
        } catch (NoSuchElementException e) {
            noSuchElementIsThrows = true;
        }

        assert (noSuchElementIsThrows);
    }

    public static void main(String args[]) {
        MainFixture fixture = new MainFixture();
        fixture.testWorkingSet();

        // logs from this call show the sequence of
        // allocation/removal events together with
        // page numbers
        fixture.simulateCyclicPageAccess();
    }
}
