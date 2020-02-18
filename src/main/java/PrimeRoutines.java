import java.util.ArrayList;
import java.util.HashMap;

public class PrimeRoutines {
    public static HashMap<Integer, ArrayList<Integer>> primeFactors;
    public static HashMap<Integer, Integer> prime2Index = new HashMap<>();
    public static HashMap<Integer, Integer> index2Prime = new HashMap<>();
    /**this function calculates the next prime after @param currentPrime and caches the information
     * in 2 tables - prime2Index and index2Prime*/
    public static int nextPrime(int currentPrime) {
        if ((index2Prime.get(1 + prime2Index.get(currentPrime))) != null) { // this info is cached - return it
            return (index2Prime.get(1 + prime2Index.get(currentPrime))); // this is inefficient double method invo-
            // cation - todo prettify
        }

        int nextPrime = currentPrime + 2;
        while (true) {
            boolean isPrime = true;
            for (int i = 2; isPrime && i < Math.sqrt(nextPrime) && (isPrime &= ((currentPrime % i == 0))); ++i) {}
            if (isPrime) {}
            ++nextPrime;
        }
    }
}
