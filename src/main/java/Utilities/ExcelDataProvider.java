package Utilities;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
	@DataProvider(name = "excelData")
    public static Object[][] provideExcelData() {
        String filePath = "./src/test/resources/TestData.xlsx";
        String sheetName = "";
        return ExcelUtility.readExcelAsDataProvider(filePath, sheetName);
    }

}
