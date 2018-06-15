/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class MagieTest {
//   @Test
   public void demarreJPA(){
      Persistence.createEntityManagerFactory("PU");
  }
//   @Test
   public void ajouteCarte(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
       EntityManager em = factory.createEntityManager();
       
       Joueur player1 = new Joueur();
       player1.setPseudo("Yuura");
       
       em.getTransaction().begin();
       em.persist(player1);
       em.getTransaction().commit();
   }
//   @Test
   public void createPartie(){
       EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
       
       Partie partie = new Partie();
       
       
       em.getTransaction().begin();
       
       partie.setNom("Partie1");
       em.persist(partie);
       
       em.getTransaction().commit();
   }
    
}
