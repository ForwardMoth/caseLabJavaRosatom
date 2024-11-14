public class Main {
    public static void main(String[] args) {
        StringBuilderObserver stringBuilderObserver = new StringBuilderObserver();
        CustomObserver observer = new CustomObserver();

        stringBuilderObserver.addObserver(observer);

        stringBuilderObserver.append("hi!")
                .append(", Mike!")
                .replace(0, 2, "hello")
                .delete(5, 7)
                .reverse();

    }
}