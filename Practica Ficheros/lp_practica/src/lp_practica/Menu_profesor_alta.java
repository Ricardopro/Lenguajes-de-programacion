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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_profesor_alta {

    JFrame ventana_profesor_alta;
    JTextField p_nombre, p_NIF, p_telefono;
    JButton cancelar, crear;

    public Menu_profesor_alta(String nombre) {

        ventana_profesor_alta = new JFrame(nombre);
        ventana_profesor_alta.getContentPane().setLayout(new GridLayout(4, 1));
        ventana_profesor_alta.setSize(330, 195);
        ventana_profesor_alta.setLocationRelativeTo(null);
        ventana_profesor_alta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c1 = new Container();
        Container c2 = new Container();
        Container c3 = new Container();
        Container c4 = new Container();

        // CONTENEDOR 1= Nombre y area texto
        c1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel nombre_prof = new JLabel("Nombre: ");

        p_nombre = new JTextField(20);
        c1.add(nombre_prof);
        c1.add(p_nombre);

        ventana_profesor_alta.add(c1);

        // CONTENEDOR 2: NIF y area texto
        c2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel NIF_prof = new JLabel("          NIF: ");
        p_NIF = new JTextField(20);
        c2.add(NIF_prof);
        c2.add(p_NIF);

        ventana_profesor_alta.add(c2);

        // CONTENEDOR 3: telefono y area texto
        c3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel telefono_prof = new JLabel("Telefono: ");

        p_telefono = new JTextField(20);
        c3.add(telefono_prof);
        c3.add(p_telefono);

        ventana_profesor_alta.add(c3);

        // CONTENEDOR 4:  boton cancelar y crear
        c4.setLayout(new FlowLayout(FlowLayout.CENTER));
        cancelar = new JButton("Cancelar");
        crear = new JButton("Crear");
        añadir_listeners();
        //cancelar.addActionListener(action_cancelar);
        //crear.addActionListener(action_crear);       
        c4.add(crear);
        c4.add(cancelar);
        ventana_profesor_alta.add(c4);

        ventana_profesor_alta.setVisible(true);
    }

    public void añadir_listeners() {
        cancelar.addActionListener(action_cancelar);
        crear.addActionListener(action_crear);
    }

    ActionListener action_cancelar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana_profesor_alta.setVisible(false);
            Main1.menuSecundario(Menu.menu_selec1);
        }
    };

    ActionListener action_crear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            try {
                Main1.c.crear_profesor_centro(p_nombre.getText(), p_NIF.getText(), p_telefono.getText());
                ventana_profesor_alta.setVisible(false);
                JOptionPane.showMessageDialog(null, "¡El profesor se dio de alta satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
                Main1.menuSecundario(Menu.menu_selec1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se han rellenado todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    };
}
