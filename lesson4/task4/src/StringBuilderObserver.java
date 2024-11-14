import java.util.ArrayList;
import java.util.List;

public class StringBuilderObserver {

    private final StringBuilder sb;
    private final List<Observer> observers;

    StringBuilderObserver() {
        this.sb = new StringBuilder();
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public StringBuilderObserver append(String str) {
        sb.append(str);
        notifyObservers();
        return this;
    }

    public StringBuilderObserver insert(int offset, String str) {
        sb.insert(offset, str);
        notifyObservers();
        return this;
    }


    public StringBuilderObserver replace(int start, int end, String str) {
        sb.replace(start, end, str);
        notifyObservers();
        return this;
    }


    public StringBuilderObserver delete(int start, int end) {
        sb.delete(start, end);
        notifyObservers();
        return this;
    }

    public StringBuilderObserver reverse() {
        sb.reverse();
        notifyObservers();
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(sb.toString());
        }
    }

}
