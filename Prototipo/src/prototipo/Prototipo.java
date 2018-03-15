/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipo;

import Controlador.Controlador;
import Modelo.CategoriaDAO;
import Modelo.TareaDAO;
import Vista.viewTarea;

/**
 *
 * @author aldod
 */
public class Prototipo {

    /**Inicia la aplicación 
     * Asocia los controladores 
     * Instancia y muestra la vista
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         viewTarea vistatarea = new viewTarea();
   TareaDAO modeloTarea = new TareaDAO();
   CategoriaDAO modCat = new CategoriaDAO();
   
   Controlador objcont = new Controlador(vistatarea, modeloTarea, modCat);
   vistatarea.setVisible(true);
   vistatarea.setLocationRelativeTo(null);
    }
    

  
    
    
}
