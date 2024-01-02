package entities;

import java.time.LocalDate;

public class Purchase {
    private LocalDate purchaseDate;
    private double purchaseValue;
    private double realEstatePercent;
    private double brokerCommission;

    public Purchase(LocalDate purchaseDate, double purchaseValue, double realEstatePercent, double brokerCommission) {
        this.purchaseDate = purchaseDate;
        this.purchaseValue = purchaseValue;
        this.realEstatePercent = realEstatePercent;
        this.brokerCommission = brokerCommission;
    }

    public int registerPurchase() {
        return 0;
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

    @Override
    public String toString() {
        return STR."""
                PURCHASE
                Purchase Date: \{getPurchaseDate()}
                Purchase Value: $USD \{getPurchaseValue()}
                RealEstate Percentage: $USD \{getRealEstatePercent()}
                Broker Commission: $USD \{getBrokerCommission()}
                """;
    }
}
