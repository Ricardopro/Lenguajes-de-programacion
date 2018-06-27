/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_seleccionar_asignaturas_estudio {

    JFrame ventana;
    JList lista;
    JButton cancelar, aceptar;

    public Menu_seleccionar_asignaturas_estudio(String nombre) throws Exception {
        ventana = new JFrame(nombre + ">Asignaturas");
        mostrar_ventana();
    }

    public void asignaturas_seleccionadas() {
        Menu_estudios_alta.lista_nombres_asignaturas_del_estudio.clear(); // inicializar lista
        Menu_estudios_alta.lista_nombres_asignaturas_del_estudio.addAll(lista.getSelectedValuesList());
        
        System.out.print(" identificaroroarjtew:" + lista.getSelectedValuesList());

    }

    public void mostrar_ventana() throws Exception {
        ventana.setSize(268, 306);
        ventana.setLayout(new FlowLayout());
        String[] opciones = Main1.c.consulta_array_nombres_asignatura_centro();
        lista = new JList(opciones);
        lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ventana.add(lista, BorderLayout.CENTER);

        // scrollpane > 8 elementos
        JScrollPane scrollPane = new JScrollPane(lista);
        ventana.add(scrollPane);

        cancelar = new JButton("Cancelar");
        aceptar = new JButton("Aceptar");
        cancelar.addActionListener(action_cancelar);
        aceptar.addActionListener(action_aceptar);

        Container aux = new Container();
        aux.setLayout(new FlowLayout());
        aux.add(aceptar);
        aux.add(cancelar);
        ventana.add(aux);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
    ActionListener action_cancelar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana.setVisible(false);
        }
    };

    ActionListener action_aceptar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana.setVisible(false);
            if (lista.getSelectedIndices().length > 0) {
                asignaturas_seleccionadas();
            } else {
                JOptionPane.showMessageDialog(null, "¡No se ha seleccionado ninguna asignatura!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    };
}
