package api.petstore.utilities;

import api.petstore.constants.Constants;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "getAllData")
    public Object [][] getAllData(){
        String filename=System.getProperty("user.dir")+ Constants.PetStoreTestData_FILEPATH;
        String sheetname = Constants.PetStoreTestData_SheetName;
        Object [][] data = ExcelUtils.getExcelData(filename,sheetname);
        return  data;
    }
    @DataProvider(name = "getUserNameColumnValues")
    public String [] getUserNameColumnValues(){
        String filename=System.getProperty("user.dir")+ Constants.PetStoreTestData_FILEPATH;
        String sheetname = Constants.PetStoreTestData_SheetName;
        String [] data = ExcelUtils.getUserNameColumnValues(filename,sheetname,"Username");
        return  data;
    }
}
