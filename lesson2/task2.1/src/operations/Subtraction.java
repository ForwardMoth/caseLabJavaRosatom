package operations;

public class Subtraction implements Operation {
    private final int a;
    private final int b;

    public Subtraction(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int calculate() {
        return a - b;
    }
}
