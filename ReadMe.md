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
    

    docker exec -it petstore_container mysql -u root -p

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

1. Ensure your configuration points to the correct database setup:


    Configuration (e.g., config-container.properties)
    
    sqlDbUrl=jdbc:mysql://localhost:3307/petstore_database
    sqlDbUsername=petstoreuser
    sqlDbPassword=PetStore@123

2. Run your tests using your preferred test runner (e.g., Maven, TestNG).
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


MongoDB Setup for Automation Testing (Windows, Mac, Linux)
----------------------------------------------------------

Step 1: Install MongoDB
-

You have multiple options to install MongoDB, depending on your system. Choose the one appropriate for your setup.

Option 1: Install MongoDB Locally


    1. Windows:
    --------
    Download MongoDB from MongoDB Download Center. https://www.mongodb.com/try/download/community 
    Follow the installation instructions for your OS.

    2. MacOS (Homebrew):
    -----------------

    Run the following command to install MongoDB via Homebrew:
        brew tap mongodb/brew
        brew install mongodb-community@5.0

    Start MongoDB:
        brew services start mongodb/brew/mongodb-community
    3. Linux:
        Follow the instructions for your Linux distribution from the https://www.mongodb.com/docs/manual/installation/
    
Option 2: Set Up MongoDB Using Docker (Recommended for Testing)

    1. Pull the MongoDB Docker image: 
        docker pull mongo:latest
    2. Run the MongoDB container:
        docker run -d --name mongo_petstore_container -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin123 -p 27017:27017 mongo:latest

This will create a MongoDB container running on port 27017 with the username admin and password admin123.


Step 2: Set Up the Database and Collections
--------

a. Access the MongoDB Shell (optional, you can skip this if you plan to use automated test scripts for database setup):
    
    docker exec -it mongo_petstore_container mongosh

b. Create a Database and Collection for Your Tests:

    Create a new database and a collection for users:
        use petstoredb
        db.createCollection("users")
c. Insert Sample Data: You can insert sample data for testing:

        db.users.insertMany([
        { "username": "john.doe", "email": "john.doe@example.com", "firstName": "John", "lastName": "Doe" },
        { "username": "jane.smith", "email": "jane.smith@example.com", "firstName": "Jane", "lastName": "Smith" }
        ])

Step 3: Modify Configuration for MongoDB
-

    In your config.properties or a similar configuration file, add the MongoDB connection string:
    
    mongoDbUrl=mongodb://admin:admin123@localhost:27017
    mongoDbName=petstoredb

Step 4: Update Your Test Framework
-

1. MongoDB Connection Setup in Code: Make sure your MongoDB database connection is handled in your MongoDBDatabase class:
   public class MongoDBDatabase implements IMongoDatabase {

       private MongoClient mongoClient;
       private MongoDatabase database;
    
       @Override
       public void connect() {
       // Extracting the connection string and database name from the configuration file
       String connectionString = ConfigManager.getInstance().getProperty("mongoDbUrl");
       String dbName = ConfigManager.getInstance().getProperty("mongoDbName");
    
            // Creating the MongoDB client and connecting to the specified database
            this.mongoClient = MongoClients.create(connectionString);
            this.database = mongoClient.getDatabase(dbName);
       }
    
       @Override
       public void disconnect() {
       if (mongoClient != null) {
       mongoClient.close();
       }
       }
    
       @Override
       public MongoClient getClient() {
       return this.mongoClient;
       }
    
       @Override
       public MongoDatabase getDatabase(String dbName) {
       return this.database;
       }
    
       public Document findUserByUsername(String username) {
       return database.getCollection("users").find(new Document("username", username)).first();
       }
       }

2. Sample MongoDB Test: You can now write tests for MongoDB, for example, testing if you can retrieve a user by username:


      @Test
      public void testFindUserByUsername() {
          String username = "john.doe";
          MongoDBDatabase mongoDBDatabase = (MongoDBDatabase) database; // Cast to MongoDBDatabase
          Document user = mongoDBDatabase.findUserByUsername(username);
          Assert.assertNotNull(user, "User should exist in the database");
          Assert.assertEquals(user.getString("username"), username);
      }
Step 5: Running MongoDB Tests in the Framework
--
1. Start the MongoDB Container: Before running tests, ensure the MongoDB container is running:

       docker start mongo_petstore_container
2. Run Your Tests: Trigger your test suite that includes MongoDB tests.
3. Stop the MongoDB Container (optional): After running your tests, you can stop the container to free resources:

       docker stop mongo_petstore_container
