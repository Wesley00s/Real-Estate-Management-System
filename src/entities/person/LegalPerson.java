package entities.person;

public class LegalPerson extends Person {
    private long ein;
    public LegalPerson(String personsName, Address personsAddress, Contact personsContact, long ein) {
        super(personsName, personsAddress, personsContact);
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