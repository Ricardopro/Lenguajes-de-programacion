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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lp_practica.Aula.tipo_aula;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_aula_alta {

    JFrame ventana_aula_alta;
    JRadioButton t_aula[];
    JButton cancelar, crear;
    tipo_aula tipo_enum = Aula.tipo_aula.A;
    Container c1, c2, c3;
    int capacidad = 0;
    JLabel txt_capacidad;
    JTextField enter_capacidad;
    JLabel txt_tipo;

    public Menu_aula_alta(String nombre) {

        ventana_aula_alta = new JFrame(nombre);
        ventana_aula_alta.getContentPane().setLayout(new GridLayout(3, 1));
        ventana_aula_alta.setSize(296, 140);
        ventana_aula_alta.setLocationRelativeTo(null);
        ventana_aula_alta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c1 = new Container();
        c2 = new Container();
        c3 = new Container();

        // CONTENEDOR 1= capacidad y bloque de texto
        c1.setLayout(new FlowLayout(FlowLayout.CENTER));
        txt_capacidad = new JLabel("Capacidad: ");
        c1.add(txt_capacidad);

        enter_capacidad = new JTextField(10);
        c1.add(enter_capacidad);

        ventana_aula_alta.add(c1);

        // CONTENEDOR 2: RadioButtons
        c2.setLayout(new FlowLayout(FlowLayout.CENTER));
        txt_tipo = new JLabel("Tipo aula: ");
        c2.add(txt_tipo);

        // SE CREA LA RELACIÓN LÓGICA ENTRE LOS OBJETOS JRADIOBUTTON PARA QUE SEAN EXCLUSIVOS
        ButtonGroup grupoBotonesOpcion = new ButtonGroup();
        t_aula = new JRadioButton[Aula.tipo_aula.values().length];
        int i = 0;
        for (Aula.tipo_aula a : Aula.tipo_aula.values()) {
            t_aula[i] = new JRadioButton(a.toString(), true);
            t_aula[i].addActionListener(seleccion);
//            ventana_aula_alta.add(t_aula[i]);
            grupoBotonesOpcion.add(t_aula[i]);
            c2.add(t_aula[i++]);
        }
        ventana_aula_alta.add(c2);

        // CONTENEDOR 3:  boton cancelar y crear
        c3.setLayout(new FlowLayout(FlowLayout.CENTER));
        cancelar = new JButton("Cancelar");
        crear = new JButton("Crear");

        //cancelar.addActionListener(action_cancelar);
        //crear.addActionListener(action_crear);
        añadir_listeners();
        c3.add(crear);
        c3.add(cancelar);
        ventana_aula_alta.add(c3);

        ventana_aula_alta.setVisible(true);
    }

    public void añadir_listeners() {
        cancelar.addActionListener(action_cancelar);
        crear.addActionListener(action_crear);

    }
    ActionListener seleccion = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            switch (ev.getActionCommand()) {
                case "A":
                    tipo_enum = Aula.tipo_aula.A;
                    break;
                case "B":
                    tipo_enum = Aula.tipo_aula.B;
                    break;
                case "C":
                    tipo_enum = Aula.tipo_aula.C;
                    break;
                case "D":
                    tipo_enum = Aula.tipo_aula.D;
                    break;
                case "E":
                    tipo_enum = Aula.tipo_aula.E;
                    break;

            }
        }
    };

    ActionListener action_cancelar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana_aula_alta.setVisible(false);
            Main1.menuSecundario(Menu.menu_selec1);
        }
    };

    ActionListener action_crear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            try {
                capacidad = Integer.parseInt(enter_capacidad.getText());
                Main1.c.crear_aula_centro(tipo_enum, capacidad);
                ventana_aula_alta.setVisible(false);
                JOptionPane.showMessageDialog(null, "¡El aula se dio de alta satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
                Main1.menuSecundario(Menu.menu_selec1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se han rellenado todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    };

}
