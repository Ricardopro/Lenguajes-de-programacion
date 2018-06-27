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
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_profesor_modificar2 extends Menu_profesor_alta {

    String prev_nombre, nombre_ventana, prof_seleccionado;

    public Menu_profesor_modificar2(String pre_nombre, String nombre, String seleccion) {
        super(nombre);
        this.prev_nombre = pre_nombre; //nombre de la ventana anterior del menu modificar
        this.nombre_ventana = nombre; // nombre de la ventana actual para llamar desde el actionlistener
        this.prof_seleccionado = seleccion;
        crear.setText("Guardar");
//        this.añadir_listeners();
    }

    @Override
    public void añadir_listeners() {
        ActionListener action_cancelar1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ventana_profesor_alta.setVisible(false);
                try {
                    //Main1.menuSecundario(Menu.menu_selec1); llamar a mod1
                    new Menu_profesor_modificar(prev_nombre);
                } catch (Exception ex) {
                    Logger.getLogger(Menu_profesor_modificar2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        ActionListener action_modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                try {
                    // comprobamos que datos han sido modificados; los datos en blanco se quedaran con el valor original
                    String n_original = Main1.c.consulta_profesor_centro(Integer.parseInt(prof_seleccionado)).get_nombre();
                    String NIF_original = Main1.c.consulta_profesor_centro(Integer.parseInt(prof_seleccionado)).get_NIF();
                    String telf_original = Main1.c.consulta_profesor_centro(Integer.parseInt(prof_seleccionado)).get_telefono();
                    
                    String n = p_nombre.getText();
                    String NIF = p_NIF.getText();
                    String telf = p_telefono.getText();
                    
                    if (n== null || (n).equals("")) {
                        n = n_original;
                    }
                    if (NIF== null || (NIF).equals("")) {
                        NIF = NIF_original;
                    }
                    if (telf== null || (telf).equals("")) {
                        telf = telf_original;
                    }
                    Main1.c.modificar_profesor_centro(prof_seleccionado, n, NIF, telf);
                    ventana_profesor_alta.setVisible(false);
                    JOptionPane.showMessageDialog(null, "¡El profesor ha sido modificado satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
                    new Menu_profesor_modificar(prev_nombre);
                } catch (Exception ex) {
                    Logger.getLogger(Menu_profesor_modificar2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        cancelar.addActionListener(action_cancelar1); // sustituimos listener por el actual
        crear.addActionListener(action_modificar); // sustituimos listener por el actual
    }

}
