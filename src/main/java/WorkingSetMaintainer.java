import java.util.HashMap;

public class WorkingSetMaintainer {

    // todo take this somewhere else as it should not be here;
    public static HashMap<Integer, Integer> prime2Index = new HashMap<>();
    public static HashMap<Integer, Integer> index2Prime = new HashMap<>();
    /**this function calculates the next prime given @param currentPrime and caches the information
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

    private int workingSetSize;
    private int workingSetLoad = 0;
    private WorkingSet workingSet = new WorkingSet();
    private SlidingWindow requestsLinkedList;

    WorkingSetMaintainer(int workingSetSize) {
        this.workingSetSize = workingSetSize;
        this.requestsLinkedList = new SlidingWindow(workingSetSize);
    }

    int getSomeoneNotFromWorkingSet() {
        int currentPrime = 2;
        while (!workingSet.isContained(currentPrime)) {
            currentPrime = nextPrime(currentPrime);
        }
        return currentPrime;
        // so we found a prime that is not in the working set, we will not find the
    }
}
