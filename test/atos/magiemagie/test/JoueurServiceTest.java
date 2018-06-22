/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class JoueurServiceTest {
    
    private JoueurService joueurService = new JoueurService();
    private PartieService partieService = new PartieService();
    private JoueurDAO joueurDao = new JoueurDAO();
    
//    @Test
    public void rejoindrePartieOK(){
        
        
        Partie nouvellePartie = partieService.creerNouvellePartie("Pathfinder");
        
        joueurService.rejoindrePartie("Cyrus", "blabla", nouvellePartie.getId());
        joueurService.rejoindrePartie("Baldir", "blabla", nouvellePartie.getId());
        joueurService.rejoindrePartie("Angraal", "blabla", nouvellePartie.getId());
        joueurService.rejoindrePartie("Alura", "blabla", nouvellePartie.getId());
    }
    
    
//    @Test
    public void orderJoueursOK(){
        Partie ordreJoueursOK = partieService.creerNouvellePartie("OrdreJoueursOK");
        joueurService.rejoindrePartie("Cyrus", "Melusine", ordreJoueursOK.getId());
        joueurService.rejoindrePartie("Baldir", "Quelaag", ordreJoueursOK.getId());
        Joueur jTest = joueurService.rejoindrePartie("Angraal", "Izalith", ordreJoueursOK.getId());
        
        assertEquals(3,(long) jTest.getOrdre());
        System.out.println(jTest.getOrdre());
    }
    
    @Test
    public void passerTourOK(){
        
//       Partie passerTourOK = partieService.creerNouvellePartie("PasserTourOk");
//       joueurService.rejoindrePartie("Cyrus", "Quelaag", passerTourOK.getId());
//       joueurService.rejoindrePartie("Baldir", "Izalith", passerTourOK.getId());
//       joueurService.rejoindrePartie("Angraal", "Melusine", passerTourOK.getId());
//       joueurService.rejoindrePartie("Alura", "Quelaana", passerTourOK.getId());
//       partieService.demarrerPartie(1L);
       joueurService.passerSonTour("Baldir", 1L);
//       joueurService.definirJoueurSuivant("Alura", 1L);
       
        
        
        
        
    }
//    @Test
    public void reOrderOK(){
        Joueur joueur = joueurDao.rechercherParPseudo("Aremis");
        
        joueurService.reOrder(joueur.getOrdre(), joueur.getPseudo());
        joueurDao.remove(joueur.getPseudo());
    }
    
    
}
