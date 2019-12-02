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

public class MorrisonsWebScraper extends WebScraper {

    /**
     * Constructor.
     */
    MorrisonsWebScraper() {

    }

    @Override
    public void run() {
        stopThread = false;

        while (!stopThread) {

            String item = "cheese";

            /** Download HTML document from website. */
            Document doc = null;
            try {
                doc = Jsoup.connect("https://groceries.morrisons.com/search?entry=cheese" + item).get();
            } catch (IOException ex) {
                Logger.getLogger(SainsburysWebScraper.class.getName()).log(Level.SEVERE, null, ex);
            }

            /** Get all of the products on the page. */
            Elements products = doc.select(".fops-item");

            /** Work through the products. */
            for (int i = 0; i < products.size(); ++i) {

                /** Get the product description. */
                Elements productDescriptions = products.get(i).select(".fop-description");

                /** Compare the descriptions pulled to the cheese types I have already defined. */
                String cheeseType = CheeseTypes.getType(productDescriptions.get(0).text());

                /** Get the URL. */
                Element link = products.select("a").first();
                String relHref = link.attr("href");
                String absHref = link.attr("abs:href");

                 /**
                 * Get the price.
                 */
                Elements priceDisplayed = products.get(i).select(".fop-price");
               


                /** Output the data downloaded. */
                System.out.println("DESCRIPTION: " + productDescriptions.text() + "; PRICE: " + priceDisplayed.text() + "; URL: " + absHref);
                String subString = new String();
                
          
                /** Create a new cheese bean from the class 'Cheese'.
                 */
                Cheese cheeseBean = new Cheese();

                /**
                 * Create a bean for cheese class.
                 */
                cheeseBean.setProductType(cheeseType);
                cheeseBean.setId(CheeseTypes.getTypeId(cheeseType));

                /**
                 * Create price comparison bean.
                 */
                PriceComparison priceComparison = new PriceComparison();
                priceComparison.setCheese(cheeseBean);
                priceComparison.setPrice(Double.parseDouble(subString.substring(1, priceDisplayed.text().length() - 2)));
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
