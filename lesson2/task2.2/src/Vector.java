

public class Vector {
    private int x;
    private int y;
    private int z;

    Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Vector(" + x + ", " + y + ", " + z + ")";
    }

    public static Vector[] getRandomVectors(int N) {
        Vector[] vectors = new Vector[N];
        for (int i = 0; i < N; i++) {
            int x = getRandom(), y = getRandom(), z = getRandom();
            vectors[i] = new Vector(x, y, z);
        }
        return vectors;
    }

    public Vector subtractionVectors(Vector vector) {
        return new Vector(x - vector.getX(), y - vector.getY(), z - vector.getZ());
    }

    public Vector sumVectors(Vector vector) {
        return new Vector(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }

    public Vector getVectorMultiplication(Vector vector) {
        int newX = y * vector.getZ() - z * vector.getY();
        int newY = x * vector.getZ() - z * vector.getX();
        int newZ = x * vector.getY() - y * vector.getX();
        return new Vector(newX, newY, newZ);
    }

    public double getCornerCos(Vector vector) {
        return getScalarMultiplication(vector) / (getLength() * vector.getLength());
    }

    public double getScalarMultiplication(Vector vector) {
        return x * vector.getX() + y * vector.getY() + z * vector.getZ();
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    private static int getRandom() {
        return (int) (Math.random() * 10);
    }
}
