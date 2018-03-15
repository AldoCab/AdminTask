/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author aldod
 * 
 * Clase usada para instanciar objetos
 */
public class Tarea {
     private int idTarea;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private String usuarios;
    private String categoria;
    private int tiempo;
    private int tiempoInterrupcione;
    private int totalInterrupciones;
    private String completado;

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(String usuarios) {
        this.usuarios = usuarios;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getTiempoInterrupcione() {
        return tiempoInterrupcione;
    }

    public void setTiempoInterrupcione(int tiempoInterrupcione) {
        this.tiempoInterrupcione = tiempoInterrupcione;
    }

    public int getTotalInterrupciones() {
        return totalInterrupciones;
    }

    public void setTotalInterrupciones(int totalInterrupciones) {
        this.totalInterrupciones = totalInterrupciones;
    }

    public String getCompletado() {
        return completado;
    }

    public void setCompletado(String completado) {
        this.completado = completado;
    }

   
 
    

    
    
    
    /*Objetos deben estar ecritos en minusculas*/
    //Tiempo interrupciones = new Tiempo();

    /*Los nombres de los metodos deberan estar escritus con un verbo la primera letra debera estar
    en minuscula, la primera letra de la segunda palabra en mayuscula
     */
    public void setTarea() {

    }

    /* Se debe comentar las acciones de los metodos asi como las funciones de las 
    variables, el algoritmo que se usa y de lo que se encarga una clase
     */
    
    
    
    public String getTarea(String tarea){
        if (tarea == null) {
            return "No se encuntra la tarea";
            /*Si la tareas esta vacia debera regresar un mensaje*/
        }else{
            return tarea;
        }
    
    }
}
