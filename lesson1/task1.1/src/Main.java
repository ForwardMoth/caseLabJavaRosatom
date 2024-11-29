import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер массива");

        int n;
        try {
            n = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Необходимо вводить число");
            return;
        }

        if (n <= 0) {
            throw new RuntimeException("Размер массива не может быть меньше 0");
        }

        System.out.println("Введите границы случайного числа");
        int leftBound, rightBound;
        try {
            leftBound = scanner.nextInt();
            rightBound = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Необходимо вводить число");
            return;
        }

        ArrayMethods[] arrays = new ArrayMethods[]{
                new DoubleArray(n, leftBound, rightBound),
                new IntArray(n, leftBound, rightBound)
        };

        for (ArrayMethods array : arrays) {
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