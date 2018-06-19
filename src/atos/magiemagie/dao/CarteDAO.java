/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.text.html.HTML;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    
    JoueurDAO joueurDao = new JoueurDAO();
    PartieDAO partieDao = new PartieDAO();
    
    public void addCarte(Carte card) {
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
                
        em.getTransaction().begin();
        em.persist(card);
        em.getTransaction().commit();
        
    }

    public Carte rechercherParId(Long idCarte) {
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Query query = em.createQuery("SELECT c FROM Carte c JOIN c.joueur j WHERE c.id = :idCarte");
        query.setParameter("idCarte", idCarte);
        
        List<Carte> cartes = query.getResultList();
        return cartes.get(0);
    }

    public void sortInvisibilite(Long idPartie, String nomLanceur) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Query query = em.createQuery("SELECT j FROM Joueur j JOIN j.partieActuelle p WHERE p.id = :idPartie AND j.pseudo != :nomLanceur");
        query.setParameter("idPartie", idPartie);
        query.setParameter("nomLanceur", nomLanceur);
        
        List<Joueur> joueurs = query.getResultList();
        
        for (Joueur joueur : joueurs){
            volerUneCarte(joueur.getPseudo(), idPartie);
        }
    }

    public void sortHypnose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sortDivination() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sortPhiltreAmour() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sortSommeilProfond() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void supprimerCarte(Carte carte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void volerUneCarte(String pseudoLanceur, String pseudoCible, Long idPartie) {
        
        Joueur joueur = joueurDao.rechercherParPseudo(pseudoCible);
        Partie partie = partieDao.rechercherParId(idPartie);
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Query query = em.createQuery("SELECT c FROM Carte c JOIN c.joueur j JOIN j.partieActuelle p WHERE j.pseudo = :pseudo AND j.partieActuelle = :idPartie ");
        query.setParameter("pseudo", pseudoCible);
        query.setParameter("idPartie", idPartie);
        
        List<Carte> cartes = query.getResultList();
        
        //Création du random number
        Random r = new Random();
        int random = r.nextInt(cartes.size());
        Carte carteVolee = cartes.get(random);
        
        
    }
    
    
    
    
    
    
    
}
