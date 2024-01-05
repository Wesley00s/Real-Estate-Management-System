package services;

import entities.properties.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static enumerations.TypeOfProperty.*;
import static services.PersonService.personsMenu;
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

    public static void propertiesMenu() {
        List<String> propertiesOptions = List.of("REGISTER PROPERTY", "REMOVE PROPERTY", "DISPLAY PROPERTIES", "BACK");

        while (true) {
            printMenu(propertiesOptions, "PROPERTIES MENU - Hello, dear owner, what do you want?");

            switch (sc.nextLine().toUpperCase()) {
                case "1" -> {
                    registerPropertyMenu();
                }
                case "2" -> {
                    return;
                }
                case "3" -> {
                    displayProperties();
                }
                case "4" -> {
                    System.out.println("Returning...\n");
                    personsMenu();
                }
            }
        }
    }

    public static void registerPropertyMenu() {
        List<String> typePropertyOptions = List.of("APARTMENT", "FARM", "HOUSE", "LAND", "BACK");

        while(true) {
            printMenu(typePropertyOptions, "REGISTER PROPERTY MENU - Choose the property type.");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> {
                    apartmentList.add(addApartment());
                    System.out.println("Apartment successfully registered...");
                }
                case "2" -> {
                    farmList.add(addFarm());
                    System.out.println("Farm successfully registered...");
                }
                case "3" -> {
                    houseList.add(addHouse());
                    System.out.println("House successfully registered...");
                }
                case "4" -> {
                    landList.add(addLand());
                    System.out.println("Land successfully registered...");
                }
                case "5" -> {
                    propertiesMenu();
                }
                default -> System.out.println("Invalid option.");
            }
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

    private static void displayProperties() {
        System.out.println("\n* APARTMENTS");
        for(Apartment apartment : apartmentList) {
            System.out.println(apartment);
        }

        System.out.println("\n* FARMS");
        for(Farm farm : farmList) {
            System.out.println(farm);
        }

        System.out.println("\n* HOUSES");
        for(House house : houseList) {
            System.out.println(house);
        }

        System.out.println("\n* LANDS");
        for(Land land : landList) {
            System.out.println(land);
        }
    }
}
