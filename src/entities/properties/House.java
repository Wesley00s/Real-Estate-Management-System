package entities.properties;

import enumerations.Status;
import enumerations.TypeOfProperty;

public class House extends Property {
    private double buildingArea;
    private int totalNumberOfRooms;
    private int parkingSpaces;
    private int yearBuilt;
    private int numbFloors;

    public House(TypeOfProperty typeOfProperty, String rentID, AddressProperty address, String desc, double totalArea, double price, double rentValue, Status status) {
        super(typeOfProperty, rentID, address, desc, totalArea, price, rentValue, status);
    }

    public House setBuildingDetails(double buildingArea, int totalNumberOfRooms, int parkingSpaces, int yearBuilt, int numbFloors) {
        this.buildingArea = buildingArea;
        this.totalNumberOfRooms = totalNumberOfRooms;
        this.parkingSpaces = parkingSpaces;
        this.yearBuilt = yearBuilt;
        this.numbFloors = numbFloors;
        return this;
    }

    public House(TypeOfProperty typeOfProperty, String rentID, String desc, double totalArea, double price, double rentValue, Status status) {
        super(typeOfProperty, rentID, desc, totalArea, price, rentValue, status);
    }
    public House() {}

    public double getBuildingArea() {
        return buildingArea;
    }

    public int getTotalNumberOfRooms() {
        return totalNumberOfRooms;
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

    public void setTotalNumberOfRooms(int totalNumberOfRooms) {
        this.totalNumberOfRooms = totalNumberOfRooms;
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
            Price: $USD \{getPrice()}
            Rent value: \{getRentValue()}
            Status: \{getStatus()}

            BUILDING DETAILS
            Building area: \{getBuildingArea()} m²
            Total number of rooms: \{getTotalNumberOfRooms()}
            Parking spaces: \{getParkingSpaces()}
            Year built: \{getYearBuilt()}
            Total floors: \{getNumbFloors()}
            -----------------------------------------------""";
    }
}
