package application;

import java.util.List;
import java.util.Scanner;

import static database.connection.Connect.getSqlPropertyData;
import static services.BrokerService.*;
import static services.PersonService.personsLoginMenu;
import static utility.MenuFormat.printMenu;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void mainMenu() {
        List<String> options = List.of("USER", "BROKER", "EXIT");

        while (true) {
            printMenu(options, "MAIN MENU - Hello dear user, what type of services are you involved?");
            switch (sc.nextLine()) {
                case "1" -> personsLoginMenu();
                case "2" -> brokerLogin();
                case "3" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
    public static void main(String[] args) {
        addMainBroker();
        getSqlPropertyData();
        mainMenu();
    }
}