package database.operations;

import entities.person.*;
import entities.properties.*;
import enumerations.Status;
import enumerations.TypeOfApart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static enumerations.PersonType.*;
import static enumerations.TypeOfProperty.*;
import static services.PropertyService.personsList;
import static services.PropertyService.propertyList;

public class LoadData {
    public static Contact loadSqlContact(ResultSet rsContact) throws SQLException {
        Contact contact = null;
        while (rsContact.next()) {
            String contactID = rsContact.getString("ContactID");
            String email = rsContact.getString("Email");
            int phone = rsContact.getInt("PhoneNumber");
            contact = new Contact(contactID, email, phone);
        }
        return contact;
    }

    public static Address loadSqlAddress(ResultSet rsPersonAddress) throws SQLException {
        Address address = null;
        while (rsPersonAddress.next()) {
            String addressID = rsPersonAddress.getString("AddressID");
            String city = rsPersonAddress.getString("City");
            String zipCode = rsPersonAddress.getString("ZipCode");
            String district = rsPersonAddress.getString("District");
            String street = rsPersonAddress.getString("Street");
            int number = rsPersonAddress.getInt("Number");

            address = new Address(addressID, city, zipCode, district, street, number);
        }
        return address;
    }

    public static AddressProperty loadSqlPropertyAddress(ResultSet rsPropertyAddress) throws SQLException {
        AddressProperty addressProperty = null;
        while (rsPropertyAddress.next()) {
            String addressPropertyID = rsPropertyAddress.getString("AddressID");
            String addressPropertyCity = rsPropertyAddress.getString("City");
            String addressPropertyZipCode = rsPropertyAddress.getString("ZipCode");
            String addressPropertyDistrict = rsPropertyAddress.getString("District");

            addressProperty = new AddressProperty(addressPropertyID, addressPropertyCity, addressPropertyZipCode, addressPropertyDistrict);
        }
        return addressProperty;
    }

    public static void loadSqlPropertyOwner(ResultSet rsPropertyOwner, Property property) throws SQLException {
        while (rsPropertyOwner.next()) {
            for (Person personType : personsList) {
                if (personType.getPersonType().equals(NATURAL_PERSON)) {
                    NaturalPerson naturalPerson = (NaturalPerson) personType;
                    if (naturalPerson.getSsn() == rsPropertyOwner.getInt("RegistrationPersonNumber")) {
                        naturalPerson.getPropertyList().add(property);
                        property.setOwner(naturalPerson);
                    }
                } else if (personType.getPersonType().equals(LEGAL_PERSON)) {
                    LegalPerson legalPerson = (LegalPerson) personType;
                    if (legalPerson.getEin() == rsPropertyOwner.getInt("RegistrationPersonNumber")) {
                        legalPerson.getPropertyList().add(property);
                        property.setOwner(legalPerson);
                    }
                } else {
                    System.out.println("Something went wrong with the type of person.");
                }
            }
        }
    }

    public static void loadSqlProperty(Connection connection) throws SQLException {
        ResultSet rsProperty = connection.createStatement().executeQuery("SELECT * FROM Property");
        Property property;
        while (rsProperty.next()) {
            Status status = Status.valueOf(rsProperty.getString("Status"));
            String propertyID = rsProperty.getString("PropertyID");
            String propertyDesc = rsProperty.getString("Description");
            double totalArea = rsProperty.getDouble("TotalArea");
            double price = rsProperty.getDouble("Price");
            double rentValue = rsProperty.getDouble("RentValue");
            int floorOfTheApart = rsProperty.getInt("FloorApart");
            int numberOfTheApart = rsProperty.getInt("NumberApart");
            String buildingName = rsProperty.getString("BuildingName");
            int totalNumberRooms = rsProperty.getInt("TotalRooms");
            int yearBuilt = rsProperty.getInt("YearBuilt");
            double condominiumValue = rsProperty.getDouble("CondominiumValue");
            double buildingArea = rsProperty.getDouble("BuildingArea");
            double distanceOfCity = rsProperty.getDouble("DistanceOfCity");
            int parkingSpaces = rsProperty.getInt("ParkingSpaces");
            int totalNumberOfFloors = rsProperty.getInt("TotalFloors");
            double frontDimension = rsProperty.getDouble("FrontDimension");
            double sideDimension = rsProperty.getDouble("SideDimension");

            property = null;
            switch (rsProperty.getString("PropertyType")) {
                case "APARTMENT" -> {
                    TypeOfApart typeOfApart = TypeOfApart.valueOf(rsProperty.getString("ApartmentType"));
                    property = new Apartment(APARTMENT, propertyID, propertyDesc, totalArea, price, rentValue, status)
                            .setBuildingDetails(floorOfTheApart, numberOfTheApart, buildingName, totalNumberRooms, yearBuilt)
                            .setAdditionalDetails(condominiumValue, typeOfApart);
                }
                case "FARM" -> {
                    property = new Farm(FARM, propertyID, propertyDesc, totalArea, price, rentValue, status)
                            .setBuildingDetails(buildingArea, totalNumberRooms, yearBuilt, distanceOfCity);
                }
                case "HOUSE" -> {
                    property = new House(HOUSE, propertyID, propertyDesc, totalArea, price, rentValue, status)
                            .setBuildingDetails(buildingArea, totalNumberRooms, parkingSpaces, yearBuilt, totalNumberOfFloors);
                }
                case "LAND" -> {
                    property = new Land(LAND, propertyID, propertyDesc, price, rentValue, status)
                            .setPropertyDetails(frontDimension, sideDimension);
                }
            }

            ResultSet rsPropertyAddress = connection.createStatement().executeQuery(STR."SELECT * FROM PropertyAddress WHERE PropertyAddress.PropertyID = '\{propertyID}'");
            assert property != null;
            property.setAddress(loadSqlPropertyAddress(rsPropertyAddress));
            rsPropertyAddress.close();

            propertyList.add(property);
            ResultSet rsPropertyOwner = connection.createStatement().executeQuery(STR."SELECT * FROM PropertyOwner WHERE PropertyOwner.PropertyID = '\{propertyID}'");
            loadSqlPropertyOwner(rsPropertyOwner, property);
            rsPropertyOwner.close();
        }
    }

    public static void loadSqlPerson(Connection connection) throws SQLException {
        ResultSet rsPerson = connection.createStatement().executeQuery("SELECT * FROM Person");
        Person person;
        while (rsPerson.next()) {
            String name = rsPerson.getString("Name");
            int registrationNumber = rsPerson.getInt("RegistrationNumber");
            String password = rsPerson.getString("Password");
            int rn = rsPerson.getInt("RegistrationNumber");

            if (rsPerson.getString("PersonType").equals("NATURAL_PERSON")) {
                person = new NaturalPerson(NATURAL_PERSON, name, password, rn);
            } else {
                person = new LegalPerson(LEGAL_PERSON, name, password, rn);
            }

            ResultSet rsPersonAddress = connection.createStatement().executeQuery(STR."SELECT * FROM PersonAddress WHERE RegistrationPersonNumber = '\{registrationNumber}'");
            person.setPersonsAddress(loadSqlAddress(rsPersonAddress));
            rsPersonAddress.close();

            ResultSet rsContact = connection.createStatement().executeQuery(STR."SELECT * FROM Contact WHERE RegistrationPersonNumber = '\{registrationNumber}'");
            person.setPersonsContact(loadSqlContact(rsContact));
            rsContact.close();

            personsList.add(person);
        }
    }
}