# Real Estate Control System 🏡

Welcome to the Real Estate Control System, a comprehensive Java project designed to meet the dynamic requirements of real estate management. This system empowers real estate agencies to efficiently organize and manage information related to properties, owners, brokers, and clients.

## Technologies and Tools Used 🛠️
-  **☕ Java:** The core programming language for building the Real Estate Control System.

- **🛠 IntelliJ IDEA:** A powerful integrated development environment (IDE) used for Java development, ensuring a smooth and productive coding experience.

- **🐘PostgreSQL:** A robust and open-source relational database management system used to store and manage the project's data.

### 🏠 Property Management

The system caters to various property types, including houses, apartments, commercial rooms, farms, and land. Keep track of each property's status, whether on offer, sold, or rented.

### 🤝 Owner and Broker Relationships

Efficiently manage relationships with property owners and brokers. Owners can possess multiple properties, and brokers handle the negotiation and transaction process. The system records essential details such as owner and buyer/lessor information, transaction intermediation, and financial aspects.

### 💼 Transaction Details

For each property transaction, whether sale or lease, the system captures comprehensive information. Sales data includes the property amount, fees, taxes, and the agency's commission. Lease details cover monthly rent, rental duration, guarantor information, and deposit amount.

### 💰 Rent Management

In rental scenarios, the system ensures transparent financial transactions. The agency records all rent payments and promptly transfers the agreed-upon amount to the property owner after deducting their commission.

### 🤝 Broker Database

Maintain an up-to-date database of brokers working for the agency. Keep track of their contributions in serving clients and facilitating property transactions.

### 👥 Client Records

Clients, both property buyers/renters and property owners, are essential to the real estate agency. The system provides a comprehensive database to store and retrieve client information, enabling efficient communication and service.

## Setting Up the Database

### Step 1: Install PostgreSQL

1. Download PostgreSQL from [https://www.postgresql.org/download/](https://www.postgresql.org/download/) based on your operating system.
2. Follow the installation instructions provided on the official website.

### Step 2: Configure pgAdmin

1. Open pgAdmin, which is usually installed automatically with PostgreSQL.
2. Right-click on "Servers" and choose "Create" > "Server...".

### Step 3: Configure the Server

1. Fill in the following fields in the "Create - Server" window:
    - **Name:** RealEstateServer (or your preferred name).
    - **Host name/address:** localhost
    - **Port:** 5432
    - **Maintenance database:** postgres
    - **Username:** postgres
    - **Password:** 123456 (or the password you set during installation).

2. Click "Save" to create the server.

### Step 4: Import the SQL File

1. Clone the Real Estate Control System repository.
2. Connect to the server in pgAdmin.
3. Right-click on the database (usually named `postgres`) and choose `Query Tool`.
4. Open the SQL file in the project directory named `src/database/RealEstate.sql`. (Don't forget to read the documentation).
5. Execute the contents of the SQL file in the Query Tool to create tables and insert some fictional data.

### Step 5: Configure JDBC in the Java Project

1. Add the JDBC library to your project, downloadable from [https://jdbc.postgresql.org/](https://jdbc.postgresql.org/).
2. Configure JDBC in your Java code. Open the `Connect.java` file and adjust the JDBC import:

   ```java
   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.SQLException;

3. Ensure the PostgreSQL JDBC driver is in your classpath. If using Maven, add the following dependency to your `pom.xml`:

    ```xml
      <dependency>
          <groupId>org.postgresql</groupId>
          <artifactId>postgresql</artifactId>
          <version>42.7.1</version>
      </dependency>
        

## Getting Started

Follow these steps to set up and run the Real Estate Control System on your local machine:

1. **Clone this repository:**

    ```bash
    git clone https://github.com/Wesley00s/RealEstate.git
    ```

2. **Set up your Java development environment:**

   Ensure you have Java Development Kit (JDK) installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/javase-downloads.html).

3. **Import the project into your preferred IDE (such as IntelliJ IDEA):**

   Open your IDE and import the project using the provided build tool (e.g., Maven or Gradle).

4. **Configure PostgreSQL for data storage:**

    - Install PostgreSQL: [Download PostgreSQL](https://www.postgresql.org/download/)
    - Create a database named "RealEstate" with the username "postgres" and password "123456" (or adjust these settings in the `Connect.java` file).

5. **Run the application:**

   Build and run the application from your IDE. The application will be accessible at `http://localhost:8080` (or another specified port).

Now you are ready to start managing your real estate transactions seamlessly! Explore the functionalities of the Real Estate Control System and enhance your real estate management experience.
