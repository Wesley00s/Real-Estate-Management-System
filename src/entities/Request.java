package entities;

import entities.person.LegalPerson;
import entities.person.NaturalPerson;
import entities.person.Person;
import entities.properties.Property;

import static database.connection.Connect.updateSqlProperty;
import static enumerations.PersonType.NATURAL_PERSON;
import static enumerations.Status.SOLD;
import static services.Negotiation.makePropertyPurchase;
import static services.PropertyService.historyPropertyList;

public class Request {
    private String id;
    private Property propertyToBePurchase;
    private Person newOwner;
    private Person oldOwner;
    private boolean isApprovedRequest;
    public Request(String id, Property propertyToBePurchase, Person newOwner, Person oldOwner) {
        this.id = id;
        this.propertyToBePurchase = propertyToBePurchase;
        this.newOwner = newOwner;
        this.oldOwner = oldOwner;
    }

    public void approveRequest(Broker broker, boolean condition) {
        if (condition) {
            makePropertyPurchase(propertyToBePurchase, broker, newOwner, oldOwner);

            getPropertyToBePurchase().setStatus(SOLD);
            getNewOwner().getPropertyList().add(propertyToBePurchase);
            getOldOwner().getPropertyList().remove(propertyToBePurchase);

            historyPropertyList.add(propertyToBePurchase);
            updateSqlProperty(newOwner, oldOwner, propertyToBePurchase);
            this.isApprovedRequest = true;
        } else {
            System.out.println("Property purchase was non-approved!");
            this.isApprovedRequest = false;
        }
    }

    public Property getPropertyToBePurchase() {
        return propertyToBePurchase;
    }

    public void setPropertyToBePurchase(Property propertyToBePurchase) {
        this.propertyToBePurchase = propertyToBePurchase;
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

    @Override
    public String toString() {
        return STR."""

                Request ID: \{getId()}
                Old owner registration number: \{getOldOwner().getPersonType().equals(NATURAL_PERSON) ? ((NaturalPerson) getOldOwner()).getSsn() : ((LegalPerson) getOldOwner()).getEin()}
                New owner registration number: \{getNewOwner().getPersonType().equals(NATURAL_PERSON) ? ((NaturalPerson) getNewOwner()).getSsn() : ((LegalPerson) getNewOwner()).getEin()}
                Property ID: \{getPropertyToBePurchase().getId()}
                Property Price: $USD \{getPropertyToBePurchase().getPrice()}
                Was approved: \{isApprovedRequest ? "Yes" : "No"}
                ------------------------------------------------
                """;
    }
}
