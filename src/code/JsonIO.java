package code;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonIO {

    public String readJson(String filepath) {
        String jsonString = null;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public void writeAccountData(Account account){
        String accountFilepath = "R:\\Java\\Bankautomat\\account_data.json";
        Gson gson = new Gson();
        String content = readJson(accountFilepath);
        ArrayList<Account> allAccounts = gson.fromJson(content, new TypeToken<ArrayList<Account>>() {}.getType());
        List<Account> oldCard = allAccounts.stream().
                filter(account1 -> account.getId() == account1.getId()).
                toList();
        allAccounts.remove(oldCard.get(0));
        allAccounts.add(account);
        String jsonText = gson.toJson(allAccounts, new TypeToken<ArrayList<Card>>() {}.getType());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(accountFilepath));
            writer.write(jsonText);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCardData(Card card){
        String cardFilepath = "R:\\Java\\Bankautomat\\card_data.json";
        Gson gson = new Gson();
        String content = readJson(cardFilepath);
        ArrayList<Card> allCards = gson.fromJson(content, new TypeToken<ArrayList<Card>>() {}.getType());
        List<Card> oldCard = allCards.stream().
                filter(card1 -> card.getId() == card1.getId()).
                toList();
        allCards.remove(oldCard.get(0));
        allCards.add(card);
        String jsonText = gson.toJson(allCards, new TypeToken<ArrayList<Card>>() {}.getType());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(cardFilepath));
            writer.write(jsonText);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
