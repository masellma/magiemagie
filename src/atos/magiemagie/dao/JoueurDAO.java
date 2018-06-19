/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class JoueurDAO {
//    rechercher par pseudo
    /**
     * Renvoi le joueur dont le pseudo existe en base de donnée ou renvoie null si pas trouvé
     * @param pseudo
     * @return 
     */
    
   public Joueur rechercherParPseudo(String pseudo){
       
       EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
       
       Query query = em.createQuery("SELECT j FROM Joueur j JOIN j.partieActuelle p WHERE j.pseudo = :pseudo");
       query.setParameter("pseudo", pseudo);
       
       List<Joueur> joueursTrouves = query.getResultList();
       
       if (joueursTrouves.isEmpty()) 
           return null;
       
       
       return joueursTrouves.get(0);
   }
   
   public Joueur rechercherParOrdre(Long ordre, Long idPartie){
       
       EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
       
       Query query = em.createQuery("SELECT j FROM Joueur j JOIN j.partieActuelle p WHERE p.id = :idPartie AND j.ordre = :ordre ");
       query.setParameter("idPartie", idPartie);
       query.setParameter("ordre", ordre);
       
       List<Joueur> joueurTrouve = query.getResultList();
       if (joueurTrouve ==  null)
           return null;
       
       return joueurTrouve.get(0);
   }
   
   public long definirOrdre(Long idPartie){
       EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
       
       Query query = em.createQuery("SELECT COUNT(j.ordre) FROM Joueur j JOIN j.partieActuelle p WHERE p.id = :idPartie");
       query.setParameter("idPartie", idPartie);
       return (long) query.getSingleResult()+1;
   }

    public void ajouter(Joueur joueur) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.persist(joueur);
        em.getTransaction().commit();
    }

    public void modifier(Joueur joueur) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.merge(joueur);
        em.getTransaction().commit();
    }

    
   
   
}
