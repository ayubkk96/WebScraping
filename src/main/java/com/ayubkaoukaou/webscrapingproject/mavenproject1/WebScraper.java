/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayubkaoukaou.webscrapingproject.mavenproject1;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;


abstract class WebScraper extends Thread implements CheeseTypes{
    
    
    /** Everything that is useful for the webscrapers extending this class. */
    
    protected int scrapeDelay_ms = 1000;
    
    protected boolean stopThread;
    
    CheeseCheckerDao cheeseCheckerDao = new CheeseCheckerDao();
    
    
    
    
  
    
  
    /** Stop scraping the websites if thread is true. */
    public void stopScraping() { 
    
    stopThread = true;
    
    }

    
    /** Getters and setters. */
    public int getScrapeDelay_ms() {
        return scrapeDelay_ms;
    }

    public void setScrapeDelay_ms(int scrapeDelay_ms) {
        this.scrapeDelay_ms = scrapeDelay_ms;
    }

    public boolean isStopThread() {
        return stopThread;
    }

    public void setStopThread(boolean stopThread) {
        this.stopThread = stopThread;
    }

    public CheeseCheckerDao getCheeseCheckerDao() {
        return cheeseCheckerDao;
    }

    public void setCheeseCheckerDao(CheeseCheckerDao cheeseCheckerDao) {
        this.cheeseCheckerDao = cheeseCheckerDao;
    }
    
    
    
    
}
    

