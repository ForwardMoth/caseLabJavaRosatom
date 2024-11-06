package operations;

public class Multiplication implements Operation {
    private final int a;
    private final int b;

    public Multiplication(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int calculate() {
        return a * b;
    }
}
