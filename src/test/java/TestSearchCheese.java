
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

@DisplayName("Test SearchCheese")
public class TestSearchCheese {

    static SessionFactory sessionFactory;

    /**
     * Getters and setters for sessionFactory.
     *
     * @return
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @BeforeAll
    public void testInit() {
        try {
            /**
             * Create a builder for the standard service registry.
             */
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

            /**
             * Load configuration from hibernate configuration file. Here we are
             * using a configuration file that specifies Java annotations.
             */
            standardServiceRegistryBuilder.configure("hibernate.cfg.xml");

            /**
             * Create the registry that will be used to build the session
             * factory.
             */
            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
            try {
                /**
                 * Create the session factory - this is the goal of the init
                 * method.
                 */
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                /* The registry would be destroyed by the SessionFactory, 
                        but we had trouble building the SessionFactory, so destroy it manually. */
                System.err.println("Session Factory build failed.");
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy(registry);
            }

            /**
             * Output result.
             */
            System.out.println("Session factory built.");

        } catch (Throwable ex) {
            /**
             * Make sure you log the exception, as it might be swallowed.
             */
            System.err.println("SessionFactory creation failed." + ex);
        }

    }

    
    /** Test search cheese
     * @param priceComparison */
    @Test
    public void testSearchCheese() {

        Session session = sessionFactory.getCurrentSession();

        CheeseCheckerDao cheeseCheckerDao = new CheeseCheckerDao();
        PriceComparison priceComparison = new PriceComparison();

        cheeseCheckerDao.setSessionFactory(sessionFactory);
 
        testInit();
        
        cheeseCheckerDao.searchCheese(priceComparison);
        
        session.close();
    }

    @AfterAll
    static void tearDownAll() {
        sessionFactory.close();
    }

}
