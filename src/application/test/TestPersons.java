package application.test;

import entities.person.Address;
import entities.person.Contact;
import entities.person.LegalPerson;
import entities.person.NaturalPerson;

public class TestPersons {
    public static void main(String[] args) {
        String personsName = "Davis";
        String city = "London";
        String zipCode = "99999999";
        String neighborhood = "Westminster";
        String street = "Abbey Road";
        int number = 78;

        Address address = new Address(city, zipCode, neighborhood, street, number);
        Contact contact = new Contact("mycontact@gmail.com", "66 55555-5555");
        NaturalPerson naturalPerson = new NaturalPerson(personsName, address, contact, 123456789);
        System.out.println(naturalPerson);
        LegalPerson legalPerson = new LegalPerson(personsName, address, contact, 123456789);
        System.out.println(legalPerson);
    }
}