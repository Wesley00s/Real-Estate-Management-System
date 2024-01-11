package entities.properties;

import enumerations.Status;
import enumerations.TypeOfProperty;

public class House extends Property {
    private double buildingArea;
    private int numbRooms;
    private int parkingSpaces;
    private int yearBuilt;
    private int numbFloors;

    public House(TypeOfProperty typeOfProperty, String id, AddressProperty address, String desc, double totalArea, double value, Status situation) {
        super(typeOfProperty, id, address, desc, totalArea, value, situation);
    }

    public House setBuildingDetails(double buildingArea, int numbRooms, int parkingSpaces, int yearBuilt, int numbFloors) {
        this.buildingArea = buildingArea;
        this.numbRooms = numbRooms;
        this.parkingSpaces = parkingSpaces;
        this.yearBuilt = yearBuilt;
        this.numbFloors = numbFloors;
        return this;
    }

    public House() {}

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

    public void setParkingSpaces(Integer parkingSpaces) {
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
            Parking spaces: \{getParkingSpaces()}
            Year built: \{getYearBuilt()}
            Total floors: \{getNumbFloors()}
            -----------------------------------------------""";
    }
}
