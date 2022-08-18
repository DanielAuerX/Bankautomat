package code;

public class Customer {
    String firstName;
    String lastName;
    int gender;         //0 = male; 1 = female; 2 = diverse
    Address address;
    String birthday;
    String email;
    int id;

    public Customer(String firstName, String lastName, int gender, Address address, String birthday, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }
}
