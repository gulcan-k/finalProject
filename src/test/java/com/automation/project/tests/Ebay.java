package com.automation.project.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Ebay {
    private WebDriver driver;
    private String URL = "http://ebay.com";

    /* Go to ebay --->          driver.get("http://ebay.com");
     * enter search term        input.sendKeys("java book");
     * click on search button   searchButton.click();
     * print number of results */
    @Test
    public void ebayTestSearchResults() throws Exception {
        driver.findElement(By.id("gh-ac")).sendKeys("java book");
        driver.findElement(By.id("gh-btn")).click();

        Thread.sleep(4000);

        WebElement searchResults = driver.findElement(By.tagName("h1"));
        //21,761 results for java book
        String resultActual = searchResults.getText();
        System.out.println(resultActual);
    }

    /* Go to ebay --->          driver.get("http://ebay.com");
     * enter search term        input.sendKeys("java book");
     * check zip code */
    @Test
    public void ebayTestGetZip() throws Exception {
        driver.findElement(By.id("gh-ac")).sendKeys("java book");
        driver.findElement(By.id("gh-btn")).click();

        Thread.sleep(4000);

        WebElement zipCode = driver.findElement(By.className("srp-controls--selected-value"));
        String actual = zipCode.getText();
        String expected = "30004";
        System.out.println("Actual zipcode: "+actual+ " - Expected zipcode: " +expected);
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
