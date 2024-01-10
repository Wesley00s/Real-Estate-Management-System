package services;

import entities.person.*;
import enumerations.PersonType;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static application.Main.mainMenu;
import static enumerations.PersonType.LEGAL_PERSON;
import static enumerations.PersonType.NATURAL_PERSON;
import static services.PropertyService.personsList;
import static services.PropertyService.personsMenu;
import static utility.Attempts.TOTAL_ATTEMPTS;
import static utility.Attempts.chances;
import static utility.MenuFormat.printMenu;
import static utility.utilPersons.PersonsManager.*;

public class PersonService {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<NaturalPerson> naturalPersonList = new ArrayList<>();
    private static final List<LegalPerson> legalPersonList = new ArrayList<>();

    public static void personsLoginMenu() {
        List<String> personOptions = List.of("NATURAL PERSON", "LEGAL PERSON", "BACK");

        while (true) {
            printMenu(personOptions, "PERSONS MENU - Der user, what kind of person are you?");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> naturalPersonsMenu();
                case "2" -> legalPersonsMenu();
                case "3" -> {System.out.println("Returning..."); mainMenu();}
            }
        }
    }

    public static void naturalPersonsMenu() {
        List<String> personOptions = List.of("I'M ALREADY REGISTERED", "I'M NOT REGISTERED YET", "CANCEL");

        while (true) {
            printMenu(personOptions, "NATURAL PERSONS MENU - Der user, are you already registered?");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> singInNaturalPersons();
                case "2" -> personLogin(NaturalPerson.class);
                case "3" -> {System.out.println("Cancelling..."); personsLoginMenu();}
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void legalPersonsMenu() {
        List<String> personOptions = List.of("I'M ALREADY REGISTERED", "I'M NOT REGISTERED YET", "CANCEL");

        while (true) {
            printMenu(personOptions, "LEGAL PERSONS MENU - Der user, are you already registered?");
            switch (sc.nextLine().toUpperCase()) {
                case "1" -> singInLegalPersons();
                case "2" -> personLogin(LegalPerson.class);
                case "3" -> {System.out.println("Cancelling..."); personsLoginMenu();}
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
            if(chances(attempts--)) return;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide your name:");
            name = sc.nextLine();
        } while (name.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) return;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide your passwaord:");
            password = sc.nextLine();
        } while (password.trim().isEmpty());

        for(Person naturalPerson : personsList) {
            if(naturalPerson.getPersonType().equals(NATURAL_PERSON) && naturalPerson.getPersonsName().equals(name) && naturalPerson.getPassword().equals(password)) {
                personsMenu (naturalPerson);
                findPerson = true;
            }
        }
        if(!findPerson) {
            System.out.println("Invalid name and password combination.");
        }
    }

    private static void singInLegalPersons() {
        String name;
        String password;
        boolean findPerson = false;

        int attempts = TOTAL_ATTEMPTS;
        do {
            if (chances(attempts--)) return;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide your name:");
            name = sc.nextLine();
        } while (name.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            if (chances(attempts--)) return;;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide your passwaord:");
            password = sc.nextLine();
        } while (password.trim().isEmpty());

        for(Person legalPerson : personsList) {
            if (legalPerson.getPersonType().equals(LEGAL_PERSON) && legalPerson.getPersonsName().equals(name) && legalPerson.getPassword().equals(password)) {
                personsMenu (legalPerson);
                findPerson = true;
            }
        }
        if (!findPerson) {
            System.out.println("Invalid name and password combination.");
        }
    }

    private static <T extends Person> void personLogin (Class<T> type) {
        if (type.equals(NaturalPerson.class)) {
            NaturalPerson naturalPerson = addPerson(NaturalPerson.class);
            naturalPerson.setPersonType(NATURAL_PERSON);
            personsList.add(naturalPerson);
            personsMenu (naturalPerson);
        } else if(type.equals(LegalPerson.class)){
            LegalPerson legalPerson = addPerson(LegalPerson.class);
            legalPerson.setPersonType(LEGAL_PERSON);
            personsList.add(legalPerson);
            personsMenu (legalPerson);
        } else {
            System.out.println("Invalid type.");
        }
    }

    public static <T extends Person> T addPerson(Class<T> type) {
        try {
            Constructor<T> constructor = type.getConstructor(String.class, Address.class, Contact.class, String.class, long.class);
            return constructor.newInstance(addPersonsName(), addPersonsAddress(), addPersonsContact(), addPassword(), ((type.equals(NaturalPerson.class)) ? addSsn() : addEin()));
        } catch (Exception e) {
            return null;
        }
    }
}