/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.main;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.CarteService;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrateur
 */
public class Main {
    JoueurService joueurServ = new JoueurService();
    CarteService carteServ = CarteService.instantiate();
    PartieService partieServ = new PartieService();
    CarteDAO carteDao = new CarteDAO();
    JoueurDAO joueurDao = new JoueurDAO();
    PartieDAO partieDao = new PartieDAO();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        JoueurService joueurServ = new JoueurService();
//        CarteService carteServ = CarteService.instantiate();
//        CarteDAO carteDao = new CarteDAO();

          
       //panelAccueil();
       new Main();



        
        
    }
    
    public Main(){
        panelAccueil();
    }
    
    
    public void panelAccueil(){
        System.out.println("Que souhaitez-vous faire?");
        System.out.println("1. Creer une nouvelle partie");
        System.out.println("2. Rejoindre une partie");
        System.out.println("3. Le saucisson, c'est bon!");
        
        Scanner actionScan = new Scanner(System.in);
        int actionChoix = actionScan.nextInt();
        
        if (actionChoix == 1) {
            System.out.println("Entrez le nom de la partie:");
            Scanner nomPartieScan = new Scanner(System.in);
            String nomPartie = nomPartieScan.nextLine();
            partieServ.creerNouvellePartie(nomPartie);
            System.out.println("La partie "+nomPartie+" a bien été créée.");
            panelAccueil();
            
        } else if (actionChoix == 2){
            
            List<Partie> partieNDM = partieServ.listerPartiesNonDemarrees();
            
            for (int i = 0; i < partieNDM.size(); i++) {
                
                System.out.println(partieNDM.get(i).getId()+": "+partieNDM.get(i).getNom());
                
            }
            System.out.println("Rejoindre quelle partie?");
            Scanner joinPartieScan = new Scanner(System.in);
            int joinPartie = joinPartieScan.nextInt();
            System.out.println("Combien de joueurs?");
            Scanner nbJoueurScan = new Scanner(System.in);
            int nbJoueur = nbJoueurScan.nextInt();
            
                for (int i = 0; i < nbJoueur; i++) {
                    System.out.println("Veuillez entrer le nom du Joueur "+i);
                    Scanner nomJoueurScan = new Scanner(System.in);
                    String nomJoueur = nomJoueurScan.nextLine();

                    System.out.println("Veuillez choisir votre sorcière");
                    Scanner avatarScan = new Scanner(System.in);
                    String avatar = avatarScan.nextLine();

                    joueurServ.rejoindrePartie(nomJoueur, avatar, joinPartie);
                }
                panelLobby(joinPartie);
            
        } else if (actionChoix == 3){
            
            System.out.println("Ca c'est bien vrai !");
            panelAccueil();
        
        } else {
            System.out.println("1, 2 ou 3, c'est trop compliqué pour ton cerveau étriqué? Allez recommence !");
            panelAccueil();
        }
        
        
        
    }
    
    
    private void panelLobby(long idPartie) {
        
        List<Joueur> joueurs = partieDao.getJoueurPartie(idPartie);
        System.out.println("Vous êtes actuellement "+joueurs.size());
        
        if (joueurs.size() < 2){
            System.out.println("Vous n'êtes plus assez pour jouer. Retour à l'accueil");
            panelAccueil();
        } else {
            System.out.println("Voulez vous lancer la partie? Y: Oui/ A: Ajouter / Q: Quit");
            Scanner startQuitScan = new Scanner(System.in);
            String startQuit = startQuitScan.nextLine();
            
            if (startQuit.equals("Y") ){
                partieServ.demarrerPartie(idPartie);
                panelPlateauDeJeu(idPartie);
            } else if (startQuit.equals("Q")){
                System.out.println("Combien de joueurs se retirent?");
                Scanner nbQuitScan = new Scanner(System.in);
                int nbQuit = nbQuitScan.nextInt();
                
                for (int i = 0; i < nbQuit; i++) {
                    System.out.println("Entrez le nom du joueur à retirer:");
                    Scanner joueurQuitScan = new Scanner(System.in);
                    String joueurQuit = startQuitScan.nextLine();
                    joueurServ.reOrder(idPartie, joueurQuit);
                    joueurDao.remove(joueurQuit);
                    System.out.println(joueurQuit+" a bien été retiré de la partie");
                }
                panelLobby(idPartie);
                
            } else if(startQuit.equals("A")){
                
                System.out.println("Combien de joueurs?");
                Scanner nbJoueurScan = new Scanner(System.in);
                int nbJoueur = nbJoueurScan.nextInt();
                
                for (int i = 0; i < nbJoueur; i++) {
                    System.out.println("Veuillez entrer le nom du Joueur "+i);
                    Scanner nomJoueurScan = new Scanner(System.in);
                    String nomJoueur = nomJoueurScan.nextLine();

                    System.out.println("Veuillez choisir votre sorcière");
                    Scanner avatarScan = new Scanner(System.in);
                    String avatar = avatarScan.nextLine();
                    joueurServ.rejoindrePartie(nomJoueur, avatar, idPartie);
                    
                    System.out.println(nomJoueur+" a été ajouté à la partie");
                }
                panelLobby(idPartie);
            } else {
                System.out.println("Je n'ai pas compris votre demande. Catapultes tes morts, je te prie");
                panelLobby(idPartie);
            }
            
        }
    }
    
    
    public void panelPlateauDeJeu(Long idPartie){
        
        int joueurActif = joueurDao.compteJoueurActif(idPartie);
        Joueur joueur = partieDao.JoueurALaMain(idPartie);
        
        if (joueurActif == 1){
            
            joueur.setEtatJoueur(Joueur.EtatJoueur.GAGNE);
            System.out.println(joueur.getPseudo()+" est le vainqueur du tournoi des sorcières !");
            
        } else {
        
        tourDeJeu(joueur.getPseudo(), idPartie);
        
        }
        
    }
    
    public void tourDeJeu(String pseudoJoueurActif, Long idPartie){
        System.out.println("C'est à "+pseudoJoueurActif+" de joueur !");
        System.out.println("Quelle est votre prochaine action? 0: Lancer un sort | 1: Passer son tour");
        joueurServ.afficherCarte(pseudoJoueurActif, idPartie);
        Scanner actionScan = new Scanner(System.in);
        int actionChoisie = actionScan.nextInt();
        
        
        if (actionChoisie == 0){
            System.out.println("--------------------------------------------------");
            System.out.println("---               Liste des sorts              ---");
            System.out.println("---                                            ---");
            System.out.println("--- Divination : Lapis & aile de ChS           ---");
            System.out.println("--- Invisibilité : Licorne & Crapaud           ---");
            System.out.println("--- Hypnose : Licorne & Mandragore             ---");
            System.out.println("--- Philtre d'Amour : Lapis & Crapaud          ---");
            System.out.println("--- Sommeil Profond : Aile de ChS & Mandragore ---");
            System.out.println("--------------------------------------------------");
            System.out.println("Choisissez la première carte");
            Carte carte1 = carteServ.choisirCarte();

            System.out.println("Choisissez la seconde carte");
            Carte carte2 = carteServ.choisirCarte();
    //        
            System.out.println("Vous avez choisi:"+carte1.getTypeCarte()+"&"+carte2.getTypeCarte());
       
            //Lancement du sort
            carteServ.lancerSort(carte1.getId(), carte2.getId(), pseudoJoueurActif, idPartie);
        } else if (actionChoisie == 1){
            
            joueurServ.passerSonTour(pseudoJoueurActif, idPartie);
            
        } else {
            System.out.println("Choix non valide, veuillez choisir un numéro de la liste");
            tourDeJeu(pseudoJoueurActif, idPartie);
        }
        
        List<Joueur> joueurs = partieDao.getJoueurPartie(idPartie);
        for (Joueur joueur : joueurs){
            if (joueur.getCartes().size() == 0)
                joueur.setEtatJoueur(Joueur.EtatJoueur.PERDU);
            
        }
        panelPlateauDeJeu(idPartie);
        
        
        
//        
    }

    

    
    
}
