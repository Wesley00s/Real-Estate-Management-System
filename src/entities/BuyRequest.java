package entities;

import entities.person.LegalPerson;
import entities.person.NaturalPerson;
import entities.person.Person;
import entities.properties.Property;
import enumerations.TypeRequest;

import static database.connection.Connect.updateSqlProperty;
import static enumerations.PersonType.NATURAL_PERSON;
import static enumerations.Status.SOLD;
import static services.Negotiation.makePropertyPurchase;
import static services.PropertyService.historyBuyPropertyList;

public class BuyRequest extends Request {
    private boolean isApprovedRequest;

    public BuyRequest(String rentID, Property propertyToBePurchase, Person newOwner, Person oldOwner, TypeRequest typeRequest) {
        super(rentID, propertyToBePurchase, newOwner, oldOwner, typeRequest);
    }

    public void approveRequest(Broker broker, boolean condition) {
        if (condition) {
            makePropertyPurchase(getProperty(), broker, getNewOwner(), getOldOwner());

            getProperty().setStatus(SOLD);
            getNewOwner().getPropertyList().add(getProperty());
            getOldOwner().getPropertyList().remove(getProperty());

            historyBuyPropertyList.add(getProperty());
            updateSqlProperty(getNewOwner(), getOldOwner(), getProperty());
            this.isApprovedRequest = true;
        } else {
            System.out.println("Property purchase was non-approved!");
            this.isApprovedRequest = false;
        }
    }

    public String toString() {
        return STR."""

                Buy Request ID: \{getId()}
                Old owner registration number: \{getOldOwner().getPersonType().equals(NATURAL_PERSON) ? ((NaturalPerson) getOldOwner()).getSsn() : ((LegalPerson) getOldOwner()).getEin()}
                New owner registration number: \{getNewOwner().getPersonType().equals(NATURAL_PERSON) ? ((NaturalPerson) getNewOwner()).getSsn() : ((LegalPerson) getNewOwner()).getEin()}
                Property ID: \{getProperty().getId()}
                Property Price: $USD \{getProperty().getPrice()}
                Prperty rent value: $USD \{getProperty().getRentValue()}
                Was approved: \{isApprovedRequest ? "Yes" : "No"}
                ------------------------------------------------
                """;
    }
}
