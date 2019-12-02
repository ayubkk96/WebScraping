
import com.ayubkaoukaou.webscrapingproject.mavenproject1.CheeseCheckerDao;
import com.ayubkaoukaou.webscrapingproject.mavenproject1.PriceComparison;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaouk
 */

@DisplayName ("Test SearchCheese")
public class TestSearchCheese {
    static SessionFactory sessionFactory;
    
    @BeforeAll
    static void initAll() {
    
   try {
            //Create a builder for the standard service registry
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

            //Load configuration from hibernate configuration file.
            //Here we are using a configuration file that specifies Java annotations.
            standardServiceRegistryBuilder.configure("resources/hibernate.cfg.xml"); 

            //Create the registry that will be used to build the session factory
            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
            try {
                //Create the session factory - this is the goal of the init method.
                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            }
            catch (Exception e) {
                    /* The registry would be destroyed by the SessionFactory, 
                        but we had trouble building the SessionFactory, so destroy it manually */
                    System.err.println("Session Factory build failed.");
                    e.printStackTrace();
                    StandardServiceRegistryBuilder.destroy( registry );
            }
            //Ouput result
            System.out.println("Session factory built.");
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
            ex.printStackTrace();
        }
    }
    
    @Test
    public void testSearchCheese(){
        
     Session session = sessionFactory.getCurrentSession();
     
         CheeseCheckerDao cheeseCheckerDao = new CheeseCheckerDao();
         
        cheeseCheckerDao.setSessionFactory(sessionFactory);
        cheeseCheckerDao.init();
         session.beginTransaction();
         
         List<PriceComparison> cheeseList = session.createQuery("from PriceComparison where price = 3").getResultList();
         for(PriceComparison cheese: cheeseList) {
             System.out.println(cheese.toString());
         }
         session.close();
     }
    
    
}
