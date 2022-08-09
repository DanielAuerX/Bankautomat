import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Database {

    public static ArrayList readCSV(String filepath){
        BufferedReader reader = null;
        String line = "";
        ArrayList<ArrayList<String>> customer = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filepath));
            while ((line = reader.readLine()) != null){
                String[] row = line.split("\n");
                for (String index : row){
                    String[] customerPartsString = index.split(";");
                    ArrayList<String> customerPartsArrayList = new ArrayList<>();
                    for (String part : customerPartsString){
                        customerPartsArrayList.add(part);
                    }
                    customer.add(customerPartsArrayList);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    public static Customer instantiateCustomer() {
        ArrayList<ArrayList> database = readCSV(Main.filepath);
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
        Customer customer = new Customer(firstName, secondName, gender, customerNum);
        return customer;
    }

    public static Account instantiateAccount() {
        ArrayList<ArrayList> database = readCSV(Main.filepath);
        int position = 0;
        for (int i = 0; i < database.size(); i++) {
            if (Main.validCustomerNum.equals(String.valueOf(database.get(i).get(3)))) {
                position = i;
                break;
            }
        }
        int accountNum = Integer.parseInt((String) database.get(position).get(4));
        int pinNum = Integer.parseInt((String)database.get(position).get(5));
        double balance = Double.parseDouble((String)database.get(position).get(6));
        Account account = new Account(accountNum,pinNum,balance);
        return account;
    }

}
