/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
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
public class Menu_asignatura_consultar2 {

    JFrame ventana_asignatura_consultar2;
//    String str_contenedores[] = {"                Nombre: ", "              Profesor: ", "                   Aula: ", "Máx. Estudiantes: ",
//        "              Fecha de inicio: ", "                    Fecha final: ", "                Días/Semana: ", "                      Horas/día: ", "Hora inicio preferente: "};
//    
    String str_contenedores[] = {"Nombre: ", "Profesor: ", " Aula: ", "Máx. Estudiantes: ",
        "Fecha de inicio: ", "Fecha final: ", "Días/Semana: ", "Horas/día: ", "Hora inicio preferente: "};

    Container contenedores[] = new Container[str_contenedores.length + 1]; // + contenedor botones 
    JButton cancelar, aceptar;
    String prev_ventana, aux;
    int codigo;

    public Menu_asignatura_consultar2(String prev_nom, String nombre, String seleccion) throws Exception {
        ventana_asignatura_consultar2 = new JFrame(nombre);
        ventana_asignatura_consultar2.setLayout(new BorderLayout());
        ventana_asignatura_consultar2.setSize(296 * 2, 296 + 50);
        ventana_asignatura_consultar2.setLocationRelativeTo(null);
        ventana_asignatura_consultar2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        prev_ventana = prev_nom; // para poder darle el nombre correspondiente al volver a la ventana anterior
        // CREACIÓN OBJETO JTabbedPane 
        JTabbedPane Multipaneles = new JTabbedPane();

        // ESTABLECIMIENTO DEL PANEL pestaña1 
        JPanel pestaña1 = new JPanel();
        pestaña1.setLayout(new GridLayout(7, 1));
        pestaña1.setSize(ventana_asignatura_consultar2.getSize());
        pestaña1.add(new JLabel("DATOS GENERALES", SwingConstants.CENTER));

        // ESTABLECIMIENTO DEL PANEL pestaña2 E INCORPORACIÓN AL MULTIPANELES 
        JPanel pestaña2 = new JPanel();
        pestaña2.setLayout(new GridLayout(7, 1));
        pestaña2.setSize(ventana_asignatura_consultar2.getSize());
//        pestaña2.setBackground(Color.RED); 
        pestaña2.add(new JLabel("HORARIO", SwingConstants.CENTER));

        for (int i = 0, j = 0; i < (contenedores.length - 1); i++) {
            contenedores[i] = new Container();
            contenedores[i].setLayout(new FlowLayout(FlowLayout.CENTER));
            contenedores[i].add(new JLabel(str_contenedores[i]));

            switch (i) {
                case 0: // NOMBRE
                    aux = Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_nombre();
                    break;
                case 1: // PROFESOR
                    codigo = Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_codigo_profesor();
                    aux = Main1.c.consulta_profesor_centro(codigo).get_nombre();
                    break;
                case 2: // AULA
                    codigo = Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_codigo_aula();
                    aux = String.valueOf(Main1.c.consulta_aula_centro(codigo).get_codigo()) + "-" + Main1.c.consulta_aula_centro(codigo).get_tipo_aula().toString();
                    break;
                case 3: // MAX ESTUDIANTES
                    codigo = Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_max_cap();
                    aux = String.valueOf(codigo);
                    break;
                case 4: //FECHA COMIENZO 
                    aux = Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_fecha_comienzo();
                    break;
                case 5: // FECHA FINAL 
                    aux = Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_fecha_final();
                    break;
                case 6:  // DIAS/SEMANA 
                    aux = "";
                    for (int w = 0; w < Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_array_dias().size(); w++) {
                        if (Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_array_dias().get(w)!=null) {
                            aux += Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_array_dias().get(w) + ", ";
                        }
                    }
                    aux = aux.substring(0, aux.length() - 2);
                    break;
                case 7: // HORAS/DIA 
                    aux = String.valueOf(Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_horas_dia());
                    break;
                case 8: //HORA COMIENZO PREFERENTE
                    aux = String.valueOf(Main1.c.consulta_asignatura_centro(Integer.parseInt(seleccion)).get_hora_comienzo_pref());
                    break;
            }
            contenedores[i].add(new JLabel(aux));

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
        ventana_asignatura_consultar2.getContentPane().add(Multipaneles, BorderLayout.CENTER); // añadimos pestañas

        // CREAMOS BOTONES CREAR Y CANCELAR
        contenedores[contenedores.length - 1] = new Container(); // el contenedor de los 2 botones
        contenedores[contenedores.length - 1].setLayout(new FlowLayout(FlowLayout.CENTER));
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(action_aceptar);
        contenedores[contenedores.length - 1].add(aceptar);
        ventana_asignatura_consultar2.getContentPane().add(contenedores[contenedores.length - 1], BorderLayout.SOUTH); //añadimos boton aceptar
        ventana_asignatura_consultar2.setVisible(true);
    }

    ActionListener action_aceptar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {
            ventana_asignatura_consultar2.setVisible(false);
//            Main1.menuSecundario(Menu.menu_selec1);
            new Menu_asignatura_consultar(prev_ventana);
        }
    };

}
