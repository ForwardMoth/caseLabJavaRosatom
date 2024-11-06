public class Main {
    public static void main(String[] args) {
        Vector[] vectors = Vector.getRandomVectors(2);

        Vector vector1 = vectors[0], vector2 = vectors[1];

        System.out.println(vector1);
        System.out.println(vector2);

        System.out.println("Длина вектора 1: " + vector1.getLength());
        System.out.println("Длина вектора 2: " + vector2.getLength());

        System.out.println("Скалярное произведение: " + vector1.getScalarMultiplication(vector2));
        System.out.println("Векторное произведение: " + vector1.getVectorMultiplication(vector2));
        System.out.println("Косинус угла между векторами: " + vector1.getCornerCos(vector2));
        System.out.println("Сумма векторов: " + vector1.sumVectors(vector2));
        System.out.println("Разность векторов: " + vector1.subtractionVectors(vector2));
    }
}