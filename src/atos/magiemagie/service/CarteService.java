/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.Random;

/**
 *
 * @author Administrateur
 */
public class CarteService {
    
    CarteDAO carteDAO = new CarteDAO();
    JoueurDAO joueurDAO = new JoueurDAO();
    PartieDAO PartieDAO = new PartieDAO();
    JoueurService joueurServ = new JoueurService();
    
    public Carte piocherCarte(Joueur joueur){
        Carte carte = new Carte();
                    Carte.TypeCarte ingredient[] = Carte.TypeCarte.values();
                    Random r = new Random();
                    int random = r.nextInt(ingredient.length);
                    
                   
                    carte.setTypeCarte(ingredient[random]);
                    carte.setJoueur(joueur);
                    carteDAO.addCarte(carte);
                    return carte;
                    
    }
    
    public void lancerSort(Long idCarte1, Long idCarte2, String joueurPseudo, Long idPartie){
        //Récuperation de la partie
        Partie partie = PartieDAO.rechercherParId(idPartie);
        
        //Récuperation du joueur
        Joueur joueur = joueurDAO.rechercherParPseudo(joueurPseudo);
        
        // Récupération des deux cartes choisies
        Carte carte1 = carteDAO.rechercherParId(idCarte1);
        Carte carte2 = carteDAO.rechercherParId(idCarte2);
        String card1Compare = carte1.getTypeCarte().toString();
        String card2Compare = carte2.getTypeCarte().toString();
        
        //Check dans le tableau des sorts si correspondance
        if ( (card1Compare == "CORNE_DE_LICORNE" && card2Compare == "BAVE_DE_CRAPAUD") || (card1Compare == "BAVE_DE_CRAPAUD" && card2Compare == "CORNE_DE_LICORNE"))
            carteDAO.sortInvisibilite();
        else if ( (card1Compare == "CORNE_DE_LICORNE" && card2Compare == "MANDRAGORE") || (card1Compare == "MANDRAGORE" && card2Compare == "CORNE_DE_LICORNE"))
            carteDAO.sortHypnose();
        else if ((card1Compare == "LAPIS_LAZULI" && card2Compare == "AILE_DE_CHAUVE_SOURIS") || (card1Compare == "AILE_DE_CHAUVE_SOURIS" && card2Compare == "LAPIS_LAZULI"))
            carteDAO.sortDivination();
        else if ((card1Compare == "LAPIS_LAZULI" && card2Compare == "BAVE_DE_CRAPAUD") || (card1Compare == "BAVE_DE_CRAPAUD" && card2Compare == "LAPIS_LAZULI"))
            carteDAO.sortPhiltreAmour();
        else if ((card1Compare == "AILE_DE_CHAUVE_SOURIS" && card2Compare == "MANDRAGORE") || (card1Compare == "MANDRAGORE" && card2Compare == "AILE_DE_CHAUVE_SOURIS"))
            carteDAO.sortSommeilProfond();
        
        //Supprime les cartes de la main du joueur
        carteDAO.supprimerCarte(carte1);
        carteDAO.supprimerCarte(carte2);
        
        //Passe à un autre joueur
        joueurServ.definirJoueurSuivant(joueurPseudo, idPartie);
        
        
    } 
    
    
}
