package api.petstore.testcases;

import api.petstore.databaseImpl.OracleDatabase;
import api.petstore.databaseImpl.SQLDatabase;
import api.petstore.interfaces.IDatabase;
import api.petstore.utilities.ConfigManager;
import api.petstore.utilities.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public static Logger Logger;
    protected IDatabase database;

    public BaseTest(){
        Logger = LogManager.getLogger("PetStore_API_Automation");
    }

    @BeforeClass
    public void setUp() {
        String dbType = ConfigManager.getInstance().getProperty("dbType");
        if ("oracle".equalsIgnoreCase(dbType)) {
            database = new OracleDatabase();
        } else if("sql".equalsIgnoreCase(dbType)){
            database = new SQLDatabase();
        }
        else {
            database = new SQLDatabase();
        }
        database.connect();
    }

    @AfterClass
    public void tearDown() {
        if (database != null) {
            database.disconnect();
        }
    }
}
