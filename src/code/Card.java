package code;

public class Card {
    public static final int maxPinTries = 3;
    private int id;
    private int accountID;
    private String customerID;
    private int pin;
    private boolean blocked;
    private int pinTries;

    public Card(int id, int accountID, String customerID, int pin, boolean blocked, int pinTries) {
        this.id = id;
        this.accountID = accountID;
        this.customerID = customerID;
        this.pin = pin;
        this.blocked = blocked;
        this.pinTries = pinTries;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public int getRemainingTries(){
        return maxPinTries-pinTries;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getId() {
        return id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public int getPinTries() {
        return pinTries;
    }

    public boolean checkPin(int input){
        if (input == pin){
            pinTries = 0;
            return true;
        }
        else {
            pinTries ++;
            if (pinTries >= maxPinTries){
                blockCard(true);
            }
            return false;
        }
    }

    private void blockCard(boolean tooManyTries){
        if (tooManyTries){
            blocked = true;
        }
    }

}
