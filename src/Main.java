//TO DO
//+ JUnit tests
//+ add package
//+ durch Fehlermeldungen gehen
//+ farben

/*
    void getBalance() {
        Account account = new Account(1001, 110, 100.00);
        assertEquals("Ihr Kontostand beträgt: 100,00€\n", account.getBalance());

    }
 */

import java.util.Scanner;

public class Main {

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

        String menuText = "Sie haben Zugriff auf Ihr Konto mit der"+BOLD+"Nummer "+customer.customerNum+RESET+
                "\nWählen Sie\n" +
                "1 - Kontostand abfragen\n" +
                "2 - Einzahlen\n" +
                "3 - Abheben\n" +
                "4 - System verlassen\n\n" +
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
                    Database.writeNewBalance(account);
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

    public static final String RESET = "\033[0m";

    public static final String RED_BACKGROUND = "\033[41m";

    public static final String TEXT_BACKGROUND = "\033[0;7m";

    public static final String BOLD = "\u001B[1m";

}

