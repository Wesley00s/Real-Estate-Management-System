package entities.person;

import entities.properties.*;
import enumerations.PersonType;

import java.util.ArrayList;
import java.util.List;

import static enumerations.TypeOfProperty.*;
import static enumerations.TypeOfProperty.LAND;
import static services.PropertyService.propertyList;

public abstract class Person {
    private PersonType personType;
    private String personsName;
    private Address personsAddress;
    private Contact personsContact;
    private String password;
    private List<Property> propertyList = new ArrayList<>();

    protected Person(PersonType personType, String personsName, String password) {
        this.personType = personType;
        this.personsName = personsName;
        this.password = password;
    }

    protected Person(PersonType personType, String personsName, Address personsAddress, Contact personsContact, String password) {
        this.personType = personType;
        this.personsName = personsName;
        this.personsAddress = personsAddress;
        this.personsContact = personsContact;
        this.password = password;
    }

    public void addPropertiesList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public String getPersonsName() {
        return personsName;
    }

    public Address getPersonsAddress() {
        return personsAddress;
    }

    public Contact getPersonsContact() {
        return personsContact;
    }

    public String getPassword() {
        return password;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public void setPersonsName(String personsName) {
        this.personsName = personsName;
    }

    public void setPersonsAddress(Address personsAddress) {
        this.personsAddress = personsAddress;
    }

    public void setPersonsContact(Contact personsContact) {
        this.personsContact = personsContact;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public void displayPropertiesList() {
        try
        {
            System.out.println("\n* APARTMENTS");
            for(Property apartment : propertyList) {
                if (apartment.getTypeOfProperty().equals(APARTMENT))
                    System.out.println(apartment);
            }

            System.out.println("\n* FARMS");
            for(Property farm : propertyList) {
                if (farm.getTypeOfProperty().equals(FARM))
                    System.out.println(farm);
            }

            System.out.println("\n* HOUSES");
            for(Property house : propertyList) {
                if (house.getTypeOfProperty().equals(HOUSE))
                    System.out.println(house);
            }

            System.out.println("\n* LANDS");
            for(Property land : propertyList) {
                if (land.getTypeOfProperty().equals(LAND))
                    System.out.println(land);
            }
        } catch (NullPointerException e) {
            System.out.println("No properties yet.");
        }
    }

    public abstract String toString();
}
