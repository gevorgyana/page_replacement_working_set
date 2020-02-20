import java.util.ArrayList;
import java.util.LinkedList;

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
     * this way, we get constant time to add/remove an element from/to the list and the hash,
     * in exchange for linear space for the FIFO queue of size workingSetSize
     * */
    void registerNewPage(int page) {
        workingSet.addElement(page); // modify the hash
        temporallyOrderedRequests.add(0, page); // it is the most recent page request so far, it will be
        // discarded after workingSetSize steps!
        if (temporallyOrderedRequests.size() == workingSetSize + 1) {
            workingSet.removeElement(temporallyOrderedRequests.removeLast()); // "clear" the tail
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

        /**
         * bottleneck # 1
         * Possible solutions
         *
         * 1) no reason to cache as there are multiple combinations of working set and allocated
         * pages' hashes; in worst case, working set contains 1 element
         *     (so the hash looks like p * .. * p workingsetSize times),
         * allocated pages
         * n elements, then we get
         *     (max - n)th, (max - n + 1)th, ..., (max)th primes multiplied in allocatedPages
         * hash and it becomes a problem to factor this thing out - so maybe we can see what happens if we
         * associate a range [l, r] with the hash on this set, and then take some k such that
         * for some i k * i elements from (max - n)th, (max - n + 1)th, ..., (max)th primes multiplied
         * go into the range and stay cached, this way, we reduce usage of processor time from linear
         * O(n) (we would have to try every possible page!) to O(k), where k is chosen; space would be
         * O(n / k);
         *
         * 2) LRU cache here
         * */
        for (int suspected : suspects) {
            if (!workingSet.isContained(suspected)) {pageNotFromWorkingSet = suspected; break;}
        }

        assert (pageNotFromWorkingSet != -1); // this cannot happen - there is always someone to be evicted
        return pageNotFromWorkingSet;
    }
}