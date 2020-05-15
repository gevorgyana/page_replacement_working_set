import java.util.LinkedList;
import java.util.NoSuchElementException;


public class WorkingSetMaintainer {

    private int workingSetSize;
    private LinkedList<Integer> recentRequests = new LinkedList<>();
    private WorkingSet workingSet = new WorkingSet();

    public LinkedList<Integer> getRecentRequests() {
        LinkedList<Integer> snapshot = new LinkedList<>();
        for (int e : recentRequests) {
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
        recentRequests.add(0, page);
        if (recentRequests.size() == workingSetSize + 1) {
            workingSet.removeElement(recentRequests.removeLast());
        }
    }

    int removeSomeoneNotFromWorkingSet() throws NoSuchElementException {
        return workingSet.removeFirstNotInWorkingSet();
    }
}
