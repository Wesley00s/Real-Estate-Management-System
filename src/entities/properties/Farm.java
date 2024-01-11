package entities.properties;

import enumerations.Status;
import enumerations.TypeOfProperty;

public class Farm extends Property {
    private double buildingArea;
    private int numbRooms;
    private int yearBuilt;
    private int distanceOfCity;

    public Farm(TypeOfProperty typeOfProperty, String id, AddressProperty address, String desc, double totalArea, double value, Status situation) {
        super(typeOfProperty, id, address, desc, totalArea, value, situation);
    }

    public Farm setBuildingDetails(double buildingArea, int numbRooms, int yearBuilt, int distanceOfCity) {
        this.buildingArea = buildingArea;
        this.numbRooms = numbRooms;
        this.yearBuilt = yearBuilt;
        this.distanceOfCity = distanceOfCity;
        return this;
    }

    public Farm() {}

    public double getBuildingArea() {
        return buildingArea;
    }

    public int getNumbRooms() {
        return numbRooms;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public int getDistanceOfCity() {
        return distanceOfCity;
    }

    public void setBuildingArea(double buildingArea) {
        this.buildingArea = buildingArea;
    }

    public void setNumbRooms(int numbRooms) {
        this.numbRooms = numbRooms;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setDistanceOfCity(int distanceOfCity) {
        this.distanceOfCity = distanceOfCity;
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

            BUILDING DETAILS
            Building area: \{getBuildingArea()} m²
            Total rooms: \{getNumbRooms()}
            Year built: \{getYearBuilt()}
            Distance od city: \{getDistanceOfCity()} KM
            -----------------------------------------------""";
    }
}
