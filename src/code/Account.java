package code;

import java.util.ArrayList;

public class Account {

    int id;
    ArrayList<String> customerID;
    int cardID;
    double balance;

    public Account(int id, ArrayList<String> customerID, int cardID, double balance) {
        this.id = id;
        this.customerID = customerID;
        this.cardID = cardID;
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

}
