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
        return "Ihr Kontostand beträgt: "+balanceStr+"€\n";
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
            System.out.println("Bitte einen Betrag aus Zahlen eingeben.\nBuchstaben sind nicht gestattet!\n");
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
            System.out.println("Bitte einen Betrag aus Zahlen eingeben.\nBuchstaben sind nicht gestattet!\n");
        }
        catch (Exception e){
            System.out.println("Es ist ein Problem aufgetreten. Versuchen Sie es erneut.");
        }
    }
}
