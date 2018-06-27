/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_asignatura_modificar2 extends Menu_asignatura_alta {

    String prev_nombre, nombre_ventana, asignatura_seleccionada;
    int codigo_asignatura_aux;

    public Menu_asignatura_modificar2(String pre_nombre, String nombre, String seleccion) throws Exception {
        super(nombre);

        this.prev_nombre = pre_nombre; //nombre de la ventana anterior del menu modificar
        this.nombre_ventana = nombre; // nombre de la ventana actual para llamar desde el actionlistener
        this.asignatura_seleccionada = seleccion;
        this.codigo_asignatura_aux = Main1.c.consulta_ID_asignatura_centro(asignatura_seleccionada);
        //System.out.print("asig: "+codigo_asignatura_aux);
        crear.setText("Guardar");

        // seleccionamos los componentes a agregar: solo se agregaran aquellos elegidos como MODIFICABLES
        // en este caso, todo sera modificable de la pestaña1;
        // de la pestaña 2 solo seran modificables 2 campos:
        ventana_asignatura_alta.remove(Multipaneles);
        Multipaneles.remove(pestaña2);
        pestaña2.removeAll();
        pestaña2.add(new JLabel("HORARIO", SwingConstants.CENTER)); // etiqueta titulo HORARIO
        pestaña2.add(contenedores[4]); // fecha comienzo
        pestaña2.add(contenedores[5]); // fecha final
        Multipaneles.addTab("HORARIO", null, pestaña2, "Horario anual de la asignatura");
        ventana_asignatura_alta.getContentPane().add(Multipaneles, BorderLayout.CENTER);
//        this.añadir_listeners(); // NO BORRAR
    }

    @Override
    public void añadir_listeners() {
        ActionListener cancelar_modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                ventana_asignatura_alta.setVisible(false);
                new Menu_asignatura_modificar(prev_nombre);
            }
        };

        ActionListener action_modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                try {
                    String n_mod = txt_contenedores[0].getText();
                    String p_mod = seleccion_p;
                    String a_mod = seleccion_a;
                    String mc_mod = txt_contenedores[1].getText();
                    
                    String fc_mod = ((JTextField) fecha_inicio.getDateEditor().getUiComponent()).getText();
                    String ff_mod = ((JTextField) fecha_final.getDateEditor().getUiComponent()).getText();
                    
                    // comprobamos los campos vacios; si hay algun campo vacio, no se sobreescribe lo anterior
                    System.out.print("asig222:_"+codigo_asignatura_aux);
                    Asignatura b =Main1.c.consulta_asignatura_centro(codigo_asignatura_aux);
                    System.out.print("asigaaa:_"+b.get_nombre());
                    String n = (Main1.c.consulta_asignatura_centro(codigo_asignatura_aux)).get_nombre();
                    //System.out.print("asig33: "+Main1.c.consulta_asignatura_centro(1).get_nombre());
                    int mc = Main1.c.consulta_asignatura_centro(codigo_asignatura_aux).get_max_cap();
                    String fc = Main1.c.consulta_asignatura_centro(codigo_asignatura_aux).get_fecha_comienzo();
                    String ff = Main1.c.consulta_asignatura_centro(codigo_asignatura_aux).get_fecha_final();
                    int p = Main1.c.consulta_asignatura_centro(codigo_asignatura_aux).get_codigo_profesor();
                    int a = Main1.c.consulta_asignatura_centro(codigo_asignatura_aux).get_codigo_aula();
                   
                    
                    if (n_mod != null && !(n_mod).equals("")) {
                        System.out.print("asigbb:_"+n_mod);
                        n = n_mod;
                    }
                    if (p_mod != null && !(p_mod).equals("")) {
                        p = Integer.valueOf(p_mod);
                    }
                    if (a_mod != null && !(a_mod).equals("")) {
                        a = Integer.valueOf(a_mod);
                    }
                    if (mc_mod != null && !(mc_mod).equals("")) {
                        mc = Integer.valueOf(mc_mod);
                    }
                    if (fc_mod != null && !fc_mod.equals("")) {
                        fc = fc_mod;
                    }
                    if (ff_mod != null && !ff_mod.equals("")) {
                        ff = ff_mod;
                    }
                    
                    int capacidad_aula_seleccionada = Main1.c.consulta_aula_centro(a).get_capacidad();
                    if (mc <= capacidad_aula_seleccionada) {
                        System.out.print("asigccc:_"+n);
                        Main1.c.modificar_asignatura_centro(Integer.toString(codigo_asignatura_aux), n, p, a, mc, fc, ff);
                        ventana_asignatura_alta.setVisible(false);
                        JOptionPane.showMessageDialog(null, "¡La asignatura ha sido modificada satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
                        new Menu_asignatura_modificar(prev_nombre);
                    } else {
                        JOptionPane.showMessageDialog(null, "La capacidad máxima de alumnos supera la capacidad del aula seleccionada:\nSeleccione otra aula o cambie la capacidad de alumnos actual.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Menu_asignatura_modificar2.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        cancelar.addActionListener(cancelar_modificar); // sustituimos listener por el actual
        crear.addActionListener(action_modificar); // sustituimos listener por el actual
    }

}
