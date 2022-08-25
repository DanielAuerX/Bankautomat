package code;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Database {

    public static ArrayList<ArrayList<String>> readCSV(String filepath){
        BufferedReader reader = null;
        String line;
        ArrayList<ArrayList<String>> customer = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filepath));
            while ((line = reader.readLine()) != null){
                String[] row = line.split("\n");
                for (String index : row){
                    String[] customerPartsString = index.split(";");
                    ArrayList<String> customerPartsArrayList = new ArrayList<>();
                    Collections.addAll(customerPartsArrayList, customerPartsString);
                    customer.add(customerPartsArrayList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    private String readJSON(String filepath) throws IOException{
        String content = new String(Files.readAllBytes(Paths.get(filepath)));
        return content;
    }

    public ArrayList<Card> getCards(){
        Gson gson = new Gson();
        ArrayList<Card> allCards = null;
        Database database = new Database();
        try {
            String content = database.readJSON("R:\\Java\\Bankautomat\\card_data.json");
            allCards = gson.fromJson(content, new TypeToken<ArrayList<Card>>() {}.getType());
            //allCustomer.stream().forEach(n -> System.out.println(n));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allCards;
    }

    public ArrayList<Account> getAccounts(){
        Gson gson = new Gson();
        ArrayList<Account> allAccounts = null;
        Database database = new Database();
        try {
            String content = database.readJSON("R:\\Java\\Bankautomat\\account_data.json");
            allAccounts = gson.fromJson(content, new TypeToken<ArrayList<Account>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allAccounts;
    }

    public ArrayList<Customer> getCustomers(){
        Gson gson = new Gson();
        ArrayList<Customer> allCustomers = null;
        Database database = new Database();
        try {
            String content = database.readJSON("R:\\Java\\Bankautomat\\customer_data.json");
            allCustomers = gson.fromJson(content, new TypeToken<ArrayList<Customer>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allCustomers;
    }

    public static void writeAccountData(Account account){
        String accountFilepath = "R:\\Java\\Bankautomat\\account_data.csv";
            ArrayList<ArrayList<String>> accountsArray = readCSV(accountFilepath);
            for (ArrayList<String> accountArray : accountsArray){
                if (account.getId() == Integer.parseInt(accountArray.get(0))){
                    accountArray.set(3, String.valueOf(account.getBalance()));
                    StringBuilder databaseText = new StringBuilder();
                    int counter1 = 1;
                    for (ArrayList<String> array : accountsArray) {
                        StringBuilder arrayText = new StringBuilder();
                        int counter2 = 0;
                        for (Object datapoint : array) {
                            if (counter2 < 3){
                                arrayText.append(datapoint).append(";");
                            }
                            else {
                                arrayText.append(datapoint);
                            }
                            counter2++;
                        }
                        if (counter1 == accountsArray.size()){
                            databaseText.append(arrayText);
                        }
                        else {
                            databaseText.append(arrayText).append("\n");
                        }
                        counter1++;
                }
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(accountFilepath));
                        writer.write(databaseText.toString());
                        writer.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

    }

    public static void writeCardData(Card card){
        String cardFilepath = "R:\\Java\\Bankautomat\\card_data.csv";
        ArrayList<ArrayList<String>> cardsArray = readCSV(cardFilepath);
        for (ArrayList<String> cardArray : cardsArray){
            if (card.getId() == Integer.parseInt(cardArray.get(0))){
                String blockedText;
                if (card.getIsBlocked()){
                    blockedText = "blocked";
                }
                else {
                    blockedText = "active";
                }
                cardArray.set(4, blockedText);
                StringBuilder databaseText = new StringBuilder();
                int counter1 = 1;
                for (ArrayList<String> array : cardsArray) {
                    StringBuilder arrayText = new StringBuilder();
                    int counter2 = 0;
                    for (Object datapoint : array) {
                        if (counter2 < 4){
                            arrayText.append(datapoint).append(";");
                        }
                        else {
                            arrayText.append(datapoint);
                        }
                        counter2++;
                    }
                    if (counter1 == cardsArray.size()){
                        databaseText.append(arrayText);
                    }
                    else {
                        databaseText.append(arrayText).append("\n");
                    }
                    counter1++;
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(cardFilepath));
                    writer.write(databaseText.toString());
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
