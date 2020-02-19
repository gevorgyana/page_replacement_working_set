import java.util.ArrayList;


public class WorkingSetMaintainer {

    private int workingSetSize;
    private WorkingSet workingSet = new WorkingSet();

    WorkingSetMaintainer(int workingSetSize) { this.workingSetSize = workingSetSize; }
    void registerNewPage(int page) { workingSet.addElement(page); }
    int getSomeoneNotFromWorkingSet(int pageAllocatorHash) {
        ArrayList<Integer> suspects = PrimeRoutines.primeFactors.get(pageAllocatorHash / workingSet.getHash());
        int retval = -1;
        for (int suspected : suspects) {
            if (workingSet.isContained(suspected)) {retval = suspected; break;}
        }
        assert (retval != -1);
        return retval;
    }
}