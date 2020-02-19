import java.util.*;

public class PrimeRoutines {

    private static int precalculationLimit = 10;
    public static HashMap<Integer, ArrayList<Integer>> primeFactors;

    {
        for (int i = 0; i < precalculationLimit; ++i) {
            for (int j = 0; j < i; ++j) {
                if ((primeFactors.get(j).size() == 1) && (i % j == 0)) {
                    primeFactors.get(i).add(j);
                }
            }
        }
    }

    public static ArrayList<Integer> primes = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
    public static int pageNumber2PrimeRepresentation(int page) { return primes.get(page - 1); }
    public static int primeRepresentation2PageNumber(int primeRepresentation) {
        return Collections.binarySearch(primes, primeRepresentation) + 1;
    }
}
