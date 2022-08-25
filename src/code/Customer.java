package code;

public class Customer {
    private String firstName;
    private String lastName;
    private int gender;
    private Address address;
    private String birthday;
    private String email;
    private int id;

    public Customer(String firstName, String lastName, int gender, Address address, String birthday, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.id = id;
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
