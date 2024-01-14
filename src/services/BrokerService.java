package services;

import entities.Broker;
import entities.Purchase;
import entities.Request;
import entities.person.Address;
import entities.person.Contact;
import entities.properties.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static application.Main.mainMenu;
import static enumerations.TypeOfProperty.*;
import static services.Negotiation.purchaseList;
import static services.PropertyService.*;
import static utility.Attempts.TOTAL_ATTEMPTS;
import static utility.Attempts.chances;
import static utility.MenuFormat.printMenu;
import static utility.utilBroker.BrokerManager.seeListOfOwners;
import static utility.utilProperties.PropertiesManager.seeProperties;

public class BrokerService {
    private static final Scanner sc = new Scanner(System.in);
    public static List<Broker> brokerList = new ArrayList<>();
    public static List<Request> requestHistory = new ArrayList<>();

    public static void addMainBroker() {
        Address brokerAddress = new Address("B-996030057R", "Lumièreville", "98741", "Quartier Lumière", "Avenue Éclatante", 584);
        Contact brokerContact = new Contact("C-102911534T", "brokeEmail@gamil.com", 655874895);
        Broker mainBroker = new Broker(179348625, "Visionnaire de I'immobilier", brokerAddress, brokerContact, "immo123");
        brokerList.add(mainBroker);
    }

    public static void brokerLogin() {
        System.out.println("\n\t\t* BROKER LOGIN");
        String name;
        String password;
        boolean findBroker = false;

        int attempts = TOTAL_ATTEMPTS;
        do {
            if (chances(attempts--)) return;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide your name:");
            name = sc.nextLine();
        } while (name.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            if (chances(attempts--)) return;;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide your password:");
            password = sc.nextLine();
        } while (password.trim().isEmpty());

        for (Broker broker : brokerList) {
            if (broker.getPassword().equals(password) || broker.getName().equals(name)) {
                brokerMenu(broker);
                findBroker = true;
            }
        }
        if (!findBroker) {
            System.out.println("Invalid name and password combination.");
        }
    }

    public static void brokerMenu(Broker broker) {
        List<String> brokerOptions = List.of("REQUESTS", "SEE PROPERTIES", "SEE PROPERTIES TRANSACTIONS HISTORY", "SEE LIST OF OWNERS", "SEE PURCHASE HISTORY", "BACK");

        while (true) {
            printMenu(brokerOptions, STR."BROKER MENU - Hello fellow \{broker.getName()}, what you want today?");
            switch (sc.nextLine()) {
                case "1" -> requestMenu(broker);
                case "2" -> seeProperties(propertyList);
                case "3" -> seePropertiesTransactionsHistory();
                case "4" -> seeListOfOwners();
                case "5" -> seePurchaseHistory();
                case "6" -> {
                    System.out.println("Returning...");
                    mainMenu();
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void seePurchaseHistory() {
        for (Purchase purchase : purchaseList) {
            purchase.registerPurchase();
        }
    }

    public static void requestMenu(Broker broker) {
        List<String> requestOptions = List.of("SEE REQUEST", "APPROVE REQUEST", "DISAPPROVE REQUEST", "SEARCH REQUEST", "SEE REQUEST HISTORY", "CANCEL");

        while (true) {
            printMenu(requestOptions, "REQUEST MENU - What you want?");
            switch (sc.nextLine()) {
                case "1" -> seeRequests();
                case "2" -> approveRequest(broker, true);
                case "3" -> approveRequest(broker, false);
                case "4" -> searchRequest();
                case "5" -> seeRequestsHistory();
                case "6" -> {
                    System.out.println("Cancelling...");
                    brokerMenu(broker);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void seeRequestsHistory() {
        if (requestHistory.isEmpty()) {
            System.out.println("Request history is empty.");
            return;
        }
        for (Request request : requestHistory) {
            System.out.println(request);
        }
    }

    public static Request searchRequest() {
        if (requestList.isEmpty()) {
            System.out.println("Request list is empty.");
            return null;
        }
        String requestID;
        System.out.println("Enter the request ID:");
        requestID = sc.nextLine();

        for (Request request : requestList) {
            if (request.getId().equals(requestID)) {
                System.out.println(request);
                return request;
            }
        }
        System.out.println("Request not found.");
        return null;
    }

    private static void approveRequest(Broker broker, boolean condition) {
        Request request = searchRequest();
        if (request != null) {
            request.approveRequest(broker, condition);
            requestList.remove(request);
            requestHistory.add(request);
        }
    }

    private static void seeRequests(){
        if (requestList.isEmpty()) {
            System.out.println("There not request at the moment.");
            return;
        }
        for (Request request : requestList) {
            System.out.println(request);
        }
    }

    private static void seePropertiesTransactionsHistory(){
        System.out.println("\n* APARTMENTS");
        for(Property apartment : historyPropertyList) {
            if (apartment.getTypeOfProperty().equals(APARTMENT))
                System.out.println(apartment);
        }

        System.out.println("\n* FARMS");
        for(Property farm : historyPropertyList) {
            if (farm.getTypeOfProperty().equals(FARM))
                System.out.println(farm);
        }

        System.out.println("\n* HOUSES");
        for(Property house : historyPropertyList) {
            if (house.getTypeOfProperty().equals(HOUSE))
                System.out.println(house);
        }

        System.out.println("\n* LANDS");
        for(Property land : historyPropertyList) {
            if (land.getTypeOfProperty().equals(LAND))
                System.out.println(land);
        }
    }
}
