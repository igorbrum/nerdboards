package rn;

import entity.Jogador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtil;

/**
 *
 * @author ibrum
 */
public class JogadorRN {
    
    public List<Jogador> listar() {
        EntityManager manager = JPAUtil.createManager();
        
        Query query = manager.createQuery("SELECT j FROM Jogador j");
        List<Jogador> listaJogadores = query.getResultList();
        return listaJogadores;
    }
    
    public Jogador buscarPorId(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Jogador jogador = manager.find(Jogador.class, id);
        manager.close();
        return jogador;
    }
    
    public Jogador inserir(Jogador jogador) {
        EntityManager manager = JPAUtil.createManager();
        
        manager.getTransaction().begin();
        manager.persist(jogador);
        manager.getTransaction().commit();
        
        manager.close();
        
        return (jogador);
    }
    
    public Jogador atualizar(Jogador jogador) {
        EntityManager manager = JPAUtil.createManager();
        
        manager.getTransaction().begin();
        jogador = manager.merge(jogador);
        manager.getTransaction().commit();
        
        manager.close();
        return (jogador);
    }
    
    public Jogador deletar(Long id) {
        EntityManager manager = JPAUtil.createManager();
        Jogador jogador = manager.find(Jogador.class, id);
        
        manager.getTransaction().begin();
        manager.remove(jogador);
        manager.getTransaction().commit();
        
        manager.close();
        
        return (jogador);
    }
}
