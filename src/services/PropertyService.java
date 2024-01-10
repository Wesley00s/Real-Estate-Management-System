package services;

import entities.person.*;
import entities.properties.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static enumerations.PersonType.LEGAL_PERSON;
import static enumerations.PersonType.NATURAL_PERSON;
import static enumerations.Situation.*;
import static enumerations.TypeOfApart.*;
import static enumerations.TypeOfProperty.*;
import static services.PersonService.personsLoginMenu;

import static utility.GenerateID.ID;
import static utility.MenuFormat.printMenu;
import static utility.utilProperties.PropertiesManager.*;

public class PropertyService {
    private static final Scanner sc = new Scanner(System.in);
    public static List<Property> propertyList = new ArrayList<>();
    public static List<Property> historyPropertyList = new ArrayList<>();
    public static List<Person> personsList = new ArrayList<>();

    protected static void negotiateMenu(Person person) {
        List<String> personsOptions = List.of("SEE PROPERTIES", "BUY PROPERTY", "RENT PROPERTY", "BACK");
        List<Property> propertyListOfNonLoggedPersons = propertyList;
        if (person.getPropertyList() != null)
            propertyListOfNonLoggedPersons.removeAll(person.getPropertyList());

        while (true) {
            printMenu(personsOptions, STR."NEGOTIATION MENU - Honorable \{person.getPersonsName()}, what you want?");
            switch (sc.nextLine()) {
                case "1" -> seeProperties(propertyListOfNonLoggedPersons);
                case "2" -> buyProperty(person);
                case "3" ->
//                        rentProperty();
                {}
                case "4" -> {System.out.println("Returning..."); personsLoginMenu();}
                default -> System.out.println("\nInvalid option!\n");
            }
        }
    }

    private static void buyProperty(Person person) {
        if (propertyList.isEmpty()) {
            System.out.println("The properties list is empty.");
            return;
        }

        List<Property> propertyListOfNonLoggedPersons = propertyList;
        Property propertyToBePurchase = null;
        if (person.getPropertyList() != null) {
            propertyListOfNonLoggedPersons.removeAll(person.getPropertyList());
            propertyToBePurchase = searchProperty(propertyListOfNonLoggedPersons);
        } else {
            propertyToBePurchase = searchProperty(propertyList);
        }
            if (propertyToBePurchase != null) {
                if (!propertyToBePurchase.getSituation().equals(FOR_SALE) && !propertyToBePurchase.getSituation().equals(SALE_OR_RENT)) {
                    System.out.println("Operation not permitted, check if the property is for sale.");
                    return;
                }

                Person oldOwner = propertyToBePurchase.getOwner();
                if (oldOwner != null) {
                    oldOwner.getPropertyList().remove(propertyToBePurchase);
                } else {
                    System.out.println("Undefined owner.");
                    return;
                }

                propertyToBePurchase.setSituation(SOLD);
                person.getPropertyList().add(propertyToBePurchase);
                propertyList.remove(propertyToBePurchase);
                historyPropertyList.add(propertyToBePurchase);
                System.out.println("Property purchased successfully!");

            } else {
                System.out.println("No available properties for purchase.");
            }
    }

