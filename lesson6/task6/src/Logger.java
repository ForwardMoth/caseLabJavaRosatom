public class Logger implements Observer {

    @Override
    public void update(String message) {
        System.out.printf("Log: %s\n", message);
    }

}
