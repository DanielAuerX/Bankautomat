package code;

public class Card {
    private int id;
    private int accountID;
    private int customerID;
    private int pin;
    private boolean isBlocked;
    private int pinTries;

    public Card(int id, int accountID, int customerID, int pin, boolean isBlocked, int pinTries) {
        this.id = id;
        this.accountID = accountID;
        this.customerID = customerID;
        this.pin = pin;
        this.isBlocked = isBlocked;
        this.pinTries = pinTries;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getId() {
        return id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getPinTries() {
        return pinTries;
    }

    public boolean checkPin(int input){
        boolean isCorrectPin;
        if (input == pin){
            isCorrectPin = true;
            pinTries = 0;
        }
        else {
            isCorrectPin =false;
            pinTries ++;
        }
        return isCorrectPin;
    }

    public void blockCard(boolean tooManyTries){
        if (tooManyTries){
            isBlocked = true;
        }
    }

}
