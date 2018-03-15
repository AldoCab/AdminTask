/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
/**
 * librerias importadas
 */


/**
 *
 * @author aldod
 */
public class Categoria {

      /**
     * @param nombreCat atributo globales de la clase
     * @param idCategoria atributo globales de la clase
     * @param conexion objeto de Connection
     */
     private int idCategoria;
    private String nombreCat;
   


    /**
     * Constructor 
     * 
     */
    public Categoria() {
       
    }

    /**
     *
     * @return string nombre de la categoria
     */
    public String getNombreCat() {
        return nombreCat;
    }

    /**
     *
     * @param nombreCat asignacion de valores a las variables globales
     */
    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    /**
     *
     * @return dato tipo entero
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria asignacion de valores a las variables globales
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

 

    
    
    

}
