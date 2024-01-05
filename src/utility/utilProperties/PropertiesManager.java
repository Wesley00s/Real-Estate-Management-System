package utility.utilProperties;

import entities.properties.AddressProperty;
import enumerations.Situation;
import enumerations.TypeOfApart;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static enumerations.Situation.*;
import static enumerations.TypeOfApart.*;
import static services.PropertyService.registerPropertyMenu;
import static utility.MenuFormat.printMenu;

public class PropertiesManager {
    private static final Scanner sc = new Scanner(System.in);
    private static final int TOTAL_ATTEMPTS = 4;
    private static int attempts;
    private static boolean invalidData;

    private static boolean chances(int chances) {
        return chances <= 0;
    }

    public static AddressProperty addAddressProperty() {
        System.out.println("\n\t\t* ADD ADDRESS INFO");
        String city;
        String zipCode;
        String neighborhood;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) return null;

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the name of the city:");
            city = sc.nextLine();

        } while (city.trim().isEmpty());

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return null;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the zip code (5 digits):");
            zipCode = sc.nextLine();

            try {
                if(Integer.parseInt(zipCode) <= 0) {
                    System.out.println("Please, provide a valid zip code.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) return null;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the neighborhood name:");
            neighborhood = sc.nextLine();

        } while (neighborhood.trim().isEmpty());

        return new AddressProperty(city, zipCode, neighborhood);
    }

    public static String addDescription() {
        String desc;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) return null;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide a description of the property:");
            desc = sc.nextLine();

        } while (desc.trim().isEmpty());

        return desc;
    }

    public static double addTotalArea() {
        String totalArea;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the total area (m²):");
            totalArea = sc.nextLine();

            try {
                if (Double.parseDouble(totalArea) <= 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Double.parseDouble(totalArea);
    }

    public static double addValue() {
        String value;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the proposed value ($USD):");
            value = sc.nextLine();

            try {
                if (Double.parseDouble(value) <= 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Double.parseDouble(value);
    }

    public static Situation addSituation() {
        List<String> situations = List.of("SELL", "RENT", "RENT OR SALE", "CANCEL");

        while (true) {
            printMenu(situations, "SITUATION - Do you want?");
            switch (sc.nextLine().toUpperCase()) {
                case "S" -> {return FOR_SALE;}
                case "R" -> {return FOR_RENT;}
                case "T" -> {return SALE_OR_RENT;}
                case "B" -> {System.out.println("Canceling..."); registerPropertyMenu();}
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static int addFloorApart() {
        System.out.println("\n\t\t* ADD BUILDING INFO");
        String floorApart;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if(chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the floor of the aparment");
            floorApart = sc.nextLine();

            try {
                if(Integer.parseInt(floorApart) <= 0) {
                    System.out.println("Please, provide a valid floor number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }

        } while (invalidData);

        return Integer.parseInt(floorApart);
    }

    public static int addNumber() {
        String number;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if(chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the number of the aparment:");
            number = sc.nextLine();

            try {
                if(Integer.parseInt(number) <= 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Integer.parseInt(number);
    }

    public static String addBuildingName() {
        String buildingName;

        attempts = TOTAL_ATTEMPTS;
        do {
            if(chances(attempts--)) return null;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the building name:");
            buildingName = sc.nextLine();

        } while (buildingName.trim().isEmpty());

        return buildingName;
    }

    public static int addNumbOfRooms() {
        String numberOfRooms;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if(chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the number of rooms:");
            numberOfRooms = sc.nextLine();

            try {
                if(Integer.parseInt(numberOfRooms) <= 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Integer.parseInt(numberOfRooms);
    }

    public static int addParkingSpaces() {
        String spaces;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the distance from the city (KM):");
            spaces = sc.nextLine();

            try {
                if (Integer.parseInt(spaces) < 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Integer.parseInt(spaces);
    }

    public static int addYearBuilt() {
        String yearWasBuilt;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the year it was built:");
            yearWasBuilt = sc.nextLine();

            try {
                if (Integer.parseInt(yearWasBuilt) < 1000 && Integer.parseInt(yearWasBuilt) > LocalDate.now().getYear()) {
                    System.out.println("Please, provide a valid year.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Integer.parseInt(yearWasBuilt);
    }

    public static int addNumbOfFloors() {
        String numbOfFloors;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Enter the number of floors:");
            numbOfFloors = sc.nextLine();

            try {
                if (Integer.parseInt(numbOfFloors) < 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Integer.parseInt(numbOfFloors);
    }

    public static double addCondominiumValue() {
        String condominiumValue;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the value of the condominium ($USD):");
            condominiumValue = sc.nextLine();

            try {
                if (Double.parseDouble(condominiumValue) <= 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Double.parseDouble(condominiumValue);
    }

    public static TypeOfApart addTypeOfApart() {
        List<String> situations = List.of("STANDARD", "COMPACT", "LOFT", "STUDIO", "DUPLEX", "TRIPLEX", "COVERAGE", "CANCEL");

        while (true) {
            printMenu(situations, "APARTMENT - What type?");
            switch (sc.nextLine().toUpperCase()) {
                case "S" -> {return STANDARD;}
                case "C" -> {return COMPACT;}
                case "L" -> {return LOFT;}
                case "T" -> {return STUDIO;}
                case "D" -> {return DUPLEX;}
                case "U" -> {return TRIPLEX;}
                case "E" -> {return COVERAGE;}
                case "B" -> {System.out.println("Canceling..."); registerPropertyMenu();}
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static int addDistanceOfCity() {
        String distance;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the distance from the city (KM):");
            distance = sc.nextLine();

            try {
                if (Integer.parseInt(distance) < 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Integer.parseInt(distance);
    }

    public static double addFrontDimension() {
        String frontDimension;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the front dimension (m)");
            frontDimension = sc.nextLine();

            try {
                if (Double.parseDouble(frontDimension) <= 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Double.parseDouble(frontDimension);
    }

    public static double addSideDimension() {
        String sideDimension;

        attempts = TOTAL_ATTEMPTS;
        do {
            invalidData = false;
            if (chances(attempts--)) return 0;

            System.out.println(STR."(\{attempts + 1} Attempts) Provide the side dimension (m)");
            sideDimension = sc.nextLine();

            try {
                if (Double.parseDouble(sideDimension) <= 0) {
                    System.out.println("Please, provide a valid number.");
                    invalidData = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format.");
                invalidData = true;
            }
        } while (invalidData);

        return Double.parseDouble(sideDimension);
    }
}
