/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;

/**
 *
 * @author aldod
 */
public class Conexion {

    /**
     *Crea la conexion hacia la base de datos
     * @return Retorna la instancia de la base de datos requerida
     */
    public Connection getConexion() {
        Connection con = null;

        try {

            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            /* Obtener la conexion */
            con = DriverManager.getConnection("jdbc:mysql://localhost/adminTareas", "root", "");
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return con;
    }
}
