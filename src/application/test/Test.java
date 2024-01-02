package application.test;

import entities.properties.*;

import static enumerations.Situation.*;
import static enumerations.TypeApart.*;

public class Test {
    public static void testApp() {
        String id = "H8f852L";
        Address address = new Address("Cityville", "123456", "Green Valley");
        String description = "Beautiful family home with a scenic view";
        double totalArea = 250.0;
        double value = 350000.0;
        double buildingArea = 200.0;
        int numbRooms = 4;
        int parkingSpaces = 2;
        int yearBuilt = 2020;
        int numbFloors = 2;

//        House sampleHouse = new House(id, address, description, totalArea, value, RENT)
//                .setBuildingDetails(buildingArea, numbRooms, parkingSpaces, yearBuilt, numbFloors);
//        System.out.println(sampleHouse);
//
//        Land sampleLand = new Land(id, address, description, totalArea, value, SALE)
//                .setPropertyDetails( 500.0, 250.0);
//        System.out.println(sampleLand);
//
        Apartment sampleApartment = new Apartment(id, address, description, totalArea, value, SOLD)
                .setBuildingDetails(5, -88, "Rundeskogen", 3, 2010)
                .setAdditionalDetails(190000, DUPLEX);
        System.out.println(sampleApartment);

        Farm sampleFarm = new Farm(id, address, description, totalArea, value, RENTED)
                .setBuildingDetails(buildingArea, numbRooms, yearBuilt, 40);
        System.out.println(sampleFarm);
    }
}
