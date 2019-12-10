package Paquete;

 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
//Imprescindible, crea el objeto sesion y gestiona la conexion a la BBDD
public class Utilidades {
	
	     private static SessionFactory sessionFactory;
     
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); //Esto es lo que hay que cambiar en cada nuevo ejercicio, dice donde esta el fichero xml
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }
        catch (Throwable ex) {
           
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

}