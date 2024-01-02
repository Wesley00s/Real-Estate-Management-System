package enumerations;

public enum Situation {
    SALE("For Sale"),
    RENT("Under Rent"),
    RENTED("Rented"),
    SOLD("Sold");

    private final String situation;

    Situation(String situation) {
        this.situation = situation;
    }

    public String toString() {
        return this.situation;
    }
}