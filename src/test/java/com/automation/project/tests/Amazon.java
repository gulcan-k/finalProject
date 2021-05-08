package com.automation.project.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Amazon {
    private WebDriver driver;
    private String URL = "http://amazon.com";

    /* go to amazon
     * enter search term
     * click on search button
     * verify title contains search term */
    @Test
    public void amazonTestGetTitleSearch() throws Exception {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("selenium book", Keys.ENTER);

        Thread.sleep(3000);

        String title = driver.getTitle();
        if (title.contains("selenium book")){
            assertTrue(true);
            System.out.println("TEST PASSED");
            System.out.println("Title: "+title);
        } else {
            System.out.println("TEST FAILED");
            assertTrue(false);
        }
    }

    /* go to amazon
     * get content of Select your address
     * verify content */
    @Test
    public void amazonTestGetContent() throws Exception {
        WebElement contentResults = driver.findElement(By.id("glow-ingress-line2"));
        String actual = contentResults.getText();
        String expected = "Select your address";
        System.out.println(actual);
        assertEquals(actual,expected);
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterMethod
    public void teardown(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
