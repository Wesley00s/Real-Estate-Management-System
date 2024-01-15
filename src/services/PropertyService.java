package services;

import entities.BuyRequest;
import entities.RentRequest;
import entities.person.*;
import entities.properties.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static database.connection.Connect.*;
import static enumerations.Status.*;
import static enumerations.TypeOfProperty.*;
import static enumerations.TypeRequest.BUY_REQUEST;
import static enumerations.TypeRequest.RENT_REQUEST;

import static services.PersonService.personsMenu;
import static utility.GenerateID.ID;
import static utility.MenuFormat.printMenu;
import static utility.utilProperties.PropertiesManager.*;

public class PropertyService {
    private static final Scanner sc = new Scanner(System.in);
    public static List<Property> propertyList = new ArrayList<>();
    public static List<Property> historyBuyPropertyList = new ArrayList<>();
    public static List<Property> historyRentPropertyList = new ArrayList<>();
    public static List<Person> personsList = new ArrayList<>();
    public static List<BuyRequest> buyRequestList = new ArrayList<>();
    public static List<RentRequest> rentRequestList = new ArrayList<>();

    protected static void negotiateMenu(Person person) {
        List<String> personsOptions = List.of("BUY", "RENT", "BACK");

        while (true) {
            printMenu(personsOptions, STR."NEGOTIATION MENU - Honorable \{person.getPersonsName()}, what you want?");
            switch (sc.nextLine()) {
                case "1" -> buyPropertyMenu(person);
                case "2" -> rentPropertyMenu(person);
                case "3" -> {System.out.println("Returning..."); personsMenu(person);}
                default -> System.out.println("\nInvalid option!\n");
            }
        }
    }

