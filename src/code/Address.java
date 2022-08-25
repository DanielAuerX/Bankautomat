package code;

public class Address  {
    private String street;
    private String houseNumber;
    private int zipCode;
    private String city;

    public Address(String street, String houseNumber, int zipCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", zipCode=" + zipCode +
                ", city='" + city + '\'' +
                '}';
    }
}