    public static void addPropertiesAndOwners() {
        Address addressP1 = new Address("New York", "10001", "Manhattan", "Broadway Street", 123);
        Contact contact1 = new Contact("person1@email.com", "987-654-3210");
        NaturalPerson person1 = new NaturalPerson(NATURAL_PERSON, "John Smith", addressP1, contact1, "password123", 987654321);

        Address addressP2 = new Address("London", "78454", "Westminster", "Buckingham Palace Road", 456);
        Contact contact2 = new Contact("person2@email.com", "123-456-7890");
        NaturalPerson person2 = new NaturalPerson(NATURAL_PERSON, "Emma Johnson", addressP2, contact2, "securePassword", 876543210);

        Address addressP3 = new Address("Paris", "75001", "Le Marais", "Rue Saint-Antoine", 789);
        Contact contact3 = new Contact("person3@email.com", "234-567-8901");
        NaturalPerson person3 = new NaturalPerson(NATURAL_PERSON, "Pierre Dubois", addressP3, contact3, "parisPassword123", 654321098);

        Address addressP4 = new Address("Tokyo", "10001", "Chiyoda", "Chiyoda City", 101);
        Contact contact4 = new Contact("person4@email.com", "345-678-9012");
        LegalPerson person4 = new LegalPerson(LEGAL_PERSON, "Yuki Tanaka", addressP4, contact4, "tokyoSecure456", 123456789);

        Address address5 = new Address("Sydney", "20008", "Darling Harbour", "George Street", 222);
        Contact contact5 = new Contact("person5@email.com", "456-789-0123");
        LegalPerson person5 = new LegalPerson(LEGAL_PERSON, "Olivia Davis", address5, contact5, "sydneyPassword789", 987654321);

        personsList.add(person1);
        personsList.add(person2);
        personsList.add(person3);
        personsList.add(person4);

        AddressProperty address1 = new AddressProperty("Los Angeles", "90001", "Hollywood");
        AddressProperty address2 = new AddressProperty("Paris", "75002", "Le Marais");
        AddressProperty address3 = new AddressProperty("Tokyo", "54899", "Chuo");
        AddressProperty farmAddress1 = new AddressProperty("Countryside", "12345", "Green Valley");
        AddressProperty farmAddress2 = new AddressProperty("Rural Area", "67890", "Golden Fields");
        AddressProperty houseAddress1 = new AddressProperty("Suburb", "54321", "Green Street");
        AddressProperty houseAddress2 = new AddressProperty("Residential Area", "98765", "Sunset Boulevard");
        AddressProperty houseAddress3 = new AddressProperty("Urban Neighborhood", "12345", "Main Street");
        AddressProperty landAddress = new AddressProperty("Countryside", "54321", "Green Valley");

        Apartment apartment1 = new Apartment(APARTMENT, ID(), address1, "Modern Apartment with a View", 90.0,800000.0, FOR_SALE)
                .setBuildingDetails(5, 203, "City Heights", 2, 2010)
                .setAdditionalDetails(300.0, STUDIO);

        Apartment apartment2 = new Apartment(APARTMENT, ID(), address2, "Charming Parisian Loft", 110.5, 1200000.0, FOR_RENT)
                .setBuildingDetails(3, 102, "Le Chic Residence", 3, 1920)
                .setAdditionalDetails(400.0, LOFT);

        Apartment apartment3 = new Apartment(APARTMENT, ID(), address3, "Japanese Zen Style Apartment", 75.0, 700000.0, FOR_SALE)
                .setBuildingDetails(8, 801, "Sakura Towers", 1, 2015)
                .setAdditionalDetails(250.0, STANDARD);

        Farm farm1 = new Farm(FARM, ID(), farmAddress1,"Spacious Countryside Farm",500.0, 300000.0, FOR_SALE)
                .setBuildingDetails(300.0, 5, 2000, 10);

        Farm farm2 = new Farm(FARM, ID(), farmAddress2, "Scenic Rural Retreat", 700.0, 500000.0, FOR_RENT)
                .setBuildingDetails(400.0, 8, 1995, 20);

        House house1 = new House(HOUSE, ID(), houseAddress1, "Cozy Suburban Home", 150.0, 250000.0, FOR_RENT)
                .setBuildingDetails(120.0, 3, 2, 1995, 2);

        House house2 = new House(HOUSE, ID(), houseAddress2, "Spacious Residential House", 200.5, 350000.0, FOR_SALE)
                .setBuildingDetails(180.0, 4, 3, 2005, 3);

        House house3 = new House(HOUSE, ID(), houseAddress3, "Modern Urban Residence", 180.0, 300000.0, FOR_RENT)
                .setBuildingDetails(150.0, 3, 1, 2010, 2);

        Land land = new Land(LAND,ID(), landAddress, "Scenic Countryside Land", 1000.0, 50000.0, FOR_SALE)
                .setPropertyDetails(20.0, 50.0);

        apartment1.setOwner(person3);
        apartment2.setOwner(person1);
        apartment3.setOwner(person4);
        farm1.setOwner(person2);
        farm2.setOwner(person4);
        house1.setOwner(person1);
        house2.setOwner(person5);
        house3.setOwner(person4);
        land.setOwner(person3);

        person1.getPropertyList().add(apartment2);
        person1.getPropertyList().add(house1);
        person2.getPropertyList().add(farm1);
        person3.getPropertyList().add(apartment1);
        person3.getPropertyList().add(land);
        person4.getPropertyList().add(apartment3);
        person4.getPropertyList().add(farm2);
        person4.getPropertyList().add(house3);
        person5.getPropertyList().add(house2);

        propertyList.add(apartment1);
        propertyList.add(apartment2);
        propertyList.add(apartment3);
        propertyList.add(farm1);
        propertyList.add(farm2);
        propertyList.add(house1);
        propertyList.add(house2);
        propertyList.add(house3);
        propertyList.add(land);
    }

