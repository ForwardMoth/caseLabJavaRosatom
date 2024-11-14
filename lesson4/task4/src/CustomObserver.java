public class CustomObserver implements Observer {

    @Override
    public void update(String str) {
        System.out.println("String changed to: " + str);
    }

}
