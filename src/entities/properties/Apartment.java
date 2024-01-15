package entities.properties;

import enumerations.Status;
import enumerations.TypeOfApart;
import enumerations.TypeOfProperty;

public class Apartment extends Property {
    private int floorOfTheApart;
    private int numberOfTheApart;
    private String buildingName;
    private int totalNumberOfRooms;
    private int yearBuilt;
    private double condominiumValue;
    private TypeOfApart typeOfApart;

    public Apartment(TypeOfProperty typeOfProperty, String rentID, AddressProperty address, String desc, double totalArea, double price, double rentValue, Status status) {
        super(typeOfProperty, rentID, address, desc, totalArea, price, rentValue, status);
    }

    public Apartment setBuildingDetails(int floorOfTheApart, int numberOfTheApart, String buildingName, int totalNumberOfRooms, int yearBuilt) {
        this.floorOfTheApart = floorOfTheApart;
        this.numberOfTheApart = numberOfTheApart;
        this.buildingName = buildingName;
        this.totalNumberOfRooms = totalNumberOfRooms;
        this.yearBuilt = yearBuilt;
        return this;
    }

    public Apartment(TypeOfProperty typeOfProperty, String rentID, String desc, double totalArea, double price, double rentValue, Status status) {
        super(typeOfProperty, rentID, desc, totalArea, price, rentValue, status);
    }

    public Apartment() {}

    public Apartment setAdditionalDetails(double condominiumValue, TypeOfApart typeOfApart) {
        this.condominiumValue = condominiumValue;
        this.typeOfApart = typeOfApart;
        return this;
    }

    public int getFloorOfTheApart() {
        return floorOfTheApart;
    }

    public int getNumberOfTheApart() {
        return numberOfTheApart;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public int getTotalNumberOfRooms() {
        return totalNumberOfRooms;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public double getCondominiumValue() {
        return condominiumValue;
    }

    public TypeOfApart getTypeOfApart() {
        return typeOfApart;
    }

    public void setFloorOfTheApart(int floorOfTheApart) {
        this.floorOfTheApart = floorOfTheApart;
    }

    public void setNumberOfTheApart(int numberOfTheApart) {
        this.numberOfTheApart = numberOfTheApart;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setTotalNumberOfRooms(int totalNumberOfRooms) {
        this.totalNumberOfRooms = totalNumberOfRooms;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setCondominiumValue(double condominiumValue) {
        this.condominiumValue = condominiumValue;
    }

    public void setTypeOfApart(TypeOfApart typeOfApart) {
        this.typeOfApart = typeOfApart;
    }

    public String toString() {
        return STR."""
                Type of property: \{getTypeOfProperty()}
                ID: \{getId()}

                ADDRESS
                \{getAddress()}
                DESCRIPTION:
                "\{getDesc()}"
                Total area: \{getTotalArea()} m²
                Value: $USD \{getPrice()}
                Rent value: $USD \{getRentValue()}
                Status: \{getStatus()}

                BUILDING DETAILS
                Floor of the apartment: \{getFloorOfTheApart()}
                Number of the apartment: \{getNumberOfTheApart()}
                Building name: \{getBuildingName()}
                Total number of rooms: \{getTotalNumberOfRooms()}
                Year it was built: \{getYearBuilt()}

                ADDTIONAL DETAILS
                Codominium price: $USD \{getCondominiumValue()}
                Type of apartment: \{getTypeOfApart()}
                -----------------------------------------------""";
    }
}
