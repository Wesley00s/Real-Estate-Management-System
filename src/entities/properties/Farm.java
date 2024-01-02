package entities.properties;

import enumerations.Situation;

public class Farm extends Property {
    private Double buildingArea;
    private Integer numbRooms;
    private Integer yearBuilt;
    private Integer distanceOfCity;

    public Farm(String id, Address address, String desc, double totalArea, double value, Situation situation) {
        super(id, address, desc, totalArea, value, situation);
    }

    public Farm setBuildingDetails(Double buildingArea, Integer numbRooms, Integer yearBuilt, Integer distanceOfCity) {
        if(buildingArea >= 0) {
            this.buildingArea = buildingArea;
        }
        if(numbRooms >= 0) {
            this.numbRooms = numbRooms;
        }
        if(yearBuilt > 1900 && yearBuilt <= 2024) {
            this.yearBuilt = yearBuilt;
        }
        if(distanceOfCity >= 0) {
            this.distanceOfCity = distanceOfCity;
        }
        return this;
    }

    public Double getBuildingArea() {
        return buildingArea;
    }

    public Integer getNumbRooms() {
        return numbRooms;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public Integer getDistanceOfCity() {
        return distanceOfCity;
    }

    public void setBuildingArea(Double buildingArea) {
        this.buildingArea = buildingArea;
    }

    public void setNumbRooms(Integer numbRooms) {
        this.numbRooms = numbRooms;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setDistanceOfCity(Integer distanceOfCity) {
        this.distanceOfCity = distanceOfCity;
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
            Year built: \{getYearBuilt()}
            Distance od city: \{getDistanceOfCity()} km
            """;
    }
}
