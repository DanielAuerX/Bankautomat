package code;

import java.util.ArrayList;
import java.util.Scanner;

public class EosBankingApplication {

    public void stopProgram(boolean canContinue){
        if (!canContinue){
            System.exit(0);
        }
    }

    public boolean checkCardID(){
        boolean isValidID = false;
        Scanner scanner = new Scanner(System.in);

        for (int i = 3; i > -1; i--){
            System.out.print("Bitte Kartennummer eingeben: ");
            String inputCardID = scanner.nextLine();

            boolean isPresentInDatabase = findCardID(inputCardID);
            if (isPresentInDatabase){
                isValidID = true;
                Atm.validatedCardID = Integer.parseInt(inputCardID);
                break;
            }
            else {
                if (i == 1){
                    System.out.println("Diese Kartennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuch.");
                }
                else if (i > 1){
                    System.out.println("Diese Kartennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuche.");
                }
                else {
                    System.out.println("Diese Kartennummer ist nicht bekannt. \nFalls Sie Ihre Bankkarte verloren haben, wenden Sie sich an das Bankpersonal.");
                }
            }
        }
        return isValidID;
    }

    private boolean findCardID(String input){
        boolean foundCardID = false;
        try {
            int inputInt = Integer.parseInt(input);
            ArrayList <ArrayList<String>> cardData = Database.readCSV("R:\\Java\\Bankautomat\\card_data.csv");
            for (ArrayList<String> cardDatapoint : cardData) {
                if (inputInt == Integer.parseInt(cardDatapoint.get(0))) {
                    foundCardID = true;
                    break;
                }
            }
        }
        catch (NumberFormatException nfe){
            System.out.println("Bitte ausschließlich Zahlen eingeben.");
            throw nfe;
        }
        return foundCardID;

    }

    public boolean validatePin(Card card){
        boolean isValidPin = false;
        for (int i = 2; i > -1; i--){
            int inputPinInt = Atm.askForPin();
            if (card.checkPin(inputPinInt)){
                isValidPin = true;
                break;
            }
            else {
                if (i > 1){
                    System.out.println("Diese Pin ist inkorrekt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuche.");
                }
                else if (i == 1){
                    System.out.println("Diese Pin ist inkorrekt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuch.");
                }
                else {
                    System.out.println("Diese Pin ist inkorrekt. Ihr Konto ist vorübergehend gesperrt! \nBitte wenden Sie sich an das Bankpersonal.");
                }
            }
        }
        return isValidPin;
    }

    public Customer getCustomer(int validCustomerID){
        Database database = new Database();
        ArrayList<Customer> allCustomers = database.getCustomers();
        Customer customer;
        int position = -1;
        for (int i = 0; i < allCustomers.size(); i++) {
            if (validCustomerID == allCustomers.get(i).getId()) {
                position = i;
                break;
            }
        }
        customer = allCustomers.get(position);
        return customer;

    }

    public Account getAccount(int accountID){
        Database database = new Database();
        ArrayList<Account> allAccounts = database.getAccounts();
        Account account;
        int position = -1;
        for (int i = 0; i < allAccounts.size(); i++) {
            if (allAccounts.get(i).getId() == accountID) {
                position = i;
                break;
            }
        }
        account = allAccounts.get(position);
        return account;

    }

    public Card getCard(int cardID) {
        Database database = new Database();
        ArrayList<Card> allCards = database.getCards();
        Card card;
        int position = -1;
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).getId() == cardID) {
                position = i;
                break;
            }
        }
        card = allCards.get(position);
        return card;
    }



}

