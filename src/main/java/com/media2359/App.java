package com.media2359;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.media2359.helper.HelperFunc;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class App {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("=== This is iOS Test Automation NHG  ===");
        System.out.println("based on document test case NHG phase 4 Duty Hour");

        // Clear directory result
        HelperFunc helperFunc = new HelperFunc();
        try {
            helperFunc.deleteDirectoryFile();
            System.out.println("Successfully remove pic");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.addListener(tla);

        List<String> suites = Lists.newArrayList();
        suites.add("iOSSuite.xml");// path to xml..

        testng.setTestSuites(suites);

        testng.run();

    }
}
