package entities.properties;

import entities.person.Person;
import enumerations.Status;
import enumerations.TypeOfProperty;

public abstract class Property {
    private Person owner;
    private TypeOfProperty typeOfProperty;
    private String id;
    private AddressProperty address;
    private String desc;
    private double totalArea;
    private double value;
    private Status situation;

    protected Property(TypeOfProperty typeOfProperty, String id, AddressProperty address, String desc, double totalArea, double value, Status situation) {
        this.typeOfProperty = typeOfProperty;
        this.id = id;
        this.address = address;
        this.desc = desc;
        this.totalArea = totalArea;
        this.value = value;
        this.situation = situation;
    }

    public Property() {}

    public Person getOwner() {
        return owner;
    }

    public TypeOfProperty getTypeOfProperty() {
        return typeOfProperty;
    }

    public String getId() {
        return id;
    }

    public AddressProperty getAddress() {
        return address;
    }

    public String getDesc() {
        return desc;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getValue() {
        return value;
    }

    public Status getSituation() {
        return situation;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setTypeOfProperty(TypeOfProperty typeOfProperty) {
        this.typeOfProperty = typeOfProperty;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(AddressProperty address) {
        this.address = address;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setSituation(Status situation) {
        this.situation = situation;
    }

    public abstract String toString();
}
