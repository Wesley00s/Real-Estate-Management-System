package entities.properties;

import enumerations.Situation;

public class Land extends Property {
    private Double frontDimension;
    private Double sideDimension;
    
    public Land(String id, Address address, String desc, double totalArea, double value, Situation situation) {
        super(id, address, desc, totalArea, value, situation);
    }

    public Land setPropertyDetails(Double frontDimension, Double sideDimension) {
        if(frontDimension >= 0) {
            this.frontDimension = frontDimension;
        }
        if(sideDimension >= 0) {
            this.sideDimension = sideDimension;
        }
        return this;
    }

    public Double getFrontDimension() {
        return frontDimension;
    }

    public Double getSideDimension() {
        return sideDimension;
    }

    public void setFrontDimension(Double frontDimension) {
        this.frontDimension = frontDimension;
    }

    public void setSideDimension(Double sideDimension) {
        this.sideDimension = sideDimension;
    }

    @Override
    public String toString() {
        return STR."""
            ID: \{getId()}

            ADDRESS
            \{getAddress()}
            Description: \{getDesc()}
            Total area: \{getTotalArea()} m²
            Value: $USD \{getValue()}
            Situation: \{getSituation()}

            PROPERTY DETAILS
            Front dimension: \{getFrontDimension()} m²
            Side diemension: \{getSideDimension()} m²
            """;
    }
}