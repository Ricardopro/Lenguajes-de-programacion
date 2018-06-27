/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_estudios_modificar extends Menu_estudios_consultar {

    String ventana_estudios_modificar;

    public Menu_estudios_modificar(String nombre) {
        super(nombre);
        try {
            consultar.setText("Modificar");
//        this.añadir_listeners(); // NO BORRAR
            ventana_estudios_modificar = nombre;
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No hay ningún dato disponible para realizar la operación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void añadir_listeners() {
        ActionListener action_cancela = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ventana_aula_consultar.setVisible(false);
                Main1.menuSecundario(Menu.menu_selec1);
            }
        };
        ActionListener modificar_est = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ventana_aula_consultar.setVisible(false);
                new Menu_estudios_modificar2(ventana_estudios_modificar, ventana_estudios_modificar + ">Asignatura " + seleccion, seleccion);
            }
        };
        cancelar.addActionListener(action_cancela);
        consultar.addActionListener(modificar_est); // agrega el action listener de MODIFICAR

    }
}
