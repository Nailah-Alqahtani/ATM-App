package FirstTask.ATM;

public class ATMAccount {
    private int id;
    private String fullName;
    private String accountNumber;
    private double balance;
    private static int nextId = 1; // the id will be automatically generated

    public ATMAccount() {

    }

    public ATMAccount(String fullName, String accountNumber, double balance) {
        this.id = nextId++;
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
