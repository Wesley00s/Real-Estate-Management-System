package entities.person;

public class Address {
    private String city;
    private String zipCode;
    private String neighborhood;
    private String street;
    private Integer number;

    public Address(String city, String zipCode, String neighborhood, String street, Integer number) {
        this.city = city;
        this.zipCode = zipCode;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
    }

    public Address(String city, String zipCode, String neighborhood) {
        this.city = city;
        this.zipCode = zipCode;
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return STR."""
                City: \{getCity()}
                Zip code: \{getZipCode()}
                Neighborhoood: \{getNeighborhood()}
                Street: \{getStreet()}
                Number: \{getNumber()}
                """;
    }
}
