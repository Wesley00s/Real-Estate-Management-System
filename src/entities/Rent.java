package entities;

import entities.properties.Property;

import java.time.LocalDate;
import java.util.List;

public class Rent {
    private String rentID;
    private Broker broker;
    private Property property;
    private LocalDate startOfRent;
    private double rentValue;
    private double securityDeposit;
    private LocalDate endOfRent;
    private double realEstatePercent;
    private double brokerCommission;
    private List<Tax> taxList;

    public Rent(String rentID, Broker broker, Property property, LocalDate startOfRent, double rentValue, double securityDeposit, LocalDate endOfRent, double brokerCommission, double realEstatePercent, List<Tax> taxList) {
        this.rentID = rentID;
        this.broker = broker;
        this.property = property;
        this.startOfRent = startOfRent;
        this.rentValue = rentValue;
        this.securityDeposit = securityDeposit;
        this.endOfRent = endOfRent;
        this.brokerCommission = brokerCommission;
        this.realEstatePercent = realEstatePercent;
        this.taxList = taxList;
    }

    public void registerRent() {
        System.out.println(STR."""
                \t* RENT
                PurchaseID: \{getRentID()}
                Purchase Date: \{getStartOfRent()}
                Rent Value: $USD \{getRentValue()}
                Scurity Deposit Amount: \{getSecurityDeposit()}
                Property ID: \{getProperty().getId()}
                Broker SSN: \{getBroker().getSsn()}

                TAX AND DISCOUNTS
                \{getTaxList()}

                TOTAL MOUNTLY: $USD \{getRentValue() - (getRentValue() * (getRealEstatePercent() / 100) + getBrokerCommission() - getSecurityDeposit())}
                ----------------------------------------------
                """);
    }

    public int endRent() {
        return 0;
    }

    public LocalDate getStartOfRent() {
        return startOfRent;
    }

    public void setStartOfRent(LocalDate startOfRent) {
        this.startOfRent = startOfRent;
    }

    public double getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public LocalDate getEndOfRent() {
        return endOfRent;
    }

    public void setEndOfRent(LocalDate endOfRent) {
        this.endOfRent = endOfRent;
    }

    public double getBrokerCommission() {
        return brokerCommission;
    }

    public void setBrokerCommission(double brokerCommission) {
        this.brokerCommission = brokerCommission;
    }

    public List<Tax> getTaxList() {
        return taxList;
    }

    public void setTaxList(List<Tax> taxList) {
        this.taxList = taxList;
    }

    public double getRealEstatePercent() {
        return realEstatePercent;
    }

    public void setRealEstatePercent(double realEstatePercent) {
        this.realEstatePercent = realEstatePercent;
    }

    public String getRentID() {
        return rentID;
    }

    public void setRentID(String rentID) {
        this.rentID = rentID;
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

    public double getRentValue() {
        return rentValue;
    }

    public void setRentValue(double rentValue) {
        this.rentValue = rentValue;
    }
}
