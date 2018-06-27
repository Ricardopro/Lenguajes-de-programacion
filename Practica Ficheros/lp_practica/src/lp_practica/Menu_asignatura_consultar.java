/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_asignatura_consultar extends Menu_aula_consultar {

    String nom;

    public Menu_asignatura_consultar(String nombre) {
        super(nombre);
        try {
            nom = nombre;
            this.inicializar();
        } catch (Exception e) {
        }
    }

    @Override
    public void inicializar() throws Exception {
        lbl_aula = new JLabel("Selecciona una asignatura: ");
        array_aulas = new JComboBox(Main1.c.consulta_array_nombres_asignatura_centro());
//        array_aulas = new JComboBox(Main1.c.consulta_array_asignatura_centro());

    }

    @Override
    public void informacion_aula() throws Exception {
        this.datos_aula(nom);
    }

    @Override
    public void datos_aula(String nombre) throws Exception {
        ventana_aula_consultar.setVisible(false);
        // *** OBTENEMOS EL ID DE LA ASIGNATURA A PARTIR DE SU NOMBRE
        String ID_asig = String.valueOf(Main1.c.consulta_ID_asignatura_centro(seleccion));
        System.out.println("EL ID DE LA ASIGNATURA " + seleccion + " ES " + ID_asig);
        new Menu_asignatura_consultar2(nom, nombre + "> Asignatura " + seleccion, ID_asig);

//        new Menu_asignatura_consultar2(nom, nombre + "> Asignatura " + seleccion, seleccion);
    }
}
