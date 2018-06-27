/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_estudios_consultar extends Menu_aula_consultar {

    String nom;

    public Menu_estudios_consultar(String nombre) {
        super(nombre);
        try {
            nom = nombre;
            this.inicializar();
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No hay ningún dato disponible para realizar la operación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void inicializar() throws Exception {
        lbl_aula = new JLabel("Selecciona un estudio: ");
        array_aulas = new JComboBox(Main1.c.consulta_array_estudio_centro());
    }

    @Override
    public void informacion_aula() throws Exception {
        this.datos_aula(nom);
    }

    @Override
    public void datos_aula(String nombre) throws Exception {
        ventana_aula_consultar.setVisible(false);
        new Menu_estudios_consultar2(nom, nombre + ">Estudios " + seleccion, seleccion);
    }
}
