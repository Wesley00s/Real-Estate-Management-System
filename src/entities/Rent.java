package entities;

import java.time.LocalDate;

public class Rent {
    private LocalDate statOfRent;
    private int leaseTerm;
    private LocalDate endOfRent;
    private double depositRent;
    private double brokerCommission;

    public Rent(LocalDate statOfRent, int leaseTerm, LocalDate endOfRent, double brokerCommission) {
        this.statOfRent = statOfRent;
        this.leaseTerm = leaseTerm;
        this.endOfRent = endOfRent;
        this.brokerCommission = brokerCommission;
    }

    public int registerRent() {
        return 0;
    }

    public int endRent() {
        return 0;
    }

    public LocalDate getStatOfRent() {
        return statOfRent;
    }

    public void setStatOfRent(LocalDate statOfRent) {
        this.statOfRent = statOfRent;
    }

    public int getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(int leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public LocalDate getEndOfRent() {
        return endOfRent;
    }

    public void setEndOfRent(LocalDate endOfRent) {
        this.endOfRent = endOfRent;
    }

    public double getDepositRent() {
        return depositRent;
    }

    public void setDepositRent(double depositRent) {
        this.depositRent = depositRent;
    }

    public double getBrokerCommission() {
        return brokerCommission;
    }

    public void setBrokerCommission(double brokerCommission) {
        this.brokerCommission = brokerCommission;
    }
}
