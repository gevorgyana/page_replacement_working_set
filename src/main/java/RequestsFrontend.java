

public class RequestsFrontend {
    private static int fakePageNum = 0;

    public int nextFakePage() {
        return PrimeRoutines.
                pageNumber2PrimeRepresentation
                        ((++fakePageNum) % 11 == 0 ? (fakePageNum = 1) : fakePageNum);
    }

    /**
     * Pulls next page from the user code; use this method in future
     * */
    // public int nextPage() {...}
}
