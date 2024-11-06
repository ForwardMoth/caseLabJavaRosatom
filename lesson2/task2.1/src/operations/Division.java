package operations;

public class Division implements Operation {
    private final int a;
    private final int b;

    public Division(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int calculate() throws Exception {
        if (b == 0) {
            throw new Exception("Ошибка - деление на 0");
        }
        return a / b;
    }
}
