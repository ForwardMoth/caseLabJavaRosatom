public class Bike implements Runnable {

    private String name;

    Bike(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void run() {
        System.out.println("Управление велосипедом");
    }
}
