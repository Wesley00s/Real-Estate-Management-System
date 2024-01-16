package database.connection;

import entities.Broker;
import entities.person.*;
import entities.properties.Property;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static database.operations.LoadData.*;
import static database.operations.SendData.*;
import static database.operations.TransactionData.salesSqlTransaction;
import static enumerations.PersonType.LEGAL_PERSON;

public class Connect {
    private static final String url = "jdbc:postgresql://localhost:5432/RealEstate";
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
                sendAddProperty(connection, property, person);
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

    public static void updateSqlProperty(Person newOwner, Person oldOwner, Property property) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                salesSqlTransaction(connection, newOwner, oldOwner, property);
            } else {
                System.out.println("[updateSqlProperty] Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."[updateSqlProperty] Error initialize database. \{e.getMessage()}");
        }
    }

    public static void getSqlBroker() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                loadSqlBroker(connection);
            } else {
                System.out.println("[setSqlBroker] Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."[getSqlBroker] Error initialize database. \{e.getMessage()}");
        }
    }

    public static void setSqlBrokerData(Broker broker, Address address, Contact contact, int ssn) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                sendSqlAddBroker(connection, broker);
                sendSqlBrokerAddAddress(connection, address, ssn);
                sendSqlAddBrokerContact(connection, contact, ssn);
            } else {
                System.out.println("[setSqlBrokerData] Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."[setSqlBrokerData] Error initialize database. \{e.getMessage()}");
        }
    }

    public static void removeSqlBrokerData(int ssn) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                sendSqlRemoveBrokerContact(connection, ssn);
                sendSqlRemoveBrokerAddress(connection, ssn);
                sendSqlRemoveBroker(connection, ssn);
            } else {
                System.out.println("[removeSqlBrokerData] Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."[removeSqlBrokerData] Error initialize database. \{e.getMessage()}");
        }
    }
}
