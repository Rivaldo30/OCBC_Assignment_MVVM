package id.ac.uversrivaldo.ocbc_assignment.Model;

public class BalanceResponse {

    private String status, accountNo;
    private double balance;

    public void balanceResponse(String status, String accountNo, double balance) {
        this.status = status;
        this.accountNo = accountNo;
        this.balance = balance;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
