/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayubkaoukaou.webscrapingproject.mavenproject1;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class OcadoWebScraper extends WebScraper {

    /**
     * Constructor
     */
    OcadoWebScraper() {

    }

    @Override
    public void run() {
        stopThread = false;

        while (!stopThread) {

            String item = "cheese";

            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.ocado.com/search?entry=cheese" + item).get();
            } catch (IOException ex) {
                Logger.getLogger(OcadoWebScraper.class.getName()).log(Level.SEVERE, null, ex);
            }
             /** Download HTML document from website. */
            System.out.println(doc.html());

            Elements products = doc.select(".fops-item");

            for (int i = 0; i < products.size(); ++i) {
                /**
                 * Get the product description.
                 */
                Elements productDescriptions = products.get(i).select(".fop-description");

                /**
                 * Compare the product description element with my list of
                 * cheese types and select the certain the types.
                 */
                String cheeseType = CheeseTypes.getType(productDescriptions.get(0).text());

                /**
                 * Get the URL.
                 */
                Element link = doc.select("a").first();
                String relHref = link.attr("href");
                String absHref = link.attr("abs:href");

                /**
                 * Get the price.
                 */
                Elements price1 = products.get(i).select(".fop-price");
                Elements priceDisplayed = price1.select(".fop-lpp");

                //Convert priceDisplayed to int
                // int price = Integer.parseInt(priceDisplayed.text());
                /**
                 * Get the weight.
                 */
                Elements weight = products.get(i).select(".fop-catch-weight");

                System.out.println("Description: " + productDescriptions.text() + "; URL: " + absHref + "; WEIGHT: "
                        + weight.text() + "; PRICE: " + priceDisplayed.text());

                /**
                 * Create a new cheese bean from the class 'Cheese'.
                 */
                Cheese cheeseBean = new Cheese();

                cheeseBean.setProductType(cheeseType);
                cheeseBean.setId(CheeseTypes.getTypeId(cheeseType));

                /**
                 * Create price comparison bean.
                 */
                PriceComparison priceComparison = new PriceComparison();
                priceComparison.setCheese(cheeseBean);
                priceComparison.setPrice(3);
                priceComparison.setDescription(productDescriptions.text());
                priceComparison.setUrl(absHref);
                priceComparison.setWeight(3);

                /**
                 * Add the cheese to my database.
                 */
                System.out.println(cheeseCheckerDao);
                cheeseCheckerDao.init();

                cheeseCheckerDao.saveCheesePrice(priceComparison);

            }

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
