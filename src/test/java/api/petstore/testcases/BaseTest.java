package api.petstore.testcases;

import api.petstore.utilities.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public static Logger Logger;
    public BaseTest(){
        Logger = LogManager.getLogger("PetStore_API_Automation");
    }

    @BeforeClass
    public void setUp() {
        DBUtils.connect();
    }

    @AfterClass
    public void tearDown() {
        DBUtils.disconnect();
    }
}
