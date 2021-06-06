package com.newstart.qa.util;

import java.io.File;
import java.util.Hashtable;

public final class DataUtil {

    private DataUtil(){

    }

    private static final String GLOBAL_DATA_FOLDER = File.separator+ "src"+ File.separator+ "test"+ File.separator + "resources" + File.separator + "data"+ File.separator;

    public static Object[][] getTestData(final String excelWorkbookName, final String sheetName, final String workSheetName){
        ExcelReader datatable = new ExcelReader(System.getProperty("user.dir")+ GLOBAL_DATA_FOLDER+ excelWorkbookName);
        int testStartRowNum = 1;
        //reads data only for test case name
        int noOfRows = datatable.getRowCount(sheetName);
        while((!datatable.getCellValue(sheetName, testStartRowNum, 0).equals(workSheetName)) && (testStartRowNum <= noOfRows)){
            testStartRowNum++;
        }

        int colStartRowNum = testStartRowNum+ 1;
        int dataStartRowNum = testStartRowNum +2;

        // calculate rows of data
        int rows = 0;
        while(!datatable.getCellValue(sheetName, dataStartRowNum+ rows, 0).equals("")){
            rows++;
        }

        // calculate cols of data
        int cols=0;
        while(!datatable.getCellValue(sheetName, colStartRowNum, cols).equals("")){
            cols++;
        }
        Object[][] data = new Object[rows][1];
        //read the data
        int dataRow=0;
        Hashtable<String, String> table = null;
        for(int rNum = dataStartRowNum; rNum < dataStartRowNum+rows; rNum++){
            table = new Hashtable<String, String>();
            for(int cNum=0; cNum < cols; cNum++){
                String key = datatable.getCellValue(sheetName, colStartRowNum, cNum);
                String value = datatable.getCellValue(sheetName, rNum, cNum);
                table.put(key, value);
            }
            data[dataRow][0]=table;
            dataRow++;
        }
        return data;
    }
}
