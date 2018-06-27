/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_profesor_consultar extends Menu_aula_consultar {

    public Menu_profesor_consultar(String nombre) throws Exception {
        super(nombre);
        this.inicializar();
    }

    @Override
    public void inicializar() throws Exception {
        lbl_aula = new JLabel("Selecciona un profesor: ");
        array_aulas = new JComboBox(Main1.c.consulta_array_nombres_profesor_centro());

//        array_aulas = new JComboBox(Main1.c.consulta_array_profesor_centro());
    }

    @Override
    public void datos_aula(String nombre) throws Exception {
        aula_consultada.setSize(450, 244);
        System.out.println("1nombre prof: " + seleccion + "fin");
//        seleccion = seleccion.trim() + " ";
        System.out.println("2nombre prof: " + seleccion + "fin");

        int ID_prof = Main1.c.consulta_ID_profesor_centro(seleccion);
        System.out.println("ID: "+ ID_prof);
//        String nombre_profesor= Main1.c.consulta_profesor_centro(Integer.parseInt(seleccion)).get_nombre();
        aula_consultada.setTitle(nombre + ">Profesor " + seleccion);
        et = new JLabel("     Información del profesor/a " + seleccion + " con ID #" + ID_prof + ":");
        et.setAlignmentX(JLabel.CENTER);
        et1 = new JLabel("          ￭ Nombre: " + Main1.c.consulta_profesor_centro(ID_prof).get_nombre());
        et2 = new JLabel("          ￭ NIF: " + Main1.c.consulta_profesor_centro(ID_prof).get_NIF());
        et3 = new JLabel("          ￭ Telefono: " + Main1.c.consulta_profesor_centro(ID_prof).get_telefono());
        aula_consultada.add(et);
        aula_consultada.add(et1);
        aula_consultada.add(et2);
        aula_consultada.add(et3);
    }

}
