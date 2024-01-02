package entities;

import java.time.LocalDate;

public class RentPayment {
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private double taxToPay;
    private double realEstatePercent;

    public RentPayment(LocalDate dueDate, LocalDate paymentDate, double taxToPay, double realEstatePercent) {
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.taxToPay = taxToPay;
        this.realEstatePercent = realEstatePercent;
    }

    public int registerPayment() {
        return 0;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public double getTaxToPay() {
        return taxToPay;
    }

    public double getRealEstatePercent() {
        return realEstatePercent;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setTaxToPay(double taxToPay) {
        this.taxToPay = taxToPay;
    }

    public void setRealEstatePercent(double realEstatePercent) {
        this.realEstatePercent = realEstatePercent;
    }

    public String toString() {
        return STR."""
                Due date: \{getDueDate()}
                Payment date: \{getPaymentDate()}
                entities.Tax to pay: $USD \{getTaxToPay()}
                Real Estate percentage: $USD \{getRealEstatePercent()}
                """;
    }
}
