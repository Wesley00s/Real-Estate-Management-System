package services;

import entities.person.Person;
import entities.properties.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static enumerations.Situation.*;
import static enumerations.TypeOfApart.*;
import static enumerations.TypeOfProperty.*;
import static services.PersonService.personsLoginMenu;
import static utility.GenerateID.ID;
import static utility.MenuFormat.printMenu;
import static utility.utilProperties.PropertiesManager.*;

public class PropertyService {
    private static final Scanner sc = new Scanner(System.in);
    public static List<Apartment> apartmentList = new ArrayList<>();
    public static List<Farm> farmList = new ArrayList<>();
    public static List<House> houseList = new ArrayList<>();
    public static List<Land> landList = new ArrayList<>();

    protected static void negotiate() {

    }

    public static void addProperties() {
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

        apartmentList.add(apartment1);
        apartmentList.add(apartment2);
        apartmentList.add(apartment3);
        farmList.add(farm1);
        farmList.add(farm2);
        houseList.add(house1);
        houseList.add(house2);
        houseList.add(house3);
        landList.add(land);
    }

    public static void personsMenu(Person person) {
        List<String> personsOptions = List.of("NEGOTIATE", "PROPERTIES", "LOG OUT");
        while (true) {
            printMenu(personsOptions, "PERSONS MENU - Dear user, please, choose an option.");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> negotiate();
                case "2" -> propertiesMenu(person);
                case "3" -> {System.out.println("Returning..."); personsLoginMenu();}
                default -> System.out.println("\nInvalid option!\n");
            }
        }
    }

    public static void propertiesMenu (Person person) {
        List<String> propertiesOptions = List.of("REGISTER PROPERTY", "REMOVE PROPERTY", "DISPLAY PROPERTIES", "BACK");

        while (true) {
            printMenu(propertiesOptions, STR."PROPERTIES MENU - Hello, dear \{person.getPersonsName()}, what do you want?");

            switch (sc.nextLine().toUpperCase()) {
                case "1" -> registerPropertyMenu(person);
                case "2" -> {return;}
                case "3" -> person.displayPropertiesList();
                case "4" -> {System.out.println("Returning...\n"); personsMenu(person);}
            }
        }
    }

    public static void registerPropertyMenu (Person person) {
        List<String> typePropertyOptions = List.of("APARTMENT", "FARM", "HOUSE", "LAND", "BACK");
        List<Apartment> personApartmentList = new ArrayList<>();
        List<Farm> personFarmList = new ArrayList<>();
        List<House> personHouseList = new ArrayList<>();
        List<Land> personLandList = new ArrayList<>();

        Apartment apartment;
        Farm farm;
        House house;
        Land land;

        while (true) {
            printMenu(typePropertyOptions, "REGISTER PROPERTY MENU - Choose the property type.");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> {
                    apartment = addApartment();
                    apartmentList.add(apartment);
                    personApartmentList.add(apartment);

                    System.out.println("Apartment successfully registered...");
                }
                case "2" -> {
                    farm = addFarm();
                    farmList.add(farm);
                    personFarmList.add(farm);

                    System.out.println("Farm successfully registered...");
                }
                case "3" -> {
                    house = addHouse();
                    houseList.add(house);
                    personHouseList.add(house);

                    System.out.println("House successfully registered...");
                }
                case "4" -> {
                    land = addLand();
                    landList.add(land);
                    personLandList.add(land);

                    System.out.println("Land successfully registered...");
                }
                case "5" -> {
                    propertiesMenu (person);
                }
                default -> System.out.println("Invalid option.");
            }
            person.addPropertiesList(personApartmentList, personFarmList, personHouseList, personLandList);
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
