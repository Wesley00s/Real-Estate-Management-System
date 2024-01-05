package application;

import java.util.List;
import java.util.Scanner;

import static services.BrokerService.brokerMenu;
import static services.PersonService.addNaturalPersons;
import static services.PersonService.personsLogin;
import static services.PropertyService.addProperties;
import static utility.MenuFormat.printMenu;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void mainMenu() {
        List<String> options = List.of("USER", "BROKER", "EXIT");

        while (true) {
            printMenu(options, "MAIN MENU - Hello dear user, what type of services are you involved?");
            switch (sc.nextLine()) {
                case "1" -> {
                    personsLogin();
                }
                case "2" -> {
                    brokerMenu();
                }
                case "3" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Ivalid option.");
            }
        }
    }
    public static void main(String[] args) {
        addNaturalPersons();
        addProperties();
        mainMenu();
    }
}