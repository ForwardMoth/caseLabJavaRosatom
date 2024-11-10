public class Main {
    public static void main(String[] args) {
        System.out.println("Программа для управление транспортом");

        // Create car
        Car car = new Car("BMW", CarType.PASSENGER);

        // inner class example
        var wheel = car.new Wheel(10);
        System.out.println(wheel);
        wheel.changeSeasonWheel();
        wheel.setDiameter(15);
        System.out.println(wheel);


        Runnable[] vehicles = new Runnable[]{
            car, new Plane(), new Bike("BMX 3000"), new Ship("Titanic")
        };

        for(Runnable vehicle : vehicles) {
            vehicle.run();
            System.out.println(vehicle);
        }
    }
}