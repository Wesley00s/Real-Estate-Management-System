-- DROP TABLE BrokerContact, BrokerAddress, Broker, propertyAddress, PropertyOwner, Property, Contact, PersonAddress, PropertyAddress, Person;

CREATE TABLE Person (
    RegistrationNumber INT PRIMARY KEY,
    PersonType VARCHAR(15) NOT NULL,
    Name VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    CONSTRAINT chk_PersonType CHECK (PersonType IN ('NATURAL_PERSON', 'LEGAL_PERSON'))
);

CREATE TABLE PersonAddress (
    AddressID VARCHAR(15) PRIMARY KEY,
    RegistrationPersonNumber INT UNIQUE,
    City VARCHAR(255) NOT NULL,
    ZipCode VARCHAR(10) NOT NULL,
    District VARCHAR(255),
    Street VARCHAR(255) NOT NULL,
    Number INT,
    FOREIGN KEY (RegistrationPersonNumber) REFERENCES Person (RegistrationNumber)
);

CREATE TABLE Property (
    PropertyID VARCHAR(15) PRIMARY KEY,
    PropertyType VARCHAR(15) NOT NULL,
    Description VARCHAR(255) NOT NULL,
    TotalArea FLOAT NOT NULL,
    Price FLOAT NOT NULL,
	RentValue FLOAT,
	Status VARCHAR(15) NOT NULL,
	FloorApart INT,
	FrontDimension FLOAT,
	SideDimension FLOAT,
	BuildingArea FLOAT,
	NumberApart INT,
	BuildingName VARCHAR(255),
	TotalRooms INT,
	ParkingSpaces INT,
	YearBuilt INT,
	TotalFloors INT,
	CondominiumValue FLOAT,
	DistanceOfCity FLOAT,
	ApartmentType VARCHAR(10)
);


CREATE TABLE PropertyAddress (
    AddressID VARCHAR(15) PRIMARY KEY,
    PropertyID VARCHAR(15) UNIQUE,
    City VARCHAR(255) NOT NULL,
    ZipCode VARCHAR(10) NOT NULL,
    District VARCHAR(255),
    FOREIGN KEY (PropertyID) REFERENCES Property (PropertyID)
);

CREATE TABLE Contact (
    ContactID VARCHAR(15) PRIMARY KEY,
    RegistrationPersonNumber INT UNIQUE,
    Email VARCHAR(255) NOT NULL,
    PhoneNumber INT NOT NULL,
    FOREIGN KEY (RegistrationPersonNumber) REFERENCES Person (RegistrationNumber)
);

CREATE TABLE PropertyOwner (
    PropertyID VARCHAR(15),
    RegistrationPersonNumber INT,
    FOREIGN KEY (PropertyID) REFERENCES Property (PropertyID),
    FOREIGN KEY (RegistrationPersonNumber) REFERENCES Person (RegistrationNumber)
);

CREATE TABLE Broker (
    Ssn INT PRIMARY KEY,
    Name VARCHAR(255),
    Password VARCHAR(255)
);

CREATE TABLE BrokerAddress (
    AddressID VARCHAR(15) PRIMARY KEY,
    Ssn INT UNIQUE,
    City VARCHAR(255) NOT NULL,
    ZipCode VARCHAR(10) NOT NULL,
    District VARCHAR(255),
    Street VARCHAR(255) NOT NULL,
    Number INT,
    FOREIGN KEY (Ssn) REFERENCES Broker (Ssn)
);

CREATE TABLE BrokerContact (
    ContactID VARCHAR(15) PRIMARY KEY,
    Ssn INT UNIQUE,
    Email VARCHAR(255) NOT NULL,
    PhoneNumber INT NOT NULL,
    FOREIGN KEY (Ssn) REFERENCES Broker (Ssn)
);

INSERT INTO Person (RegistrationNumber, PersonType, Name, Password)
VALUES
    (987654321, 'NATURAL_PERSON', 'John Smith', 'password123'),
    (876543210, 'NATURAL_PERSON', 'Emma Johnson', 'securePassword'),
    (654321098, 'NATURAL_PERSON', 'Pierre Dubois', 'parisPassword123'),
    (123456789, 'LEGAL_PERSON', 'Yuki Tanaka', 'tokyoSecure456'),
    (258123895, 'LEGAL_PERSON', 'Olivia Davis', 'sydneyPassword789');
	
INSERT INTO PersonAddress (AddressID, RegistrationPersonNumber, City, ZipCode, District, Street, Number)
VALUES
    ('A-550595055S', 987654321, 'New York', '10001', 'Manhattan', 'Broadway Street', 123),
    ('A-902065973S', 876543210, 'London', '78454', 'Westminster', 'Buckingham Palace Road', 456),
    ('A-999562001S', 654321098, 'Paris', '75001', 'Le Marais', 'Rue Saint-Antoine', 789),
    ('A-120902663S', 123456789, 'Tokyo', '10001', 'Chiyoda', 'Chiyoda City', 101),
    ('A-250098752S', 258123895, 'Sydney', '20008', 'Darling Harbour', 'George Street', 222);

