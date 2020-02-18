import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/** lru on prime factorization*/

public class WorkingSetMaintainer {

    private int workingSetSize;
    private WorkingSet workingSet = new WorkingSet();
    private SlidingWindow requestsLinkedList;

    WorkingSetMaintainer(int workingSetSize) {
        this.workingSetSize = workingSetSize;
        this.requestsLinkedList = new SlidingWindow(workingSetSize);
    }

    void registerNewPage(int page) {
        requestsLinkedList.processNextPage(page);
        workingSet.addElement(page);
    }

    int getSomeoneNotFromWorkingSet(int pageAllocatorHash) {
        ArrayList<Integer> suspects = PrimeRoutines.primeFactors.get(pageAllocatorHash / workingSet.getHash());
        // the answer is guaranteed to exist
        int retval = -1; // it will never return -1!!!!!!!!! java wants me to initialize it!!!!
        for (int suspected : suspects) {
            if (workingSet.isContained(suspected)) {retval = suspected; break;}
        }
        return retval;
        // so we found a prime that is not in the working set and returned it
    }
}