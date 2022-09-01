package code;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    public static int validatedCardID;

    private ArrayList<Card> getCards(){
        Gson gson = new Gson();
        JsonIO jsonIO = new JsonIO();
        String content = jsonIO.readJson("R:\\Java\\Bankautomat\\card_data.json");
        return gson.fromJson(content, new TypeToken<ArrayList<Card>>() {}.getType());
    }

    private ArrayList<Account> getAccounts(){
        Gson gson = new Gson();
        JsonIO jsonIO = new JsonIO();
        String content = jsonIO.readJson("R:\\Java\\Bankautomat\\account_data.json");
        return gson.fromJson(content, new TypeToken<ArrayList<Account>>() {}.getType());
    }

    private ArrayList<Customer> getCustomers(){
        Gson gson = new Gson();
        JsonIO jsonIO = new JsonIO();
        String content = jsonIO.readJson("R:\\Java\\Bankautomat\\customer_data.json");
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
                filter(card -> card.getId() == cardID).
                toList();
        return cards.get(0);
    }

    public boolean findCardByID(String input){
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

}
