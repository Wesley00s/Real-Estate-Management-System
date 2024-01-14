package entities;

public class Tax {
    private String taxDescription;
    private double taxValue;
    private boolean isRegistered;

    public Tax(String taxDescription, double taxValue) {
        this.taxDescription = taxDescription;
        this.taxValue = taxValue;
    }

    public boolean idRegisteredTax() {
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
                Tax Description: \{getTaxDescription()}
                Tax Value: \{getTaxValue()}
                Was regitered: \{isRegistered ? "Yes" : "No"}""";
    }
}