public class Car implements Runnable {
    private String model;

    private CarType carType;

    Car(String model, CarType carType) {
        this.model = model;
        this.carType = carType;
    }

    public class Wheel {
        private int diameter;
        private boolean isWinter = false;

        Wheel(int diameter) {
            this.diameter = diameter;
        }

        void changeSeasonWheel() {
            isWinter = !isWinter;
        }

        void setDiameter(int diameter) {
            this.diameter = diameter;
        }

        @Override
        public String toString() {
            return "Car wheel{" +
                    "diameter=" + diameter +
                    ", isWinter=" + isWinter +
                    '}';
        }
    }

    @Override
    public void run() {
        System.out.println("Управление автомобилем");
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                '}';
    }
}
