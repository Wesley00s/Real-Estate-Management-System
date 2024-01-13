package entities.properties;

import enumerations.Status;
import enumerations.TypeOfProperty;

public class Farm extends Property {
    private double buildingArea;
    private int totalNumberOfRooms;
    private int yearBuilt;
    private double distanceOfCity;

    public Farm(TypeOfProperty typeOfProperty, String id, AddressProperty address, String desc, double totalArea, double price, Status status) {
        super(typeOfProperty, id, address, desc, totalArea, price, status);
    }

    public Farm setBuildingDetails(double buildingArea, int totalNumberOfRooms, int yearBuilt, double distanceOfCity) {
        this.buildingArea = buildingArea;
        this.totalNumberOfRooms = totalNumberOfRooms;
        this.yearBuilt = yearBuilt;
        this.distanceOfCity = distanceOfCity;
        return this;
    }

    public Farm(TypeOfProperty typeOfProperty, String id, String desc, double totalArea, double price, Status status) {
        super(typeOfProperty, id, desc, totalArea, price, status);
    }

    public Farm() {}

    public double getBuildingArea() {
        return buildingArea;
    }

    public int getTotalNumberOfRooms() {
        return totalNumberOfRooms;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public double getDistanceOfCity() {
        return distanceOfCity;
    }

    public void setBuildingArea(double buildingArea) {
        this.buildingArea = buildingArea;
    }

    public void setTotalNumberOfRooms(int totalNumberOfRooms) {
        this.totalNumberOfRooms = totalNumberOfRooms;
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
            Price: $USD \{getPrice()}
            Status: \{getStatus()}

            BUILDING DETAILS
            Building area: \{getBuildingArea()} m²
            Total number of rooms: \{getTotalNumberOfRooms()}
            Year built: \{getYearBuilt()}
            Distance of city: \{getDistanceOfCity()} KM
            -----------------------------------------------""";
    }
}
