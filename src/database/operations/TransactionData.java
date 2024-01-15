package database.operations;

import entities.person.LegalPerson;
import entities.person.NaturalPerson;
import entities.person.Person;
import entities.properties.Property;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static enumerations.PersonType.NATURAL_PERSON;

public class TransactionData {
    public static void salesSqlTransaction(Connection connection, Person newOwner, Person oldOwner, Property property) throws SQLException {
        int registrationPersonNumber;
        registrationPersonNumber = oldOwner.getPersonType().equals(NATURAL_PERSON) ? ((NaturalPerson) oldOwner).getSsn() : ((LegalPerson) oldOwner).getEin();
        String sql = "UPDATE PropertyOwner SET RegistrationPersonNumber = ? WHERE PropertyID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, registrationPersonNumber);
            preparedStatement.setString(2, property.getId());

            preparedStatement.executeUpdate();
        }
    }
}