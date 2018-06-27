/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_bajas {

    int enter_ID;

    public Menu_bajas(String nombre) {
        mostrar_ventana(nombre.split(">", 3)[1].toLowerCase()); // para coger la X del String: UIB>X>Baja
    }

    public void mostrar_ventana(String n) {
        enter_ID = 0;
        try {
            enter_ID = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID de " + n + " a dar de baja: "));
            switch (Menu.menu_selec1) {
                case "Aula":
                    Main1.c.borrar_aula_centro(enter_ID);
                    break;
                case "Profesor":
                    Main1.c.borrar_profesor_centro(enter_ID);
                    break;
                case "Asignatura":
                    Main1.c.borrar_asignatura_centro(enter_ID);
                    break;
                case "Estudios":
                    Main1.c.borrar_estudio_centro(enter_ID);
                    break;
            }

            Menu.menu.setVisible(true);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error");
            Menu.menu.setVisible(true);
        }
    }
}
