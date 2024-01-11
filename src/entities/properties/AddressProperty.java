package entities.properties;

import entities.person.Address;

public class AddressProperty extends Address {
    public AddressProperty(String id, String city, String zipCode, String neighborhood) {
        super(id, city, zipCode, neighborhood);
    }

    @Override
    public String toString() {
        return STR."""
                ID: \{getId()}
                City: \{getCity()}
                Zip code: \{getZipCode()}
                Neighbohood: \{getNeighborhood()}
                """;
    }
}
