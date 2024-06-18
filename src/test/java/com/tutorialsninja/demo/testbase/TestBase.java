package com.tutorialsninja.demo.testbase;

import com.tutorialsninja.demo.propertyreader.PropertyReader;
import com.tutorialsninja.demo.utilities.Utility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase extends Utility {
    String browser = PropertyReader.getInstance().getProperty("browser");

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        selectBrowser(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
       // closeBrowser();
    }
}
