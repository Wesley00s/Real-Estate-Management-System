package entities.properties;

import enumerations.Situation;
import enumerations.TypeApart;

public class Apartment extends Property {
    private Integer floorApart;
    private Integer number;
    private String buildingName;
    private Integer roomsNumber;
    private Integer yearBuilt;
    private Double condominiumValue;
    private TypeApart typeApart;

    public Apartment(String id, Address address, String desc, double totalArea, double value, Situation situation) {
        super(id, address, desc, totalArea, value, situation);
    }

    public Apartment setBuildingDetails(int floorApart, int number, String buildingName, int roomsNumber, int yearBuilt) {
        if(floorApart >= 0) {
            this.floorApart = floorApart;
        }
        if(number >= 0) {
            this.number = number;
        }
        this.buildingName = buildingName;
        if(roomsNumber >= 0) {
            this.roomsNumber = roomsNumber;
        }
        this.yearBuilt = yearBuilt;
        return this;
    }

    public Apartment setAdditionalDetails(double condominiumValue, TypeApart typeApart) {
        if(condominiumValue >= 0) {
            this.condominiumValue = condominiumValue;
        }
        this.typeApart = typeApart;
        return this;
    }

    public Integer getFloorApart() {
        return floorApart;
    }

    public Integer getNumber() {
        return number;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public Integer getRoomsNumber() {
        return roomsNumber;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public Double getCondominiumValue() {
        return condominiumValue;
    }

    public TypeApart getTypeApart() {
        return typeApart;
    }

    public void setFloorApart(int floorApart) {
        this.floorApart = floorApart;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setRoomsNumber(Integer roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setCondominiumValue(Double condominiumValue) {
        this.condominiumValue = condominiumValue;
    }

    public void setTypeApart(TypeApart typeApart) {
        this.typeApart = typeApart;
    }

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
                Floor of the apartment: \{getFloorApart()}
                Number: \{getNumber()}
                Building name: \{getBuildingName()}
                Rooms number: \{getRoomsNumber()}
                Year construction: \{getYearBuilt()}

                ADDTIONAL DETAILS
                Codominium value: \{getCondominiumValue()}
                Type of apartment: \{getTypeApart()}
                """;
    }
}
