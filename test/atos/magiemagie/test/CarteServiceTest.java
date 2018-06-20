/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.service.CarteService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class CarteServiceTest {
    
    private CarteService carteServ = CarteService.instantiate();
    
   @Test
   public void lancerSortInvisibiliteOK(){
       
       carteServ.lancerSort(13L, 14L, "Baldir", 1L);
   }
    
}
