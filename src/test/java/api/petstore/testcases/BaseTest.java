package api.petstore.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

    public static Logger Logger;
    public BaseTest(){
        Logger = LogManager.getLogger("PetStore_API_Automation");
    }
}
