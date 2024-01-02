package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utility.MenuFormat.printMenu;

public class PersonService {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<String> personsOptions =
            new ArrayList<>(List.of("NEGOTIATE", "PROPERTIES", "EXIT"));
    public static void personsMenu() {
        while (true) {
            printMenu(personsOptions, "PERSONS MENU - Dear user, please, choose an option.");
            switch (sc.nextLine().toUpperCase()) {
                case "N" -> negotiate();
                case "P" -> properties();
                case "E" -> System.exit(0);
                default -> System.out.println("\nInvalid option!\n");
            }
        }
    }

    private static void negotiate() {

    }

    private static void properties () {

    }
}
