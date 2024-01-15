package entities.properties;

import enumerations.Status;
import enumerations.TypeOfProperty;

public class Land extends Property {
    private double totalArea;
    private double frontDimension;
    private double sideDimension;

    public Land(TypeOfProperty typeOfProperty, String rentID, AddressProperty address, String desc, double price, double rentValue, Status status) {
        super(typeOfProperty, rentID, address, desc, price, rentValue, status);
    }

    public Land(TypeOfProperty typeOfProperty, String rentID, String desc, double price, double rentValue, Status status) {
        super(typeOfProperty, rentID, desc, price, rentValue, status);
    }

    public Land setPropertyDetails(double frontDimension, double sideDimension) {
        this.frontDimension = frontDimension;
        this.sideDimension = sideDimension;
        return this;
    }

    public Land() {}

    public double getFrontDimension() {
        return frontDimension;
    }

    public double getSideDimension() {
        return sideDimension;
    }

    public double getTotalArea() {
        return getFrontDimension() * getSideDimension();
    }

    public void setFrontDimension(double frontDimension) {
        this.frontDimension = frontDimension;
    }

    public void setSideDimension(double sideDimension) {
        this.sideDimension = sideDimension;
    }

    @Override
    public String toString() {
        return STR."""
                Type of property: \{getTypeOfProperty()}
                ID: \{getId()}

                ADDRESS
                \{getAddress()}
                DESCRIPTION:
                "\{getDesc()}"
                Total area: \{getTotalArea()} m²
                Price: $USD \{getPrice()}
                Rent value: $USD \{getRentValue()}
                Status: \{getStatus()}

                PROPERTY DETAILS
                Front dimension: \{getFrontDimension()} m
                Side diemension: \{getSideDimension()} m
                -----------------------------------------------""";
    }
}