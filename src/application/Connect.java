package application;

import entities.person.*;
import entities.properties.*;
import enumerations.PersonType;
import enumerations.Status;
import enumerations.TypeOfProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static enumerations.PersonType.*;
import static enumerations.TypeOfProperty.*;
import static services.PropertyService.personsList;
import static utility.utilSql.SqlUtil.*;

public class Connect {
    public static void setSqlData() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcTest", "postgres", "123456")) {
            if (connection != null) {
                System.out.println("Database initialized successful.\n");
                ResultSet rsPerson = connection.createStatement().executeQuery("SELECT * FROM Person");
                ResultSet rsPersonAddress;
                ResultSet rsContact;

                ResultSet rsPropertyOwner = connection.createStatement().executeQuery("SELECT * FROM PropertyOwner");
                ResultSet rsProperty = connection.createStatement().executeQuery("SELECT * FROM Property");
                ResultSet rsPropertyAddress = connection.createStatement().executeQuery("SELECT * FROM PropertyAddress");

                while (rsPerson.next()) {
                    String name = rsPerson.getString("Name");
                    String registrationNumber = rsPerson.getString("RegistrationNumber");
                    String password = rsPerson.getString("Password");
                    int rn = rsPerson.getInt("RegistrationNumber");

                    String propertyID = rsProperty.getString("PropertyID");
                    String propertyDesc = rsProperty.getString("Description");
                    double totalArea = rsProperty.getDouble("TotalArea");
                    double value = rsProperty.getDouble("Value");

                    Person person;
                    if (rsPerson.getString("PersonType").equals("NATURAL_PERSON")) {
                        person = new NaturalPerson(NATURAL_PERSON, name, password, rn);
                    } else {
                        person = new LegalPerson(LEGAL_PERSON, name, password, rn);
                    }

                    rsPersonAddress = connection.createStatement().executeQuery(STR."SELECT * FROM PersonAddress WHERE RegistrationPersonNumber = '\{registrationNumber}'");
                    person.setPersonsAddress(sqlAddress(rsPersonAddress));
                    rsPersonAddress.close();

                    rsContact = connection.createStatement().executeQuery(STR."SELECT * FROM Contact WHERE RegistrationPersonNumber = '\{registrationNumber}'");
                    person.setPersonsContact(sqlContact(rsContact));
                    rsContact.close();

                    rsPropertyOwner = connection.createStatement().executeQuery(STR."""
                            SELECT *
                            FROM Person
                            JOIN PropertyOwner
                                ON PropertyOwner.RegistrationPersonNumber = \{registrationNumber}
                            JOIN Property
                                ON Property.PropertyID = \{propertyID}""");

                    while (rsProperty.next()) {
                        AddressProperty addressProperty = null;
                        Status status = Status.valueOf(rsProperty.getString("Status"));
                        Property property;


                        rsPropertyAddress = connection.createStatement().executeQuery(STR."SELECT * FROM PropertyAddress JOIN Property ON Property.PropertyID = PropertyAddress.\{rsProperty.getString("PropertyID")}");

                        while (rsPropertyAddress.next()) {
                            String addressPropertyID = rsPropertyAddress.getString("PropertyID");
                            String addressPropertyCity = rsPropertyAddress.getString("City");
                            String addressPropertyZipCode = rsPropertyAddress.getString("ZipCode");
                            String addressPropertyDistrict = rsPropertyAddress.getString("District");

                            addressProperty = new AddressProperty(addressPropertyID, addressPropertyCity, addressPropertyZipCode, addressPropertyDistrict);
                        }

                        switch (rsProperty.getString("PropertyType")) {
                            case "APARTMENT" -> {
                                int floorApart;
                                int number;
                                String buildingName;
                                int roomsNumber;
                                int yearBuilt;

                                property = new Apartment(APARTMENT, propertyID, addressProperty, propertyDesc, totalArea, value, status);
                            }
                            case "FARM" -> property = new Farm(FARM, propertyID, addressProperty, propertyDesc, totalArea, value, status);
                            case "HOUSE" -> property = new House(HOUSE, propertyID, addressProperty, propertyDesc, totalArea, value, status);
                            case "LAND" -> property = new Land(LAND, propertyID, addressProperty, propertyDesc, totalArea, value, status);
                        }
                    }

                    personsList.add(person);
                }
            } else {
                System.out.println("Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."Error initialize database. \{e.getMessage()}");
        }
    }
}
