/**
 * Stores the product of all elements it contains;
 * So really it should be called implicit set;
 * its interface only allows to check whether a given element
 * is stored by it and add new elements; its initial purpose is to store prime
 * numbers; it is also supposed to store multiple elements of
 * the same nature - so it is actually multiset
 * */

public class WorkingSet {
    private int hash = 1;
    public int getHash() {
        return hash;
    }
    public void addElement(int element) { hash *= element; }
    public boolean isContained(int element) {
        return (hash % element == 0);
    }
    /**Let's assume that @param element has been previously registered in the set -
     * todo error handling*/
    public void removeElement(int element) {
        hash /= element;
    }
}
