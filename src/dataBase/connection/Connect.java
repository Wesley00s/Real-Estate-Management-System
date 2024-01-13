package dataBase.connection;

import entities.person.*;
import entities.properties.Property;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static dataBase.operations.LoadData.*;
import static dataBase.operations.SendData.*;
import static enumerations.PersonType.LEGAL_PERSON;

public class Connect {
    private static final String url = "jdbc:postgresql://localhost:5432/jdbcTest";
    private static final String user = "postgres";
    private static final String password = "123456";
    public static void getSqlPropertyData() {
        try (Connection connection = DriverManager.getConnection(url, user,  password)) {
            if (connection != null) {
                loadSqlPerson(connection);
                loadSqlProperty(connection);
            } else {
                System.out.println("[getSqlPropertyData] Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."[getSqlPropertyData] Error initialize database. \{e.getMessage()}");
        }
    }

    public static void setSqlPersonalData(Person person, Address personAddress, Contact contact) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                sendSqlAddPerson(connection, person);
                int registrationNumber;
                if (person.getPersonType().equals(LEGAL_PERSON)) {
                    registrationNumber = ((LegalPerson) person).getEin();
                } else {
                    registrationNumber = ((NaturalPerson) person).getSsn();
                }
                sendSqlAddContact(connection, contact, registrationNumber);
                sendSqlAddAddress(connection, personAddress, registrationNumber);
            } else {
                System.out.println("[setSqlPersonalData] Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."[setSqlPersonalData] Error initialize database. \{e.getMessage()}");
        }
    }

    public static void setSqlPropertyData(Person person, Property property, Address address) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                sendSqlAddProperty(connection, property);
                sendAddPropertyOwner(connection, property, person);
                sendSqlAddPropertyAddress(connection, address, property.getId());
            } else {
                System.out.println("[setSqlPropertyData] Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."[setSqlPropertyData] Error initialize database. \{e.getMessage()}");
        }
    }

    public static void removeSqlPropertyData(String propertyID) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                sendSqlRemovePropertyAddress(connection, propertyID);
                sendSqlRemovePropertyOwner(connection, propertyID);
                sendSqlRemoveProperty(connection, propertyID);
            } else {
                System.out.println("[removeSqlPropertyData] Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."[removeSqlPropertyData] Error initialize database. \{e.getMessage()}");
        }
    }
}
