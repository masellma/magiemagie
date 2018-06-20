/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.main;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.service.CarteService;
import atos.magiemagie.service.JoueurService;
import java.util.Scanner;

/**
 *
 * @author Administrateur
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JoueurService joueurServ = new JoueurService();
        CarteService carteServ = CarteService.instantiate();
        CarteDAO carteDao = new CarteDAO();
        
//        System.out.println("Choisissez la première carte");
//        Scanner scan1 = new Scanner(System.in);
//        Long carte1 = scan1.nextLong();
//        Carte card1 = carteDao.rechercherParId(carte1);
//        
//        System.out.println("Choisissez la seconde carte");
//        Scanner scan2 = new Scanner(System.in);
//        Long carte2 = scan2.nextLong();
//        Carte card2 = carteDao.rechercherParId(carte2);
//        
//        System.out.println("Vous avez choisi:"+card1.getTypeCarte()+"&"+card2.getTypeCarte());
       
        
        //Lancement du sort
//        carteServ.lancerSort(carte1, carte2, "Baldir", 1L);
        joueurServ.passerSonTour("Angraal", 1L);
        
    }
    
}
