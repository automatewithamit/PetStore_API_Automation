package api.petstore.testcases;

import api.petstore.interfaces.IBaseDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//
//To ensure that the executeQuery and other methods from SQLDatabase, OracleDatabase, and MongoDatabase are accessible,
//we need to correctly typecast the database field in the SQLDatabaseTests class.
//Additionally, we should ensure that the database field in DatabaseBaseTest is of the correct type (ISQLDatabase for SQL tests).
//Below is how you can achieve this:
//1. Ensure Proper Type Casting: When accessing methods specific to ISQLDatabase, we need to cast the database field properly.
//2. Generic Base Test Class: Create a generic DatabaseBaseTest class that can work with different types of databases.
//3. Use Polymorphism Correctly: Ensure each specific test class knows which database type it’s working with.

public abstract class BaseTest_DB<T extends IBaseDatabase> {
    protected T database;
    public static Logger Logger;
    public BaseTest_DB(){
        Logger = LogManager.getLogger("PetStore_API_Automation");
    }
    @BeforeClass
    public void setUp() {
        startDatabase();
        database = createDatabase();
        database.connect();
        Logger.info("Database connection established.");
    }

    @AfterClass
    public void tearDown() {
        if (database != null) {
            database.disconnect();
            Logger.info("Database connection closed.");
        }
        stopDatabase();
    }

    protected abstract T createDatabase();
    protected abstract String getDatabaseType();

    private void startDatabase() {
        try {
            String databaseType = getDatabaseType();
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder = new ProcessBuilder("powershell.exe", "./start-database.sh", databaseType);
            } else {
                processBuilder = new ProcessBuilder("/bin/bash", "-c", "./start-database.sh " + databaseType);
            }
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stopDatabase() {
        try {
            String databaseType = getDatabaseType();
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder = new ProcessBuilder("powershell.exe", "./stop-database.sh", databaseType);
            } else {
                processBuilder = new ProcessBuilder("/bin/bash", "-c", "./stop-database.sh " + databaseType);
            }
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
//    @BeforeClass
//    public void setUp() {
//        database = createDatabase();
//        database.connect();
//        Logger.info("Database connection established.");
//    }
//
//    @AfterClass
//    public void tearDown() {
//        if (database != null) {
//            database.disconnect();
//            Logger.info("Database connection closed.");
//        }
//    }
//
//    protected abstract T createDatabase();
}

