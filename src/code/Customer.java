package code;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private int gender;
    private Address address;
    private Date birthday;
    private String email;
    private String id;

    public Customer(String firstName, String lastName, int gender, Address address, Date birthday, String email, String id) throws Exception {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        boolean isValidEmail = validateEmail(email);
        if (isValidEmail){
            this.email = email;
        }
        else {
            throw new Exception("Invalid email address!");
        }
        this.id = id;
        this.birthday = birthday;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    private boolean validateEmail(String email){
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
