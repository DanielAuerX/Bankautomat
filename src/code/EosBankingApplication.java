package code;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class EosBankingApplication {

    public void stopProgram(boolean canContinue) {
        if (!canContinue) {
            System.exit(0);
        }
    }

    public boolean checkCardID() {
        boolean isValidID = false;
        Scanner scanner = new Scanner(System.in);
        Repository repository = new Repository();

        for (int i = 3; i > -1; i--) {
            System.out.print("Bitte Kartennummer eingeben: ");
            String inputCardID = scanner.nextLine();

            boolean isPresentInDatabase = repository.findCardByID(inputCardID);
            if (isPresentInDatabase) {
                isValidID = true;
                Repository.validatedCardID = Integer.parseInt(inputCardID);
                break;
            } else {
                if (i == 1) {
                    System.out.println("Diese Kartennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch " + i + " Versuch.");
                } else if (i > 1) {
                    System.out.println("Diese Kartennummer ist nicht bekannt. Versuchen Sie es erneut.\nSie haben noch " + i + " Versuche.");
                } else {
                    System.out.println("Diese Kartennummer ist nicht bekannt. \nFalls Sie Ihre Bankkarte verloren haben, wenden Sie sich an das Bankpersonal.");
                }
            }
        }
        return isValidID;
    }

    public boolean validatePin(Card card) {
        JsonIO jsonIO = new JsonIO();
        Atm atm = new Atm();
        boolean isValidPin = false;
        while (card.getPinTries() < 4) {;
            int inputPinInt = atm.askForPin();
            if (card.checkPin(inputPinInt)) {
                jsonIO.writeCardData(card);
                return true;
            } else {
                switch (card.getPinTries()) {
                    case 1 -> {
                        System.out.println("Diese Pin ist inkorrekt. Versuchen Sie es erneut.\nSie haben noch zwei Versuche.");
                        jsonIO.writeCardData(card);
                    }

                    case 2 -> {
                        System.out.println("Diese Pin ist inkorrekt. Versuchen Sie es erneut.\nSie haben noch einen Versuch.");
                        jsonIO.writeCardData(card);
                    }
                    case 3 -> {
                        System.out.println("Diese Pin ist inkorrekt. Ihr Konto ist vorübergehend gesperrt!\nBitte wenden Sie sich an das Bankpersonal.");
                        card.blockCard(true);
                        jsonIO.writeCardData(card);
                        return false;
                    }
                }
            }
        }
        return isValidPin;
    }

    public Customer createCustomer() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bitte den Vornamen eingeben: ");
        String firstName = scanner.nextLine();
        System.out.print("Bitte den Nachnamen eingeben: ");
        String lastName = scanner.nextLine();
        System.out.print("Bitte Gender angeben (0 = männlich, 1 = weiblich, 2 = diverse): ");
        int gender = Integer.parseInt(scanner.nextLine());
        System.out.print("Bitte die Straße eingeben (Adresse): ");
        String street = scanner.nextLine();
        System.out.print("Bitte die Hausnummer eingeben (Adresse): ");
        String houseNumber = scanner.nextLine();
        System.out.print("Bitte die Postleitzahl eingeben (Adresse): ");
        int zip = Integer.parseInt(scanner.nextLine());
        System.out.print("Bitte den Ort eingeben (Adresse): ");
        String city = scanner.nextLine();
        System.out.print("Bitte Geburtsdatum eingeben (dd.mm.yyyy): ");
        String birthday = scanner.nextLine();
        System.out.print("Bitte die Email eingeben: ");
        String email = scanner.nextLine();

        var address = new Address(street, houseNumber, zip, city);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date birthdayDate = format.parse(birthday);
        final String uuid = UUID.randomUUID().toString();

        return new Customer(firstName, lastName, gender, address, birthdayDate, email, uuid);
    }

}