    private static void buyPropertyMenu(Person person) {
        List<String> buyPropertyMenuOptions = List.of("SEE AVAILABLE PROPERTIES", "BUY PROPERTY", "BACK");
        List<Property> propertyListOfNonLoggedPersons = propertyList;
        if (person.getPropertyList() != null)
            propertyListOfNonLoggedPersons.removeAll(person.getPropertyList());

        while (true) {
            printMenu(buyPropertyMenuOptions, "BUY PROPERTY MENU - What do you order");
            switch (sc.nextLine()) {
                case "1" -> seeProperties(propertyListOfNonLoggedPersons);
                case "2" -> buyProperty(person);
                case "3" -> {
                    System.out.println("Returning...");
                    negotiateMenu(person);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void rentPropertyMenu(Person person) {
        List<String> buyPropertyMenuOptions = List.of("SEE AVAILABLE PROPERTIES", "RENT PROPERTY", "BACK");
        List<Property> propertyListOfNonLoggedPersons = propertyList;
        if (person.getPropertyList() != null)
            propertyListOfNonLoggedPersons.removeAll(person.getPropertyList());

        while (true) {
            printMenu(buyPropertyMenuOptions, "RENT PROPERTY MENU - What do you order");
            switch (sc.nextLine()) {
                case "1" -> seeProperties(propertyListOfNonLoggedPersons);
                case "2" -> rentProperty(person);
                case "3" -> {
                    System.out.println("Returning...");
                    negotiateMenu(person);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void rentProperty(Person renter) {
        System.out.println("\n\t\t* RENT PROPERTY");
        if (propertyList.isEmpty()) {
            System.out.println("The properties list is empty.");
            return;
        }

        List<Property> propertyListOfNonLoggedPersons = propertyList;
        Property propertyToBeRent;
        if (renter.getPropertyList() != null) {
            propertyListOfNonLoggedPersons.removeAll(renter.getPropertyList());
            propertyToBeRent = searchProperty(propertyListOfNonLoggedPersons);
        } else {
            propertyToBeRent = searchProperty(propertyList);
        }
        if (propertyToBeRent != null) {
            if (!propertyToBeRent.getStatus().equals(FOR_RENT) && !propertyToBeRent.getStatus().equals(SALE_OR_RENT)) {
                System.out.println("Operation not permitted, check if the property is for rent");
                return;
            }

            Person owner = propertyToBeRent.getOwner();
            if (owner != null) {
                owner.getPropertyList().remove(propertyToBeRent);
            } else {
                System.out.println("Undefined owner.");
                return;
            }

            boolean requestOccurrence = false;
            for (RentRequest rentRequest : rentRequestList) {
                if (rentRequest.getProperty().equals(propertyToBeRent) && rentRequest.getNewOwner().equals(renter)) {
                    System.out.println("You have already made the request.");
                    requestOccurrence = true;
                }
            }
            if (!requestOccurrence) {
                rentRequestList.add(new RentRequest(STR."R-\{ID()}T", propertyToBeRent, renter, owner, RENT_REQUEST));
                System.out.println("The request has been sent.");
            }

        } else {
            System.out.println("No available properties for purchase.");
        }
    }      
 
    private static void buyProperty(Person newOwner) {
        System.out.println("\n\t\t* BUY PROPERTY");
        if (propertyList.isEmpty()) {
            System.out.println("The properties list is empty.");
            return;
        }

        List<Property> propertyListOfNonLoggedPersons = propertyList;
        Property propertyToBePurchase;
        if (newOwner.getPropertyList() != null) {
            propertyListOfNonLoggedPersons.removeAll(newOwner.getPropertyList());
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
            boolean requestOccurrence = false;
            for (BuyRequest buyRequest : buyRequestList) {
                if (buyRequest.getProperty().equals(propertyToBePurchase) && buyRequest.getNewOwner().equals(newOwner)) {
                    System.out.println("You have already made the request.");
                    requestOccurrence = true;
                }
            }
            if (!requestOccurrence) {
                buyRequestList.add(new BuyRequest(STR."R-\{ID()}T", propertyToBePurchase, newOwner, oldOwner, BUY_REQUEST));
                System.out.println("The request has been sent.");
            }
        } else {
            System.out.println("No available properties for purchase.");
        }
    }

    public static void propertiesMenu (Person person) {
        List<String> propertiesOptions = List.of("REGISTER PROPERTY", "REMOVE PROPERTY", "DISPLAY MY PROPERTIES", "DISPLAY MY RENTED PROPERTIES", "BACK");

        while (true) {
            printMenu(propertiesOptions, STR."PROPERTIES MENU - Hello, dear \{person.getPersonsName()}, what do you want?");

            switch (sc.nextLine()) {
                case "1" -> registerPropertyMenu(person);
                case "2" -> removeProperty(person);
                case "3" -> person.displayPropertiesList();
                case "4" -> person.displayPropertiesRentList();
                case "5" -> {System.out.println("Returning...\n"); personsMenu(person);}
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
                case "5" -> {
                    System.out.println("Returning...");
                    propertiesMenu(person);
                }
                default -> System.out.println("Invalid option.");
            }
            AddressProperty addressProperty = addAddressProperty();
            assert property != null;
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
        return new Apartment(APARTMENT, STR."P-\{ID()}Y", addDescription(), addTotalArea(), addPrice(), addRentValue(), addSituation())
                .setBuildingDetails(addFloorApart(), addNumber(), addBuildingName(), addNumbOfRooms(), addYearBuilt())
                .setAdditionalDetails(addCondominiumValue(), addTypeOfApart());
    }

    private static Farm addFarm() {
        return new Farm(FARM, STR."P-\{ID()}Y", addDescription(), addTotalArea(), addPrice(), addRentValue(), addSituation())
                .setBuildingDetails(addBuildingArea(), addNumbOfRooms(), addYearBuilt(), addDistanceOfCity());
    }

    private static House addHouse() {
        return new House(HOUSE, STR."P-\{ID()}Y", addDescription(), addTotalArea(), addPrice(), addRentValue(), addSituation())
                .setBuildingDetails(addBuildingArea(), addNumbOfRooms(), addParkingSpaces(), addYearBuilt(), addNumbOfFloors());
    }

    private static Land addLand() {
        return new Land(LAND, STR."P-\{ID()}Y", addDescription(), addPrice(), addRentValue(), addSituation())
                .setPropertyDetails(addFrontDimension(), addSideDimension());
    }
}
