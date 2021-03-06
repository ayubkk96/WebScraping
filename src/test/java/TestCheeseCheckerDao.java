/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.ayubkaoukaou.webscrapingproject.mavenproject1.CheeseCheckerDao;
import com.ayubkaoukaou.webscrapingproject.mavenproject1.PriceComparison;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import org.hibernate.Session;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;

@DisplayName ("Test CheeseCheckerDao")
public class TestCheeseCheckerDao {
    
    static SessionFactory sessionFactory;
    
      /** Getters and setters for sessionFactory.
     * @return  */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    } 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
   @BeforeAll
     public void testInit() {
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
    
        

    @Test
    void testSaveCheese() {
        //Create instance of class we are going to test
        CheeseCheckerDao cheeseCheckerDao = new CheeseCheckerDao();
        PriceComparison priceComparison = new PriceComparison();
        
        cheeseCheckerDao.setSessionFactory(sessionFactory);
        
        //Create cheese with random name
        String randomName = String.valueOf(Math.random());
        testInit();
        priceComparison.setDescription(randomName);
        
        //Use CheeseCheckerDao to save cheese
        cheeseCheckerDao.saveCheesePrice(priceComparison);
        
        //Check that cheese exists in database
        //Get a new Session instance from the session factory
        Session session = sessionFactory.getCurrentSession();

        //Start transaction
        session.beginTransaction();

        //Get cheese with random name
        List<PriceComparison> cheeseList = session.createQuery("from Cheese where name='"+randomName+"'").getResultList();
        if(cheeseList.size() != 1)//One result should be stored
            fail("Cheese not successfully stored. Cheese list size: " + cheeseList.size());
        
        //Delete hat from database
        session.delete(cheeseList.get(0));
        
        //Commit transaction to save it to database
        session.getTransaction().commit();
        
        //Close the session and release database connection
        session.close();
    }
        @AfterAll
        static void tearDownAll() { sessionFactory.close(); }
    
   
}
