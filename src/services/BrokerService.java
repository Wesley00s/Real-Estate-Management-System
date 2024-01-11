package services;

import entities.properties.*;

import java.util.List;
import java.util.Scanner;

import static application.Main.mainMenu;
import static enumerations.TypeOfProperty.*;
import static services.PropertyService.*;
import static utility.MenuFormat.printMenu;
import static utility.utilBroker.BrokerManager.seeListOfOwners;
import static utility.utilProperties.PropertiesManager.seeProperties;

public class BrokerService {
    private static final Scanner sc = new Scanner(System.in);

    public static void brokerMenu() {
        List<String> brokerOptions = List.of("SEE REQUESTS", "SEE PROPERTIES", "SEE PROPERTIES TRANSACTIONS HISTORY", "SEE LIST OF OWNERS", "BACK");

        while (true) {
            printMenu(brokerOptions, "BROKER MENU - Hello fellow broker, what you want today?");
            switch (sc.nextLine()) {
                case "1" -> seeRequests();

                case "2" -> seeProperties(propertyList);
                case "3" -> seePropertiesTransactionsHistory();
                case "4" -> seeListOfOwners();
                case "5" -> {
                    System.out.println("Returning...");
                    mainMenu();
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void seeRequests(){};
    private static void seePropertiesTransactionsHistory(){
        System.out.println("\n* APARTMENTS");
        for(Property apartment : historyPropertyList) {
            if (apartment.getTypeOfProperty().equals(APARTMENT))
                System.out.println(apartment);
        }

        System.out.println("\n* FARMS");
        for(Property farm : historyPropertyList) {
            if (farm.getTypeOfProperty().equals(FARM))
                System.out.println(farm);
        }

        System.out.println("\n* HOUSES");
        for(Property house : historyPropertyList) {
            if (house.getTypeOfProperty().equals(HOUSE))
                System.out.println(house);
        }

        System.out.println("\n* LANDS");
        for(Property land : historyPropertyList) {
            if (land.getTypeOfProperty().equals(LAND))
                System.out.println(land);
        }    }
}
