package entities.properties;

public class Address {
    private String city;
    private String zipCode;
    private String neighborhood;

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

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String toString() {
        return STR."""
                City: \{getCity()}
                Zip code: \{getZipCode()}
                Neighbohood: \{getNeighborhood()}
                """;
    }
}
