package code;

public class Card {
    private int id;
    private int accountID;
    private int customerID;
    private int pin;
    private boolean isBlocked;

    public Card(int id, int accountID, int customerID, int pin, boolean isBlocked) {
        this.id = id;
        this.accountID = accountID;
        this.customerID = customerID;
        this.pin = pin;
        this.isBlocked = isBlocked;
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

    public boolean checkPin(int input){
        boolean isCorrectPin;
        if (input == pin){
            isCorrectPin = true;
        }
        else {
            isCorrectPin =false;
        }
        return isCorrectPin;
    }

    public void blockCard(boolean tooManyTries){
        if (tooManyTries){
            isBlocked = true;
        }
    }

}
