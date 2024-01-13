package entities.properties;

import enumerations.Status;
import enumerations.TypeOfProperty;

public class Land extends Property {
    private double frontDimension;
    private double sideDimension;

    public Land(TypeOfProperty typeOfProperty, String id, AddressProperty address, String desc, double totalArea, double value, Status situation) {
        super(typeOfProperty, id, address, desc, totalArea, value, situation);
    }

    public Land setPropertyDetails(double frontDimension, double sideDimension) {
        this.frontDimension = frontDimension;
        this.sideDimension = sideDimension;
        return this;
    }

    public Land(TypeOfProperty typeOfProperty, String id, String desc, double totalArea, double value, Status situation) {
        super(typeOfProperty, id, desc, totalArea, value, situation);
    }
    public Land() {}

    public double getFrontDimension() {
        return frontDimension;
    }

    public double getSideDimension() {
        return sideDimension;
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
                Value: $USD \{getValue()}
                Situation: \{getSituation()}

                PROPERTY DETAILS
                Front dimension: \{getFrontDimension()} m
                Side diemension: \{getSideDimension()} m
                -----------------------------------------------""";
    }
}