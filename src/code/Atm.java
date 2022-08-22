package code;

import java.util.Scanner;

public class Atm {

    public static String validatedCardID = "";

    public static void main(String[] args) {

        boolean quit = false;
        EosBankingApplication eos = new EosBankingApplication();

        System.out.println("B A N K O M A T EOS-Bank Standort: Hamburg");

        boolean isValidCardID = eos.checkCardID();
        eos.stopProgram(isValidCardID);

        Card card = eos.instantiateCard(validatedCardID);
        Customer customer = eos.instantiateCustomer(card.getCustomerID());
        Account account = eos.instantiateAccount(card.getAccountID(), card.getId());

        String name = getGreeting(customer);
        System.out.println("Guten Tag "+name+"!");

        if (card.getIsBlocked()){
            System.out.println(BOLD+"Ihre Karte ist gesperrt!\n"+RESET
                    +"Wenden Sie sich bitte umgehend an das Bankpersonal.");
            eos.stopProgram(false);
        }

        boolean isValidPin = eos.validatePin(card);
        if (!isValidPin){
            card.blockCard(true);
            Database.writeCardData(card);
        }
        eos.stopProgram(isValidPin);

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

    private static String showBalanceText(Account account){
        String balanceStr = String.format("%.2f", account.getBalance());
        String formattedText;
        if (account.getBalance() < 0){
            formattedText = "\n"+ TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+ BOLD+ RED_BACKGROUND+balanceStr+"€\n"+ RESET;
        }
        else {
            formattedText = "\n"+ TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+ BOLD+balanceStr+"€\n"+ RESET;
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

    public static int askForPin() {
        int inputPinInt = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Bitte Geheimzahl eingeben: ");
            String inputPinStr = scanner.nextLine();
            inputPinInt = Integer.parseInt(inputPinStr);
            if (inputPinInt < 0){
                throw new IllegalArgumentException("Don't enter negative numbers.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Bitte ausschließlich Zahlen eingeben.");
            throw nfe;
        } catch (IllegalArgumentException iae){
            System.out.println("Bitte keine negativen Zahlen eingeben");
            throw iae;
        }
        return inputPinInt;
    }

    private static final String RESET = "\033[0m";

    private static final String RED_BACKGROUND = "\033[41m";

    private static final String TEXT_BACKGROUND = "\033[0;7m";

    private static final String BOLD = "\u001B[1m";
}
