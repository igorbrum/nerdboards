package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ibrum
 */
public class JPAUtil {
    private static final String NOMEPU= "nerdboardsPU";
    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory(NOMEPU);
    
    public static EntityManager createManager(){
        return FACTORY.createEntityManager();
    }
}
