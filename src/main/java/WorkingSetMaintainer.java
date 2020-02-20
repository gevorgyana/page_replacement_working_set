import java.util.ArrayList;
import java.util.LinkedList;

// @Tested
public class WorkingSetMaintainer {

    private int workingSetSize;
    private LinkedList<Integer> temporallyOrderedRequests = new LinkedList();
    private WorkingSet workingSet = new WorkingSet();

    public LinkedList<Integer> getTemporallyOrderedRequests() {
        LinkedList<Integer> snapshot = new LinkedList<>();
        for (int e : temporallyOrderedRequests) {
            snapshot.addLast(e);
        }
        return snapshot;
    }

    WorkingSetMaintainer(int workingSetSize) { this.workingSetSize = workingSetSize; }

    /**
     * This method remembers the sequence of workingSetSize most recent requests it has seen;
     * this way, we can get constant time to add/remove an element from/to the list and the hash,
     * in exchange for linear space for the FIFO queue of size workingSetSize
     * */
    void registerNewPage(int page) {
        workingSet.addElement(page); // modify the hash
        temporallyOrderedRequests.add(0, page); // it is the post recent page request so far, it will be discarded after
                                  // workingSetSize steps!
        if (temporallyOrderedRequests.size() == workingSetSize + 1) {
            workingSet.removeElement(temporallyOrderedRequests.removeLast()); // "clean" the tail
        }
    }

    int getSomeoneNotFromWorkingSet(int pageAllocatorHash) {
        ArrayList<Integer> suspects = PrimeRoutines.
                primeFactors(pageAllocatorHash / workingSet.getHash());
        assert (!suspects.isEmpty()); // this will never happen as we have selected the sizes of working set
        // and our page allocator to differ by one; the size of page allocator is bigger and it contains
        // at least all elements from the working set - invariant

        int pageNotFromWorkingSet = -1; // now we need to select somebody from the pageAllocatorHash who is not
        // inside the working set
        for (int suspected : suspects) {
            if (!workingSet.isContained(suspected)) {pageNotFromWorkingSet = suspected; break;}
        }

        assert (pageNotFromWorkingSet != -1); // this cannot happen
        return pageNotFromWorkingSet;
    }
}