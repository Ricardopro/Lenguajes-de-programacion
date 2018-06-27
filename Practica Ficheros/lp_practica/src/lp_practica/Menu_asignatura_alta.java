/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import lp_practica.Estudio.Dia;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_asignatura_alta {

    JFrame ventana_asignatura_alta;
    String str_contenedores[] = {"                Nombre: ", "              Profesor: ", "                   Aula: ", "Máx. Estudiantes: ",
        "              Fecha de inicio: ", "                       Fecha final: ", "                Días/Semana: ", "                      Horas/día: ", "Hora inicio preferente: "};
    Container contenedores[] = new Container[str_contenedores.length + 1]; // + contenedor botones
    JTextField txt_contenedores[] = new JTextField[str_contenedores.length - 5];
    JButton cancelar, crear;
    JComboBox array_profesores, array_aulas;
    JDateChooser fecha_inicio, fecha_final;
    JCheckBox t_semana[];
    String seleccion_p, seleccion_a;
    ArrayList<Dia> dias_por_semana = new ArrayList<>();
    boolean dia_selec[]; // para saber que dia de la t_semana fue seleccionado

    // COMPONENTES MODIFICABLES HEREDADAS POR ASIGNATURA MODIFICAR
    JTabbedPane Multipaneles;
    JPanel pestaña1, pestaña2;

    public Menu_asignatura_alta(String nombre) throws Exception {
        ventana_asignatura_alta = new JFrame(nombre);
        ventana_asignatura_alta.setLayout(new BorderLayout());
        ventana_asignatura_alta.setSize(296 * 2, 296 + 50);
        ventana_asignatura_alta.setLocationRelativeTo(null);
        ventana_asignatura_alta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // CREACIÓN OBJETO JTabbedPane 
        Multipaneles = new JTabbedPane();

        // ESTABLECIMIENTO DEL PANEL pestaña1 
        pestaña1 = new JPanel();
        pestaña1.setLayout(new GridLayout(5, 1));
        pestaña1.setSize(ventana_asignatura_alta.getSize());
        pestaña1.add(new JLabel("DATOS GENERALES", SwingConstants.CENTER));

        // ESTABLECIMIENTO DEL PANEL pestaña2 E INCORPORACIÓN AL MULTIPANELES 
        pestaña2 = new JPanel();
        pestaña2.setLayout(new GridLayout(7, 1));
        pestaña2.setSize(ventana_asignatura_alta.getSize());
//        pestaña2.setBackground(Color.RED); 
        pestaña2.add(new JLabel("HORARIO", SwingConstants.CENTER));

        for (int i = 0, j = 0; i < (contenedores.length - 1); i++) {
            contenedores[i] = new Container();
            contenedores[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            contenedores[i].add(new JLabel(str_contenedores[i]));
            switch (i) {
                case 0:
                case 3:
                case 7:
                case 8:
                    txt_contenedores[j] = new JTextField(30);
                    contenedores[i].add(txt_contenedores[j++]);
                    break;
                case 1:
                    array_profesores = new JComboBox(Main1.c.consulta_array_profesor_centro());
                    array_profesores.addActionListener(selec_profesor);
                    contenedores[i].add(array_profesores);
                    break;
                case 2:
                    array_aulas = new JComboBox(Main1.c.consulta_array_aula_centro());
                    array_aulas.addActionListener(selec_aula);
                    contenedores[i].add(array_aulas);
                    break;
                case 4:
                    fecha_inicio = new JDateChooser();
                    fecha_inicio.setDateFormatString("dd-MM-yy");
                    contenedores[i].add(fecha_inicio);
                    break;
                case 5:
                    fecha_final = new JDateChooser();
                    fecha_final.setDateFormatString("dd-MM-yy");
                    contenedores[i].add(fecha_final);
                    break;
                case 6:
                    insertar_checkbox_semana(i);
                    break;
            }
            if (i > 3) {
                pestaña2.add(contenedores[i]); // añadir a pestaña 2
            } else {
                pestaña1.add(contenedores[i]); // añadir a pestaña 1
            }
        }
        // addTab(String title, Icon icon, Component component, String tip) 
        Multipaneles.addTab("DATOS GENERALES", null, pestaña1, "Datos generales de la asignatura");
        Multipaneles.addTab("HORARIO", null, pestaña2, "Horario anual de la asignatura");

        // INSERCIÓN DEL MULTIPANEL AL CONTENEDOR 
        ventana_asignatura_alta.getContentPane().add(Multipaneles, BorderLayout.CENTER); // añadimos pestañas

        // CREAMOS BOTONES CREAR Y CANCELAR
        contenedores[contenedores.length - 1] = new Container(); // el contenedor 11 es el contenedor de los 2 botones
        contenedores[contenedores.length - 1].setLayout(new FlowLayout(FlowLayout.CENTER));
        cancelar = new JButton("Cancelar");
        crear = new JButton("Crear");
        añadir_listeners();
        contenedores[contenedores.length - 1].add(crear);
        contenedores[contenedores.length - 1].add(cancelar);
        ventana_asignatura_alta.getContentPane().add(contenedores[contenedores.length - 1], BorderLayout.SOUTH); //añadimos boton aceptar
        ventana_asignatura_alta.setVisible(true);

    }

    public void añadir_listeners() {
        cancelar.addActionListener(action_cancelar);
        crear.addActionListener(action_crear);
    }

    public void insertar_checkbox_semana(int indice) {
        t_semana = new JCheckBox[Semana.Dias.values().length];
        dia_selec = new boolean[t_semana.length];
        int i = 0;
        for (Semana.Dias a : Semana.Dias.values()) {
            t_semana[i] = new JCheckBox(Semana.cambiar_dia_a_string(a), true);
            t_semana[i].setSelected(false); // INICIALMENTE NO SELECCIONADO
            t_semana[i].addItemListener(listener);
            contenedores[indice].add(t_semana[i++]);
        }
        ventana_asignatura_alta.add(contenedores[indice]);
    }
    ItemListener listener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent ie) {
            if (ie.getItem().equals(t_semana[0])) { // SI SELECCIONAMOS LUNES
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    dia_selec[0] = true;
                } else {
                    dia_selec[0] = false;
                }
            }
            if (ie.getItem().equals(t_semana[1])) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    dia_selec[1] = true;
                } else {
                    dia_selec[1] = false;
                }
            }
            if (ie.getItem().equals(t_semana[2])) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    dia_selec[2] = true;
                } else {
                    dia_selec[2] = false;
                }
            }
            if (ie.getItem().equals(t_semana[3])) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    dia_selec[3] = true;
                } else {
                    dia_selec[3] = false;
                }
            }
            if (ie.getItem().equals(t_semana[4])) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    dia_selec[4] = true;
                } else {
                    dia_selec[4] = false;
                }
            }
        }
    };

    public void dias_por_semana() {
        for (JCheckBox d : t_semana) { //t_semana[d]
            switch (d.getText()) {
                case "L":
                    if (dia_selec[0]) {
                        dias_por_semana.add(Dia.LUNES);
                    }
                    break;
                case "M":
                    if (dia_selec[1]) {
                        dias_por_semana.add(Dia.MARTES);
                    }
                    break;
                case "X":
                    if (dia_selec[2]) {
                        dias_por_semana.add(Dia.MIERCOLES);
                    }
                    break;
                case "J":
                    if (dia_selec[3]) {
                        dias_por_semana.add(Dia.JUEVES);
                    }
                    break;
                case "V":
                    if (dia_selec[4]) {
                        dias_por_semana.add(Dia.VIERNES);
                    }
                    break;
            }
        }
    }

    ActionListener selec_aula = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            JComboBox cb = (JComboBox) ev.getSource();
            seleccion_a = (String) cb.getSelectedItem();
        }
    };

    ActionListener action_cancelar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana_asignatura_alta.setVisible(false);
            Main1.menuSecundario(Menu.menu_selec1);
        }
    };

    ActionListener selec_profesor = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            JComboBox cb = (JComboBox) ev.getSource();
            seleccion_p = (String) cb.getSelectedItem();
        }
    };

    // COMPROBAR TODOS LOS CAMPOS OCUPADOS ANTES DE CREAR
    ActionListener action_crear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            try {
                String n = txt_contenedores[0].getText();
                int mc = Integer.parseInt(txt_contenedores[1].getText());
                String fc = ((JTextField) fecha_inicio.getDateEditor().getUiComponent()).getText();
                String ff = ((JTextField) fecha_final.getDateEditor().getUiComponent()).getText();
                int p = Integer.parseInt(seleccion_p);
//                int a = Main1.c.consulta_aula_centro(Integer.parseInt(seleccion_a)).get_identificador();
                int a = Integer.parseInt(seleccion_a);

                Aula.tipo_aula tip = Main1.c.consulta_aula_centro(a).get_tipo_aula();
                int phc = Integer.parseInt(txt_contenedores[3].getText());
                int hc = phc;
                int hxd = Integer.parseInt(txt_contenedores[2].getText());;
                int hf = hc + hxd;
                dias_por_semana.clear();
                dias_por_semana(); // añade todos los dias seleccionados al array de dias
                int capacidad_aula_seleccionada = Main1.c.consulta_aula_centro(a).get_capacidad();

// comprobamos que se haya seleccionado al menos un día y que la capacidad maxima de alumnos NO SUPERE la capacidad maxima del aula
                if (dias_por_semana.size() > 0 && mc <= capacidad_aula_seleccionada) {
                    Main1.c.crear_asignatura_centro(n, tip, mc, fc, ff, p, a, hc, hf, phc, hxd, dias_por_semana);
                    ventana_asignatura_alta.setVisible(false);
                    JOptionPane.showMessageDialog(null, "¡La asignatura se dio de alta satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
                    Main1.menuSecundario(Menu.menu_selec1);
                } else {
                    if (mc <= capacidad_aula_seleccionada) { // si no se selecciono ningun dia
                        JOptionPane.showMessageDialog(null, "¡No se ha seleccionado ningún día!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else { // si la capacidad de alumnos SUPERA la capacidad del aula
                        JOptionPane.showMessageDialog(null, "La capacidad máxima de alumnos supera la capacidad del aula seleccionada:\nSeleccione otra aula o cambie la capacidad de alumnos actual.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se han rellenado todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    };
}
