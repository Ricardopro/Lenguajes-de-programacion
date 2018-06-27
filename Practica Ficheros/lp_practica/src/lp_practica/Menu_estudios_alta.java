/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import com.toedter.calendar.JDateChooser;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_estudios_alta {

    JFrame ventana_estudios_alta;
    String str_contenedores[] = {"                Nombre: ", "                  Tipo: ", "                    C.A: ",
        "              Fecha de inicio: ", "                       Fecha final: ", "                Asignaturas: "};
    Container contenedores[] = new Container[str_contenedores.length + 1]; // + contenedor botones
    JTextField txt_contenedores[] = new JTextField[2];
    Estudio.tipo_estudio tipo_enum = Estudio.tipo_estudio.conferencia;
    JButton añadir, cancelar, crear;
    JDateChooser fecha_inicio, fecha_final;
    JRadioButton[] t_estudio;
    static ArrayList<String> lista_nombres_asignaturas_del_estudio;
    ArrayList<Integer> lista_ID_asignaturas;

    public Menu_estudios_alta(String nombre) {
        ventana_estudios_alta = new JFrame(nombre);
        ventana_estudios_alta.setLayout(new GridLayout(str_contenedores.length + 1, 1));
        ventana_estudios_alta.setSize(296 * 2, 296 + 50);
        ventana_estudios_alta.setLocationRelativeTo(null);
        ventana_estudios_alta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lista_nombres_asignaturas_del_estudio = new ArrayList<>();
        lista_ID_asignaturas = new ArrayList<>();
        for (int i = 0, j = 0; i < (contenedores.length - 1); i++) {
            contenedores[i] = new Container();
            contenedores[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            contenedores[i].add(new JLabel(str_contenedores[i]));
            switch (i) {
                case 0:
                case 2:
                    txt_contenedores[j] = new JTextField(30);
                    contenedores[i].add(txt_contenedores[j++]);
                    break;
                case 1:
                    insertar_tipo_estudio(i);
                    break;
//                    array_profesores = new JComboBox(Main1.c.consulta_array_profesor_centro());
//                    array_profesores.addActionListener(selec_profesor);
//                    contenedores[i].add(array_profesores);
//                    break;

                case 3:
                    fecha_inicio = new JDateChooser();
                    fecha_inicio.setDateFormatString("dd-MM-yy");
                    contenedores[i].add(fecha_inicio);
                    break;
                case 4:
                    fecha_final = new JDateChooser();
                    fecha_final.setDateFormatString("dd-MM-yy");
                    contenedores[i].add(fecha_final);
                    break;
                case 5:
                    añadir = new JButton("Añadir");
                    añadir.addActionListener(mostrar_ventana);
                    contenedores[i].add(añadir);
                    break;

            }
            // INSERCIÓN AL CONTENEDOR 
            ventana_estudios_alta.getContentPane().add(contenedores[i]);
        }

        // CREAMOS BOTONES CREAR Y CANCELAR
        contenedores[contenedores.length - 1] = new Container(); // el contenedor 11 es el contenedor de los 2 botones
        contenedores[contenedores.length - 1].setLayout(new FlowLayout(FlowLayout.CENTER));
        cancelar = new JButton("Cancelar");
        crear = new JButton("Crear");
        añadir_listeners();
        contenedores[contenedores.length - 1].add(crear);
        contenedores[contenedores.length - 1].add(cancelar);
        ventana_estudios_alta.getContentPane().add(contenedores[contenedores.length - 1]); //añadimos boton aceptar
        ventana_estudios_alta.setVisible(true);

    }

    public void añadir_listeners() {
        cancelar.addActionListener(action_cancelar);
        crear.addActionListener(action_crear);
    }

    public void insertar_tipo_estudio(int indice) {
        ButtonGroup grupoBotonesOpcion = new ButtonGroup();
        t_estudio = new JRadioButton[Aula.tipo_aula.values().length];
        int i = 0;
        for (Estudio.tipo_estudio a : Estudio.tipo_estudio.values()) {
            t_estudio[i] = new JRadioButton(Estudio.cambiar_estudio_a_string(a), true);
            t_estudio[i].addActionListener(seleccion);
            grupoBotonesOpcion.add(t_estudio[i]);
            contenedores[indice].add(t_estudio[i++]);
        }
        ventana_estudios_alta.add(contenedores[indice]);
    }
    ActionListener seleccion = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            switch (ev.getActionCommand()) {
                case "Conferencia":
                    tipo_enum = Estudio.tipo_estudio.conferencia;
                    break;
                case "Curso de Esp.":
                    tipo_enum = Estudio.tipo_estudio.curso_de_especializacion;
                    break;
                case "Máster":
                    tipo_enum = Estudio.tipo_estudio.master;
                    break;
                case "Mesa redonda":
                    tipo_enum = Estudio.tipo_estudio.mesaredonda;
                    break;
                case "Taller":
                    tipo_enum = Estudio.tipo_estudio.taller;
                    break;

            }
        }
    };

    ActionListener action_cancelar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana_estudios_alta.setVisible(false);
            Main1.menuSecundario(Menu.menu_selec1);
        }
    };
    ActionListener mostrar_ventana = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            try {
                new Menu_seleccionar_asignaturas_estudio(ventana_estudios_alta.getTitle());
            } catch (Exception ex) {
                Logger.getLogger(Menu_estudios_alta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public void añadir_asignaturas() throws Exception {
        for (String a : lista_nombres_asignaturas_del_estudio) {
            lista_ID_asignaturas.add(Main1.c.consulta_nombre_asignatura_centro(a).get_codigo());
        }
    }

    public Turno obtenerTurno() {
        Turno turno = null;
        for (Integer id_asignatura : lista_ID_asignaturas) {
            if (turno != Turno.COMPLETO) {
                try {
                    if (Main1.c.consulta_asignatura_centro(id_asignatura).get_hora_comienzo() >= Turno.MAÑANA.getInicio() && Main1.c.consulta_asignatura_centro(id_asignatura).get_hora_final() <= Turno.MAÑANA.getFin()) {
                        if (turno == null) {
                            turno = Turno.MAÑANA;
                        }
                        if (turno == Turno.TARDE) {
                            turno = Turno.COMPLETO;
                        }
                    } else if (Main1.c.consulta_asignatura_centro(id_asignatura).get_hora_comienzo() >= Turno.TARDE.getInicio() && Main1.c.consulta_asignatura_centro(id_asignatura).get_hora_final() <= Turno.TARDE.getFin()) {
                        if (turno == null) {
                            turno = Turno.TARDE;
                        }
                        if (turno == Turno.MAÑANA) {
                            turno = Turno.COMPLETO;
                        }
                    } else if (Main1.c.consulta_asignatura_centro(id_asignatura).get_hora_comienzo() >= Turno.MAÑANA.getInicio() && Main1.c.consulta_asignatura_centro(id_asignatura).get_hora_final() <= Turno.TARDE.getFin()) {
                        turno = Turno.COMPLETO;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Menu_estudios_alta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return turno;
    }
    // COMPROBAR TODOS LOS CAMPOS OCUPADOS ANTES DE CREAR
    ActionListener action_crear = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            try {

                // si todos los datos han sido rellenados
                String n = txt_contenedores[0].getText();
                String ca = txt_contenedores[1].getText();;
                String fc = ((JTextField) fecha_inicio.getDateEditor().getUiComponent()).getText();
                String ff = ((JTextField) fecha_final.getDateEditor().getUiComponent()).getText();
                lista_ID_asignaturas.clear();
                añadir_asignaturas();
                
                if (lista_ID_asignaturas.size() > 0) {
                    Turno t = obtenerTurno();
                    Main1.c.crear_estudio_centro(n, tipo_enum, ca, fc, ff, lista_ID_asignaturas, t);
                    ventana_estudios_alta.setVisible(false);
                    JOptionPane.showMessageDialog(null, "¡Los estudios se dieron de alta satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
                    Main1.menuSecundario(Menu.menu_selec1);
                } else {
                    JOptionPane.showMessageDialog(null, "¡No se ha seleccionado ninguna asignatura!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se han rellenado todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    };
}
