package entities;

public class Tax {
    private String taxID;
    private String taxDescription;
    private double taxValue;
    private boolean isRegistered;

    public Tax(String taxID, String taxDescription, double taxValue) {
        this.taxID = taxID;
        this.taxDescription = taxDescription;
        this.taxValue = taxValue;
    }

    public boolean isRegisteredTax() {
        return isRegistered;
    }

    public void register() {
        this.isRegistered = true;
    }

    public String getTaxDescription() {
        return taxDescription;
    }

    public double getTaxValue() {
        return taxValue;
    }

    public void setTaxDescription(String taxDescription) {
        this.taxDescription = taxDescription;
    }

    public void setTaxValue(double taxValue) {
        this.taxValue = taxValue;
    }

    public String toString() {
        return STR."""
                Tax ID: \{getTaxID()}
                Tax Description: \{getTaxDescription()}
                Tax Value: $USD \{getTaxValue()}
                Was regitered: \{isRegistered ? "Yes" : "No"}""";
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }
}