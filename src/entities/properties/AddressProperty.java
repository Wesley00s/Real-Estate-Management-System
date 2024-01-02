package entities.properties;

import entities.person.Address;

public class AddressProperty extends Address {
    public AddressProperty(String city, String zipCode, String neighborhood) {
        super(city, zipCode, neighborhood);
    }

    @Override
    public String toString() {
        return STR."""
                City: \{getCity()}
                Zip code: \{getZipCode()}
                Neighbohood: \{getNeighborhood()}
                """;
    }
}
