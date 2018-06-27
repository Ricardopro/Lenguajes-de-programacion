/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static lp_practica.Main1.path;
import static lp_practica.Menu.menu_selec1;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_aula_consultar {

    JFrame ventana_aula_consultar;
    Container c1, c2;
    JButton consultar, cancelar;
    JComboBox array_aulas;
    String seleccion;
    JFrame aula_consultada;
    JLabel lbl_aula;
    JLabel et, et1, et2, et3;

    public Menu_aula_consultar(String nombre) {
        try {

            ventana_aula_consultar = new JFrame(nombre);
            ventana_aula_consultar.setName(nombre);
            ventana_aula_consultar.getContentPane().setLayout(new GridLayout(2, 1));
            ventana_aula_consultar.setSize(296, 140);
            ventana_aula_consultar.setLocationRelativeTo(null);
            ventana_aula_consultar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            c1 = new Container();
            c2 = new Container();

            // CONTENEDOR 1= label y bloque de texto
            c1.setLayout(new FlowLayout(FlowLayout.CENTER));
            lbl_aula = new JLabel();
            inicializar();
            c1.add(lbl_aula);

//        array_aulas = new JComboBox(Main1.c.consulta_array_aula_centro());
            array_aulas.addActionListener(listaseleccion);
            array_aulas.setSelectedIndex(0);
            c1.add(array_aulas);

            ventana_aula_consultar.add(c1);

            // CONTENEDOR 3:  boton cancelar y consultar
            c2.setLayout(new FlowLayout(FlowLayout.CENTER));

            cancelar = new JButton("Cancelar");
            consultar = new JButton("Consultar");
            añadir_listeners();
            c2.add(consultar);
            c2.add(cancelar);
            ventana_aula_consultar.add(c2);

            ventana_aula_consultar.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay ningún dato disponible para realizar la operación.", "Error", JOptionPane.ERROR_MESSAGE);
            switch (Menu.menu_selec1) {
                case "Aula":
                    Main1.menuSecundario("Aula");
                    break;
                case "Profesor":
                    Main1.menuSecundario("Profesor");
                    break;
                case "Asignatura":
                    Main1.menuSecundario("Asignatura");
                    break;
                case "Estudios":
                    Main1.menuSecundario("Estudios");
                    break;
            }
        }
    }

    public void inicializar() throws Exception {
        lbl_aula.setText("Selecciona un aula: ");
        array_aulas = new JComboBox(Main1.c.consulta_array_aula_centro());
    }

    public void añadir_listeners() {
        cancelar.addActionListener(action_cancelar);
        consultar.addActionListener(action_consultar);
    }
    ActionListener listaseleccion = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            JComboBox cb = (JComboBox) ev.getSource();
            seleccion = (String) cb.getSelectedItem();
        }
    };
    ActionListener action_cancelar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana_aula_consultar.setVisible(false);
            Main1.menuSecundario(Menu.menu_selec1);
        }
    };

    ActionListener action_consultar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            try {
                informacion_aula();
            } catch (Exception ex) {
                Logger.getLogger(Menu_aula_consultar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    ActionListener action_aceptar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana_aula_consultar.setVisible(true);
            aula_consultada.setVisible(false);
        }
    };

    public void informacion_aula() throws Exception {
        aula_consultada = new JFrame();
        aula_consultada.getContentPane().setLayout(new GridLayout(5, 1));
        aula_consultada.setLocationRelativeTo(null);
        aula_consultada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        datos_aula(ventana_aula_consultar.getTitle());

        Container c = new Container();
        c.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton aceptar = new JButton("Aceptar");
        aceptar.addActionListener(action_aceptar);
        c.add(aceptar);
        aula_consultada.add(c);
        ventana_aula_consultar.setVisible(false);
        aula_consultada.setVisible(true);
    }
//  ya que la consulta de un profesor sera similar, el menú de consulta del profesor sera hijo de esta clase y se 
//  sobreescribira este método con los datos del PROFESOR en lugar del AULA

    public void datos_aula(String nombre) throws Exception {
        aula_consultada.setSize(332, 244);
        aula_consultada.setTitle(nombre + ">Aula " + seleccion);
        et = new JLabel("     Información del aula " + seleccion);
        et.setAlignmentX(JLabel.CENTER);
        et1 = new JLabel("          ￭ ID: " + Main1.c.consulta_aula_centro(Integer.parseInt(seleccion)).get_codigo());
        et2 = new JLabel("          ￭ Tipo: " + Main1.c.consulta_aula_centro(Integer.parseInt(seleccion)).get_tipo_aula());
        et3 = new JLabel("          ￭ Capacidad: " + Main1.c.consulta_aula_centro(Integer.parseInt(seleccion)).get_capacidad());
        aula_consultada.add(et);
        aula_consultada.add(et1);
        aula_consultada.add(et2);
        aula_consultada.add(et3);
    }
}
