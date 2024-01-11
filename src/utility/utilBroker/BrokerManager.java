package utility.utilBroker;

import entities.person.Person;

import static services.PropertyService.personsList;

public class BrokerManager {
    public static void seeListOfOwners() {
        for (Person person : personsList) {
            System.out.println(person);
        }
    }
}
