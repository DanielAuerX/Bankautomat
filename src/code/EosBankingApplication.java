package code;

import java.util.Scanner;

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
        boolean isValidPin = false;
        while (card.getPinTries() < 4) {;
            int inputPinInt = Atm.askForPin();
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

}

