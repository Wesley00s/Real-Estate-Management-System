package services;

import entities.*;
import entities.person.Address;
import entities.person.Contact;
import entities.properties.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static application.Main.mainMenu;
import static enumerations.TypeOfProperty.*;
import static enumerations.TypeRequest.BUY_REQUEST;
import static enumerations.TypeRequest.RENT_REQUEST;
import static services.Negotiation.purchaseList;
import static services.Negotiation.rentList;
import static services.PropertyService.*;
import static utility.Attempts.TOTAL_ATTEMPTS;
import static utility.Attempts.chances;
import static utility.MenuFormat.printMenu;
import static utility.utilBroker.BrokerManager.*;
import static utility.utilProperties.PropertiesManager.seeProperties;

public class BrokerService {
    private static final Scanner sc = new Scanner(System.in);
    public static List<Broker> brokerList = new ArrayList<>();
    public static List<BuyRequest> buyRequestHistory = new ArrayList<>();
    public static List<RentRequest> rentRequestHistory = new ArrayList<>();

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
        List<String> brokerOptions = List.of(
                "REQUESTS",
                "SEE PROPERTIES",
                "SEE PROPERTIES TRANSACTIONS HISTORY",
                "SEE LIST OF OWNERS",
                "SEE PURCHASE HISTORY",
                "SEE RENTED HISTORY",
                "BROKER MANAGER",
                "LOG OUT");

        while (true) {
            printMenu(brokerOptions, STR."BROKER MENU - Hello fellow \{broker.getName()}, what you want today?");
            switch (sc.nextLine()) {
                case "1" -> requestMenu(broker);
                case "2" -> seeProperties(propertyList);
                case "3" -> seePropertiesTransactionsHistory();
                case "4" -> seeListOfOwners();
                case "5" -> seePurchaseHistory();
                case "6" -> seeRentedHistory();
                case "7" -> brokerManagerMenu(broker);
                case "8" -> {
                    System.out.println("Returning...");
                    mainMenu();
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void seePurchaseHistory() {
        if (propertyList.isEmpty()) {
            System.out.println("Purchase list is empty.");
            return;
        }
        for (Purchase purchase : purchaseList) {
            purchase.registerPurchase();
        }
    }

    public static void seeRentedHistory() {
        if (rentList.isEmpty()) {
            System.out.println("Rent list is empty.");
            return;
        }
        for (Rent rent : rentList) {
            rent.registerRent();
        }
    }

    public static void requestMenu(Broker broker) {
        List<String> requestOptions = List.of(
                "SEE BUY REQUEST",
                "SEE RENT REQUEST",
                "APPROVE BUY REQUEST",
                "DISAPPROVE BUY REQUEST",
                "APPROVE RENT REQUEST",
                "DISAPPROVE RENT REQUEST",
                "SEARCH BUY REQUEST",
                "SEARCH RENT REQUEST",
                "SEE BUY REQUEST HISTORY",
                "SEE RENT REQUEST HISTORY",
                "BACK");

        while (true) {
            printMenu(requestOptions, "REQUEST MENU - What you want?");
            switch (sc.nextLine()) {
                case "1" -> seeRequests(buyRequestList);
                case "2" -> seeRequests(rentRequestList);
                case "3" -> approveRequest( buyRequestList, broker, true);
                case "4" -> approveRequest( buyRequestList, broker, false);
                case "5" -> approveRequest(rentRequestList, broker, true);
                case "6" -> approveRequest(rentRequestList, broker, false);
                case "7" -> searchRequest(buyRequestList);
                case "8" -> searchRequest(rentRequestList);
                case "9" -> seeRequestsHistory(buyRequestHistory);
                case "10" -> seeRequestsHistory(rentRequestHistory);
                case "11" -> {
                    System.out.println("Returning...");
                    brokerMenu(broker);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static <T extends Request> void seeRequestsHistory(List<T> requests) {
        if (requests.isEmpty()) {
            System.out.println("Request history is empty.");
            return;
        }
        for (T request : requests) {
            System.out.println(request);
        }
    }

    public static <T extends Request> Request searchRequest(List<T> requests) {
        if (requests.isEmpty()) {
            System.out.println("Request list is empty.");
            return null;
        }
        String requestID;
        System.out.println("Enter the request ID:");
        requestID = sc.nextLine();

        for (T request : requests) {
            if (request.getId().equals(requestID)) {
                System.out.println(request);
                return request;
            }
        }
        System.out.println("Request not found.");
        return null;
    }

    private static <T extends Request> void approveRequest(List<T> requests, Broker broker, boolean condition) {
        Request request = searchRequest(requests);

        if (request != null) {
            request.approveRequest(broker, condition);
            if (request.getTypeRequest().equals(BUY_REQUEST)) {
                buyRequestList.remove((BuyRequest) request);
                buyRequestHistory.add((BuyRequest) request);
            } else if (request.getTypeRequest().equals(RENT_REQUEST)) {
                rentRequestList.remove((RentRequest) request);
                rentRequestHistory.add((RentRequest) request);
            }
        }
    }

    private static <T extends Request> void seeRequests(List<T> requests) {
        if (requests.isEmpty()) {
            System.out.println("There not request at the moment.");
            return;
        }
        for (T request : requests) {
            System.out.println(request);
        }
    }

    private static void seePropertiesTransactionsHistory(){
        System.out.println("\n* APARTMENTS");
        for(Property apartment : historyBuyPropertyList) {
            if (apartment.getTypeOfProperty().equals(APARTMENT))
                System.out.println(apartment);
        }

        System.out.println("\n* FARMS");
        for(Property farm : historyBuyPropertyList) {
            if (farm.getTypeOfProperty().equals(FARM))
                System.out.println(farm);
        }

        System.out.println("\n* HOUSES");
        for(Property house : historyBuyPropertyList) {
            if (house.getTypeOfProperty().equals(HOUSE))
                System.out.println(house);
        }

        System.out.println("\n* LANDS");
        for(Property land : historyBuyPropertyList) {
            if (land.getTypeOfProperty().equals(LAND))
                System.out.println(land);
        }
    }
}
