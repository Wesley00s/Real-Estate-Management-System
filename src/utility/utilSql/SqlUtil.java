package utility.utilSql;

import entities.person.Address;
import entities.person.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtil {
    public static Contact sqlContact(ResultSet rsContact) throws SQLException {
        Contact contact = null;
        while (rsContact.next()) {
            String contactID = rsContact.getString("ContactID");
            String email = rsContact.getString("Email");
            String phone = rsContact.getString("PhoneNumber");
            contact = new Contact(contactID, email, phone);
        }
        return contact;
    }

    public static Address sqlAddress(ResultSet rsPersonAddress) throws SQLException {
        Address address = null;
        while (rsPersonAddress.next()) {
            String addressID = rsPersonAddress.getString("AddressID");
            String city = rsPersonAddress.getString("City");
            String zipCode = rsPersonAddress.getString("ZipCode");
            String district = rsPersonAddress.getString("District");
            String street = rsPersonAddress.getString("Street");
            int number = rsPersonAddress.getInt("Number");

            address = new Address(addressID, city, zipCode, district, street, number);
        }
        return address;
    }
}
