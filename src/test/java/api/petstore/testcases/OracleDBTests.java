package api.petstore.testcases;
import api.petstore.databaseImpl.OracleDatabase;
import api.petstore.interfaces.IOracleDatabase;

public class OracleDBTests extends BaseTest_DB<IOracleDatabase> {

    @Override
    protected IOracleDatabase createDatabase() {
        return new OracleDatabase();
    }

    @Override
    protected String getDatabaseType() {
        return "oracledb";
    }
}
