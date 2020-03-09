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
     * Remembers the sequence of workingSetSize most
     * recent requests it has seen
     * */
    void registerNewPage(int page) {
        workingSet.addElement(page);
        temporallyOrderedRequests.add(0, page); // it is the
        // most recent page request so far, it will be discarded
        // after workingSetSize steps!
        if (temporallyOrderedRequests.size() == workingSetSize + 1) {
            workingSet.removeElement(temporallyOrderedRequests.removeLast()); // "clear" the tail
        }
    }

    int getSomeoneNotFromWorkingSet() {
        return workingSet.removeFirstNotInWorkingSet();
    }
}