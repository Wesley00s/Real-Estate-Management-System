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
    private double price;
    private double rentValue;
    private Status status;
    private double finalPrice;

    protected Property(TypeOfProperty typeOfProperty, String id, AddressProperty address, String desc, double totalArea, double price, double rentValue, Status status) {
        this.typeOfProperty = typeOfProperty;
        this.id = id;
        this.address = address;
        this.desc = desc;
        this.totalArea = totalArea;
        this.price = price;
        this.rentValue = rentValue;
        this.status = status;
    }

    protected Property(TypeOfProperty typeOfProperty, String id, AddressProperty address, String desc, double price, double rentValue, Status status) {
        this.typeOfProperty = typeOfProperty;
        this.id = id;
        this.address = address;
        this.desc = desc;
        this.price = price;
        this.rentValue = rentValue;
        this.status = status;
    }
    protected Property(TypeOfProperty typeOfProperty, String id, String desc, double totalArea, double price, double rentValue, Status status) {
        this.typeOfProperty = typeOfProperty;
        this.id = id;
        this.desc = desc;
        this.totalArea = totalArea;
        this.price = price;
        this.rentValue = rentValue;
        this.status = status;
    }
    protected Property(TypeOfProperty typeOfProperty, String id, String desc, double price, double rentValue, Status status) {
        this.typeOfProperty = typeOfProperty;
        this.id = id;
        this.desc = desc;
        this.price = price;
        this.rentValue = rentValue;
        this.status = status;
    }
    public Property() {
    }

    public void setFinalPrice(double total, double taxes) {
        this.finalPrice = total - taxes;
    }

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

    public double getPrice() {
        return price;
    }

    public Status getStatus() {
        return status;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public abstract String toString();

    public double getRentValue() {
        return rentValue;
    }

    public void setRentValue(double rentValue) {
        this.rentValue = rentValue;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}
