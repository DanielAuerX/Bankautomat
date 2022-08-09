//TO DO
//+ datenbank? in datenbank speichern
//+ JUnit tests
//+ refactor Database classe, validatePin etc in Account
//+ add package

import java.util.Scanner;

public class Main {
    static String filepath = "R:\\Java\\Bankautomat\\customer_database.csv";
    static String validCustomerNum = "";

    public static void main(String[] args) {

        boolean quit = false;

        System.out.println("B A N K O M A T EOS-Bank Standort: Hamburg");
        Scanner scanner = new Scanner(System.in);

        boolean isValidCustomer = Customer.validateCustomerNum(scanner);
        stopProgram(isValidCustomer);

        Customer customer = Database.instantiateCustomer();
        Account account = Database.instantiateAccount();

        String name = customer.greeting();
        System.out.println("Guten Tag "+name+"!");

        boolean isValidPin = Account.validatePin(scanner,account.pinNum);
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

    private static void stopProgram(boolean invalidInput){
        if (!invalidInput){
            System.exit(0);
        }
    }

}

