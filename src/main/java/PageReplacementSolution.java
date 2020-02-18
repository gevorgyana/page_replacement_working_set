/**
 * Todo try to store all elements in sorted order
 * you will need linear memory, but get constant
 * access time, i really do not know
 * what makes this solution not suitable, it looks good
 * when there is not much to store
 *
 * and the algorithm that involves factorization is not useful at all in this case
 * although we can employ LRU cahce to make use of temporal locality!!!!
 * */

/**
 * This class is supposed to work with prime numbers only!
 * Because there is always one unique way to decompose
 * any number into primes; therefore we can decide quickly (+-, better than searching for
 * the number of ways we could decompose a number into factors, which can be big if they are
 * not primes),
 * which element is inside out factorization
 * */

// complete

public class PageReplacementSolution {
    private RequestsFrontend requestsFrontend;
    private int configurationConstant;
    private PageAllocator pageAllocator;

    PageReplacementSolution(int configurationConstant) {
        this.configurationConstant = configurationConstant;
        requestsFrontend = new RequestsFrontend();
    }

    public void processNextPageFreeRequest() {

    }

    public void processNextPageAllocationRequest() { // this is the entry point to the logic
        // of the algorithm; everything happens here, it is like the lowest frame
        // in backtrace of a program
        int nextPage = requestsFrontend.nextFakePage(); // change this stub method later
        pageAllocator.allocatePage(nextPage);
    }

    public static void main(String args[]) {
        PageReplacementSolution pageReplacementSolution = new PageReplacementSolution(2);
        for (int i = 0; i < 10; ++i) {
            pageReplacementSolution.processNextPageAllocationRequest();
        }
    }
}
