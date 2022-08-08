//TO DO
//+ datenbank? wie werden die objekte initizialisiert?
//+ JUnit tests
//+ check static
//+ add package


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Customer firstCustomer = new Customer("Peter", "Pahn", 0, 1001); // nur zum testen
        Account firstAccount = new Account(10001, 110, 100.00);
        int[] customerNums = {1001, 1002, 1003, 1004};
        int correctPin = 110;
        boolean quit = false;
        String menuText = "Sie haben Zugriff auf Ihr Konto mit der Nummer "+firstCustomer.customerNum+
                "\nWählen Sie\n" +
                "1 – Kontostand abfragen\n" +
                "2 – Einzahlen\n" +
                "3 – Abheben\n" +
                "4 – System verlassen\n\n" +
                "Eingabe: ";

        System.out.println("B A N K O M A T EOS-Bank Standort: Hamburg");
        Scanner scanner = new Scanner(System.in);

        boolean isValidCustomer = validateCustomer(scanner,customerNums);
        stopProgram(isValidCustomer);

        String name = firstCustomer.greeting();
        System.out.println("Guten Tag "+name+"!");

        boolean isValidPin = validatePin(scanner,correctPin);
        stopProgram(isValidPin);

        while (!quit){
            System.out.print(menuText);
            String input = scanner.nextLine();
            switch (input){
                case "1":
                    System.out.println(firstAccount.getBalance());
                    break;
                case "2":
                    firstAccount.deposit(scanner);
                    System.out.println(firstAccount.getBalance());
                    break;
                case "3":
                    firstAccount.withdraw(scanner);
                    System.out.println(firstAccount.getBalance());
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

    private static boolean isValidCustomer(String input, int[] customerNums){  //geht später durch datenbank (findCustomer?)
        boolean isCustomer = false;
        try {
            int inputInt = Integer.parseInt(input);
            for (int customerNum:customerNums){
                if (customerNum==inputInt){
                    isCustomer = true;
                    break;
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("Bitte ausschließlich Zahlen eingeben.");
        }
        finally {
            return isCustomer;
        }

    }

    private static boolean validateCustomer(Scanner scanner, int[] customerNums){
        boolean isValid = false;

        for (int i = 3; i > -1; i--){
            System.out.print("Bitte Kundennummer eingeben: ");
            String inputCustomerNum = scanner.nextLine();

            boolean correctCustomerNum = isValidCustomer(inputCustomerNum, customerNums);
            if (correctCustomerNum){
                isValid = true;
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
        return isValid;
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





}