INSERT INTO Property (PropertyID, PropertyType, Description, TotalArea, Price, RentValue, Status, FloorApart, FrontDimension, SideDimension, BuildingArea, NumberApart, BuildingName, TotalRooms, ParkingSpaces, YearBuilt, TotalFloors, CondominiumValue, DistanceOfCity, ApartmentType)
VALUES
	('P-762199459Y', 'APARTMENT', 'Modern Apartment with a View', 90.0, 800000.0, 6000.0, 'FOR_SALE', 5, -1, -1, -1, 203, 'City Heights', 2, -1, 1920, -1, 400000.0, -1, 'STUDIO'),
	('P-574301360Y', 'APARTMENT', 'Charming Parisian Loft', 110.5, 400000.0, 3500.0, 'FOR_RENT', 3, -1, -1, -1, 102, 'Le Chic Residence', 3, -1, 2010, -1, 250000.0,  -1, 'LOFT'),
    ('P-602199922Y', 'APARTMENT', 'Japanese Zen Style Apartment', 75.0, 700000.0, 4000.0, 'FOR_SALE', 8, -1, -1, -1, 801, 'Sakura Towers', 4, -1, 2015, -1, 300000.0, -1, 'STANDARD'),
    ('P-932849881Y', 'FARM', 'Spacious Countryside Farm', 500.0, 300000.0, 1000.0, 'FOR_SALE', -1, -1, -1, 300.0, -1, NULL, 5, -1, 1986, -1, -1, 10, NULL),
    ('P-149235343Y', 'FARM', 'Scenic Rural Retreat', 700.0, 450000.0, 1000.0, 'FOR_RENT', -1, -1, -1, 450.0, -1, NULL, 4, -1, 1992, -1, -1, 20, NULL),
    ('P-856300893Y', 'HOUSE', 'Cozy Suburban Home', 150.0, 500000.0, 2500.0, 'FOR_RENT', -1, -1, -1, 120.0, -1, NULL, 3, 3, 2010, 2, -1, -1, NULL),
    ('P-795346968Y', 'HOUSE', 'Spacious Residential House', 200.5, 350000.0, 3000.0, 'FOR_SALE', -1, -1, -1, 150.0, -1, NULL, 3, 1, 2000, 3, -1, -1, NULL),
    ('P-235566080Y', 'HOUSE', 'Modern Urban Residence', 180.0, 650000.0, 3000.0, 'FOR_RENT', -1, -1, -1, 100.0, -1, NULL, 4, 2, 1999, 2, -1, -1, NULL),
    ('P-103375631Y', 'LAND', 'Scenic Countryside Land', 1000.0, 100000.0, 800.0, 'FOR_SALE', -1, 20.0, 50.0, -1, -1, NULL, -1, -1, -1, -1, -1, -1, NULL);

INSERT INTO PropertyAddress (AddressID, PropertyID, City, ZipCode, District)
VALUES
    ('AP-220811563S', 'P-762199459Y', 'Los Angeles', '90001', 'Hollywood'),
    ('AP-966934057S', 'P-574301360Y', 'Paris', '75002', 'Le Marais'),
    ('AP-637089832S', 'P-602199922Y', 'Tokyo', '54899', 'Chuo'),
    ('AP-516422294S', 'P-932849881Y', 'Countryside', '12345', 'Green Valley'),
    ('AP-888799197S', 'P-149235343Y', 'Rural Area', '67890', 'Golden Fields'),
    ('AP-180057299S', 'P-856300893Y', 'Suburb', '54321', 'Green Street'),
    ('AP-304329455S', 'P-795346968Y', 'Residential Area', '98765', 'Sunset Boulevard'),
    ('AP-858083414S', 'P-235566080Y', 'Urban Neighborhood', '12345', 'Main Street'),
    ('AP-184456367S', 'P-103375631Y', 'Countryside', '54321', 'Green Valley');

INSERT INTO Contact (ContactID, RegistrationPersonNumber, Email, PhoneNumber)
VALUES
    ('C-686286334T', 987654321, 'person1@email.com', 987654321),
    ('C-458881639T', 876543210, 'person2@email.com', 123456780),
    ('C-732595323T', 654321098, 'person3@email.com', 234567801),
    ('C-339533394T', 123456789, 'person4@email.com', 345678012),
    ('C-612073344T', 258123895, 'person5@email.com', 456780123);

INSERT INTO PropertyOwner (PropertyID, RegistrationPersonNumber)
VALUES
    ('P-762199459Y', 987654321),
    ('P-574301360Y', 876543210),
    ('P-602199922Y', 654321098),
    ('P-932849881Y', 876543210),
    ('P-149235343Y', 654321098),
    ('P-856300893Y', 987654321),
    ('P-795346968Y', 258123895),
    ('P-235566080Y', 654321098),
    ('P-103375631Y', 654321098);

INSERT INTO Broker (Ssn, Name, Password)
VALUES
    (179348625, 'Visionnaire de Immobilier', 'immo123'),
    (201489875, 'Michael Broker', 'broker123'),
    (753194618, 'Sophia RealEstate', 'realestate456');

INSERT INTO BrokerAddress (AddressID, Ssn, City, ZipCode, District, Street, Number)
VALUES
    ('B-996030057R', 179348625, 'Lumièreville', '98741', 'Quartier Lumière', 'Avenue Éclatante', 584),
    ('B-201365447R', 201489875, 'Metropolis', '54321', 'Downtown', 'Main Street', 789),
    ('B-543198745R', 753194618, 'Sunshine City', '12345', 'Central District', 'Sunset Boulevard', 456);

INSERT INTO BrokerContact (ContactID, Ssn, Email, PhoneNumber)
VALUES
    ('C-102911534T', 179348625, 'brokeEmail@gamil.com', 655874895),
    ('C-526541111T', 201489875, 'michaelBroker@email.com', 985256981),
    ('C-962100021T', 753194618, 'sophiaRealestate@email.com', 987654323);
