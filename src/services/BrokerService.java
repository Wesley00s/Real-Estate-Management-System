package services;

import entities.properties.Apartment;
import entities.properties.Farm;
import entities.properties.House;
import entities.properties.Land;

import java.util.List;
import java.util.Scanner;

import static application.Main.mainMenu;
import static services.PropertyService.*;
import static utility.MenuFormat.printMenu;

public class BrokerService {
    private static final Scanner sc = new Scanner(System.in);

    public static void brokerMenu() {
        List<String> brokerOptions = List.of("SEE REQUESTS", "SEE PROPERTIES","BACK");

        while (true) {
            printMenu(brokerOptions, "BROKER MENU - Hello fellow broker, what you want today?");
            switch (sc.nextLine()) {
                case "1" -> {
//                seeRequests();
                }
                case "2" -> {
                    seeProperties();
                }
                case "3" -> {
                    System.out.println("Returning...");
                    mainMenu();
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
    public static void seeProperties() {
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
