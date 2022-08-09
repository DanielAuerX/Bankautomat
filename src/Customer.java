import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    String firstName;
    String secondName;
    int gender;         //0 = male; 1 = female; 2 = diverse
    int customerNum;

    public Customer(String firstName, String secondName, int gender, int customerNum) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.customerNum = customerNum;
    }

    public String greeting(){
        if (gender == 0){
            return "Herr "+secondName;
        }
        else if (gender == 1){
            return "Frau "+secondName;
        }
        else {
            return firstName+" "+secondName;
        }
    }

    public static boolean validateCustomerNum(Scanner scanner){
        boolean isValidCustomer = false;

        for (int i = 3; i > -1; i--){
            System.out.print("Bitte Kundennummer eingeben: ");
            String inputCustomerNum = scanner.nextLine();

            boolean correctCustomerNum = findCustomer(inputCustomerNum);
            if (correctCustomerNum){
                isValidCustomer = true;
                Main.validCustomerNum = inputCustomerNum;
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
            ArrayList <ArrayList> customerData = Database.readCSV(Database.filepath);
            for (ArrayList customerDatum : customerData) {
                if (inputInt == Integer.parseInt((String) customerDatum.get(3))) {
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

}
