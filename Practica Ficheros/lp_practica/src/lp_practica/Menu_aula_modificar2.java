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
public class Menu_aula_modificar2 extends Menu_aula_alta {

    String prev_nombre, nombre_ventana, aula_seleccionada;

    public Menu_aula_modificar2(String pre_nombre, String nombre, String seleccion) {
        super(nombre);

        this.prev_nombre = pre_nombre; //nombre de la ventana anterior del menu modificar
        this.nombre_ventana = nombre; // nombre de la ventana actual para llamar desde el actionlistener
        this.aula_seleccionada = seleccion;
        crear.setText("Guardar");

//        this.añadir_listeners(); // NO BORRAR
    }

    @Override
    public void añadir_listeners() {
        ActionListener cancelar_modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ventana_aula_alta.setVisible(false);
                new Menu_aula_modificar(prev_nombre);
            }
        };

        ActionListener action_modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                
                try {

                    String capacidad_mod = enter_capacidad.getText();
                    
                    // comprobamos los datos que han sido cambiados; en caso contrario se mantiene el original
                    int capacidad_original = Main1.c.consulta_aula_centro(Integer.parseInt(aula_seleccionada)).get_capacidad();
                    Aula.tipo_aula tipo_original = Main1.c.consulta_aula_centro(Integer.parseInt(aula_seleccionada)).get_tipo_aula();
                    
                    if (capacidad_mod == null || (capacidad_mod).equals("") || capacidad == capacidad_original) {
                        capacidad = capacidad_original;
                    }else{
                        capacidad = Integer.parseInt(enter_capacidad.getText());
                    }
                    if (tipo_enum == null && tipo_enum == tipo_original) {
                        tipo_enum = tipo_original;
                    }
                    
                    try {
                        Main1.c.modificar_aula_centro(aula_seleccionada, tipo_enum, capacidad);
                    } catch (Exception ex) {
                        Logger.getLogger(Menu_aula_modificar2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ventana_aula_alta.setVisible(false);
                    JOptionPane.showMessageDialog(null, "¡El aula ha sido modificada satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
                    new Menu_aula_modificar(prev_nombre);
                } catch (Exception ex) {
                    Logger.getLogger(Menu_aula_modificar2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        cancelar.addActionListener(cancelar_modificar); // sustituimos listener por el actual
        crear.addActionListener(action_modificar); // sustituimos listener por el actual
    }

}
