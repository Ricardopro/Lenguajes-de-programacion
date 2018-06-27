/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_profesor_modificar extends Menu_aula_modificar {

    public Menu_profesor_modificar(String nombre) throws Exception {
        super(nombre); 
            this.inicializar();
//        this.añadir_listeners();
 
    }

    @Override
    public void inicializar() throws Exception {
        lbl_aula.setText("Selecciona un profesor a modificar: ");
        array_aulas = new JComboBox(Main1.c.consulta_array_profesor_centro());
    }

    @Override
    public void añadir_listeners() {
        cancelar.addActionListener(super.action_cancelar);
        ActionListener modificar_profesor = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ventana_aula_consultar.setVisible(false);
                new Menu_profesor_modificar2(ventana_aula_modificar, ventana_aula_modificar + ">Profesor " + seleccion, seleccion);
            }
        };
        consultar.addActionListener(modificar_profesor); // agrega el action listener de MODIFICAR

    }

}
