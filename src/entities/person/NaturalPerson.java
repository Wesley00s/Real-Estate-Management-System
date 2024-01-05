package entities.person;

public class NaturalPerson extends Person {
    private long ssn;

    public NaturalPerson(String personsName, Address personsAddress, Contact personsContact, String password, long ssn) {
        super(personsName, personsAddress, personsContact, password);
        this.ssn = ssn;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
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
