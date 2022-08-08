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

}
