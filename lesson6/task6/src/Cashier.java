public class Cashier extends Thread {

    private final int id;
    private final Bank bank;
    private volatile boolean active;

    public Cashier(int id, Bank bank) {
        this.id = id;
        this.bank = bank;
        this.active = true;
    }

    @Override
    public void run() {
        try {
            while (active) {
                final var transaction = bank.getTransactions().poll();
                if (transaction == null) {
                    bank.notifyObservers("transaction is empty");
                    Thread.sleep(500);
                    continue;
                }

                processTransaction(transaction);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stopCashier() {
        bank.notifyObservers(String.format("Cashier %s is interrupted", id));
        active = false;
    }

    private void processTransaction(Transaction transaction) {
        switch (transaction.type().toUpperCase()) {
            case "DEPOSIT":
                handleDeposit(transaction);
                break;
            case "WITHDRAW":
                handleWithdraw(transaction);
                break;
            case "EXCHANGE":
                handleExchange(transaction);
                break;
            case "TRANSFER":
                handleTransfer(transaction);
                break;
            default:
                bank.notifyObservers("Unknown transaction type: " + transaction.type());
        }
    }

    private void handleDeposit(Transaction transaction) {
        if (transaction.receiverId() == 0) {
            bank.notifyObservers("Deposit failed: Invalid client ID in transaction " + transaction);
            return;
        }
        deposit(transaction.receiverId(), transaction.amount());
    }

    private void handleWithdraw(Transaction transaction) {
        if (transaction.senderId() == 0) {
            bank.notifyObservers("Withdrawal failed: Invalid client ID in transaction " + transaction);
            return;
        }
        withdraw(transaction.senderId(), transaction.amount());
    }

    private void handleExchange(Transaction transaction) {
        if (transaction.senderId() == 0 || transaction.amount() <= 0) {
            bank.notifyObservers("Currency exchange failed: Invalid transaction details " + transaction);
            return;
        }
        exchangeCurrency(transaction.senderId(), "USD", "EUR", transaction.amount());
    }

    private void handleTransfer(Transaction transaction) {
        if (transaction.senderId() == 0 || transaction.receiverId() == 0 || transaction.amount() <= 0) {
            bank.notifyObservers("Transfer failed: Invalid transaction details " + transaction);
            return;
        }
        transferFunds(transaction.senderId(), transaction.receiverId(), transaction.amount());
    }

    private void deposit(int clientId, double amount) {
        final var client = bank.getClient(clientId);
        if (client == null) {
            bank.notifyObservers("Withdrawal failed: Client not found (ID " + clientId + ")");
            return;
        }
        synchronized (client) {
            double newBalance = client.getBalance() + amount;
            client.setBalance(newBalance);
            bank.notifyObservers("Deposit successful: Client ID " + clientId + ", Amount " + amount + ", New Balance " + newBalance);
        }
    }

    private void withdraw(int clientId, double amount) {
        final var client = bank.getClient(clientId);
        if (client == null) {
            bank.notifyObservers("Withdrawal failed: Client not found (ID " + clientId + ")");
            return;
        }
        synchronized (client) {
            if (client.getBalance() >= amount) {
                final var newBalance = client.getBalance() - amount;
                client.setBalance(newBalance);
                bank.notifyObservers("Withdrawal successful: Client ID " + clientId + ", Amount " + amount + ", New Balance " + newBalance);
            } else {
                bank.notifyObservers("Withdrawal failed: Insufficient funds (Client ID " + clientId + ")");
            }
        }
    }

    void exchangeCurrency(int clientId, String fromCurrency, String toCurrency,
                          double amount) {
        final var client = bank.getClient(clientId);
        if (client == null) {
            bank.notifyObservers("Currency exchange failed: Client not found (ID " + clientId + ")");
            return;
        }
        synchronized (client) {
            if (!fromCurrency.equals(client.getCurrency())) {
                bank.notifyObservers("Currency exchange failed: Client currency mismatch (Client ID " + clientId + ")");
                return;
            }

            if (client.getBalance() >= amount) {
                final var rate = bank.getExchangeRate(fromCurrency, toCurrency);
                final var exchangedAmount = amount * rate;

                client.setBalance(client.getBalance() - amount);
                client.setBalance(client.getBalance() + exchangedAmount);

                bank.notifyObservers("Currency exchange successful: Client ID " + clientId +
                        ", Exchanged " + amount + " " + fromCurrency + " to " +
                        exchangedAmount + " " + toCurrency + " at rate " + rate);
            } else {
                bank.notifyObservers("Currency exchange failed: Insufficient funds (Client ID " + clientId + ")");
            }
        }
    }

    void transferFunds(int senderId, int receiverId, double amount) {
        final var sender = bank.getClient(senderId);
        final var receiver = bank.getClient(receiverId);

        if (sender == null || receiver == null) {
            bank.notifyObservers("Transfer failed: Invalid sender or receiver (Sender ID " + senderId + ", Receiver ID " + receiverId + ")");
            return;
        }

        synchronized (sender) {
            synchronized (receiver) {
                if (sender.getBalance() >= amount) {
                    sender.setBalance(sender.getBalance() - amount);
                    receiver.setBalance(receiver.getBalance() + amount);
                    bank.notifyObservers("Transfer successful: Sender ID " + senderId +
                            ", Receiver ID " + receiverId + ", Amount " + amount);
                } else {
                    bank.notifyObservers("Transfer failed: Insufficient funds (Sender ID " + senderId + ")");
                }
            }
        }
    }

}
