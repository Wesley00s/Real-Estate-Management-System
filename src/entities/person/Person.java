package entities.person;

public abstract class Person {
    private String personsName;
    private Address personsAddress;
    private Contact personsContact;

    protected Person(String personsName, Address personsAddress, Contact personsContact) {
        this.personsName = personsName;
        this.personsAddress = personsAddress;
        this.personsContact = personsContact;
    }

    public String getPersonsName() {
        return personsName;
    }

    public Address getPersonsAddress() {
        return personsAddress;
    }

    public Contact getPersonsContact() {
        return personsContact;
    }

    public void setPersonsName(String personsName) {
        this.personsName = personsName;
    }

    public void setPersonsAddress(Address personsAddress) {
        this.personsAddress = personsAddress;
    }

    public void setPersonsContact(Contact personsContact) {
        this.personsContact = personsContact;
    }

    public abstract String toString();
}
