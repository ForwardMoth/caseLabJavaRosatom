record Coordinates(int x, int y) {
    public int getEuclideanDistance(Coordinates coordinates) {
        return dC2(x, coordinates.x()) + dC2(y, coordinates.y());
    }

    private int dC2(int c1, int c2) {
        return (c1 - c2) * (c1 - c2);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
