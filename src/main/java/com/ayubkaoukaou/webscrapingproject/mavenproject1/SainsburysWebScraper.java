/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayubkaoukaou.webscrapingproject.mavenproject1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SainsburysWebScraper extends WebScraper {

    /**
     * Constructor
     */
    SainsburysWebScraper() {

    }

    @Override
    public void run() {
        stopThread = false;

        while (!stopThread) {

            String item = "cheese";

            /** Download HTML document from website. */
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.sainsburys.co.uk/webapp/wcs/stores/servlet/SearchDisplayView?catalogId=10241&storeId=10151&langId=44&krypto=Mx1Dv7IDyo0OEdCrdM2q7RNsJRU1mjfmqyular%2Fr%2BO54i%2BD1nJEPaMm4Rxy5IDedsMj17sgDhFdM93yuTLLEzd2xjAy8pQYiGhugaqOTQGA%3D#langId=44&storeId=10151&catalogId=10241&categoryId=&parent_category_rn=&top_category=&pageSize=60&orderB"
                        + "y=&searchTerm=cheese&beginIndex=0&hideFilters=true&categoryFacetId1=" + item).get();
            } catch (IOException ex) {
                Logger.getLogger(SainsburysWebScraper.class.getName()).log(Level.SEVERE, null, ex);
            }

            /** Get all of the products on the page. */
            Elements products = doc.select(".gridItem");

            /** Work through the products. */
            for (int i = 0; i < products.size(); ++i) {

                /** Get the product description. */
                Elements productDescriptions = products.get(i).select(".productInfo");

                /** Compare the descriptions pulled to the cheese types I have already defined. */
                String cheeseType = CheeseTypes.getType(productDescriptions.get(0).text());

                /** Get the URL. */
                Element link = products.select("a").first();
                String relHref = link.attr("href");
                String absHref = link.attr("abs:href");

                  /**
                 * Get the price.
                 */
                Elements priceDisplayed = products.get(i).select(".pricePerUnit");

                /** Output the data downloaded. */
                System.out.println("DESCRIPTION: " + productDescriptions.text() + "; PRICE: " + priceDisplayed + "; URL: " + absHref);

                /**
                 * Create a new cheese bean from the class 'Cheese'
                 */
                Cheese cheeseBean = new Cheese();

                /**
                 * Create a bean for cheese class
                 */
                cheeseBean.setProductType(cheeseType);
                cheeseBean.setId(CheeseTypes.getTypeId(cheeseType));
               
                /**
                 * Create price comparison bean
                 */
                PriceComparison priceComparison = new PriceComparison();
                priceComparison.setCheese(cheeseBean);
                priceComparison.setPrice(4);
                priceComparison.setDescription(productDescriptions.text());
                priceComparison.setUrl(absHref);
                priceComparison.setWeight(4);

                /**Add the cheese to the database. */
                System.out.println(cheeseCheckerDao);
                cheeseCheckerDao.init();
                cheeseCheckerDao.saveCheesePrice(priceComparison);

            }

            /** Sleep for sleep delay. */
            System.out.println(this.getClass().toString() + "sleeping for " + scrapeDelay_ms + "ms");
            try {
                sleep(scrapeDelay_ms);
            } catch (InterruptedException ex) {
                System.out.println("Sleep interrupted");
            }
        }
    }
}
