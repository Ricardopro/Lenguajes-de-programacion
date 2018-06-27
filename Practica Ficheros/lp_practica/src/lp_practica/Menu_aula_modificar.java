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
public class Menu_aula_modificar extends Menu_aula_consultar {

    String ventana_aula_modificar;

    public Menu_aula_modificar(String nombre) {
        super(nombre);
        try {
            consultar.setText("Modificar");
//            this.añadir_listeners(); // NO BORRAR
            ventana_aula_modificar = super.ventana_aula_consultar.getName();
        } catch (Exception e) {}
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
        ActionListener modificar_aula = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ventana_aula_consultar.setVisible(false);
                new Menu_aula_modificar2(ventana_aula_modificar, ventana_aula_modificar + ">Aula " + seleccion, seleccion);
            }
        };

        cancelar.addActionListener(action_cancela);
        consultar.addActionListener(modificar_aula); // agrega el action listener de MODIFICAR
    }
}
