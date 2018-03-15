/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aldod
 */
public class TareaDAO {
    Conexion conexion;

    /**
     *
     */
    public TareaDAO() {
        conexion = new Conexion();
    }

    /**
     *Metodo para insertar nuevas tareas
     * @param nombre Requiere el nombre de la nueva tarea
     * @param categoria Requiere el id de la categoria
     */
    public void insertarTareas(String nombre,int categoria) {
        try {
            String query = "CALL sp_nuevasTareas(?,?);";
            Connection acceso = conexion.getConexion();
            CallableStatement cs = acceso.prepareCall(query);
            cs.setString(1, nombre);
            cs.setInt(2, categoria);
            
            ResultSet i =cs.executeQuery();
            cs.close();
            acceso.close();

        } catch (SQLException ex) {
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Metodo para actualizar los registros de las tareas dentro de la base de datos
     * @param idTarea Id para identificar la tarea
     * @param nombre Nombre para actualizar la tarea
     * @param fechaInicio Fecha de inicio para actualizar la tarea
     * @param fechaFin Fecha de finalizacion para actualizar la tarea
     * @param usuarios Nombre de Usuario para actualizar la tarea
     * @param categoria Id para asociar la categoria y actualizar la tarea
     * @param tiempo Nuevo tiempo para actualizar la tarea
     * @param tiempoInterrupciones Nuevo tiempo en la pausas para actualizar la tarea
     * @param totalInterrupciones Nuevo total de pausas para actualizar la tarea
     * @param estado Nuevo estado para para actualizar la tarea
     */
    public void actualizarTareas(int idTarea,String nombre, String fechaInicio,
                               String fechaFin, String usuarios, String categoria,
                               int tiempo, int tiempoInterrupciones,int totalInterrupciones,
                               String estado) {
        try {
            String query = "CALL sp_nuevasTareas(?,?);";
            Connection acceso = conexion.getConexion();
            CallableStatement cs = acceso.prepareCall(query);
            cs.setInt(1, idTarea);
            cs.setString(2, fechaInicio);
            cs.setString(3, fechaFin);
            cs.setString(4, usuarios);
            cs.setInt(5, tiempo);
            cs.setString(6, nombre);
            cs.setInt(7, tiempoInterrupciones);
            cs.setInt(8, totalInterrupciones);
            cs.setString(9, estado);
            
           

            ResultSet i =cs.executeQuery();
            cs.close();
            acceso.close();

        } catch (SQLException ex) {
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *Lista las tareas incompletas dentro de la base de datos
     * @return Retorna un arraylista con los registros de la base de datos en tiempo real
     */
    public ArrayList<Tarea> listTarea() {
        ArrayList listaTarea = new ArrayList();
        Tarea tarea;

        try {
            Connection acceso = conexion.getConexion();
            String sql = "SELECT * FROM tareas where estado = 'I';";
            PreparedStatement prepConsulta = acceso.prepareStatement(sql);
            ResultSet resConsulta = prepConsulta .executeQuery();
            while (resConsulta.next()) {
                
                tarea = new Tarea();
                tarea.setIdTarea(resConsulta.getInt(1));
                tarea.setNombre(resConsulta.getString(2));
                tarea.setFechaInicio(resConsulta.getString(3));
                tarea.setFechaFin(resConsulta.getString(4));
                tarea.setUsuarios(resConsulta.getString(5));
                 tarea.setCategoria(resConsulta.getString(6));
                 tarea.setTiempo(resConsulta.getInt(7));
                 tarea.setTiempoInterrupcione(resConsulta.getInt(8));
                tarea.setTotalInterrupciones(resConsulta.getInt(9));
                tarea.setCompletado(resConsulta.getString(10));
                
                listaTarea.add(tarea);
            }
            
            prepConsulta .close();
            resConsulta.close();
            acceso.close();

        } catch (SQLException e) {
            System.out.println("error" + e);
        }
        return listaTarea;
    }
    
    public ArrayList<Tarea> listReportes() {
        ArrayList listaTarea = new ArrayList();
        Tarea tarea;

        try {
            Connection acceso = conexion.getConexion();
            String sql = "SELECT * FROM tareas;";
            PreparedStatement prepConsulta = acceso.prepareStatement(sql);
            ResultSet resConsulta = prepConsulta .executeQuery();
            while (resConsulta.next()) {
                
                tarea = new Tarea();
                tarea.setIdTarea(resConsulta.getInt(1));
                tarea.setNombre(resConsulta.getString(2));
                tarea.setFechaInicio(resConsulta.getString(3));
                tarea.setFechaFin(resConsulta.getString(4));
                tarea.setUsuarios(resConsulta.getString(5));
                 tarea.setCategoria(resConsulta.getString(6));
                 tarea.setTiempo(resConsulta.getInt(7));
                 tarea.setTiempoInterrupcione(resConsulta.getInt(8));
                tarea.setTotalInterrupciones(resConsulta.getInt(9));
                tarea.setCompletado(resConsulta.getString(10));
                
                listaTarea.add(tarea);
            }
            
            prepConsulta .close();
            resConsulta.close();
            acceso.close();

        } catch (SQLException e) {
            System.out.println("error" + e);
        }
        return listaTarea;
    }

   
    /**
     *Elimina un registro de la base de datos asociado a un id
     * @param id Identifica la tarea a eliminar
     */
    public void borrarTarea(int id) {
       
        try {
            Connection acceso = conexion.getConexion();
            String sql = "DELETE from tareas WHERE idTareas=?";
            PreparedStatement pS = acceso.prepareStatement(sql);

            pS.setInt(1, id);
           
            int i = pS.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }

            pS.close();
            acceso.close();

        } catch (SQLException ex) {
            System.out.println("Error" + ex.getErrorCode() + "" + ex.getMessage());
        }
    }
    
    /**
     *Marca como completado una tarea
     * @param id Identifica la tarea
     * @param estado Nuevo estado de la tarea
     */
    public void compTarea(int id, String estado){
        try {
            String query = "CALL sp_complTarea(?,?);";
            Connection acceso = conexion.getConexion();
            CallableStatement cs = acceso.prepareCall(query);
            cs.setString(1, estado);
            cs.setInt(2, id);
            
            ResultSet i =cs.executeQuery();
            cs.close();
            acceso.close();

        } catch (SQLException ex) {
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *Introduce el tiempo de pausas dentro de las tareas
     * @param id Identifica la tarea
     * @param pausa Tiempo de interrupción
     * @param tpausa Total de interrupciones
     */
    public void pausaTarea(int id,int pausa, int tpausa){
        try {
            String query = "CALL sp_pausaTarea(?,?,?);";
            Connection acceso = conexion.getConexion();
            CallableStatement cs = acceso.prepareCall(query);
            cs.setInt(1, id);
            cs.setInt(2, pausa);
            cs.setInt(3, tpausa);
            
            ResultSet i =cs.executeQuery();
            cs.close();
            acceso.close();

        } catch (SQLException ex) {
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *Inseta la duracion de una tarea
     * @param id Identifica la tarea
     * @param tiempo Tiempo de realizacion de la tarea
     */
    public void tiempoTarea(int id,int tiempo){
        try {
            String query = "CALL sp_tiempoTarea(?,?);";
            Connection acceso = conexion.getConexion();
            CallableStatement cs = acceso.prepareCall(query);
            cs.setInt(1, id);
            cs.setInt(2, tiempo);
            
            ResultSet i =cs.executeQuery();
            cs.close();
            acceso.close();

        } catch (SQLException ex) {
            Logger.getLogger(TareaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
