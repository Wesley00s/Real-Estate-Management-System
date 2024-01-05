package services;

import java.util.List;
import java.util.Scanner;

import static services.PropertyService.*;
import static utility.MenuFormat.printMenu;

public class PersonService {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<String> personsOptions = List.of("NEGOTIATE", "PROPERTIES", "EXIT");

    public static void personsMenu() {
        while (true) {
            printMenu(personsOptions, "PERSONS MENU - Dear user, please, choose an option.");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> negotiate();
                case "2" -> propertiesMenu();
                case "3" -> {System.out.println("Exiting..."); System.exit(0);}
                default -> System.out.println("\nInvalid option!\n");
            }
        }
    }
}
