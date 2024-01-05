package services;

import entities.person.Person;
import entities.properties.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static enumerations.TypeOfProperty.*;
import static services.PersonService.personsLogin;
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

    public static void personsMenu(Person person) {
        List<String> personsOptions = List.of("NEGOTIATE", "PROPERTIES", "LOG OUT");
        while (true) {
            printMenu(personsOptions, "PERSONS MENU - Dear user, please, choose an option.");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> negotiate();
                case "2" -> propertiesMenu(person);
                case "3" -> {
                    System.out.println("Returning...");
                    apartmentList.clear();
                    farmList.clear();
                    houseList.clear();
                    landList.clear();
                    personsLogin();
                }
                default -> System.out.println("\nInvalid option!\n");
            }
        }
    }

    public static void propertiesMenu(Person person) {
        List<String> propertiesOptions = List.of("REGISTER PROPERTY", "REMOVE PROPERTY", "DISPLAY PROPERTIES", "BACK");

        while (true) {
            printMenu(propertiesOptions, "PROPERTIES MENU - Hello, dear owner, what do you want?");

            switch (sc.nextLine().toUpperCase()) {
                case "1" -> {
                    registerPropertyMenu(person);
                }
                case "2" -> {
                    return;
                }
                case "3" -> {
                    person.displayPropertiesList();
                }
                case "4" -> {
                    System.out.println("Returning...\n");
                    personsMenu(person);
                }
            }
        }
    }

    public static void registerPropertyMenu(Person person) {
        List<String> typePropertyOptions = List.of("APARTMENT", "FARM", "HOUSE", "LAND", "BACK");
        List<Apartment> personApartmentList = new ArrayList<>();
        List<Farm> personFarmList = new ArrayList<>();
        List<House> personHouseList = new ArrayList<>();
        List<Land> personLandList = new ArrayList<>();

        Apartment apartment;
        Farm farm;
        House house;
        Land land;

        while(true) {
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
                    propertiesMenu(person);
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
                .setBuildingDetails(addTotalArea(), addNumbOfRooms(), addYearBuilt(), addDistanceOfCity());
    }

    private static House addHouse() {
        return new House(HOUSE, ID(), addAddressProperty(), addDescription(), addTotalArea(), addValue(), addSituation())
                .setBuildingDetails(addTotalArea(), addNumbOfRooms(), addParkingSpaces(), addYearBuilt(), addNumbOfFloors());
    }

    private static Land addLand() {
        return new Land(LAND, ID(), addAddressProperty(), addDescription(), addTotalArea(), addValue(), addSituation())
                .setPropertyDetails(addFrontDimension(), addSideDimension());
    }
}
