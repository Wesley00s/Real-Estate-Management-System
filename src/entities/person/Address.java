package entities.person;

public class Address {
    private String rentID;
    private String city;
    private String zipCode;
    private String district;
    private String street;
    private int number;

    public Address(String rentID, String city, String zipCode, String district, String street, int number) {
        this.rentID = rentID;
        this.city = city;
        this.zipCode = zipCode;
        this.district = district;
        this.street = street;
        this.number = number;
    }

    public Address(String rentID, String city, String zipCode, String district) {
        this.rentID = rentID;
        this.city = city;
        this.zipCode = zipCode;
        this.district = district;
    }

    public String getId() {
        return rentID;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public void setId(String rentID) {
        this.rentID = rentID;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return STR."""
                ID: \{getId()}
                City: \{getCity()}
                Zip code: \{getZipCode()}
                District: \{getDistrict()}
                Street: \{getStreet()}
                Number: \{getNumber()}""";
    }
}
