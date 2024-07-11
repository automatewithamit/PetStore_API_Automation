package api.petstore.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "getAllData")
    public String [][] getAllData(){
        String filename="path of the test data file";
        String sheetname = "";
        String [][] data = ExcelUtils.getExcelData(filename,sheetname);
        return  data;
    }
    @DataProvider(name = "getUserNameColumnValues")
    public String [] getUserNameColumnValues(){
        String filename="path of the test data file";
        String sheetname = "";
        String [] data = ExcelUtils.getUserNameColumnValues(filename,sheetname,"UserName");
        return  data;
    }
}
