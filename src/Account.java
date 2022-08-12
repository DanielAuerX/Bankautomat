import java.util.Scanner;

public class Account {

    int accountNum;
    int pinNum;
    double balance;

    public Account(int accountNum, int pinNum, double balance) {
        this.accountNum = accountNum;
        this.pinNum = pinNum;
        this.balance = balance;
    }

    public String getBalance(){
        String balanceStr = String.format("%.2f", balance);
        String formattedText;
        if (balance < 0){
            formattedText = "\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+Main.RED_BACKGROUND+balanceStr+"€\n"+Main.RESET;
        }
        else {
            formattedText = "\n"+Main.TEXT_BACKGROUND+"Ihr Kontostand beträgt: "+Main.BOLD+balanceStr+"€\n"+Main.RESET;
        }

        return formattedText;
    }

    public void deposit(Scanner scanner){
        try {
            System.out.println("Bitte Betrag eingeben: ");
            String input = scanner.nextLine();
            input= input.replace(",",".");
            double amount = Double.parseDouble(input);
            balance += amount;
        }
        catch (NumberFormatException e){
            System.out.println("Bitte einen Betrag aus Zahlen eingeben.\nBuchstaben sind nicht gestattet!");
        }
        catch (Exception e){
            System.out.println("Es ist ein Problem aufgetreten. Versuchen Sie es erneut.");
        }
    }

    public void withdraw(Scanner scanner){
        try {
            System.out.println("Bitte Betrag eingeben: ");
            String input = scanner.nextLine();
            input= input.replace(",",".");
            double amount = Double.parseDouble(input);
            balance -= amount;
        }
        catch (NumberFormatException e){
            System.out.println("Bitte einen Betrag aus Zahlen eingeben.\nBuchstaben sind nicht gestattet!");
        }
        catch (Exception e){
            System.out.println("Es ist ein Problem aufgetreten. Versuchen Sie es erneut.");
        }
    }

    public boolean validatePin(Scanner scanner){
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

            if (inputPinInt==pinNum){
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

}
