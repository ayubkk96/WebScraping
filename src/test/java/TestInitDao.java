
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName ("Test Init Dao")
public class TestInitDao {
    
     /** Spring injecting the session factory. */
     static SessionFactory sessionFactory;

     /** Getters and setters for sessionFactory.
     * @return  */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    } 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Test
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
    
     @AfterAll
        static void tearDownAll() { sessionFactory.close(); }
    
    
    
    
}
