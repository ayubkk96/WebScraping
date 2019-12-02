/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayubkaoukaou.webscrapingproject.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScraperManager {
     List<WebScraper> scraperList;
    
    
    
    
 
    public  void startScraping() throws InterruptedException {
        /** Create a list of web scrapers that are extended from the WebScraper class. */
    List<WebScraper> scraperList = new ArrayList();
    
    
    /** Create objects from the web scraping classes and add them to the scraper list. */
    //SainsburysWebScraper sainsburysScraper = new SainsburysWebScraper();
   // OcadoWebScraper ocadoWebScraper = new OcadoWebScraper();
    MorrisonsWebScraper morrisonsWebScraper = new MorrisonsWebScraper();
    
   // scraperList.add(sainsburysScraper);
   // scraperList.add(ocadoWebScraper);
    scraperList.add(morrisonsWebScraper);
    
    
    /** Loop through the list and find the webScrapers and then start the thread. */
    for (WebScraper webScraper : scraperList){
        webScraper.start();
    }
    
    /** If the user does not enter "stop" then the scanner will stay in continuous loop
     and wait until the user enters "stop". If the user types stop then stopScraping method from the WebScraper class is called 
     * and join will be executed. */
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        if (!userInput.equals("stop")) {
            while(!userInput.equals("stop")) {
                userInput = input.nextLine();
            }
        }
        else if (userInput.equals("stop")) {
            for(WebScraper webScraper : scraperList) {
                webScraper.stopScraping();
                webScraper.join();
            }
                
                }
    }
/** Getters and setters. */
    public List<WebScraper> getScraperList() {
        return scraperList;
    }

    public void setScraperList(List<WebScraper> scraperList) {
        this.scraperList = scraperList;
    }
    
    
    
    
}
