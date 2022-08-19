package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EosBankingApplication {

    public void stopProgram(boolean canContinue){
        if (!canContinue){
            System.exit(0);
        }
    }

    public boolean checkCardID(){
        boolean isValidID = false;
        Scanner scanner = new Scanner(System.in);

        for (int i = 3; i > -1; i--){
            System.out.print("Bitte Kartennummer eingeben: ");
            String inputCardID = scanner.nextLine();

            boolean isPresentInDatabase = findCardID(inputCardID);
            if (isPresentInDatabase){
                isValidID = true;
                Atm.validatedCardID = inputCardID;
                break;
            }
            else {
                if (i == 1){
                    System.out.println("Diese Kartennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuch.");
                }
                else if (i > 1){
                    System.out.println("Diese Kartennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch "+i+" Versuche.");
                }
                else {
                    System.out.println("Diese Kartennummer ist nicht bekannt. \nFalls Sie Ihre Bankkarte verloren haben, wenden Sie sich an das Bankpersonal.");
                }
            }
        }
        return isValidID;
    }

    public boolean findCardID(String input){
        boolean foundCardID = false;
        try {
            int inputInt = Integer.parseInt(input);
            ArrayList <ArrayList<String>> cardData = Database.readCSV("R:\\Java\\Bankautomat\\card_data.csv");
            for (ArrayList<String> cardDatapoint : cardData) {
                if (inputInt == Integer.parseInt(cardDatapoint.get(0))) {
                    foundCardID = true;
                    break;
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("Bitte ausschließlich Zahlen eingeben.");
        }
        return foundCardID;

    }

    public Card instantiateCard(String cardID) {
        ArrayList<String> database = Database.getCardData(cardID);
        // 0 card id, 1 account id, 2 customer id, 3 pin, 4 isBlocked
        boolean isBlocked;
        isBlocked = !database.get(4).equals("active");
        return new Card(Integer.parseInt(database.get(0)),Integer.parseInt(database.get(1)),
                Integer.parseInt(database.get(2)), Integer.parseInt(database.get(3)), isBlocked);
    }

    public boolean validatePin(Card card){
        boolean isValidPin = false;
        for (int i = 2; i > -1; i--){
            int inputPinInt = Atm.askForPin();
            if (card.checkPin(inputPinInt)){
                isValidPin = true;
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
        return isValidPin;
    }

    public Customer instantiateCustomer(int validCustomerID) {
        ArrayList<String> customerData = Database.getCustomerData(String.valueOf(validCustomerID));
        // 0 firstname, 1 lastname, 2 id, 3 gender, 4 birthday, 5 email, 6 street, 7 house number, 8 zip code, 9 city
        Address address = new Address(customerData.get(6), Integer.parseInt(customerData.get(7)),
                Integer.parseInt(customerData.get(8)), customerData.get(9));

        return new Customer(customerData.get(0), customerData.get(1), Integer.parseInt(customerData.get(3)),
                address, customerData.get(4), customerData.get(5), Integer.parseInt(customerData.get(2)));
    }

    public Account instantiateAccount(int accountID, int cardID) {
        ArrayList<String> database = Database.getAccountData(String.valueOf(accountID));
        // 0 account number, 1 customer number, 2 card ID, 3 balance
        String[] customerIdArray = database.get(1).split(",");
        ArrayList<String> customerIdArrayList = new ArrayList<>();
        Collections.addAll(customerIdArrayList, customerIdArray);
        return new Account(accountID, customerIdArrayList, cardID, Double.parseDouble(database.get(3)));
    }

}

