package code;

public class Address {
    String street;
    int houseNumber;
    String city;
    int zipCode;

    public Address(String street, int houseNumber, int zipCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
    }
}
