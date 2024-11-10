public class Plane implements Runnable {
    @Override
    public void run() {
        System.out.println("Управление самолётом");
    }

    @Override
    public String toString() {
        return "Plane";
    }
}
