import java.util.Arrays;

public class DoubleArray extends NumberArray {

    private double[] array;

    DoubleArray(int size, int left, int right) {
        super(size);
        fillArray(left, right);
    }

    private void fillArray(int left, int right) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = left + (Math.random() * (right - left + 1));
        }
    }

    @Override
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public void getMin() {
        double result = array[0];
        for (double val : array) {
            result = Math.min(result, val);
        }
        System.out.println("Минимальное значение: " + result);
    }

    @Override
    public void getMax() {
        double result = array[0];
        for (double val : array) {
            result = Math.max(result, val);
        }
        System.out.println("Максимальное значение: " + result);
    }

    @Override
    public void getAverage() {
        double sum = 0;
        for (double val : array) {
            sum += val;
        }
        System.out.println("Среднее значение: " + sum / array.length);
    }

    @Override
    public void sortArray(int order) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (Double.compare(array[i], array[j]) * order > 0) {
                    double temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        printArray();
    }
}
