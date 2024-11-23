import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Bank {

    private final ConcurrentHashMap<Integer, Client> clients;
    private final ConcurrentHashMap<String, Double> exchangeRates;
    private final Queue<Transaction> transactions;

    private final List<Observer> observers;

    private final ScheduledThreadPoolExecutor executor;

    public Bank() {
        clients = new ConcurrentHashMap<>();
        exchangeRates = new ConcurrentHashMap<>();
        transactions = new LinkedBlockingQueue<>();

        observers = new ArrayList<>();

        exchangeRates.put("USD", 1.0); // base currency
        exchangeRates.put("EUR", 0.9);
        exchangeRates.put("GBP", 0.8);
        exchangeRates.put("JPY", 110.0);

        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(this::updateExchangeRates, 0, 1, TimeUnit.SECONDS);
    }

    public void stopUpdates() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Принудительное завершение
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public Queue<Transaction> getTransactions() {
        return transactions;
    }

    public void addClient(Client client) {
        clients.put(client.getId(), client);
        notifyObservers("Client added: " + client.getId());
    }

    public Client getClient(int clientId) {
        return clients.get(clientId);
    }

    private void updateExchangeRates() {
        Random random = new Random();

        exchangeRates.forEach((currency, currentRate) -> {
            double fluctuation = 1 + (random.nextDouble() - 0.5) / 10;
            double newRate = currentRate * fluctuation;
            exchangeRates.put(currency, Math.round(newRate * 100.0) / 100.0);
        });

        notifyObservers("Exchange rates updated: " + exchangeRates);
    }

    public Map<String, Double> getExchangeRates() {
        return Collections.unmodifiableMap(exchangeRates);
    }

    public double getExchangeRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return 1.0;
        }

        final var fromRate = exchangeRates.get(fromCurrency);
        final var toRate = exchangeRates.get(toCurrency);

        if (fromRate == null || toRate == null) {
            throw new IllegalArgumentException("Unknown currency: " + fromCurrency + " or " + toCurrency);
        }

        return fromRate / toRate;
    }

    public void addTransaction(Transaction transaction) {
        transactions.offer(transaction);
        notifyObservers("Transaction added: " + transaction);
    }

    void addObserver(Observer observer) {
        observers.add(observer);
    }

    void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

}
