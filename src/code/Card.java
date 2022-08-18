package code;

public class Card {
    int id;
    int accountID;
    int customerID;
    int pin;
    boolean isBlocked;

    public Card(int id, int accountID, int customerID, int pin, boolean isBlocked) {
        this.id = id;
        this.accountID = accountID;
        this.customerID = customerID;
        this.pin = pin;
        this.isBlocked = isBlocked;
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
