package services;

import entities.Request;
import entities.person.*;
import entities.properties.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static database.connection.Connect.*;
import static enumerations.Status.*;
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
    public static List<Request> requestList = new ArrayList<>();

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
                case "3" -> rentProperty(person);
                case "4" -> {System.out.println("Returning..."); personsMenu(person);}
                default -> System.out.println("\nInvalid option!\n");
            }
        }
    }

    private static void rentProperty(Person person) {
        System.out.println("\n\t\t* RENT PROPERTY");
        if (propertyList.isEmpty()) {
            System.out.println("The properties list is empty.");
            return;
        }

        List<Property> propertyListOfNonLoggedPersons = propertyList;
        Property propertyToBeRent;
        if (person.getPropertyList() != null) {
            propertyListOfNonLoggedPersons.removeAll(person.getPropertyList());
            propertyToBeRent = searchProperty(propertyListOfNonLoggedPersons);
        } else {
            propertyToBeRent = searchProperty(propertyList);
        }
        if (propertyToBeRent != null) {
            if (!propertyToBeRent.getStatus().equals(FOR_RENT) && !propertyToBeRent.getStatus().equals(SALE_OR_RENT)) {
                System.out.println("Operation not permitted, check if the property is for rent");
                return;
            }

            Person oldOwner = propertyToBeRent.getOwner();
            if (oldOwner != null) {
                oldOwner.getPropertyList().remove(propertyToBeRent);
            } else {
                System.out.println("Undefined owner.");
                return;
            }

            propertyToBeRent.setStatus(RENTED);
            historyPropertyList.add(propertyToBeRent);
            System.out.println("Property rented successfully!");
        } else {
            System.out.println("No available properties for purchase.");
        }
    }

    private static void buyProperty(Person person) {
        System.out.println("\n\t\t* BUY PROPERTY");
        if (propertyList.isEmpty()) {
            System.out.println("The properties list is empty.");
            return;
        }

        List<Property> propertyListOfNonLoggedPersons = propertyList;
        Property propertyToBePurchase;
        if (person.getPropertyList() != null) {
            propertyListOfNonLoggedPersons.removeAll(person.getPropertyList());
            propertyToBePurchase = searchProperty(propertyListOfNonLoggedPersons);
        } else {
            propertyToBePurchase = searchProperty(propertyList);
        }
        if (propertyToBePurchase != null) {
            if (!propertyToBePurchase.getStatus().equals(FOR_SALE) && !propertyToBePurchase.getStatus().equals(SALE_OR_RENT)) {
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
            requestList.add(new Request(STR."S-\{ID()}N", propertyToBePurchase, person, oldOwner));
            System.out.println("The request has been sent.");
//            propertyToBePurchase.setStatus(SOLD);
//            person.getPropertyList().add(propertyToBePurchase);
//            oldOwner.getPropertyList().remove(propertyToBePurchase);
//            historyPropertyList.add(propertyToBePurchase);
//            updateSqlProperty(person, oldOwner, propertyToBePurchase);
//            System.out.println("Property purchased successfully!");

        } else {
            System.out.println("No available properties for purchase.");
        }
    }

//    public static void addPropertiesAndOwners() {
//        Address addressP1 = new Address("A-550595055S", "New York", "10001", "Manhattan", "Broadway Street", 123);
//        Contact contact1 = new Contact("C-686286334T", "person1@email.com", "987-654-3210");
//        NaturalPerson person1 = new NaturalPerson(NATURAL_PERSON, "John Smith", addressP1, contact1, "password123", 987654321);
//
//        Address addressP2 = new Address("A-902065973S", "London", "78454", "Westminster", "Buckingham Palace Road", 456);
//        Contact contact2 = new Contact("C-458881639T", "person2@email.com", "123-456-7890");
//        NaturalPerson person2 = new NaturalPerson(NATURAL_PERSON, "Emma Johnson", addressP2, contact2, "securePassword", 876543210);
//
//        Address addressP3 = new Address("A-999562001S", "Paris", "75001", "Le Marais", "Rue Saint-Antoine", 789);
//        Contact contact3 = new Contact("C-732595323T", "person3@email.com", "234-567-8901");
//        NaturalPerson person3 = new NaturalPerson(NATURAL_PERSON, "Pierre Dubois", addressP3, contact3, "parisPassword123", 654321098);
//
//        Address addressP4 = new Address("A-120902663S", "Tokyo", "10001", "Chiyoda", "Chiyoda City", 101);
//        Contact contact4 = new Contact("C-339533394T", "person4@email.com", "345-678-9012");
//        LegalPerson person4 = new LegalPerson(LEGAL_PERSON, "Yuki Tanaka", addressP4, contact4, "tokyoSecure456", 123456789);
//
//        Address address5 = new Address("A-250098752S", "Sydney", "20008", "Darling Harbour", "George Street", 222);
//        Contact contact5 = new Contact("C-612073344T", "person5@email.com", "456-789-0123");
//        LegalPerson person5 = new LegalPerson(LEGAL_PERSON, "Olivia Davis", address5, contact5, "sydneyPassword789", 258123895);
//
//        personsList.add(person1);
//        personsList.add(person2);
//        personsList.add(person3);
//        personsList.add(person4);
//        personsList.add(person5);
//
//        AddressProperty address1 = new AddressProperty("AP-220811563S", "Los Angeles", "90001", "Hollywood");
//        AddressProperty address2 = new AddressProperty("AP-966934057S", "Paris", "75002", "Le Marais");
//        AddressProperty address3 = new AddressProperty("AP-637089832S", "Tokyo", "54899", "Chuo");
//        AddressProperty farmAddress1 = new AddressProperty("AP-516422294S", "Countryside", "12345", "Green Valley");
//        AddressProperty farmAddress2 = new AddressProperty("AP-888799197S", "Rural Area", "67890", "Golden Fields");
//        AddressProperty houseAddress1 = new AddressProperty("AP-180057299S", "Suburb", "54321", "Green Street");
//        AddressProperty houseAddress2 = new AddressProperty("AP-304329455S", "Residential Area", "98765", "Sunset Boulevard");
//        AddressProperty houseAddress3 = new AddressProperty("AP-858083414S", "Urban Neighborhood", "12345", "Main Street");
//        AddressProperty landAddress = new AddressProperty("AP-184456367S", "Countryside", "54321", "Green Valley");
//
//        Apartment apartment1 = new Apartment(APARTMENT, "P-762199459Y", address1, "Modern Apartment with a View", 90.0,800000.0, FOR_SALE)
//                .setBuildingDetails(5, 203, "City Heights", 2, 2010)
//                .setAdditionalDetails(300.0, STUDIO);
//
//        Apartment apartment2 = new Apartment(APARTMENT, "P-574301360Y", address2, "Charming Parisian Loft", 110.5, 1200000.0, FOR_RENT)
//                .setBuildingDetails(3, 102, "Le Chic Residence", 3, 1920)
//                .setAdditionalDetails(400.0, LOFT);
//
//        Apartment apartment3 = new Apartment(APARTMENT, "P-602199922Y", address3, "Japanese Zen Style Apartment", 75.0, 700000.0, FOR_SALE)
//                .setBuildingDetails(8, 801, "Sakura Towers", 1, 2015)
//                .setAdditionalDetails(250.0, STANDARD);
//
//        Farm farm1 = new Farm(FARM, "P-932849881Y", farmAddress1,"Spacious Countryside Farm",500.0, 300000.0, FOR_SALE)
//                .setBuildingDetails(300.0, 5, 2000, 10);
//
//        Farm farm2 = new Farm(FARM, "P-149235343Y", farmAddress2, "Scenic Rural Retreat", 700.0, 500000.0, FOR_RENT)
//                .setBuildingDetails(400.0, 8, 1995, 20);
//
//        House house1 = new House(HOUSE, "P-856300893Y", houseAddress1, "Cozy Suburban Home", 150.0, 250000.0, FOR_RENT)
//                .setBuildingDetails(120.0, 3, 2, 1995, 2);
//
//        House house2 = new House(HOUSE, "P-795346968Y", houseAddress2, "Spacious Residential House", 200.5, 350000.0, FOR_SALE)
//                .setBuildingDetails(180.0, 4, 3, 2005, 3);
//
//        House house3 = new House(HOUSE, "P-235566080Y", houseAddress3, "Modern Urban Residence", 180.0, 300000.0, FOR_RENT)
//                .setBuildingDetails(150.0, 3, 1, 2010, 2);
//
//        Land land = new Land(LAND,"P-103375631Y", landAddress, "Scenic Countryside Land", 1000.0, 50000.0, FOR_SALE)
//                .setPropertyDetails(20.0, 50.0);
//
//        apartment1.setOwner(person3);
//        apartment2.setOwner(person1);
//        apartment3.setOwner(person4);
//        farm1.setOwner(person2);
//        farm2.setOwner(person4);
//        house1.setOwner(person1);
//        house2.setOwner(person5);
//        house3.setOwner(person4);
//        land.setOwner(person3);
//
//        person1.getPropertyList().add(apartment2);
//        person1.getPropertyList().add(house1);
//        person2.getPropertyList().add(farm1);
//        person3.getPropertyList().add(apartment1);
//        person3.getPropertyList().add(land);
//        person4.getPropertyList().add(apartment3);
//        person4.getPropertyList().add(farm2);
//        person4.getPropertyList().add(house3);
//        person5.getPropertyList().add(house2);
//
//        propertyList.add(apartment1);
//        propertyList.add(apartment2);
//        propertyList.add(apartment3);
//        propertyList.add(farm1);
//        propertyList.add(farm2);
//        propertyList.add(house1);
//        propertyList.add(house2);
//        propertyList.add(house3);
//        propertyList.add(land);
//    }

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
                case "2" -> removeProperty(person);
                case "3" -> person.displayPropertiesList();
                case "4" -> {System.out.println("Returning...\n"); personsMenu(person);}
                default -> System.out.println("Invalid option");
            }
        }
    }

    public static void removeProperty(Person person) {
        System.out.println("\n\t\t* REMOVE PROPERTY");
        String propertyID;
        boolean findProperty = false;
        System.out.println("Enter the ID of the property to be removed:");
        propertyID = sc.nextLine();

        if (person.getPropertyList().isEmpty()) {
            System.out.println("Property list is empty.");
            return;
        }

        Iterator<Property> iterator = person.getPropertyList().iterator();
        while (iterator.hasNext()) {
            Property property = iterator.next();
            if (property.getId().equals(propertyID)) {
                iterator.remove();
                System.out.println(STR."\{property.getTypeOfProperty()} removed.");
                removeSqlPropertyData(propertyID);
                findProperty = true;
            }
        }

        if (!findProperty) {
            System.out.println("Property not found.\n");
        }
    }

    public static void registerPropertyMenu (Person person) {
        List<String> typePropertyOptions = List.of("APARTMENT", "FARM", "HOUSE", "LAND", "BACK");
        Property property = null;

        while (true) {
            printMenu(typePropertyOptions, "REGISTER PROPERTY MENU - Choose the property type.");
            switch (sc.nextLine()) {
                case "1" -> property = addApartment();
                case "2" ->  property = addFarm();
                case "3" -> property = addHouse();
                case "4" -> property = addLand();
                case "5" -> propertiesMenu(person);
                default -> System.out.println("Invalid option.");
            }
            AddressProperty addressProperty = addAddressProperty();
            property.setAddress(addressProperty);

            System.out.println(STR."\{property.getTypeOfProperty()} successfully registered...");
            propertyList.add(property);
            property.setOwner(person);
            person.getPropertyList().add(property);
            setSqlPropertyData(person, property, addressProperty);
            propertiesMenu(person);
        }
    }

    private static Apartment addApartment() {
        return new Apartment(APARTMENT, STR."P-\{ID()}Y", addDescription(), addTotalArea(), addPrice(), addSituation())
                .setBuildingDetails(addFloorApart(), addNumber(), addBuildingName(), addNumbOfRooms(), addYearBuilt())
                .setAdditionalDetails(addCondominiumValue(), addTypeOfApart());
    }

    private static Farm addFarm() {
        return new Farm(FARM, STR."P-\{ID()}Y", addDescription(), addTotalArea(), addPrice(), addSituation())
                .setBuildingDetails(addBuildingArea(), addNumbOfRooms(), addYearBuilt(), addDistanceOfCity());
    }

    private static House addHouse() {
        return new House(HOUSE, STR."P-\{ID()}Y", addDescription(), addTotalArea(), addPrice(), addSituation())
                .setBuildingDetails(addBuildingArea(), addNumbOfRooms(), addParkingSpaces(), addYearBuilt(), addNumbOfFloors());
    }

    private static Land addLand() {
        return new Land(LAND, STR."P-\{ID()}Y", addDescription(), addPrice(), addSituation())
                .setPropertyDetails(addFrontDimension(), addSideDimension());
    }
}
