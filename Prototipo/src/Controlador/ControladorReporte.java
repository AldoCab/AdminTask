/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.TareaDAO;
import Vista.viewReportes;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aldod
 */
public class ControladorReporte {
    viewReportes vistareportes = new viewReportes();
     TareaDAO modeloTarea = new TareaDAO();
DefaultTableModel modelot2;

    public ControladorReporte( viewReportes vistareportes, TareaDAO modeloTarea) {
        this.vistareportes =vistareportes;
        this.modeloTarea =modeloTarea;
        verReportes();
        
    }
    
    public void registrosReportes(JTable tablaReportes) {
        modelot2 = new DefaultTableModel();

        modelot2.addColumn("Nombre");
        modelot2.addColumn("Fecha Inicio");

        modelot2.addColumn("Fecha Fin");
        modelot2.addColumn("Usuario");
        modelot2.addColumn("Categoria");
        modelot2.addColumn("Tiempo");
        modelot2.addColumn("Tiempo interrupcion");
        modelot2.addColumn("Total interrupcion");
        modelot2.addColumn("Estado");

        tablaReportes.setModel(modelot2);

        Object[] columna = new Object[9];
        int nRegistros = modeloTarea.listReportes().size();

        for (int i = 0; i < nRegistros; i++) {
            columna[0] = modeloTarea.listReportes().get(i).getNombre();
            columna[1] = modeloTarea.listReportes().get(i).getFechaInicio();
            columna[2] = modeloTarea.listReportes().get(i).getFechaFin();
            columna[3] = modeloTarea.listReportes().get(i).getUsuarios();
            columna[4] = modeloTarea.listReportes().get(i).getCategoria();
            columna[5] = modeloTarea.listReportes().get(i).getTiempo();
            columna[6] = modeloTarea.listReportes().get(i).getTiempoInterrupcione();
            columna[7] = modeloTarea.listReportes().get(i).getTotalInterrupciones();
            columna[8] = modeloTarea.listReportes().get(i).getCompletado();

            modelot2.addRow(columna);
        }

    }
    
     public void verReportes(){
    registrosReportes(vistareportes.jtReportes);
    }
    
}
