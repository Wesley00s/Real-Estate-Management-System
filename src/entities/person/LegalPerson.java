package entities.person;

import enumerations.PersonType;

public class LegalPerson extends Person {
    private long ein;

    public LegalPerson(PersonType personType, String personsName, Address personsAddress, Contact personsContact, String password, long ein) {
        super(personType, personsName, personsAddress, personsContact, password);
        this.ein = ein;
    }

    public long getEin() {
        return ein;
    }

    public void setEin(long ein) {
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