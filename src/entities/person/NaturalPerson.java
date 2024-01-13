package entities.person;

import enumerations.PersonType;

public class NaturalPerson extends Person {
    private int ssn;

    public NaturalPerson(PersonType personType, String personsName, String password, int ssn) {
        super(personType, personsName, password);
        this.ssn = ssn;
    }
    public NaturalPerson(PersonType personType, String personsName, Address personsAddress, Contact personsContact, String password, int ssn) {
        super(personType, personsName, personsAddress, personsContact, password);
        this.ssn = ssn;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return STR."""
                NATURAL PERSON
                Social Security Number (SSN): \{getSsn()}
                Name: \{getPersonsName()}

                ADDRESS
                \{getPersonsAddress()}

                CONTACT
                \{getPersonsContact()}
                """;
    }

}
