/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_asignatura_modificar extends Menu_asignatura_consultar {

    String ventana_aula_modificar;

    public Menu_asignatura_modificar(String nombre) {
        super(nombre);
        try {
            consultar.setText("Modificar");
//        this.añadir_listeners(); // NO BORRAR
            ventana_aula_modificar = nombre;
        } catch (Exception e) {
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
        ActionListener modificar_asig = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ventana_aula_consultar.setVisible(false);
                try {
                    new Menu_asignatura_modificar2(ventana_aula_modificar, ventana_aula_modificar + ">Asignatura " + seleccion, seleccion);
                } catch (Exception ex) {
                    Logger.getLogger(Menu_asignatura_modificar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        cancelar.addActionListener(action_cancela);
        consultar.addActionListener(modificar_asig); // agrega el action listener de MODIFICAR
    }
}
