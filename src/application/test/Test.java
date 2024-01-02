package application.test;

import entities.properties.*;

import static enumerations.Situation.*;
import static enumerations.TypeOfApart.*;
import static enumerations.TypeOfProperty.*;

public class Test {
    public static void main() {
        String id = "H8f852L";
        AddressProperty address = new AddressProperty("Cityville", "123456", "Green Valley");
        String description = "Beautiful family home with a scenic view";
        double totalArea = 250.0;
        double value = 350000.0;
        double buildingArea = 200.0;
        int numbRooms = 4;
        int parkingSpaces = 2;
        int yearBuilt = 2020;
        int numbFloors = 2;

        House sampleHouse = new House(HOUSE, id, address, description, totalArea, value, FOR_RENT)
                .setBuildingDetails(buildingArea, numbRooms, parkingSpaces, yearBuilt, numbFloors);
        System.out.println(STR."\{sampleHouse}===========================================================\n");

        Land sampleLand = new Land(LAND, id, address, description, totalArea, value, FOR_SALE)
                .setPropertyDetails( 500.0, 250.0);
        System.out.println(STR."\{sampleLand}===========================================================\n");

        Apartment sampleApartment = new Apartment(APARTMENT, id, address, description, totalArea, value, SOLD)
                .setBuildingDetails(5, 78, "Rundeskogen", 3, 2010)
                .setAdditionalDetails(190000.0, DUPLEX);
        System.out.println(STR."\{sampleApartment}===========================================================\n");

        Farm sampleFarm = new Farm(FARM, id, address, description, totalArea, value, RENTED)
                .setBuildingDetails(buildingArea, numbRooms, yearBuilt, 40);
        System.out.println(sampleFarm);
    }
}
