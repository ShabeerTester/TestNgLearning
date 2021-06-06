package com.newstart.qa.testcases;

import com.newstart.qa.util.DataUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ReadDataSet1 {

    private int run = 1;

    @Test(dataProvider = "fetchData")
    public void printData(Hashtable<String, String> data) {
        System.out.println("Test run " + run);
        System.out.println("String data: " + data.get("stringData"));
        System.out.println("Numeric data: " + data.get("numericData"));
        System.out.println("Date data: " + data.get("dateData"));
        run++;
    }

    @DataProvider
    public Object[][] fetchData() {
        return DataUtil.getTestData("TestData.xlsx", "Data", getClass().getSimpleName());
    }
}
