import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * todo provide framework for measurement of time
 * required to finish with a portions of requests
 * this is related to the whole project rather than to this
 * class
 * */

// complete

public class RequestsFrontend {
    private static int fakePageNum = 0;

    /**
     * Returns 1, .. , 10, cycled sequence; this is the sequence of pages required for testing
     * */
    public int nextFakePage() {
        return ((++fakePageNum) % 11 == 0 ? (fakePageNum = 1) : fakePageNum);
    }

    /**
     * Pulls next page from the user code; use this method in future
     * */
    // public int nextPage() {...}

    public static void main(String args[]) {

        RequestsFrontend requestsFrontend = new RequestsFrontend();

        /*
        for (int i = 0; i < 20; ++i) {
            Logger.getAnonymousLogger().info(Integer.toString(requestsFrontend.nextFakePage()));
        }
         */

    }
}
