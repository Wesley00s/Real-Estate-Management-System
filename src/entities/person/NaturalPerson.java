package entities.person;

public class NaturalPerson extends Person {
    private long ssn;
    public NaturalPerson(String personsName, Address personsAddress, Contact personsContact, long ssn) {
        super(personsName, personsAddress, personsContact);
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
                PERSON
                Social Security Number (SSN): \{getSsn()}
                Name: \{getPersonsName()}
                Address: \{getPersonsAddress()}
                Contact: \{getPersonsContact()}
                """;
    }
}
