package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String text;

    public ReversedSequence(String text) {
        this.text = text;
    }

    @Override
    public int length() {
        return text .length();
    }

    @Override
    public char charAt(int index) {
        return text.charAt(text.length() - 1 - index);
    }

    @Override
    public String toString() {
        return new StringBuilder(text).reverse().toString();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        String reversedSub = new StringBuilder(text).reverse().toString();
        return reversedSub.subSequence(start, end);
    }
}
// END
