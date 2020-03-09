public class MainFixture {

    public void simulate() {
        PageReplacementSolution pageReplacementSolution = new PageReplacementSolution(2);
        for (int i = 0; i < 10; ++i) {
            pageReplacementSolution.processNextPageAllocationRequest();
        }
    }

    public void checkWorkingSetImplementaton()
    {
        WorkingSetMaintainer workingSetMaintainer = new WorkingSetMaintainer(2);
        workingSetMaintainer.registerNewPage(1);
        workingSetMaintainer.registerNewPage(1);
        workingSetMaintainer.registerNewPage(1);
        workingSetMaintainer = new WorkingSetMaintainer(2);
        workingSetMaintainer.registerNewPage(1);
        workingSetMaintainer.registerNewPage(2);
        workingSetMaintainer.registerNewPage(3);
        workingSetMaintainer.registerNewPage(4);
        workingSetMaintainer.registerNewPage(1);
        // workingSetMaintainer.registerNewPage1(2);

        // invariant - at any point in between
        // these lines the size of working set is 2;
        // the size of working set is the sum of all counters
        // it contains
    }

    public static void main(String args[]) {
        MainFixture fixture = new MainFixture();
        fixture.checkWorkingSetImplementaton();

        // logs from this call show the sequence of
        // allocation/removal events together with
        // page numbers
        fixture.simulate();
    }
}
