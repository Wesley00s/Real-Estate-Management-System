package utility.utilPersons;

import entities.person.*;

import java.util.Scanner;

import static services.PersonService.personsLoginMenu;
import static services.PropertyService.personsList;
import static utility.Attempts.*;
import static utility.GenerateID.ID;

public class PersonsManager {
    private static final Scanner sc = new Scanner(System.in);
    private static int attempts;
    private static boolean invalidData;

    public static String addPersonsName() {
        String personName;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) personsLoginMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the newOwner name:");
            personName = sc.nextLine();

        } while (personName.trim().isEmpty());

        return personName;
    }

    public static Address addPersonsAddress() {
        System.out.println("\n\t\t* ADD ADDRESS INFO");
        String city;
        String zipCode;
        String district;
        String street;
        String number;

        attempts = TOTAL_ATTEMPTS;
        do {
            if (chances(attempts--)) personsLoginMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the name of the city:");
            city = sc.nextLine();

        } while (city.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) personsLoginMenu();

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
            if(chances(attempts--)) personsLoginMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the district name:");
            district = sc.nextLine();

        } while (district.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) personsLoginMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the street name:");
            street = sc.nextLine();

        } while (street.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) personsLoginMenu();

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

        return new Address(STR."A-\{ID()}S", city, zipCode, district, street, Integer.parseInt(number));
    }

    public static Contact addPersonsContact() {
        System.out.println("\n\t\t* ADD CONTACT INFO");
        String email;
        String phone;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) personsLoginMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the email:");
            email = sc.nextLine();

        } while (email.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) personsLoginMenu();

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

        return new Contact(STR."C-\{ID()}T", email, Integer.parseInt(phone));
    }

    public static boolean isRegisterNumberDuplicate(int registerNumber) {
        if (personsList == null || personsList.isEmpty()) return false;

        boolean find = false;

        for (Person newOwner : personsList) {
            switch (newOwner.getPersonType()) {
                case NATURAL_PERSON -> {
                    NaturalPerson naturalPerson = (NaturalPerson) newOwner;
                    if (naturalPerson.getSsn() == registerNumber) {
                        find = true;
                    }
                }
                case LEGAL_PERSON -> {
                    LegalPerson legalPerson = (LegalPerson) newOwner;
                    if (legalPerson.getEin() == registerNumber) {
                        find = true;
                    }
                }
            }
        }
        if (find) {
            System.out.println("Already registered identifier.\n");
            return true;
        }
        return false;
    }

    public static int addSsn() {
        String ssn;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) personsLoginMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the Social Security Number (SSN - 9 digits):");
            ssn = sc.nextLine();

            try {
                if(Integer.parseInt(ssn) <= 100000000 || Integer.parseInt(ssn) > 999999999) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                } else if (isRegisterNumberDuplicate(Integer.parseInt(ssn))){
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Integer.parseInt(ssn);
    }

    public static int addEin() {
        String ein;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) personsLoginMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the Employer Identification Number (EIN - 9 digits):");
            ein = sc.nextLine();

            try {
                if(Integer.parseInt(ein) <= 100000000 || Integer.parseInt(ein) > 999999999) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                } else if (isRegisterNumberDuplicate(Integer.parseInt(ein))){
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Integer.parseInt(ein);
    }

    public static String addPassword() {
        String password;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) personsLoginMenu();

            System.out.println(STR."(\{attempts + 1} Attempts) Create an password:");
            password = sc.nextLine();

        } while (password.trim().isEmpty());

        return password;
    }
}
