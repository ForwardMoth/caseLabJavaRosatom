public abstract class NumberArray {

    protected final int size;

    NumberArray(int size) {
        this.size = size;
    }

    abstract void getMin();

    abstract void getMax();

    abstract void getAverage();

    abstract void printArray();

    abstract void sortArray(int order);

}
