import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Logger logger = new Logger();
        bank.addObserver(logger);

        bank.addClient(new Client(1, 1000.0, "USD"));
        bank.addClient(new Client(2, 500.0, "EUR"));
        bank.addClient(new Client(3, 800.0, "JPY"));

        System.out.println("Initial exchange rates: " + bank.getExchangeRates());

        List<Cashier> cashiers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            final var cashier = new Cashier(i + 1, bank);
            cashier.start();
            cashiers.add(cashier);
        }

        System.out.println("Adding transactions...");

        bank.addTransaction(new Transaction(0, 1, 200.0, "DEPOSIT"));
        bank.addTransaction(new Transaction(1, 0, 150.0, "WITHDRAW"));
        bank.addTransaction(new Transaction(1, 2, 300.0, "TRANSFER"));
        bank.addTransaction(new Transaction(1, 0, 500.0, "EXCHANGE"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (Cashier cashier : cashiers) {
            cashier.stopCashier();
        }

        bank.stopUpdates();

        System.out.println("Final client balances:");
        for (int id = 1; id <= 3; id++) {
            Client client = bank.getClient(id);
            if (client != null) {
                System.out.printf("Client ID %d: %.2f %s%n", id, client.getBalance(), client.getCurrency());
            }
        }

        System.out.println("Final exchange rates: " + bank.getExchangeRates());
    }

}