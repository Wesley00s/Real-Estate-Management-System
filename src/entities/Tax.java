package entities;

public class Tax {
    private String taxDescription;
    private double taxValue;

    public Tax(String taxDescription, double taxValue) {
        this.taxDescription = taxDescription;
        this.taxValue = taxValue;
    }

    public int registerTax() {
        return 0;
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
                TAX
                Tax Description: \{getTaxDescription()}
                Tax Value: \{getTaxValue()}
                """;
    }
}