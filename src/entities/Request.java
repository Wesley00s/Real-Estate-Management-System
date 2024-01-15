package entities;

import entities.person.Person;
import entities.properties.Property;
import enumerations.TypeRequest;

public abstract class Request {
    private String id;
    private Property property;
    private Person newOwner;
    private Person oldOwner;
    private boolean isApproved;
    private TypeRequest typeRequest;

    public Request(String id, Property property, Person newOwner, Person oldOwner, TypeRequest typeRequest) {
        this.id = id;
        this.property = property;
        this.newOwner = newOwner;
        this.oldOwner = oldOwner;
        this.typeRequest = typeRequest;
    }

    public abstract void approveRequest(Broker broker, boolean condition);

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Person getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(Person newOwner) {
        this.newOwner = newOwner;
    }

    public Person getOldOwner() {
        return oldOwner;
    }

    public void setOldOwner(Person oldOwner) {
        this.oldOwner = oldOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract String toString();

    public TypeRequest getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(TypeRequest typeRequest) {
        this.typeRequest = typeRequest;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
