package entities.person;

import enumerations.PersonType;

public class LegalPerson extends Person {
    private int ein;

    public LegalPerson(PersonType personType, String personsName, String password, int ein) {
        super(personType, personsName, password);
        this.ein = ein;
    }

    public LegalPerson(PersonType personType, String personsName, Address personsAddress, Contact personsContact, String password, int ein) {
        super(personType, personsName, personsAddress, personsContact, password);
        this.ein = ein;
    }

    public int getEin() {
        return ein;
    }

    public void setEin(int ein) {
        this.ein = ein;
    }

    @Override
    public String toString() {
        return STR."""
                LEGAL PERSON
                Employer Identification Number (EIN): \{getEin()}
                Name: \{getPersonsName()}

                ADDRESS
                \{getPersonsAddress()}

                CONTACT
                \{getPersonsContact()}
                """;
    }
}