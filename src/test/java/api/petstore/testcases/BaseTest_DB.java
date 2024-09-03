package api.petstore.testcases;

import api.petstore.interfaces.IBaseDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class BaseTest_DB<T extends IBaseDatabase> {
    protected T database;
    public static Logger Logger;

    public BaseTest_DB() {
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

    // Abstract method to create the specific database instance (SQL, Oracle, MongoDB, etc.)
    protected abstract T createDatabase();

    // Abstract method to get the type of the database (e.g., "sql", "oracle", "mongodb")
    protected abstract String getDatabaseType();

    private void startDatabase() {
        try {
            String databaseType = getDatabaseType();
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder = new ProcessBuilder("powershell.exe", "-ExecutionPolicy", "Bypass", "-File", "./start-database.ps1", databaseType);
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
                processBuilder = new ProcessBuilder("powershell.exe", "-ExecutionPolicy", "Bypass", "-File", "./stop-database.ps1", databaseType);
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
}
