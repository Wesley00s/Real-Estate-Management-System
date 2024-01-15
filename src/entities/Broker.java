package entities;

import entities.person.Address;
import entities.person.Contact;

import java.time.LocalTime;

public class Broker {
    private int ssn;
    private String name;
    private Address address;
    private Contact contact;
    private String password;

    public Broker(int ssn, String name, Address address, Contact contact, String password) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.password = password;
    }
    public Broker(int ssn, String name, String password) {
        this.ssn = ssn;
        this.name = name;
        this.password = password;
    }

    public int getSsn() {
        return ssn;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String toString() {
        return STR."""
                Social Security Number (SSN): \{getSsn()}
                Name: \{getName()}

                ADDRESS
                \{getAddress()}

                CONTACT
                \{getContact()}
                -----------------------------------------
                """;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
