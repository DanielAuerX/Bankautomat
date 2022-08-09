//TO DO
//+ datenbank? in datenbank speichern
//+ JUnit tests
//+ refactor Database classe, validatePin etc in Account
//+ add package


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String filepath = "R:\\Java\\Bankautomat\\customer_database.csv";
    static String validCustomerNum = "";

    public static void main(String[] args) {

        boolean quit = false;

        System.out.println("B A N K O M A T EOS-Bank Standort: Hamburg");
        Scanner scanner = new Scanner(System.in);

        boolean isValidCustomer = validateCustomerNum(scanner);
        stopProgram(isValidCustomer);

        Customer customer = instantiateCustomer();
        Account account = instantiateAccount();

        String name = customer.greeting();
        System.out.println("Guten Tag "+name+"!");

        boolean isValidPin = validatePin(scanner,account.pinNum);
        stopProgram(isValidPin);

        String menuText = "Sie haben Zugriff auf Ihr Konto mit der Nummer "+customer.customerNum+
                "\nWählen Sie\n" +
                "1 – Kontostand abfragen\n" +
                "2 – Einzahlen\n" +
                "3 – Abheben\n" +
                "4 – System verlassen\n\n" +
                "Eingabe: ";

        while (!quit){
            System.out.print(menuText);
            String input = scanner.nextLine();
            switch (input){
                case "1":
                    System.out.println(account.getBalance());
                    break;
                case "2":
                    account.deposit(scanner);
                    System.out.println(account.getBalance());
                    break;
                case "3":
                    account.withdraw(scanner);
                    System.out.println(account.getBalance());
                    break;
                case "4":
                    System.out.println("Auf Wiedersehen!");
                    quit = true;
                    break;
                default:
                    System.out.println("Inkorrekte Eingabe! Versuchen Sie es erneut.\n");
            }
        }

    }

    private static boolean findCustomer(String input){
        boolean isCustomer = false;
        try {
            int inputInt = Integer.parseInt(input);
            ArrayList <ArrayList> customerData = readCSV(filepath);
            for (int i = 0; i < customerData.size(); i++) {
                if (inputInt == Integer.parseInt((String) customerData.get(i).get(3))) {
                    isCustomer = true;
                    break;
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("Bitte ausschließlich Zahlen eingeben.");
        }
        return isCustomer;

    }

    private static boolean validateCustomerNum(Scanner scanner){
        boolean isValidCustomer = false;

        for (int i = 3; i > -1; i--){
            System.out.print("Bitte Kundennummer eingeben: ");
            String inputCustomerNum = scanner.nextLine();

            boolean correctCustomerNum = findCustomer(inputCustomerNum);
            if (correctCustomerNum){
                isValidCustomer = true;
                validCustomerNum = inputCustomerNum;
                break;
            }
            else if(!correctCustomerNum) {
                if (i == 1){
                    System.out.println("Diese Kundennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuch.");
                }
                else if (i > 1){
                    System.out.println("Diese Kundennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuche.");
                }
                else if (i == 0){
                    System.out.println("Diese Kundennummer ist nicht bekannt. \nFalls Sie Ihre Kundennummer vergessen haben, wenden Sie sich an das Bankpersonal.");
                }
            }
        }
        return isValidCustomer;
    }

    private static boolean validatePin(Scanner scanner, int pin){
        boolean isValid = false;
        for (int i = 2; i > -1; i--){
            int inputPinInt = 0;
            try{
                System.out.print("Bitte Geheimzahl eingeben: ");
                String inputPinStr = scanner.nextLine();
                inputPinInt = Integer.parseInt(inputPinStr);
            }
            catch (NumberFormatException e){
                System.out.println("Bitte ausschließlich Zahlen eingeben.");
            }

            if (inputPinInt==pin){
                isValid = true;
                break;
            }
            else if(inputPinInt!=pin) {
                if (i > 1){
                    System.out.println("Diese Pin ist inkorrekt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuche.");
                }
                else if (i == 1){
                    System.out.println("Diese Pin ist inkorrekt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuch.");
                }
                else if (i == 0){
                    System.out.println("Diese Pin ist inkorrekt. Ihr Konto ist vorübergehend gesperrt! \nBitte wenden Sie sich an das Bankpersonal.");
                }
            }
        }
        return isValid;
    }

    private static void stopProgram(boolean invalidInput){
        if (!invalidInput){
            System.exit(0);
        }
    }

    private static ArrayList readCSV(String filepath){
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

    private static Customer instantiateCustomer() {
        ArrayList<ArrayList> database = readCSV(filepath);
        int position = 0;
        for (int i = 0; i < database.size(); i++) {
            if (validCustomerNum.equals(String.valueOf(database.get(i).get(3)))) {
                position = i;
                break;
            }
        }
        String firstName = String.valueOf(database.get(position).get(0));
        String secondName = String.valueOf(database.get(position).get(1));
        int gender = Integer.parseInt(String.valueOf(database.get(position).get(2)));
        int customerNum = Integer.parseInt(validCustomerNum);
        Customer customer = new Customer(firstName, secondName, gender, customerNum);
        return customer;
    }

    private static Account instantiateAccount() {
        ArrayList<ArrayList> database = readCSV(filepath);
        int position = 0;
        for (int i = 0; i < database.size(); i++) {
            if (validCustomerNum.equals(String.valueOf(database.get(i).get(3)))) {
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

