import java.util.LinkedList;
import java.util.logging.Logger;

// complete

/**
 * This class is supposed to work with prime numbers only!
 * Because there is always one unique way to decompose
 * any number into primes; therefore we can decide quickly (+-, better than searching for
 * the number of ways we could decompose a number into factors, which can be big if they are
 * not primes),
 * which element is inside out factorization
 * */

public class RequestsFrontend {
    private static int fakePageNum = 0;

    /**
     * 1, .., 10 sequence -> cycled -> apply pageNumber2PrimeRepresentation to each item
     * */
    public int nextFakePage() {
        return PrimeRoutines.pageNumber2PrimeRepresentation((++fakePageNum) % 11 == 0 ? (fakePageNum = 1) : fakePageNum);
    }

    /**
     * Pulls next page from the user code; use this method in future
     * */
    // public int nextPage() {...}
}
