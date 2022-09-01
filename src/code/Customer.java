package code;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private String firstName;
    private String lastName;
    private int gender;
    private Address address;
    private Date birthday;
    private String email;
    private int id;

    public Customer(String firstName, String lastName, int gender, Address address, Date birthday, String email, int id) throws ParseException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.id = id;
        //SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        //this.birthday = format.parse(birthday);
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

    public int getId() {
        return id;
    }
}
