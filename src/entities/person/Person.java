package entities.person;

import entities.properties.Apartment;
import entities.properties.Farm;
import entities.properties.House;
import entities.properties.Land;

import java.util.List;

public abstract class Person {
    private String personsName;
    private Address personsAddress;
    private Contact personsContact;
    private String password;
    private List<Apartment> apartmentList;
    private List<Farm> farmList;
    private List<House> houseList;
    private List<Land> landList;

    protected Person(String personsName, Address personsAddress, Contact personsContact, String password) {
        this.personsName = personsName;
        this.personsAddress = personsAddress;
        this.personsContact = personsContact;
        this.password = password;
    }

    public void addPropertiesList(List<Apartment> apartmentList, List<Farm> farmList, List<House> houseList, List<Land> landList) {
        this.apartmentList = apartmentList;
        this.farmList = farmList;
        this.houseList = houseList;
        this.landList = landList;
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

    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public List<Farm> getFarmList() {
        return farmList;
    }

    public List<House> getHouseList() {
        return houseList;
    }

    public List<Land> getLandList() {
        return landList;
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

    public void setApartmentList(List<Apartment> apartmentList) {
        this.apartmentList = apartmentList;
    }

    public void setFarmList(List<Farm> farmList) {
        this.farmList = farmList;
    }

    public void setHouseList(List<House> houseList) {
        this.houseList = houseList;
    }

    public void setLandList(List<Land> landList) {
        this.landList = landList;
    }

    public void displayPropertiesList() {
        try
        {
            System.out.println("\n* APARTMENTS");
            for(Apartment apartment : getApartmentList()) {
                System.out.println(apartment);
            }

            System.out.println("\n* FARMS");
            for(Farm farm : getFarmList()) {
                System.out.println(farm);
            }

            System.out.println("\n* HOUSES");
            for(House house : getHouseList()) {
                System.out.println(house);
            }

            System.out.println("\n* LANDS");
            for(Land land : getLandList()) {
                System.out.println(land);
            }
        } catch (NullPointerException e) {
            System.out.println("No properties yet.");
        }
    }

    public abstract String toString();
}
