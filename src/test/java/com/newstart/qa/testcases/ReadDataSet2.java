package com.newstart.qa.testcases;

import com.newstart.qa.util.DataUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ReadDataSet2 {

    @Test(dataProvider = "fetchData")
    public void printData(Hashtable<String, String> data){
        System.out.println("String data: "+data.get("name"));
        System.out.println("Numeric data: "+data.get("age"));
    }

    @DataProvider
    public Object[][] fetchData(){
        return DataUtil.getTestData("TestData.xlsx","Data", getClass().getSimpleName());
    }
}
