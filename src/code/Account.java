package code;

public class Account {

    int id;
    int pin;
    double balance;

    public Account(int id, int pin, double balance) {
        this.id = id;
        this.pin = pin;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public int getPin() {
        return pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        balance -= amount;
    }

}
