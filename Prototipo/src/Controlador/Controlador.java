/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.viewTarea;
import Modelo.*;
import Vista.viewReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aldod
 */
public class Controlador extends MouseAdapter implements ActionListener {

    long tiempoInicio = 0;
    long tiempoPausa;
    long duracion;
    //final Integer min=60000000000;
    viewTarea vistatarea = new viewTarea();
    
    TareaDAO modeloTarea = new TareaDAO();
    CategoriaDAO modCat = new CategoriaDAO();
    
    DefaultTableModel modelot;
    

    /**
     *
     * @param vistatarea Inicializa un objeto de la vista
     * @param modeloTarea Inicializa modelo de la tabla tareas
     * @param modCat Inicializa el modelo de la tabla categorias
     */
    public Controlador(viewTarea vistatarea, TareaDAO modeloTarea, CategoriaDAO modCat) {
        this.modeloTarea = modeloTarea;
        this.vistatarea = vistatarea;
        this.vistatarea.btnAgregar.addActionListener(this);
        this.vistatarea.btnCompletar.addActionListener(this);
        this.vistatarea.btnEditar.addActionListener(this);
        this.vistatarea.btnEliminar.addActionListener(this);
        this.vistatarea.btnPausar.addActionListener(this);
        this.vistatarea.btnIniciar.addActionListener(this);
        this.vistatarea.menReportes.addActionListener(this);

        cbCategorias(vistatarea.cbxCategoria);
        RegistrosCalc(vistatarea.jtbTareas);

    }

    /**
     * Asigna los nombres de las categorias dentro de un combobox
     *
     * @param comb Combobox traido desde la vista
     */
    public void cbCategorias(JComboBox comb) {
        int nRegistros = modCat.listCat().size();

        for (int i = 0; i < nRegistros; i++) {
            comb.addItem(modCat.listCat().get(i).getNombreCat());
        }
    }

    /**
     *Introduce los registros incompletos de la base de datos en la tabla
     * @param tablaAgregada Requiere de una jtable de la vista
     */
    public void RegistrosCalc(JTable tablaAgregada) {
        modelot = new DefaultTableModel();

        modelot.addColumn("Nombre");
        modelot.addColumn("Fecha Inicio");

        modelot.addColumn("Fecha Fin");
        modelot.addColumn("Usuario");
        modelot.addColumn("Categoria");
        modelot.addColumn("Tiempo");
        modelot.addColumn("Tiempo interrupcion");
        modelot.addColumn("Total interrupcion");
        modelot.addColumn("Estado");

        tablaAgregada.setModel(modelot);

        Object[] columna = new Object[9];
        int nRegistros = modeloTarea.listTarea().size();

        for (int i = 0; i < nRegistros; i++) {
            columna[0] = modeloTarea.listTarea().get(i).getNombre();
            columna[1] = modeloTarea.listTarea().get(i).getFechaInicio();
            columna[2] = modeloTarea.listTarea().get(i).getFechaFin();
            columna[3] = modeloTarea.listTarea().get(i).getUsuarios();
            columna[4] = modeloTarea.listTarea().get(i).getCategoria();
            columna[5] = modeloTarea.listTarea().get(i).getTiempo();
            columna[6] = modeloTarea.listTarea().get(i).getTiempoInterrupcione();
            columna[7] = modeloTarea.listTarea().get(i).getTotalInterrupciones();
            columna[8] = modeloTarea.listTarea().get(i).getCompletado();

            modelot.addRow(columna);
        }

    }
    
     

    /**
     *Inserta un registro nuevo en la base de datos
     * Actualiza el jtable de la vista
     */
    public void agregarR() {
        modeloTarea.insertarTareas(vistatarea.txtNombre.getText(), vistatarea.cbxCategoria.getSelectedIndex() + 1);
        RegistrosCalc(vistatarea.jtbTareas);
    }

    /**
     *Elimina un registro de la base de datos
     * Actualiza el jtable de la vista
     */
    public void borrarR() {
        int id = modeloTarea.listTarea().get(vistatarea.jtbTareas.getSelectedRow()).getIdTarea();
        modeloTarea.borrarTarea(id);
        RegistrosCalc(vistatarea.jtbTareas);
    }

