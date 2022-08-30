package code;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
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
        boolean foundCardID;
        try {
            int inputInt = Integer.parseInt(input);
            ArrayList <Card> cardData = getCards();
            foundCardID = cardData.stream().anyMatch(card -> card.getId() == inputInt);
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

    public ArrayList<Card> getCards(){
        Gson gson = new Gson();
        Database database = new Database();
        String content = database.readJSON("R:\\Java\\Bankautomat\\card_data.json");
        return gson.fromJson(content, new TypeToken<ArrayList<Card>>() {}.getType());
    }

    public ArrayList<Account> getAccounts(){
        Gson gson = new Gson();
        Database database = new Database();
        String content = database.readJSON("R:\\Java\\Bankautomat\\account_data.json");
        return gson.fromJson(content, new TypeToken<ArrayList<Account>>() {}.getType());
    }

    public ArrayList<Customer> getCustomers(){
        Gson gson = new Gson();
        Database database = new Database();
        String content = database.readJSON("R:\\Java\\Bankautomat\\customer_data.json");
        return gson.fromJson(content, new TypeToken<ArrayList<Customer>>() {}.getType());
    }

    public Customer getCustomer(int validCustomerID){
        ArrayList<Customer> allCustomers = getCustomers();
        List<Customer> customers = allCustomers.stream().
                filter(customer -> customer.getId() == validCustomerID).
                toList();
        //stream for each lambda
        // hashmap
        return customers.get(0);
    }

    public Account getAccount(int accountID){
        ArrayList<Account> allAccounts = getAccounts();
        List<Account> accounts = allAccounts.stream().
                filter(account -> account.getId() == accountID).
                toList();
        return accounts.get(0);
    }

    public Card getCard(int cardID) {
        ArrayList<Card> allCards = getCards();
        List<Card> cards = allCards.stream().
                filter(account -> account.getId() == cardID).
                toList();
        return cards.get(0);
    }

}

