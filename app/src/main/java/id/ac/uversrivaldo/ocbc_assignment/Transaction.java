package id.ac.uversrivaldo.ocbc_assignment;

public class Transaction {
    private String transactionId;
    private Double amount;
    private String description;
    private String transactionType;
    private Receipient receipient;


    public Transaction(String transactionId, Double amount, String description, String transactionType, Receipient receipient) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.description = description;
        this.transactionType = transactionType;
        this.receipient = receipient;
    }

    public Transaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Receipient getReceipient() {
        return receipient;
    }

    public void setReceipient(Receipient receipient) {
        this.receipient = receipient;
    }
}
