package entities;

import entities.person.LegalPerson;
import entities.person.NaturalPerson;
import entities.person.Person;
import entities.properties.Property;
import enumerations.TypeRequest;

import static enumerations.PersonType.NATURAL_PERSON;
import static enumerations.Status.RENTED;
import static services.Negotiation.makePropertyRent;
import static services.PropertyService.historyRentPropertyList;

public class RentRequest extends Request{
    private boolean isApprovedRequest;

    public RentRequest(String rentID, Property propertyToBePurchase, Person renter, Person owner, TypeRequest typeRequest) {
        super(rentID, propertyToBePurchase, renter, owner, typeRequest);
    }

    public void approveRequest(Broker broker, boolean condition) {
        if (condition) {
            makePropertyRent(getProperty(), broker, getNewOwner(), getOldOwner());
            getProperty().setStatus(RENTED);
            historyRentPropertyList.add(getProperty());

            getNewOwner().getRentedList().add(getProperty());
            System.out.println("Property rented successfully!");
            this.isApprovedRequest = true;
        } else {
            System.out.println("Property rented was non-approved!");
            this.isApprovedRequest = false;
        }
    }

    public String toString() {
        return STR."""

                Rent Request ID: \{getId()}
                Renter registration number: \{getOldOwner().getPersonType().equals(NATURAL_PERSON) ? ((NaturalPerson) getOldOwner()).getSsn() : ((LegalPerson) getOldOwner()).getEin()}
                Owner registration number: \{getNewOwner().getPersonType().equals(NATURAL_PERSON) ? ((NaturalPerson) getNewOwner()).getSsn() : ((LegalPerson) getNewOwner()).getEin()}
                Property ID: \{getProperty().getId()}
                Rent price: $USD \{getProperty().getRentValue()}
                Was approved: \{isApprovedRequest ? "Yes" : "No"}
                ------------------------------------------------
                """;
    }
}