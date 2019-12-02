/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayubkaoukaou.webscrapingproject.mavenproject1;

//Hibernate imports
import java.util.List;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
public class CheeseCheckerDao {
    /** Spring injecting the session factory. */
     SessionFactory sessionFactory;

     /** Getters and setters for sessionFactory.
     * @return  */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    } 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
   /** initialising session factory*/
     public void init(){
        try {
            /** Create a builder for the standard service registry. */
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

            /** Load configuration from hibernate configuration file.
            Here we are using a configuration file that specifies Java annotations. */
            standardServiceRegistryBuilder.configure("hibernate.cfg.xml"); 

            /** Create the registry that will be used to build the session factory. */
            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
            try {
                /**Create the session factory - this is the goal of the init method. */
                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            }
            catch (Exception e) {
                    /* The registry would be destroyed by the SessionFactory, 
                        but we had trouble building the SessionFactory, so destroy it manually. */
                    System.err.println("Session Factory build failed.");
                    e.printStackTrace();
                    StandardServiceRegistryBuilder.destroy( registry );
            }

            /** Output result. */
            System.out.println("Session factory built.");

        }
        catch (Throwable ex) {
            /** Make sure you log the exception, as it might be swallowed. */
            System.err.println("SessionFactory creation failed." + ex);
        }
     }
     
     
     /** Shutdown the session in Hibernate. */
      public void shutDown(){
        sessionFactory.close();
    }
    
     public void saveCheesePrice(PriceComparison priceComparison ){
        /** Get a new Session instance from the SessionFactory. */
        Session session = sessionFactory.getCurrentSession();

        /** Start transaction. */
        session.beginTransaction();

        /** Add Cheese to database - will not be stored until we commit the transaction. */
        session.save(priceComparison);

        /** Commit transaction to save it to database. */
        session.getTransaction().commit();
        
        /** Close the session and release database connection. */
        session.close();
        System.out.println("Cheese added to database with ID: " + priceComparison.getId());
    }
     
     /**Search for the cheese */    
     public void searchCheese(){
         Session session = sessionFactory.getCurrentSession();
         
         session.beginTransaction();
         
         List<PriceComparison> cheeseList = session.createQuery("from PriceComparison where price = 3").getResultList();
         for(PriceComparison cheese: cheeseList) {
             System.out.println(cheese.toString());
         }
         session.close();
     }
     
     /** Update the cheese */;
     public void updateCheese() {
         
         
         
     }
     
     
}
