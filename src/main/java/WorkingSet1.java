import java.util.HashMap;
import java.util.TreeSet;

/**
 * this class maintains an internal set of pages ready to be evicted by
 * page allocator; it does so by watching incoming page requests
 * */

public class WorkingSet1 {

    HashMap<Integer, Integer> page2counter = new HashMap<>();

    // it is meant to be empty at the very beginning;
    // as soon as it is needed it is not empty
    TreeSet<Integer> evictedPages = new TreeSet<>();

    public void addElement(int page) {
        if (evictedPages.contains(page)) {
            evictedPages.remove(page);
        }

        if (page2counter.containsKey(page)) {
            int currentCounter = page2counter.get(page);
            page2counter.replace(page, currentCounter + 1);
        } else {
            page2counter.put(page, 1);
        }
    }

    public void removeElement(Integer last) {
        int currentIndex = page2counter.get(last);
        if (currentIndex == 1) {
            page2counter.remove(last);
            evictedPages.add(last);
        } else {
            page2counter.replace(last, currentIndex - 1);
        }
    }

    public int removeFirstNotInWorkingSet() {
        int retVal = evictedPages.first();
        evictedPages.remove(evictedPages.first());
        return retVal;
    }
}
