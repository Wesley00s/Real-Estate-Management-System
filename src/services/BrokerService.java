package services;

import entities.properties.*;

import java.util.List;
import java.util.Scanner;

import static application.Main.mainMenu;
import static enumerations.TypeOfProperty.*;
import static services.PropertyService.*;
import static utility.MenuFormat.printMenu;
import static utility.utilProperties.PropertiesManager.seeProperties;

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
                    seeProperties(propertyList);
                }
                case "3" -> {
                    System.out.println("Returning...");
                    mainMenu();
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
