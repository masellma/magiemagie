/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

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
    
    @Test
    public void rejoindrePartieOK(){
        
        
        Partie nouvellePartie = partieService.creerNouvellePartie("Partie 1");
        
        
        joueurService.rejoindrePartie("maxime", "blabla", nouvellePartie.getId());
    }
    
}
