package entities.properties;

import enumerations.Situation;
import enumerations.TypeOfApart;
import enumerations.TypeOfProperty;

public class Apartment extends Property {
    private int floorApart;
    private int number;
    private String buildingName;
    private int roomsNumber;
    private int yearBuilt;
    private double condominiumValue;
    private TypeOfApart typeOfApart;

    public Apartment(TypeOfProperty typeOfProperty, String id, AddressProperty address, String desc, double totalArea, double value, Situation situation) {
        super(typeOfProperty, id, address, desc, totalArea, value, situation);
    }

    public Apartment setBuildingDetails(int floorApart, int number, String buildingName, int roomsNumber, int yearBuilt) {
        this.floorApart = floorApart;
        this.number = number;
        this.buildingName = buildingName;
        this.roomsNumber = roomsNumber;
        this.yearBuilt = yearBuilt;
        return this;
    }

    public Apartment setAdditionalDetails(double condominiumValue, TypeOfApart typeOfApart) {
        this.condominiumValue = condominiumValue;
        this.typeOfApart = typeOfApart;
        return this;
    }

    public int getFloorApart() {
        return floorApart;
    }

    public int getNumber() {
        return number;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public int getRoomsNumber() {
        return roomsNumber;
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

    public void setFloorApart(int floorApart) {
        this.floorApart = floorApart;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
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
                Value: $USD \{getValue()}
                Situation: \{getSituation()}

                BUILDING DETAILS
                Floor of the apartment: \{getFloorApart()}
                Number: \{getNumber()}
                Building name: \{getBuildingName()}
                Rooms number: \{getRoomsNumber()}
                Year it was built: \{getYearBuilt()}

                ADDTIONAL DETAILS
                Codominium value: $USD \{getCondominiumValue()}
                Type of apartment: \{getTypeOfApart()}
                -----------------------------------------------""";
    }
}
