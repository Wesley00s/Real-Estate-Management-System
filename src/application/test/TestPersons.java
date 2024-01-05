package application.test;

import static services.PersonService.addNaturalPersons;
import static services.PersonService.personsLogin;
import static services.PropertyService.addProperties;

public class TestPersons {
    public static void main(String[] args) {
        addNaturalPersons();
        addProperties();

        personsLogin();
    }
}