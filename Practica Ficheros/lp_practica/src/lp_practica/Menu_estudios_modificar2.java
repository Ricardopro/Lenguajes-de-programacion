/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_estudios_modificar2 extends Menu_estudios_alta {

    String prev_nombre, nombre_ventana, estudios_seleccionados;

    public Menu_estudios_modificar2(String pre_nombre, String nombre, String seleccion) {
        super(nombre);

        this.prev_nombre = pre_nombre; //nombre de la ventana anterior del menu modificar
        this.nombre_ventana = nombre; // nombre de la ventana actual para llamar desde el actionlistener
        this.estudios_seleccionados = seleccion;
        
        // eliminamos los componentes innecesarios (no modificables)
        ventana_estudios_alta.remove(contenedores[1]);
        ventana_estudios_alta.remove(contenedores[2]);
        ventana_estudios_alta.remove(contenedores[6]);
        crear.setText("Guardar"); 
//        this.añadir_listeners(); // NO BORRAR
    }

    @Override
    public void añadir_listeners() {
        ActionListener cancelar_modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ventana_estudios_alta.setVisible(false);
                new Menu_estudios_modificar(prev_nombre);
            }
        };

        ActionListener action_modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    System.out.println("MODIFICAR2");
                    String n_mod = txt_contenedores[0].getText();
                    String fc_mod = ((JTextField) fecha_inicio.getDateEditor().getUiComponent()).getText();
                    String ff_mod = ((JTextField) fecha_final.getDateEditor().getUiComponent()).getText();
                    // añadimos asignaturas seleccionadas al arraylist lista_ID_asignaturas de la clase padre
                    lista_ID_asignaturas.clear();
                    añadir_asignaturas();
                    Turno t_mod = obtenerTurno();
                    
                    // comprobamos la modificacion
                    String n = Main1.c.consulta_estudio_centro(Integer.parseInt(estudios_seleccionados)).get_nombre();
                    String fc = Main1.c.consulta_estudio_centro(Integer.parseInt(estudios_seleccionados)).get_fecha_comienzo();
                    String ff = Main1.c.consulta_estudio_centro(Integer.parseInt(estudios_seleccionados)).get_fecha_final();
                    
                    ArrayList<Integer> lista_asignaturas_original = new ArrayList<>();
                    lista_asignaturas_original.addAll(Main1.c.consulta_estudio_centro(Integer.parseInt(estudios_seleccionados)).get_lista_de_asignaturas());
                    Turno t = Main1.c.consulta_estudio_centro(Integer.parseInt(estudios_seleccionados)).get_Turno();
                    
                    if (n_mod != null && !(n_mod).equals("")) {
                        n = n_mod;
                    }
                    if (fc_mod != null && !fc_mod.equals("")) {
                        fc = fc_mod;
                    }
                    if (ff_mod != null && !ff_mod.equals("")) {
                        ff = ff_mod;
                    }
                    
                    if(!lista_ID_asignaturas.isEmpty() && !lista_ID_asignaturas.equals(lista_asignaturas_original)){ try {
                        // si se cambiaron las asignaturas; actualizar array de asignaturas y TURNO
                        Main1.c.consulta_estudio_centro(Integer.parseInt(estudios_seleccionados)).set_lista_asignaturas(lista_ID_asignaturas);
                    } catch (Exception ex) {
                        Logger.getLogger(Menu_estudios_modificar2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    t=t_mod;
                    }
                    
                    try {
                        Main1.c.modificar_estudio_centro(estudios_seleccionados, n, lista_ID_asignaturas, fc, ff, t);
                    } catch (Exception ex) {
                        Logger.getLogger(Menu_estudios_modificar2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ventana_estudios_alta.setVisible(false);
                    JOptionPane.showMessageDialog(null, "¡Los estudios han sido modificados satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
                    new Menu_estudios_modificar(prev_nombre);
                } catch (Exception ex) {
                    Logger.getLogger(Menu_estudios_modificar2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        cancelar.addActionListener(cancelar_modificar); // sustituimos listener por el actual
        crear.addActionListener(action_modificar); // sustituimos listener por el actual
    }
}
