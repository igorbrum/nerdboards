package rn;

import entity.Mesa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ibrum
 */
public class MesaRN {
    private static final String NOMEPU= "nerdboardsPU";
    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory(NOMEPU);
    
    private static EntityManager createManager(){
        return FACTORY.createEntityManager();
    }
    
    public List<Mesa> listar() {
        EntityManager manager = createManager();
        
        Query query = manager.createQuery("SELECT m FROM Mesa m");
        List<Mesa> listaMesas = query.getResultList();
        return listaMesas;
    }
    
    public Mesa buscarPorId(Long id) {
        EntityManager manager = createManager();
        Mesa mesa = manager.find(Mesa.class, id);
        manager.close();
        return mesa;
    }
    
    public Mesa inserir(Mesa mesa) {
        EntityManager manager = createManager();
        
        manager.getTransaction().begin();
        manager.persist(mesa);
        manager.getTransaction().commit();
        
        manager.close();
        
        return (mesa);
    }
    
    public Mesa atualizar(Mesa mesa) {
        EntityManager manager = createManager();
        
        manager.getTransaction().begin();
        mesa = manager.merge(mesa);
        manager.getTransaction().commit();
        
        manager.close();
        return (mesa);
    }
    
    public Mesa deletar(Long id) {
        EntityManager manager = createManager();
        Mesa mesa = manager.find(Mesa.class, id);
        
        manager.getTransaction().begin();
        manager.remove(mesa);
        manager.getTransaction().commit();
        
        manager.close();
        
        return (mesa);
    }
}
