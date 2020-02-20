import java.util.*;
import java.util.logging.Logger;

public final class PrimeRoutines {
    public static ArrayList<Integer> primes = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
    public static int pageNumber2PrimeRepresentation(int page) { return primes.get(page - 1); }
    public static int primeRepresentation2PageNumber(int primeRepresentation) {
        return Collections.binarySearch(primes, primeRepresentation) + 1;
    }

    // # bottleneck # 2 - lru cache is the only reasonable solution here
    public static ArrayList<Integer> primeFactors(int i) {
        ArrayList<Integer> retval = new ArrayList<>();
        for (int suspect : primes) {
            if (i % suspect == 0) {
                retval.add(suspect);
            }
        }
        return retval;
    }
}