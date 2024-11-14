import java.util.Arrays;

public class IntArray extends NumberArray {

    private int[] array;

    IntArray(int size, int left, int right) {
        super(size);
        fillArray(left, right);
    }

    @Override
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public void getMin() {
        int result = array[0];
        for (int val : array) {
            result = Math.min(result, val);
        }
        System.out.println("Минимальное значение: " + result);
    }

    @Override
    public void getMax() {
        int result = array[0];
        for (int val : array) {
            result = Math.max(result, val);
        }
        System.out.println("Максимальное значение: " + result);
    }

    @Override
    public void getAverage() {
        double sum = 0;
        for (int val : array) {
            sum += val;
        }
        System.out.println("Среднее значение: " + sum / array.length);
    }

    @Override
    public void sortArray(int order) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (Integer.compare(array[i], array[j]) * order > 0) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        printArray();
    }

    private void fillArray(int left, int right) {
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = left + (int) (Math.random() * (right - left + 1));
        }
    }
}