    public static void personsMenu(Person person) {
        List<String> personsOptions = List.of("NEGOTIATE", "PROPERTIES", "LOG OUT");
        while (true) {
            printMenu(personsOptions, STR."PERSONS MENU - Dear \{person.getPersonsName()}, please choose an option");
            switch (sc.nextLine()) {
                case "1" -> negotiateMenu(person);
                case "2" -> propertiesMenu(person);
                case "3" -> {System.out.println("Returning..."); personsLoginMenu();}
                default -> System.out.println("\nInvalid option!\n");
            }
        }
    }

    public static void propertiesMenu (Person person) {
        List<String> propertiesOptions = List.of("REGISTER PROPERTY", "REMOVE PROPERTY", "DISPLAY MY PROPERTIES", "BACK");

        while (true) {
            printMenu(propertiesOptions, STR."PROPERTIES MENU - Hello, dear \{person.getPersonsName()}, what do you want?");

            switch (sc.nextLine()) {
                case "1" -> registerPropertyMenu(person);
                case "2" -> {return;}
                case "3" -> person.displayPropertiesList();
                case "4" -> {System.out.println("Returning...\n"); personsMenu(person);}
            }
        }
    }

    public static void registerPropertyMenu (Person person) {
        List<String> typePropertyOptions = List.of("APARTMENT", "FARM", "HOUSE", "LAND", "BACK");
        List<Property> userPropertyList = new ArrayList<>();
        Property property = null;

        while (true) {
            printMenu(typePropertyOptions, "REGISTER PROPERTY MENU - Choose the property type.");
            switch (sc.nextLine()) {
                case "1" -> property = addApartment();
                case "2" ->  property = addFarm();
                case "3" -> property = addHouse();
                case "4" -> property = addLand();
                case "5" -> propertiesMenu (person);
                default -> System.out.println("Invalid option.");
            }

            System.out.println(STR."\{property.getTypeOfProperty()} successfully registered...");
            propertyList.add(property);
            property.setOwner(person);
            userPropertyList.add(property);
            person.addPropertiesList(userPropertyList);
        }
    }

    private static Apartment addApartment() {
        return new Apartment(APARTMENT, ID(), addAddressProperty(), addDescription(), addTotalArea(), addValue(), addSituation())
                .setBuildingDetails(addFloorApart(), addNumber(), addBuildingName(), addNumbOfRooms(), addYearBuilt())
                .setAdditionalDetails(addCondominiumValue(), addTypeOfApart());
    }

    private static Farm addFarm() {
        return new Farm(FARM, ID(), addAddressProperty(), addDescription(), addTotalArea(), addValue(), addSituation())
                .setBuildingDetails(addBuildingArea(), addNumbOfRooms(), addYearBuilt(), addDistanceOfCity());
    }

    private static House addHouse() {
        return new House(HOUSE, ID(), addAddressProperty(), addDescription(), addTotalArea(), addValue(), addSituation())
                .setBuildingDetails(addBuildingArea(), addNumbOfRooms(), addParkingSpaces(), addYearBuilt(), addNumbOfFloors());
    }

    private static Land addLand() {
        return new Land(LAND, ID(), addAddressProperty(), addDescription(), addTotalArea(), addValue(), addSituation())
                .setPropertyDetails(addFrontDimension(), addSideDimension());
    }
}
