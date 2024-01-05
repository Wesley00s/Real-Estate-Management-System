package services;

import entities.person.LegalPerson;
import entities.person.NaturalPerson;
import entities.person.Person;
import entities.properties.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static services.PropertyService.propertiesMenu;
import static services.PropertyService.registerPropertyMenu;
import static utility.Attempts.TOTAL_ATTEMPTS;
import static utility.Attempts.chances;
import static utility.MenuFormat.printMenu;
import static utility.utilPersons.PersonsManager.*;

public class PersonService {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<NaturalPerson> naturalPersonList = new ArrayList<>();
    private static final List<LegalPerson> legalPersonList = new ArrayList<>();

    public static void personsLogin() {
        List<String> personOptions = List.of("NATURAL PERSON", "LEGAL PERSON", "EXIT");

        while (true) {
            printMenu(personOptions, "PERSONS MENU - Der user, what kind of person are you?");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> {
                    naturalPersonsMenu();
                }
                case "2" -> {
//                    legalPersonsMenu();
                }
                case "3" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        }
    }

    public static void naturalPersonsMenu() {
        List<String> personOptions = List.of("I'M ALREADY REGISTERED", "I'M NOT REGISTERED YET", "CANCEL");

        while (true) {
            printMenu(personOptions, "NATURAL PERSONS MENU - Der user, are you already registered?");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> {
                    singInNaturalPersons();
                }
                case "2" -> {
                    naturalPersonLogin();
                }
                case "3" -> {
                    System.out.println("Cancelling...");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void singInNaturalPersons() {
        String name;
        String password;
        boolean findPerson = false;

        int attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) singInNaturalPersons();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide your name:");
            name = sc.nextLine();

        } while (name.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) singInNaturalPersons();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide your passwaord:");
            password = sc.nextLine();

        } while (password.trim().isEmpty());

        for(NaturalPerson naturalPerson : naturalPersonList) {
            if(naturalPerson.getPersonsName().equals(name) && naturalPerson.getPassword().equals(password)) {
                propertiesMenu(naturalPerson);
                findPerson = true;
            }
        }
        if(!findPerson) {
            System.out.println("Inválid name and password combination.");
        }
    }

    private static void legalPersonLogin() {
        legalPersonList.add(addLegalPerson());
    }

    private static void naturalPersonLogin() {
        NaturalPerson naturalPerson = addNaturalPerson();
        naturalPersonList.add(naturalPerson);
        propertiesMenu(naturalPerson);
    }

    public static NaturalPerson addNaturalPerson() {
        return new NaturalPerson(addPersonsName(), addPersonsAddress(), addPersonsContact(), addPassword(), addSsn());
    }

    public static LegalPerson addLegalPerson() {
        return new LegalPerson(addPersonsName(), addPersonsAddress(), addPersonsContact(), addPassword(), addEin());
    }
}
