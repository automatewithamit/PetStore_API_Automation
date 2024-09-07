package api.petstore.testcases;

import api.petstore.databaseImpl.OracleDatabase;
import api.petstore.databaseImpl.SQLDatabase;
import api.petstore.interfaces.IBaseDatabase;
import api.petstore.interfaces.IDatabase;
import api.petstore.utilities.ConfigManager;
import api.petstore.utilities.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    public static Logger Logger;

    public BaseTest() {
        Logger = LogManager.getLogger("PetStore_API_Automation");
    }

    @BeforeClass
    public void setUp() {

    }

    @AfterClass
    public void tearDown() {

    }

}
