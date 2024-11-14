import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер массива");
        int n = scanner.nextInt();

        if (n <= 0) {
            throw new RuntimeException("Размер массива не может быть меньше 0");
        }

        System.out.println("Введите границы случайного числа");
        int leftBound = scanner.nextInt(), rightBound = scanner.nextInt();

        NumberArray[] arrays = new NumberArray[]{
                new DoubleArray(n, leftBound, rightBound),
                new IntArray(n, leftBound, rightBound)
        };

        for (NumberArray array : arrays) {
            array.printArray();
            array.getMin();
            array.getMax();
            array.getAverage();
            System.out.println("Сортировка в порядке возрастания");
            array.sortArray(1);
            System.out.println("Сортировка в порядке убывания");
            array.sortArray(-1);
            System.out.println();
        }
    }
}