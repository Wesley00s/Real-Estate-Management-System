package services;

import entities.person.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static application.Main.mainMenu;
import static services.PropertyService.personsMenu;
import static utility.Attempts.TOTAL_ATTEMPTS;
import static utility.Attempts.chances;
import static utility.MenuFormat.printMenu;
import static utility.utilPersons.PersonsManager.*;

public class PersonService {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<NaturalPerson> naturalPersonList = new ArrayList<>();
    private static final List<LegalPerson> legalPersonList = new ArrayList<>();

    public static void addNaturalPersons() {
        Address address1 = new Address("New York", "10001", "Manhattan", "Broadway Street", 123);
        Contact contact1 = new Contact("person1@email.com", "987-654-3210");
        NaturalPerson person1 = new NaturalPerson("John Smith", address1, contact1, "password123", 987654321);

        Address address2 = new Address("London", "78454", "Westminster", "Buckingham Palace Road", 456);
        Contact contact2 = new Contact("person2@email.com", "123-456-7890");
        NaturalPerson person2 = new NaturalPerson("Emma Johnson", address2, contact2, "securePassword", 876543210);

        Address address3 = new Address("Paris", "75001", "Le Marais", "Rue Saint-Antoine", 789);
        Contact contact3 = new Contact("person3@email.com", "234-567-8901");
        NaturalPerson person3 = new NaturalPerson("Pierre Dubois", address3, contact3, "parisPassword123", 654321098);

        Address address4 = new Address("Tokyo", "10001", "Chiyoda", "Chiyoda City", 101);
        Contact contact4 = new Contact("person4@email.com", "345-678-9012");
        LegalPerson person4 = new LegalPerson("Yuki Tanaka", address4, contact4, "tokyoSecure456", 123456789);

        Address address5 = new Address("Sydney", "20008", "Darling Harbour", "George Street", 222);
        Contact contact5 = new Contact("person5@email.com", "456-789-0123");
        LegalPerson person5 = new LegalPerson("Olivia Davis", address5, contact5, "sydneyPassword789", 987654321);

        naturalPersonList.add(person1);
        naturalPersonList.add(person2);
        naturalPersonList.add(person3);
        legalPersonList.add(person4);
        legalPersonList.add(person5);
    }

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
            if(chances(attempts--)) return;;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide your passwaord:");
            password = sc.nextLine();
        } while (password.trim().isEmpty());

        for(NaturalPerson naturalPerson : naturalPersonList) {
            if(naturalPerson.getPersonsName().equals(name) && naturalPerson.getPassword().equals(password)) {
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

        for(LegalPerson legalPerson : legalPersonList) {
            if (legalPerson.getPersonsName().equals(name) && legalPerson.getPassword().equals(password)) {
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
            naturalPersonList.add(naturalPerson);
            personsMenu (naturalPerson);
        } else if(type.equals(LegalPerson.class)){
            LegalPerson legalPerson = addPerson(LegalPerson.class);
            legalPersonList.add(legalPerson);
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