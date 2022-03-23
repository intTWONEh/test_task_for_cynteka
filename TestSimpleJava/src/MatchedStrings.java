public class MatchedStrings {
    private final String left;
    private final String right;

    public MatchedStrings(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    @Override
    public String toString() {
        return left + " : " + right;
    }
}
