Setting Up MySQL Container for Testing
----------------------------------------
Prerequisites
---------------
Docker: Ensure Docker is installed and running on your machine.

Windows: Download and install Docker Desktop for Windows.

Mac: Download and install Docker Desktop for Mac.

Project Repository: Clone this project repository to your local machine.

Step 1: Pull the MySQL Docker Image
------------------------------------

Open a terminal (Command Prompt/PowerShell on Windows or Terminal on Mac) and run the following command to pull the latest MySQL image:
Command : docker pull mysql:latest

Step 2: Run the MySQL Container
-------------------------------
Run the following command to start the MySQL container:
docker run -d --name petstore_container -e MYSQL_ROOT_PASSWORD=PetStore@123 -e MYSQL_DATABASE=petstore_database -e MYSQL_USER=petstoreuser -e MYSQL_PASSWORD=PetStore@123 -p 3307:3306 mysql:latest

**Container Name:** petstore_container
**Environment Variables:**
**MYSQL_ROOT_PASSWORD:** Root password for MySQL (PetStore@123).
**MYSQL_DATABASE:** Name of the database (petstore_database).
**MYSQL_USER:** MySQL user (petstoreuser).
**MYSQL_PASSWORD:** Password for the MySQL user (PetStore@123).
**Port Mapping:** The MySQL container's port 3306 is mapped to port 3307 on the host machine.

Step 3: Access the MySQL Container
-----------------------------------
To interact with the MySQL database within the container:

1. Open the MySQL Shell:
   i. docker exec -it petstore_container mysql -u root -p

2. Enter the MySQL Root Password:
    i. When prompted, enter the root password (PetStore@123).
3. Select the Database: 
   i. USE petstore_database;

Step 4: Create the Database Schema and Insert Data
--------------------------------------------------
Create the necessary tables and insert test data:

1. Create the users Table:

CREATE TABLE IF NOT EXISTS users (
UserId INT PRIMARY KEY AUTO_INCREMENT,
Username VARCHAR(50) NOT NULL,
FirstName VARCHAR(50),
LastName VARCHAR(50),
Email VARCHAR(100),
Password VARCHAR(100),
Phone VARCHAR(20)
);
2. Insert Sample Data:

INSERT INTO users (Username, FirstName, LastName, Email, Password, Phone)
VALUES
('john.doe', 'John', 'Doe', 'john.doe@example.com', 'password123', '1234567890'),
('jane.smith', 'Jane', 'Smith', 'jane.smith@example.com', 'password123', '0987654321'),
('alice.jones', 'Alice', 'Jones', 'alice.jones@example.com', 'password123', '5555555555');

Step 5: Running Tests
---------------------

Ensure your configuration points to the correct database setup:

Configuration (e.g., config-container.properties)

sqlDbUrl=jdbc:mysql://localhost:3307/petstore_database
sqlDbUsername=petstoreuser
sqlDbPassword=PetStore@123

Run your tests using your preferred test runner (e.g., Maven, TestNG).
The tests will now connect to the MySQL container running on port 3307.

Step 6: Stopping and Restarting the Container
---------------------------------------------
1. To stop the container: docker stop petstore_container
2. To restart the container: docker start petstore_container

Notes
-----
Port Conflicts: If port 3307 is already in use on your host machine, choose another port.
Cross-Platform Compatibility: These steps should work seamlessly on both Windows and Mac systems. 
Ensure Docker is running before executing commands.