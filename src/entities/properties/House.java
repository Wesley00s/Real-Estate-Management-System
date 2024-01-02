package entities.properties;

import enumerations.Situation;

public class House extends Property {
    private double buildingArea;
    private int numbRooms;
    private int parkingSpaces;
    private int yearBuilt;
    private int numbFloors;

    public House(String id, Address address, String desc, double totalArea, double value, Situation situation) {
        super(id, address, desc, totalArea, value, situation);
    }

    public House setBuildingDetails(double buildingArea, int numbRooms, int parkingSpaces, int yearBuilt, int numbFloors) {
        this.buildingArea = buildingArea;
        this.numbRooms = numbRooms;
        this.parkingSpaces = parkingSpaces;
        this.yearBuilt = yearBuilt;
        this.numbFloors = numbFloors;
        return this;
    }

    public double getBuildingArea() {
        return buildingArea;
    }

    public int getNumbRooms() {
        return numbRooms;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public int getNumbFloors() {
        return numbFloors;
    }

    public void setBuildingArea(double buildingArea) {
        this.buildingArea = buildingArea;
    }

    public void setNumbRooms(int numbRooms) {
        this.numbRooms = numbRooms;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setNumbFloors(int numbFloors) {
        this.numbFloors = numbFloors;
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

            BUILDING DETAILS
            Building area: \{getBuildingArea()} m²
            Total rooms: \{getNumbRooms()}
            Parking spaces: \{getParkingSpaces()}
            Year built: \{getYearBuilt()}
            Total floors: \{getNumbFloors()}
            """;
    }
}
