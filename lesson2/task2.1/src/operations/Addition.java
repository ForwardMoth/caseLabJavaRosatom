package operations;

public class Addition implements Operation {
    private final int a;
    private final int b;

    public Addition(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int calculate() {
        return a + b;
    }
}
