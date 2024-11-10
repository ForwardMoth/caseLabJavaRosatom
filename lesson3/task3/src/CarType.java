public enum CarType {
    OFF_ROAD("Внедорожник"),
    TRUCK("Грузовик"),
    PASSENGER("Легковой автомобиль");

    private String name;

    CarType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
