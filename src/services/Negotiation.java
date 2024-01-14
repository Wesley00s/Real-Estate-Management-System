package services;

import entities.Broker;
import entities.Purchase;
import entities.Tax;
import entities.person.Person;
import entities.properties.Property;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static database.connection.Connect.updateSqlProperty;
import static enumerations.Status.SOLD;
import static services.PropertyService.historyPropertyList;
import static utility.Attempts.TOTAL_ATTEMPTS;
import static utility.Attempts.chances;
import static utility.GenerateID.ID;

public class Negotiation {
    public static List<Purchase> purchaseList = new ArrayList<>();
    public static List<Tax> taxList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void makePropertyPurchase(Property propertyToBePurchase, Broker broker, Person person, Person oldOwner) {
        boolean invalidFormat;

        int attempts = TOTAL_ATTEMPTS;
        String brokerCommission;
        do {
            invalidFormat = false;
            if (chances(attempts--)) return;
            System.out.println(STR."(\{attempts + 1} attempts) Enter the broker commission:");
            brokerCommission = sc.nextLine();

            try {
                if (Double.parseDouble(brokerCommission) < 0) {
                    System.out.println("Insert an valid value.");
                    invalidFormat = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid type o data.");
                invalidFormat = true;
            }
        } while (invalidFormat);

        attempts = TOTAL_ATTEMPTS;
        String realEstatePercentage;
        do {
            invalidFormat = false;
            if (chances(attempts--)) return;
            System.out.println(STR."(\{attempts + 1} attempts) Enter the Real Estate percentage (%):");
            realEstatePercentage = sc.nextLine();

            try {
                if (Double.parseDouble(realEstatePercentage) < 0) {
                    System.out.println("Insert an valid value.");
                    invalidFormat = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid type o data.");
                invalidFormat = true;
            }
        } while (invalidFormat);
        double propertyPrice = propertyToBePurchase.getPrice();

        Tax taxBrokerCommission = new Tax("Broker commission $USD", Double.parseDouble(brokerCommission));
        Tax taxRealEstatePercentage = new Tax(STR."Real Estate percentage \{realEstatePercentage}%", propertyPrice * Double.parseDouble(realEstatePercentage) / 100);
        taxList.add(taxBrokerCommission);
        taxList.add(taxRealEstatePercentage);
        Purchase purchase = new Purchase(STR."N-\{ID()}N", broker, propertyToBePurchase, LocalDate.now(), propertyPrice, Double.parseDouble(realEstatePercentage), Double.parseDouble(brokerCommission), taxList);

        historyPropertyList.add(propertyToBePurchase);
        updateSqlProperty(person, oldOwner, propertyToBePurchase);
        System.out.println("Property purchase was approved!");
        purchaseList.add(purchase);
    }
}
