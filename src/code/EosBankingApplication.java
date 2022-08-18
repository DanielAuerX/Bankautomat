package code;

import java.util.ArrayList;
import java.util.Scanner;

public class EosBankingApplication {

    public static String validCustomerID = "";

    public static void main(String[] args) {

        boolean quit = false;

        System.out.println("B A N K O M A T EOS-Bank Standort: Hamburg");

        boolean isValidCustomer = validateCustomerID();
        stopProgram(isValidCustomer);

        Customer customer = instantiateCustomer(validCustomerID);
        Account account = instantiateAccount(validCustomerID);

        String name = getGreeting(customer);
        System.out.println("Guten Tag "+name+"!");

        boolean isValidPin = validatePin(account);
        stopProgram(isValidPin);

        String menuText = BOLD+"Sie haben Zugriff auf Ihr Konto mit der Nummer "+customer.getId() +RESET+
                "\nWählen Sie\n" +
                "1 - Kontostand abfragen\n" +
                "2 - Einzahlen\n" +
                "3 - Abheben\n" +
                "4 - System verlassen\n\n" +
                "Eingabe: ";

        Scanner scanner = new Scanner(System.in);
        while (!quit){
            System.out.print(menuText);
            String input = scanner.nextLine();
            switch (input){
                case "1":
                    System.out.println(showBalanceText(account));
                    break;
                case "2":
                    double amount = askForAmount();
                    account.deposit(amount);
                    System.out.println(showBalanceText(account));
                    break;
                case "3":
                    amount = askForAmount();
                    account.withdraw(amount);
                    System.out.println(showBalanceText(account));
                    break;
                case "4":
                    System.out.println("Auf Wiedersehen!");
                    Database.writeAccountData(account);
                    quit = true;
                    break;
                default:
                    System.out.println("Inkorrekte Eingabe! Versuchen Sie es erneut.\n");
            }
        }
    }

    private static void stopProgram(boolean invalidInput){
        if (!invalidInput){
            System.exit(0);
        }
    }

    private static String getGreeting(Customer customer){
        if (customer.getGender() == 0){
            return "Herr "+ customer.getLastName();
        }
        else if (customer.getGender() == 1){
            return "Frau "+ customer.getLastName();
        }
        else {
            return customer.getFirstName()+" "+ customer.getLastName();
        }
    }

    private static boolean validateCustomerID(){
        boolean isValidCustomer = false;
        Scanner scanner = new Scanner(System.in);

        for (int i = 3; i > -1; i--){
            System.out.print("Bitte Kundennummer eingeben: ");
            String inputCustomerNum = scanner.nextLine();

            boolean correctCustomerID = findCustomer(inputCustomerNum);
            if (correctCustomerID){
                isValidCustomer = true;
                EosBankingApplication.validCustomerID = inputCustomerNum;
                break;
            }
            else {
                if (i == 1){
                    System.out.println("Diese Kundennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuch.");
                }
                else if (i > 1){
                    System.out.println("Diese Kundennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuche.");
                }
                else {
                    System.out.println("Diese Kundennummer ist nicht bekannt. \nFalls Sie Ihre Kundennummer vergessen haben, wenden Sie sich an das Bankpersonal.");
                }
            }
        }
        return isValidCustomer;
    }

    private static boolean findCustomer(String input){
        boolean isCustomer = false;
        try {
            int inputInt = Integer.parseInt(input);
            ArrayList <ArrayList<String>> customerData = Database.readCSV("R:\\Java\\Bankautomat\\customer_data.csv");
            for (ArrayList<String> customerDatum : customerData) {
                if (inputInt == Integer.parseInt(customerDatum.get(2))) {
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

    private static int askForPin() {
        int inputPinInt = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Bitte Geheimzahl eingeben: ");
            String inputPinStr = scanner.nextLine();
            inputPinInt = Integer.parseInt(inputPinStr);
        } catch (NumberFormatException e) {
            System.out.println("Bitte ausschließlich Zahlen eingeben.");
        }
        return inputPinInt;
    }

    private static boolean validatePin(Account account){
        boolean isValid = false;
        for (int i = 2; i > -1; i--){
            int inputPinInt = askForPin();
            if (inputPinInt== account.pin){
                isValid = true;
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
        return isValid;
    }

    private static String showBalanceText(Account account){
        String balanceStr = String.format("%.2f", account.getBalance());
        String formattedText;
        if (account.getBalance() < 0){
            formattedText = "\n"+ EosBankingApplication.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+ EosBankingApplication.BOLD+ EosBankingApplication.RED_BACKGROUND+balanceStr+"€\n"+ EosBankingApplication.RESET;
        }
        else {
            formattedText = "\n"+ EosBankingApplication.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+ EosBankingApplication.BOLD+balanceStr+"€\n"+ EosBankingApplication.RESET;
        }

        return formattedText;
    }

    private static double askForAmount() {
        Scanner scanner = new Scanner(System.in);
        double amount = 0;
        try {
            System.out.println("Bitte Betrag eingeben: ");
            String input = scanner.nextLine();
            input = input.replace(",", ".");
            amount = Double.parseDouble(input);
            if (amount<=0){
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Bitte einen Betrag aus Zahlen eingeben.\nBuchstaben sind nicht gestattet!");
        } catch (IllegalArgumentException e) {
            System.out.println("Bitte eine Zahl, die größer als Null ist, eingeben!");
        } catch (Exception e) {
            System.out.println("Es ist ein Problem aufgetreten. Versuchen Sie es erneut.");
        }
        return amount;
    }

    private static Customer instantiateCustomer(String validCustomerID) {
        ArrayList<String> customerData = Database.getCustomerData(validCustomerID);
        // 0 firstname, 1 lastname, 2 id, 3 gender, 4 birthday, 5 email, 6 street, 7 house number, 8 zip code, 9 city
        Address address = new Address(customerData.get(6), Integer.parseInt(customerData.get(7)),
                Integer.parseInt(customerData.get(8)), customerData.get(9));

        return new Customer(customerData.get(0), customerData.get(1), Integer.parseInt(customerData.get(3)),
                address, customerData.get(4), customerData.get(5), Integer.parseInt(customerData.get(2)));
    }

    private static Account instantiateAccount(String validCustomerID) {
        ArrayList<String> database = Database.getAccountData(validCustomerID);
        // 0 account number, 1 customer number, 2 pin, 3 balance
        String id = database.get(0);
        return new Account(Integer.parseInt(id),Integer.parseInt(database.get(2)),
                Double.parseDouble(database.get(3)));
    }

    public static final String RESET = "\033[0m";

    public static final String RED_BACKGROUND = "\033[41m";

    public static final String TEXT_BACKGROUND = "\033[0;7m";

    public static final String BOLD = "\u001B[1m";

}