    /**
     *Marca como completado la tarea seleccionada
     * Actualiza el jtable
     */
    public void complT() {

        int id = modeloTarea.listTarea().get(vistatarea.jtbTareas.getSelectedRow()).getIdTarea();
        modeloTarea.compTarea(id, "C");
        RegistrosCalc(vistatarea.jtbTareas);

    }

    /**
     *Inicia el conteo de tiempo de una tarea
     * Si hay una interrupcion activa guarda los datos dentro de la base de datos
     * Actualiza el jtable
     */
    public void iniciarT() {
        if (tiempoInicio != 0) {
            int id = modeloTarea.listTarea().get(vistatarea.jtbTareas.getSelectedRow()).getIdTarea();
            int tiempo = modeloTarea.listTarea().get(vistatarea.jtbTareas.getSelectedRow()).getTiempoInterrupcione();
            int tpausa = modeloTarea.listTarea().get(vistatarea.jtbTareas.getSelectedRow()).getTotalInterrupciones();
            System.out.println(tpausa);
            duracion = (System.nanoTime() - tiempoPausa);
            long seg = (duracion / 1000000000);
            System.out.println("Segundos" + seg);
            long min = (seg / 60);
            System.out.println(min);
            long ttiempo = min+tiempo;
            System.out.println(ttiempo);

            if (min > 0) {
                int t = (int)ttiempo;
                modeloTarea.pausaTarea(id, t, tpausa + 1);
                RegistrosCalc(vistatarea.jtbTareas);
            }
        }

        tiempoInicio = System.nanoTime();
        vistatarea.btnPausar.setEnabled(true);

    }

    /**
     *Pausa una tarea 
     * Introduce el tiempo dentro de la base de datos
     * Actualiza la tabla
     */
    public void pausarT() {
        int id = modeloTarea.listTarea().get(vistatarea.jtbTareas.getSelectedRow()).getIdTarea();
        int tiempo = modeloTarea.listTarea().get(vistatarea.jtbTareas.getSelectedRow()).getTiempo();
        duracion = (System.nanoTime() - tiempoInicio);
        long seg = (duracion / 1000000000);
        System.out.println("Segundos " + seg);
        long min = (seg / 60);

        System.out.println("minutos" + min);
        long ttiempo = min + tiempo;
        if (min >= 1) {

            int t = (int) ttiempo;
            modeloTarea.tiempoTarea(id, t);
            RegistrosCalc(vistatarea.jtbTareas);
        }

        tiempoPausa = System.nanoTime();

    }

   
    @Override
    /**
     * Captura todos los eventos de los botones de la vista
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistatarea.btnAgregar) {
            agregarR();
        }
        if (e.getSource() == vistatarea.btnCompletar) {
            if (vistatarea.jtbTareas.getSelectedRow() > (-1)) {

                complT();
            } else {
                JOptionPane.showMessageDialog(vistatarea, "Por favor selecciona una tarea");
            }
        }
        if (e.getSource() == vistatarea.btnEliminar) {
            if (vistatarea.jtbTareas.getSelectedRow() > (-1)) {
                System.out.println(vistatarea.jtbTareas.getSelectedRow());
                borrarR();
            } else {
                JOptionPane.showMessageDialog(vistatarea, "Por favor selecciona una tarea");
            }

        }
        if (e.getSource() == vistatarea.btnEditar) {
            if (vistatarea.jtbTareas.getSelectedRow() > (-1)) {
                System.out.println(vistatarea.jtbTareas.getSelectedRow());
            } else {
                JOptionPane.showMessageDialog(vistatarea, "Por favor selecciona una tarea");
            }
        }

        if (e.getSource() == vistatarea.btnPausar) {
            System.out.println("inicio");
            pausarT();
        }
        if (e.getSource() == vistatarea.btnIniciar) {
            if (vistatarea.jtbTareas.getSelectedRow() > (-1)) {
                JOptionPane.showMessageDialog(vistatarea, "Tarea iniciada");
                iniciarT();
            } else {
                JOptionPane.showMessageDialog(vistatarea, "Por favor selecciona una tarea");
            }
        }
        if (e.getSource() == vistatarea.menReportes) {
            
            viewReportes vistareportes = new viewReportes();
            ControladorReporte contreportes = new ControladorReporte(vistareportes,modeloTarea);
            
            vistareportes.setVisible(true);
            
            
        }

    }
}
