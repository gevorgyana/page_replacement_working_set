import java.util.LinkedList;

/**
 *
 * pages
 *
 * 1 | 1 2 | 1 2 3
 *
 * working set
 *
 * 1 | 1 2 | 2 3
 *
 * pages in page loader
 *
 * 1 | 1 2 | 1 2 3
 *
 * */

// complete

    /*

    this class is not used at all, i have no idea why it appeared;
    maybe it will be needed when i turn working set into multiset, but no - it is not
    needed anyway!

public class SlidingWindow {
    private int slidingWindowSize;
    private LinkedList<Integer> requestsSlidingWindow = new LinkedList<> ();

    SlidingWindow(int slidingWindowSize) {
        this.slidingWindowSize = slidingWindowSize;
    }

    public void processNextPage(int page) {
        requestsSlidingWindow.addLast(page);
        if (requestsSlidingWindow.size() > slidingWindowSize) {
            requestsSlidingWindow.pop();
        }
    }
}
*/