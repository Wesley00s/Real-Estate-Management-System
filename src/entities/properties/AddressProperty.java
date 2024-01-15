package entities.properties;

import entities.person.Address;

public class AddressProperty extends Address {
    public AddressProperty(String rentID, String city, String zipCode, String district) {
        super(rentID, city, zipCode, district);
    }

    @Override
    public String toString() {
        return STR."""
                ID: \{getId()}
                City: \{getCity()}
                Zip code: \{getZipCode()}
                District: \{getDistrict()}
                """;
    }
}
