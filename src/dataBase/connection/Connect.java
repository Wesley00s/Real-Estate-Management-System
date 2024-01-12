package dataBase.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dataBase.operations.LoadData.*;

public class Connect {
    public static void setSqlData() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcTest", "postgres", "123456")) {
            if (connection != null) {
                System.out.println("Database initialized successful.\n");
                ResultSet rsPerson = connection.createStatement().executeQuery("SELECT * FROM Person");
                ResultSet rsProperty = connection.createStatement().executeQuery("SELECT * FROM Property");
                loadSqlPerson(rsPerson, connection);
                loadSqlProperty(rsProperty, connection);
            } else {
                System.out.println("Database non-initialized.");
            }
        } catch (SQLException e) {
            System.out.println(STR."Error initialize database. \{e.getMessage()}");
        }
    }
}
