package entities;

import entities.properties.Property;

import java.time.LocalDate;
import java.util.List;

public class Purchase {
    private String purchaseID;

    private Broker broker;
    private Property property;
    private LocalDate purchaseDate;
    private double purchaseValue;
    private double realEstatePercent;
    private double brokerCommission;
    private List<Tax> taxList;

    public Purchase(String purchaseID, Broker broker, Property property, LocalDate purchaseDate, double purchaseValue, double brokerCommission, double realEstatePercent, List<Tax> taxList) {
        this.purchaseID = purchaseID;
        this.broker = broker;
        this.property = property;
        this.purchaseDate = purchaseDate;
        this.purchaseValue = purchaseValue;
        this.realEstatePercent = realEstatePercent;
        this.brokerCommission = brokerCommission;
        this.taxList = taxList;
    }

    public Purchase(String purchaseID, Broker broker, Property property, LocalDate purchaseDate, double purchaseValue, double brokerCommission, double realEstatePercent) {
        this.purchaseID = purchaseID;
        this.broker = broker;
        this.property = property;
        this.purchaseDate = purchaseDate;
        this.purchaseValue = purchaseValue;
        this.realEstatePercent = realEstatePercent;
        this.brokerCommission = brokerCommission;
    }

    public void registerPurchase() {
        System.out.println(STR."""
                \t* PURCHASE
                PurchaseID: \{getPurchaseID()}
                Purchase Date: \{getPurchaseDate()}
                Purchase Value: $USD \{getPurchaseValue()}
                Property ID: \{getProperty().getId()}
                Broker SSN: \{getBroker().getSsn()}

                TAX AND DISCOUNTS
                \{getTaxList()}

                FINAL VALUE: $USD \{getProperty().getFinalPrice()}
                ----------------------------------------------
                """);
    }

    public String getPurchaseID() {
        return purchaseID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public double getPurchaseValue() {
        return purchaseValue;
    }

    public double getRealEstatePercent() {
        return realEstatePercent;
    }

    public double getBrokerCommission() {
        return brokerCommission;
    }

    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseValue(double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public void setRealEstatePercent(double realEstatePercent) {
        this.realEstatePercent = realEstatePercent;
    }

    public void setBrokerCommission(double brokerCommission) {
        this.brokerCommission = brokerCommission;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<Tax> getTaxList() {
        return taxList;
    }

    public void setTaxList(List<Tax> taxList) {
        this.taxList = taxList;
    }
}
