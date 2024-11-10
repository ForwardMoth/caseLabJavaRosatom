public class Ship implements Runnable {

    private String name;


    Ship(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void run() {
        System.out.println("Управление кораблём");
    }
}
