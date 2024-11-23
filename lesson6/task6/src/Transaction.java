public record Transaction(int senderId, int receiverId, double amount, String type) {

    @Override
    public String toString() {
        return "Transaction{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
