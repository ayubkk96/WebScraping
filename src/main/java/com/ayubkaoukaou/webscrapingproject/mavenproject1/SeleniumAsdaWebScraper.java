/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayubkaoukaou.webscrapingproject.mavenproject1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author kaouk
 */
public class SeleniumAsdaWebScraper {
    
    public void showAsdaHTML(){
        //We need an options class to run headless - not needed if we want default options
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        //Create instance of web driver - this must be on the path.
        WebDriver driver = new ChromeDriver(options);

        //Navigate Chrome to page.
        driver.get("https://groceries.asda.com/search/cheese");

        //Wait for page to load
        try {
            Thread.sleep(3000);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        //Output the HTML of the page - should contain products
        System.out.println(driver.getPageSource());

        //Output details for individual products
        List<WebElement> cakeList = driver.findElements(By.className("co-product__anchor"));
        for (WebElement cake : cakeList) {
            System.out.println(cake.getText());
        }

        //Exit driver and close Chrome
        driver.quit();
    }
    
}
