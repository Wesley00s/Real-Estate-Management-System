package services;

import entities.Broker;
import entities.Purchase;
import entities.Rent;
import entities.Tax;
import entities.person.Person;
import entities.properties.Property;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static application.Main.mainMenu;
import static database.connection.Connect.updateSqlProperty;
import static services.PropertyService.historyBuyPropertyList;
import static services.PropertyService.historyRentPropertyList;
import static utility.Attempts.TOTAL_ATTEMPTS;
import static utility.Attempts.chances;
import static utility.GenerateID.ID;

public class Negotiation {
    public static List<Purchase> purchaseList = new ArrayList<>();
    public static List<Rent> rentList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    private static double addBrokerCommission(Property property) {
        int attempts = TOTAL_ATTEMPTS;
        String brokerCommission;
        boolean invalidFormat;
        do {
            invalidFormat = false;
            if (chances(attempts--)) mainMenu();
            System.out.println(STR."(\{attempts + 1} attempts) Enter the broker commission:");
            brokerCommission = sc.nextLine();

            try {
                if (Double.parseDouble(brokerCommission) < 0) {
                    System.out.println("Insert an valid value.");
                    invalidFormat = true;
                } else {
                    if (Double.parseDouble(brokerCommission) > property.getRentValue() * 0.05) {
                        System.out.println("The broker commission cannot be greater than 5% of the total amount.");
                        invalidFormat = true;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid type o data.");
                invalidFormat = true;
            }
        } while (invalidFormat);
        return Double.parseDouble(brokerCommission);
    }

    private static double addRealEstatePercent() {
        int attempts = TOTAL_ATTEMPTS;
        String realEstatePercentage;
        boolean invalidFormat;
        do {
            invalidFormat = false;
            if (chances(attempts--)) mainMenu();
            System.out.println(STR."(\{attempts + 1} attempts) Enter the Real Estate percentage (%):");
            realEstatePercentage = sc.nextLine();

            try {
                if (Double.parseDouble(realEstatePercentage) < 0) {
                    System.out.println("Insert an valid value.");
                    invalidFormat = true;
                } else {
                    if (Double.parseDouble(realEstatePercentage) > 20) {
                        System.out.println("The broker commission cannot be greater than 20% of the total amount.");
                        invalidFormat = true;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid type o data.");
                invalidFormat = true;
            }
        } while (invalidFormat);
        return Double.parseDouble(realEstatePercentage);
    }

    private static double addSecurityDeposit(Property property) {
        int attempts = TOTAL_ATTEMPTS;
        String securityDeposit;
        boolean invalidFormat;
        do {
            invalidFormat = false;
            if (chances(attempts--)) mainMenu();
            System.out.println(STR."(\{attempts + 1} attempts) Enter the security deposit amount ($USD):");
            securityDeposit = sc.nextLine();

            try {
                if (Double.parseDouble(securityDeposit) < 0) {
                    System.out.println("Insert an valid value.");
                    invalidFormat = true;
                } else {
                    if (Double.parseDouble(securityDeposit) < property.getRentValue() * 3) {
                        System.out.println("The security deposit must be at least 3 mounts worth of the monthly rental amount.");
                        invalidFormat = true;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid type o data.");
                invalidFormat = true;
            }
        } while (invalidFormat);
        return Double.parseDouble(securityDeposit);
    }

    private static LocalDate addRentalEndDate() {
        int attempts = TOTAL_ATTEMPTS;
        String stringDate;
        LocalDate endOfRent = null;
        boolean invalidFormat;
        do {
            invalidFormat = false;
            if (chances(attempts--)) mainMenu();
            System.out.println(STR."(\{attempts + 1} attempts) Enter the end date:");
            stringDate = sc.nextLine();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                dateFormat.setLenient(false);
                Date parsedDate = dateFormat.parse(stringDate);

                endOfRent = parsedDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

                if (endOfRent.isBefore(LocalDate.now())) {
                    System.out.println("Date condition not allowed.");
                    invalidFormat = true;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in YYYY/MM/DD format.");
                invalidFormat = true;
            }
        } while (invalidFormat);
        return endOfRent;
    }

    public static void makePropertyPurchase(Property propertyToBePurchase, Broker broker, Person newOwner, Person oldOwner) {
        List<Tax> taxList = new ArrayList<>();

        double brokerCommission = addBrokerCommission(propertyToBePurchase);
        double realEstatePercentage = addRealEstatePercent();
        double propertyPrice = propertyToBePurchase.getPrice();
        double realEstatePercentageAmount = propertyPrice * realEstatePercentage / 100;

        Tax taxBrokerCommission = new Tax("Broker commission $USD", brokerCommission);
        Tax taxRealEstatePercentage = new Tax(STR."Real Estate percentage \{realEstatePercentage}%", realEstatePercentageAmount);
        taxList.add(taxBrokerCommission);
        taxList.add(taxRealEstatePercentage);
        propertyToBePurchase.setFinalPrice(propertyPrice, (brokerCommission + realEstatePercentage));
        Purchase purchase = new Purchase(STR."N-\{ID()}N", broker, propertyToBePurchase, LocalDate.now(), propertyPrice, brokerCommission, realEstatePercentage, taxList);

        historyBuyPropertyList.add(propertyToBePurchase);
        updateSqlProperty(newOwner, oldOwner, propertyToBePurchase);
        System.out.println("Property purchase was approved!");
        purchaseList.add(purchase);
    }

    public static void makePropertyRent(Property propertyToBeRent, Broker broker, Person renter, Person owner) {
        List<Tax> taxList = new ArrayList<>();

        double brokerCommission = addBrokerCommission(propertyToBeRent);
        double realEstatePercentage = addRealEstatePercent();
        double securityDeposit = addSecurityDeposit(propertyToBeRent);
        LocalDate endOfRent = addRentalEndDate();
        double rentValue = propertyToBeRent.getRentValue();
        double realEstatePercentageAmount = rentValue * realEstatePercentage / 100;

        Tax taxBrokerCommission = new Tax("Broker commission $USD", brokerCommission);
        Tax taxRealEstatePercentage = new Tax(STR."Real Estate percentage \{realEstatePercentage}%", realEstatePercentageAmount);
        taxList.add(taxBrokerCommission);
        taxList.add(taxRealEstatePercentage);
        propertyToBeRent.setFinalPrice(rentValue, (brokerCommission + realEstatePercentage));
        Rent rent = new Rent(STR."N-\{ID()}N", broker, propertyToBeRent, LocalDate.now(), rentValue, securityDeposit, endOfRent, brokerCommission, realEstatePercentage, taxList);
        historyRentPropertyList.add(propertyToBeRent);
        rentList.add(rent);
    }
}
