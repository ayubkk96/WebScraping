/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayubkaoukaou.webscrapingproject.mavenproject1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
         
        System.out.println("Github test");
        /** Tell Spring to create and wire beans with annotations */
      ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        /** Get web scraper manager bean */
        ScraperManager manager = (ScraperManager) context.getBean("scraperManager");
        
        /** Start the web scraping program */
       manager.startScraping();
        
        
       
        
        
        
        
        
 
    }
    
}
