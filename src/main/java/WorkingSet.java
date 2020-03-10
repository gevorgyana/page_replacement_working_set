import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * this class maintains an internal set of pages ready to be evicted by
 * page allocator; it does so by watching incoming page requests; there
 * are 2 types of requests that have to be emitted by WorkingSetMaintainer
 * - add a new page and remove one from the working set.
 * */

public class WorkingSet {

    HashMap<Integer, Integer> page2counter = new HashMap<>();

    // it is meant to be empty at the very beginning;
    // as soon as it is needed it is not empty
    // tree because i need to find at least 1 element
    // from the set on eviction request -> it has to be iterable
    TreeSet<Integer> evictedPages = new TreeSet<>();

    public void addElement(int page) {
        evictedPages.remove(page);
        if (page2counter.containsKey(page)) {
            int currentCounter = page2counter.get(page);
            page2counter.replace(page, currentCounter + 1);
        } else {
            page2counter.put(page, 1);
        }
    }

    public void removeElement(Integer last) throws NoSuchElementException {
        Integer currentCounter = page2counter.get(last);
        if (currentCounter == null) {
            throw new NoSuchElementException("there is currently no such element " +
                    "in the working set");
        }
        if (currentCounter == 1) {
            page2counter.remove(last);
            evictedPages.add(last);
        } else {
            page2counter.replace(last, currentCounter - 1);
        }
    }

    public int removeFirstNotInWorkingSet() throws NoSuchElementException {
        int retVal = evictedPages.first();
        evictedPages.remove(evictedPages.first());
        return retVal;
    }
}
