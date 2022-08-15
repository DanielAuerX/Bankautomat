package code;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Database {

    public static String filepath = "R:\\Java\\Bankautomat\\customer_database.csv";

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

    public static Customer instantiateCustomer() {
        ArrayList<ArrayList<String>> database = readCSV(filepath);
        int position = 0;
        for (int i = 0; i < database.size(); i++) {
            if (Main.validCustomerNum.equals(String.valueOf(database.get(i).get(3)))) {
                position = i;
                break;
            }
        }
        String firstName = String.valueOf(database.get(position).get(0));
        String secondName = String.valueOf(database.get(position).get(1));
        int gender = Integer.parseInt(String.valueOf(database.get(position).get(2)));
        int customerNum = Integer.parseInt(Main.validCustomerNum);
        return new Customer(firstName, secondName, gender, customerNum);
    }

    public static Account instantiateAccount() {
        ArrayList<ArrayList<String>> database = readCSV(filepath);
        int position = 0;
        for (int i = 0; i < database.size(); i++) {
            if (Main.validCustomerNum.equals(String.valueOf(database.get(i).get(3)))) {
                position = i;
                break;
            }
        }
        int accountNum = Integer.parseInt(database.get(position).get(4));
        int pinNum = Integer.parseInt(database.get(position).get(5));
        double balance = Double.parseDouble(database.get(position).get(6));
        return new Account(accountNum,pinNum,balance);
    }

    public static void writeNewBalance(Account account){
        ArrayList<ArrayList<String>> database = readCSV(filepath);
        for (int i = 0; i < database.size(); i++){
            if (account.accountNum == Integer.parseInt(database.get(i).get(4))){
                if (account.balance == Double.parseDouble(database.get(i).get(6))){
                    break;
                }
                else {
                    ArrayList<String> customer = database.get(i);
                    customer.set(6, String.valueOf(account.balance));
                    StringBuilder databaseText = new StringBuilder();
                    int counter1 = 1;
                    for (ArrayList<String> array : database) {
                        StringBuilder arrayText = new StringBuilder();
                        int counter2 = 0;
                        for (Object datapoint : array) {
                            if (counter2 < 6){
                                arrayText.append(datapoint).append(";");
                            }
                            else {
                                arrayText.append(datapoint);
                            }
                            counter2++;
                        }
                        if (counter1 == database.size()){
                            databaseText.append(arrayText);
                        }
                        else {
                            databaseText.append(arrayText).append("\n");
                        }
                        counter1++;
                    }
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
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
