/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu {

    static JFrame menu;
    JButton boton[];
    static String menu_selec1, menu_selec2;

    public Menu(String nombre, String botones[]) { // falta distinguir de que ventana viene

        menu = new JFrame(nombre);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boton = new JButton[botones.length];
        menu.setName(nombre);
        menu.setSize(350, 250);
        menu.setLocationRelativeTo(null);

        if (botones.length == 4) { // menu: estudios, asignaturas, aula, profesores
            menu.getContentPane().setLayout(new GridLayout(2, 2)); // contiene los 4 botones del menu
            for (int i = 0; i < botones.length; i++) {
                boton[i] = new JButton(botones[i]);
                boton[i].addActionListener(seleccion1);
                menu.add(boton[i]);
            }
        } else {
            if (botones.length == 5) { // menu: alta, baja, consultar, modificar, atras
                menu.getContentPane().setLayout(new GridLayout(2, 1)); // contiene los 4 botones del menu (2,2) + atras
                Container cont = new Container();
                cont.setLayout(new GridLayout(2, 2));
                for (int i = 0; i < botones.length; i++) {
                    boton[i] = new JButton(botones[i]);
                    boton[i].addActionListener(seleccion2);
                    cont.add(boton[i]);
                }
                menu.add(cont);
                boton[botones.length - 1].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menu.setVisible(false);
                        Main1.menuPrincipal();
                    }
                });
                menu.add(boton[botones.length - 1]); // boton atras
            }
        }
        menu.setVisible(true);

    }

    ActionListener seleccion1 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            menu.setVisible(false);
            menu_selec1 = ev.getActionCommand();
            Main1.menuSecundario(ev.getActionCommand());
        }
    };

    ActionListener seleccion2 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            menu_selec2 = ev.getActionCommand();
            try {
                seleccion_menu();
            } catch (Exception ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public void seleccion_menu() throws Exception {
        String aux = menu_selec1 + "," + menu_selec2; // "Profesor,Alta"
        switch (aux) {
            // AULA
            case "Aula,Alta":
                menu.setVisible(false);
                Main1.crear_ventana_aula_alta(menu_selec1 + ">" + menu_selec2);
                break;
            case "Aula,Baja":
            case "Profesor,Baja":
            case "Asignatura,Baja":
            case "Estudios,Baja":
                menu.setVisible(false);
                Main1.crear_ventana_bajas(menu_selec1 + ">" + menu_selec2);
                break;
            case "Aula,Modificar":
                menu.setVisible(false);
                Main1.crear_ventana_aula_modificar(menu_selec1 + ">" + menu_selec2);
                break;
            case "Aula,Consultar":
                menu.setVisible(false);
                Main1.crear_ventana_aula_consultar(menu_selec1 + ">" + menu_selec2);
                break;

            // PROFESOR    
            case "Profesor,Alta":
                menu.setVisible(false);
                Main1.crear_ventana_profesor_alta(menu_selec1 + ">" + menu_selec2);
                break;
            case "Profesor,Modificar":
                menu.setVisible(false);
                Main1.crear_ventana_profesor_modificar(menu_selec1 + ">" + menu_selec2);
                break;
            case "Profesor,Consultar":
                menu.setVisible(false);
                Main1.crear_ventana_profesor_consultar(menu_selec1 + ">" + menu_selec2);
                break;

            // ASIGNATURA            
            case "Asignatura,Alta":
                menu.setVisible(false);
                Main1.crear_ventana_asignatura_alta(menu_selec1 + ">" + menu_selec2);
                break;
            case "Asignatura,Modificar":
                menu.setVisible(false);
                Main1.crear_ventana_asignatura_modificar(menu_selec1 + ">" + menu_selec2);
                break;
            case "Asignatura,Consultar":
                menu.setVisible(false);
                Main1.crear_ventana_asignatura_consultar(menu_selec1 + ">" + menu_selec2);
                break;

            // ESTUDIOS            
            case "Estudios,Alta":
                menu.setVisible(false);
                Main1.crear_ventana_estudios_alta(menu_selec1 + ">" + menu_selec2);
                break;
            case "Estudios,Modificar":
                menu.setVisible(false);
                Main1.crear_ventana_estudios_modificar(menu_selec1 + ">" + menu_selec2);
                break;
            case "Estudios,Consultar":
                menu.setVisible(false);
                Main1.crear_ventana_estudios_consultar(menu_selec1 + ">" + menu_selec2);
                break;

        }
    }
}
