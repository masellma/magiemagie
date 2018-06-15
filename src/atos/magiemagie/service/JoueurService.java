/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class JoueurService {
    
    private JoueurDAO dao = new JoueurDAO();
    private PartieDAO partieDao = new PartieDAO();
    
    public void rejoindrePartie(String nomJoueur, String avatar, long idPartie){
        
        //Recherche si le joueur existe déjà
        Joueur joueur = dao.rechercherParPseudo(nomJoueur);
        if (joueur == null){
            // Le joueur n'existe pas encore
            joueur = new Joueur();
            joueur.setPseudo(nomJoueur);
            joueur.setNbPartiesGagnees(0);
            joueur.setNbPartiesJouees(0);
        }
        
        joueur.setAvatar(avatar);
        joueur.setEtatJoueur(Joueur.EtatJoueur.PAS_LA_MAIN);
        joueur.setOrdre(dao.definirOrdre(idPartie));
        
        // Associe le joueur à la partie et vice-versa
        Partie p = partieDao.rechercherParId(idPartie);
        joueur.setPartieActuelle(p);
        List<Joueur> listeJoueurs = p.getJoueurs();
        listeJoueurs.add(joueur);
        
        if (joueur.getId() ==  null) {
            dao.ajouter(joueur);
        } else {
            dao.modifier(joueur);
        }
        
        
    }
    
    
    
    
    
    
    
}
