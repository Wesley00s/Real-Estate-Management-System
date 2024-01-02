package entities.properties;

import enumerations.Situation;

public abstract class Property {
    private String id;
    private Address address;
    private String desc;
    private Double totalArea;
    private Double value;
    private Situation situation;

    protected Property(String id, Address address, String desc, double totalArea, double value, Situation situation) {
        this.id = id;
        this.address = address;
        this.desc = desc;
        if (totalArea >= 0) {
            this.totalArea = totalArea;
        }
        if(value >= 0) {
            this.value = value;
        }
        this.situation = situation;
    }

    public String getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public String getDesc() {
        return desc;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public Double getValue() {
        return value;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public abstract String toString();
}
