package code;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

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

    public static ArrayList<String> getCustomerData(String validCustomerID){
        ArrayList<ArrayList<String>> database = readCSV("R:\\Java\\Bankautomat\\customer_data.csv");
        ArrayList<String> customerData;
        int position = 0;
        for (int i = 0; i < database.size(); i++) {
            if (validCustomerID.equals(String.valueOf(database.get(i).get(2)))) {
                position = i;
                break;
            }
        }
        customerData = database.get(position);
        return customerData;

    }

    public static ArrayList<String> getAccountData(String validCustomerID){
        ArrayList<ArrayList<String>> database = readCSV("R:\\Java\\Bankautomat\\account_data.csv");
        ArrayList<String> accountData;
        int position = 0;
        for (int i = 0; i < database.size(); i++) {
            if (String.valueOf(database.get(i).get(1)).contains(validCustomerID)) {
                position = i;
                break;
            }
        }
        accountData = database.get(position);
        return accountData;

    }

    public static void writeNewBalance(Account account){
        String accountFilepath = "R:\\Java\\Bankautomat\\account_data.csv";

        ArrayList<String> accountData = getAccountData(EosBankingApplication.validCustomerID);
        if (Double.parseDouble(accountData.get(3)) != account.getBalance()){
            ArrayList<ArrayList<String>> accountsArray = readCSV(accountFilepath);
            for (ArrayList<String> accountArray : accountsArray){
                if (account.getId() == Integer.parseInt(accountArray.get(0))){
                    accountArray.set(3, String.valueOf(account.balance));
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
    }

}
