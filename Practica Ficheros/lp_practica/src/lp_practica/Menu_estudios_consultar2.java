/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import lp_practica.Estudio.Dia;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Menu_estudios_consultar2 {

    JFrame ventana_estudios_consultar2;
//    String str_contenedores[] = {"                Nombre: ", "              Profesor: ", "              Estudios: ", "                   Aula: ", "           Turno: ",
//        "              Fecha de inicio: ", "                    Fecha final: ", "                Días/Semana: ", "                      Horas/día: ", "Hora inicio preferente: ", "                     Tipo: ", "                  Curso académico: ", "                  Asignaturas: "};
    String str_contenedores[] = {"                Nombre: ", "              Profesor: ", "              Estudios: ", "                   Aula: ", "           Turno: ",
        "              Fecha de inicio: ", "                    Fecha final: ", "                     Tipo: ", "                  Curso académico: ", "                  Asignaturas: "};

    Container contenedores[] = new Container[str_contenedores.length + 1]; // + contenedor botones 
    JButton cancelar, aceptar;
    String aux, prev_ventana;
    int codigo;
    JComboBox lista_de_profesores, lista_de_aulas, lista_de_asignaturas;

    public Menu_estudios_consultar2(String prev_nom, String nombre, String seleccion) throws Exception {
        ventana_estudios_consultar2 = new JFrame(nombre);
        ventana_estudios_consultar2.setLayout(new BorderLayout());
        ventana_estudios_consultar2.setSize(415, 520);
        ventana_estudios_consultar2.setLocationRelativeTo(null);
        ventana_estudios_consultar2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        prev_ventana=prev_nom; // para poder darle el nombre correspondiente al volver a la ventana anterior
        // CREACIÓN OBJETO JTabbedPane 
        JTabbedPane Multipaneles = new JTabbedPane();

        // ESTABLECIMIENTO DEL PANEL pestaña1 
        JPanel pestaña1 = new JPanel();
        pestaña1.setLayout(new GridLayout(13, 1)); // antes 7
        pestaña1.setSize(ventana_estudios_consultar2.getSize());
        pestaña1.add(new JLabel("DATOS GENERALES", SwingConstants.CENTER));

        // ESTABLECIMIENTO DEL PANEL pestaña2 E INCORPORACIÓN AL MULTIPANELES 
        JPanel pestaña2 = new JPanel();
        pestaña2.setLayout(new GridLayout(13, 1));
        pestaña2.setSize(ventana_estudios_consultar2.getSize());
        pestaña2.add(new JLabel("HORARIO", SwingConstants.CENTER));

        for (int i = 0, j = 0; i < (contenedores.length - 1); i++) {
            contenedores[i] = new Container();
            contenedores[i].setLayout(new FlowLayout(FlowLayout.CENTER));
//            contenedores[i].add(new JLabel(str_contenedores[i]));
            switch (i) {
                case 0: // NOMBRE
                    contenedores[i].add(new JLabel(str_contenedores[i]));
                    aux = Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion)).get_nombre();
                    contenedores[i].add(new JLabel(aux));
                    break;
                case 1: // PROFESOR // LISTA PROFESORES 
                    contenedores[i].add(new JLabel(str_contenedores[i]));
                    lista_de_profesores = new JComboBox(Main1.c.consulta_array_profesor_estudios(Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion))));
                    contenedores[i].add(lista_de_profesores);
                    break;
                case 2: // ESTUDIOS
                    break;
                case 3: // AULA
                    contenedores[i].add(new JLabel(str_contenedores[i]));
                    lista_de_aulas = new JComboBox(Main1.c.consulta_array_aulas_estudios(Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion))));
                    contenedores[i].add(lista_de_aulas);
                    break;
                case 4: // MAX ESTUDIANTES // TURNO 
                    contenedores[i].add(new JLabel(str_contenedores[i]));
                    aux = String.valueOf(Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion)).get_Turno());
                    contenedores[i].add(new JLabel(aux));
                    break;
                case 5: //FECHA COMIENZO 
                    contenedores[i].add(new JLabel(str_contenedores[i]));
                    aux = Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion)).get_fecha_comienzo();
                    contenedores[i].add(new JLabel(aux));
                    break;
                case 6: // FECHA FINAL 
                    contenedores[i].add(new JLabel(str_contenedores[i]));
                    aux = Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion)).get_fecha_final();
                    contenedores[i].add(new JLabel(aux));
                    break;
//                case 7:  // DIAS/SEMANA 
//                    aux = "";
//                    for (Dia a : Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_array_dias()) {
//                        aux += a.toString() + ", ";
//                    }
//                    aux = aux.substring(0, aux.length() - 2);
//                    break;
//                case 8: // HORAS/DIA 
//                    aux = String.valueOf(Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion)).get);
//                    break;
//                case 9: //HORA COMIENZO PREFERENTE
//                    aux = String.valueOf(Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_hora_comienzo_pref());
//                    break;
//                case 10: // CALENDARIO
//                    aux = "sadsad";
//                    break;
                case 7: // TIPO 
                    contenedores[i].add(new JLabel(str_contenedores[i]));
//                    Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion))==Estudio.tipo_estudio.master;
                    System.out.println("tipoar: "+ Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion)).get_tipo_estudio());
                    aux = Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion)).get_tipo_estudio().toString();
                    contenedores[i].add(new JLabel(aux));
                    break;
                case 8: // CURSO ACADEMICO
                    contenedores[i].add(new JLabel(str_contenedores[i]));
                    aux = Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion)).get_cursoAcademico();
                    contenedores[i].add(new JLabel(aux));
                    break;
                case 9: // ASIGNATURAS
                    contenedores[i].add(new JLabel(str_contenedores[i]));
                    lista_de_asignaturas = new JComboBox(Main1.c.consulta_array_asignaturas_estudios(Main1.c.consulta_estudio_centro(Integer.parseInt(seleccion))));
                    contenedores[i].add(lista_de_asignaturas);
                    break;
                case 10:
                    break;
            }

            if (i > 3 && i!=7) {
                pestaña2.add(contenedores[i]); // añadir a pestaña 2
            } else {
                pestaña1.add(contenedores[i]); // añadir a pestaña 1
            }
        }
        // addTab(String title, Icon icon, Component component, String tip) 
        Multipaneles.addTab("DATOS GENERALES", null, pestaña1, "Datos generales de la asignatura");
        Multipaneles.addTab("HORARIO", null, pestaña2, "Horario anual de la asignatura");

        // INSERCIÓN DEL MULTIPANEL AL CONTENEDOR 
        ventana_estudios_consultar2.getContentPane().add(Multipaneles, BorderLayout.CENTER); // añadimos pestañas

        // CREAMOS BOTONES CREAR Y CANCELAR
        contenedores[contenedores.length - 1] = new Container(); // el contenedor 11 es el contenedor de los 2 botones
        contenedores[contenedores.length - 1].setLayout(new FlowLayout(FlowLayout.CENTER));
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(action_aceptar);
        contenedores[contenedores.length - 1].add(aceptar);
        ventana_estudios_consultar2.getContentPane().add(contenedores[contenedores.length - 1], BorderLayout.SOUTH); //añadimos boton aceptar
        ventana_estudios_consultar2.setVisible(true);
    }

    ActionListener action_aceptar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana_estudios_consultar2.setVisible(false);
//            Main1.menuSecundario(Menu.menu_selec1);
            new Menu_estudios_consultar(prev_ventana);
        }
    };

}
