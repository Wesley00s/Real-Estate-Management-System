package utility.utilPersons;

import entities.person.Address;
import entities.person.Contact;
import entities.person.LegalPerson;
import entities.person.NaturalPerson;
import entities.properties.AddressProperty;
import services.PropertyService;

import java.util.Locale;
import java.util.Scanner;

import static services.PersonService.naturalPersonsMenu;
import static services.PropertyService.registerPropertyMenu;

import static utility.Attempts.*;

public class PersonsManager {
    private static final Scanner sc = new Scanner(System.in);
    private static int attempts;
    private static boolean invalidData;
    public static String addPersonsName() {
        String personName;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the person name:");
            personName = sc.nextLine();

        } while (personName.trim().isEmpty());

        return personName;
    }

    public static Address addPersonsAddress() {
        System.out.println("\n\t\t* ADD ADDRESS INFO");
        String city;
        String zipCode;
        String neighborhood;
        String street;
        String number;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the name of the city:");
            city = sc.nextLine();

        } while (city.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the zip code (5 digits):");
            zipCode = sc.nextLine();

            try {
                if(Integer.parseInt(zipCode) <= 10000 || Integer.parseInt(zipCode) > 99999 ) {
                    System.out.println("Please, provide a valid zip code.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the neighborhood name:");
            neighborhood = sc.nextLine();

        } while (neighborhood.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the street name:");
            street = sc.nextLine();

        } while (street.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the number:");
            number = sc.nextLine();

            try {
                if(Integer.parseInt(number) <= 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return new Address(city, zipCode, neighborhood, street, Integer.parseInt(number));
    }

    public static Contact addPersonsContact() {
        String email;
        String phone;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the email:");
            email = sc.nextLine();

        } while (email.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the phone:");
            phone = sc.nextLine();

            try {
                if(Integer.parseInt(phone) <= 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return new Contact(email, phone);
    }

    public static long addSsn() {
        String snn;

        do {
            invalidData = false;
            if (chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the Social Security Number (SSN):");
            snn = sc.nextLine();

            try {
                if(Long.parseLong(snn) <= 100000000 || Long.parseLong(snn) > 999999999) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Long.parseLong(snn);
    }

    public static long addEin() {
        String ein;

        do {
            invalidData = false;
            if (chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the Employer Identification Number (EIN):");
            ein = sc.nextLine();

            try {
                if(Long.parseLong(ein) <= 100000000 || Long.parseLong(ein) > 999999999) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Long.parseLong(ein);
    }

    public static String addPassword() {
        String password;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) naturalPersonsMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Create an password:");
            password = sc.nextLine();

        } while (password.trim().isEmpty());

        return password;
    }
}
