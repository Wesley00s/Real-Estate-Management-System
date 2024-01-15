package utility.utilBroker;

import entities.Broker;
import entities.person.*;

import java.util.List;
import java.util.Scanner;

import static database.connection.Connect.removeSqlBrokerData;
import static database.connection.Connect.setSqlBrokerData;
import static services.BrokerService.brokerList;
import static services.BrokerService.brokerMenu;
import static services.PersonService.personsLoginMenu;
import static services.PropertyService.personsList;
import static utility.Attempts.TOTAL_ATTEMPTS;
import static utility.Attempts.chances;
import static utility.GenerateID.ID;
import static utility.MenuFormat.printMenu;

public class BrokerManager {
    private static final Scanner sc = new Scanner(System.in);
    private static int attempts;
    private static boolean invalidData;

    public static String addBrokerName(Broker broker) {
        String personName;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) brokerMenu(broker);

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the broker name:");
            personName = sc.nextLine();

        } while (personName.trim().isEmpty());

        return personName;
    }
    public static Address addBrokerAddress(Broker broker) {
        System.out.println("\n\t\t* ADD ADDRESS INFO");
        String city;
        String zipCode;
        String district;
        String street;
        String number;

        attempts = TOTAL_ATTEMPTS;
        do {
            if (chances(attempts--)) brokerMenu(broker);

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the name of the city:");
            city = sc.nextLine();

        } while (city.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) brokerMenu(broker);

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
            if(chances(attempts--)) brokerMenu(broker);

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the district name:");
            district = sc.nextLine();

        } while (district.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) brokerMenu(broker);

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

    public static Contact addPBrokerContact(Broker broker) {
        System.out.println("\n\t\t* ADD CONTACT INFO");
        String email;
        String phone;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) brokerMenu(broker);

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the email:");
            email = sc.nextLine();

        } while (email.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) brokerMenu(broker);

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

    public static boolean isRegisterNumberDuplicate(int ssn) {
        if (brokerList == null || brokerList.isEmpty()) return false;

        boolean find = false;

        for (Broker broker : brokerList) {
            if (broker.getSsn() == ssn) {
                find = true;
                break;
            }
        }
        if (find) {
            System.out.println("Already registered identifier.\n");
            return true;
        }
        return false;
    }

    public static int addBrokerSsn(Broker broker) {
        System.out.println("\n\t\t* ADD BROKER");
        String ssn;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) brokerMenu(broker);

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

    public static String addBrokerPassword(Broker broker) {
        String password;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) brokerMenu(broker);

            System.out.println(STR."(\{attempts + 1} Attempts) Create an password:");
            password = sc.nextLine();

        } while (password.trim().isEmpty());

        return password;
    }

    private static Broker searchBroker() {
        System.out.println("Enter the broker SSN:");
        String brokerSSN = sc.nextLine();
        try {
            for (Broker broker : brokerList) {
                if (broker.getSsn() == Integer.parseInt(brokerSSN)) {
                    return broker;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Broker not found.");
        }
        return null;
    }

    private static void removeBroker(Broker broker) {
        System.out.println("\n\t\t* REMOVE BROKER");
        Broker searchBroker = searchBroker();

        if (searchBroker != null && searchBroker != broker) {
            removeSqlBrokerData(broker.getSsn());
            brokerList.remove(searchBroker);
            System.out.println("The broker has been removed.");
        } else {
            System.out.println("The broker can't be removed.");
        }
    }

    private static void displayBrokerList() {
        System.out.println("\n\t\t* BROKER LIST");
        for (Broker broker : brokerList) {
            System.out.println(broker);
        }
    }

    private static void addBroker(Broker broker) {
        int brokerSsn = addBrokerSsn(broker);
        String brokerName = addBrokerName(broker);
        Address address = addBrokerAddress(broker);
        Contact contact = addPBrokerContact(broker);
        Broker newBroker = new Broker(brokerSsn, brokerName, address, contact, addBrokerPassword(broker));
        setSqlBrokerData(newBroker, address, contact, brokerSsn);
        brokerList.add(newBroker);
        System.out.println("The broker has been added.");
    }

    public static void brokerManagerMenu(Broker broker) {
        List<String> listBrokerManagerOptions = List.of("ADD NEW BROKER", "REMOVE BROKER", "DISPLAY BROKER LIST", "SEARCH BROKER", "BACK");
        while (true) {
            printMenu(listBrokerManagerOptions, "BROKER MANAGER MENU - Choose an option:");
            switch (sc.nextLine()) {
                case "1" -> addBroker(broker);
                case "2" -> removeBroker(broker);
                case "3" -> displayBrokerList();
                case "4" -> {
                    Broker result = searchBroker();
                    if (result != null) {
                        System.out.println(result);
                    }
                }
                case "5" -> {
                    System.out.println("Returning...");
                    brokerMenu(broker);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void seeListOfOwners() {
        for (Person person : personsList) {
            System.out.println(person);
        }
    }
}
