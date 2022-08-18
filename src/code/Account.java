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

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        balance -= amount;
    }

    public int testMethod(int amount){
        if (amount < 0){
            throw new IllegalArgumentException("Smaller than 0!");
        }
        amount = amount + amount;
        return amount;
    }

}
