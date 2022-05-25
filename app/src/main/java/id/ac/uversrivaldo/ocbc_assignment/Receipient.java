package id.ac.uversrivaldo.ocbc_assignment;

public class Receipient {

    public int accountNo;
    public String accountHolder;

    public Receipient(int accountNo, String accountHolder) {
        this.accountNo = accountNo;
        this.accountHolder = accountHolder;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}
