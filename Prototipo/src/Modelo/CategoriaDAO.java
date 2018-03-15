/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *Clase asociada al manejo de las categorias de las tareas
 * @author aldod
 */
public class CategoriaDAO {
    Conexion conexion;
    
    /**
     *Crea un objeto de la clase encargada a la conexion de la base de datos
     */
    public CategoriaDAO(){
        conexion = new Conexion();
    }
    
    /**
     *Lista todos los registros de categorias
     * @return Retorna una ArrayList con los datos de la base de datos
     */
    public ArrayList<Categoria> listCat() {
        ArrayList listaCategorias = new ArrayList();
        Categoria listCat;
        try {
            Connection acceso = conexion.getConexion();
            PreparedStatement ps = acceso.prepareStatement("SELECT * FROM tipostareas");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listCat = new Categoria();
                listCat.setIdCategoria(rs.getInt(1));
                listCat.setNombreCat(rs.getString(2));
                listaCategorias.add(listCat);
            }
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
        return listaCategorias;
    }
}

