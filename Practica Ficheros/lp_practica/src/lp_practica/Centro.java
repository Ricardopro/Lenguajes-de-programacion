/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Ricardo Bibiloni
 */
public class Centro {

    private final String nombre;

    String list_estudio = "list_estudio.txt";
    String list_asignatura = "list_asignatura.txt";
    String list_profesor = "list_profesor.txt";
    String list_aula = "list_aula.txt";

    //son privates
    public Centro(String nom) {
        this.nombre = nom;

    }

    public void crear_estudio_centro(String n, Estudio.tipo_estudio tip, String ca, String fc, String ff, ArrayList<Integer> asig, Turno t) throws Exception {
        EstudioInOut fichero = new EstudioInOut(list_estudio);

        Estudio b = new Estudio(n, tip, ca, fc, ff, asig, t);
        fichero.write(b);
        fichero.close();

    }

    public void crear_asignatura_centro(String nombrex, Aula.tipo_aula tip, int mc, String fc, String ff, int p, int a, int hc, int hf, int phc, int hxd, ArrayList<Estudio.Dia> diasxsemana) throws Exception {
        AsignaturaInOut fichero = new AsignaturaInOut(list_asignatura);
        Asignatura b = new Asignatura(nombrex, tip, mc, fc, ff, p, a, hc, hf, phc, hxd, diasxsemana);
        fichero.write(b);
        fichero.close();
    }

    public void crear_aula_centro(Aula.tipo_aula tipo, int capacidad) throws Exception {
        AulaInOut fichero = new AulaInOut(list_aula);
        Aula a = new Aula(tipo, capacidad);
        fichero.write(a);
        fichero.close();
    }

    public void crear_profesor_centro(String n, String nif, String telf) throws Exception {
        ProfesorInOut fichero = new ProfesorInOut(list_profesor);
        Profesor a = new Profesor(n, nif, telf);
        fichero.write(a);
        fichero.close();

    }

    public void borrar_estudio_centro(int identificador_estudio) throws Exception {
        boolean encontrado = false, borrado = false;
        EstudioInOut fichero = new EstudioInOut(list_estudio);
        Estudio b = fichero.read(identificador_estudio);
        try {
            b.set_identificador_estudio(0);
            encontrado = true;
            borrado = true;
            fichero.write(b, identificador_estudio);
            fichero.close();

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja el estudio: el estudio no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (encontrado && borrado) {
                JOptionPane.showMessageDialog(null, "¡El estudio se dio de baja satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
            } else if (encontrado && !borrado) {
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja el estudio: el estudio tiene asignada una asignatura.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el estudio: el aula no existe.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        fichero.close();
    }

    public void borrar_asignatura_centro(int identificador_asignatura) throws Exception {
        boolean encontrado = false, borrado = false;

        AsignaturaInOut fichero = new AsignaturaInOut(list_asignatura);
        Asignatura b = fichero.read(identificador_asignatura);
        try {
            b.set_identificador_asignatura(0);
            encontrado = true;
            borrado = true;
            fichero.write(b, identificador_asignatura);

            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja la asignatura: la asignatura no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (encontrado && borrado) {
                JOptionPane.showMessageDialog(null, "¡La asignatura se dio de baja satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
            } else if (encontrado && !borrado) {
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja la asignatura: la asignatura tiene asignada una asignatura.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo dar de baja la asignatura: el aula no existe.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        fichero.close();
    }

    public void borrar_aula_centro(int identificador_aula) throws Exception {

        boolean encontrado = false, borrado = false;
        AulaInOut fichero = new AulaInOut(list_aula);
        try {
            Aula b = new Aula();
            b = fichero.read(identificador_aula);
            b.set_identificador_aula(0);
//            System.out.println("idaula:" + b.get_identificador_aula());
            encontrado = true;
            borrado = true;
            fichero.write(b, identificador_aula);
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja el aula: el aula no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (encontrado && borrado) {
                JOptionPane.showMessageDialog(null, "¡El aula se dio de baja satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
            } else if (encontrado && !borrado) {
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja el aula: el aula tiene asignada una asignatura.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el aula: el aula no existe.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        fichero.close();

    }

    public void borrar_profesor_centro(int identificador_profesor) throws Exception {

        boolean encontrado = false, borrado = false;
        ProfesorInOut fichero = new ProfesorInOut(list_profesor);
        try {
            Profesor b = fichero.read(identificador_profesor);
            b.set_identificador_profesor(0);
            encontrado = true;
            borrado = true;
            fichero.write(b, identificador_profesor);
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja el profesor: el profesor no fue encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (encontrado && borrado) {
                JOptionPane.showMessageDialog(null, "¡El profesor se dio de baja satisfactoriamente!", "Operación completada con éxito", JOptionPane.INFORMATION_MESSAGE);
            } else if (encontrado && !borrado) {
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja el profesor: el profesor tiene asignada alguna asignatura.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el profesor: el aula no existe.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        fichero.close();
    }

    public void modificar_estudio_centro(String identificador, String n, ArrayList<Integer> asig, String fc, String ff, Turno t) throws Exception {
        EstudioInOut fichero = new EstudioInOut(list_estudio);
        Estudio b = fichero.read(Integer.parseInt(identificador));
        b.set_identificador_estudio(b.conseguir_numero_estudio());
        b.set_nombre(n);
        // b.set_cursoAcademico(ca);
        b.set_desarrollo(true);
        fichero.write(b, Integer.parseInt(identificador));
        fichero.close();

    }

    public void modificar_asignatura_centro(String identificador, String n, int p, int a, int mc, String fc, String ff) throws Exception {
        AsignaturaInOut fichero = new AsignaturaInOut(list_asignatura);
        Asignatura b = fichero.read(Integer.parseInt(identificador));
        b.set_max_cap(mc);
        b.set_codigo_profesor(p);
        b.set_codigo_aula(a);
        b.set_nombre(n);
        //b.set_desarrollo(0);
        fichero.write(b, Integer.parseInt(identificador));
        fichero.close();
    }

    public void modificar_aula_centro(String identificador, Aula.tipo_aula tipo, int capacidad) throws Exception {
        AulaInOut fichero = new AulaInOut(list_aula);
//        System.out.print(" identificaroroarjtew:" + identificador + "_");
//        System.out.print(" identificaroroarjtew:" + Integer.parseInt(identificador) + "_");
        Aula a = fichero.read(Integer.parseInt(identificador));
        a.set_tipo_aula(tipo);
        a.set_capacidad(capacidad);
        fichero.write(a, Integer.parseInt(identificador));
        fichero.close();
    }

    public void modificar_profesor_centro(String identificador, String n, String nif, String telf) throws Exception {
        ProfesorInOut fichero = new ProfesorInOut(list_profesor);
        Profesor a = fichero.read(Integer.parseInt(identificador));

        a.set_nombre(n);
        a.set_nif(nif);
        a.set_telefono(telf);

        fichero.write(a, Integer.parseInt(identificador));
        fichero.close();
    }

    public String[] consulta_array_estudio_centro() throws Exception {
        ArrayList<String> lista_ID_estudios = new ArrayList<>();
        EstudioInOut fichero = new EstudioInOut(list_estudio);
        Estudio ccc = fichero.read();

        try {
            while (ccc != null) {
                if (ccc.get_identificador_estudio() != 0) {
                    lista_ID_estudios.add(String.valueOf(ccc.get_codigo()));
                }
                ccc = fichero.read();
            }
        } catch (EOFException e) {
        }
        fichero.close();
        return lista_ID_estudios.toArray(new String[lista_ID_estudios.size()]);
    }

    public String[] consulta_array_asignatura_centro() throws Exception {
        ArrayList<String> lista_ID_asignaturas = new ArrayList<>();
        AsignaturaInOut fichero = new AsignaturaInOut(list_asignatura);
        Asignatura ccc = fichero.read();
        try {
            while (ccc != null) {
                if (ccc.get_identificador_asignatura() != 0) {
                    lista_ID_asignaturas.add(String.valueOf(ccc.get_codigo()));
                }
                ccc = fichero.read();
            }

        } catch (EOFException e) {
        }
        fichero.close();

        return lista_ID_asignaturas.toArray(new String[lista_ID_asignaturas.size()]);
    }

    public String[] consulta_array_nombres_asignatura_centro() throws Exception {
        ArrayList<String> lista_nombre_asignaturas = new ArrayList<>();
        AsignaturaInOut fichero = new AsignaturaInOut(list_asignatura);
        Asignatura ccc = fichero.read();
        while (ccc != null) {
            if (ccc.get_identificador_asignatura() != 0) {

                lista_nombre_asignaturas.add(String.valueOf(ccc.get_nombre()));
            }
            ccc = fichero.read();
        }

        fichero.close();

        return lista_nombre_asignaturas.toArray(new String[lista_nombre_asignaturas.size()]);
    }

    public String[] consulta_array_aula_centro() throws Exception {
        ArrayList<String> lista_ID_aulas = new ArrayList<>();
        AulaInOut fichero = new AulaInOut(list_aula);
        Aula ccc = fichero.read();
        try {
            while (ccc != null) {
                if (ccc.get_identificador() != 0) {
                    lista_ID_aulas.add(String.valueOf(ccc.get_codigo()));
                }
                ccc = fichero.read();
            }
        } catch (EOFException e) {
        }
        fichero.close();

        return lista_ID_aulas.toArray(new String[lista_ID_aulas.size()]);
    }

    public String[] consulta_array_profesor_centro() throws Exception {
        ArrayList<String> lista_NIF_profesores = new ArrayList<>();
        ProfesorInOut fichero = new ProfesorInOut(list_profesor);
        Profesor ccc = fichero.read();

        try {
            while (ccc != null) {
                if (ccc.get_identificador_profesor() != 0) {
                    //System.out.println("id:" + String.valueOf(ccc.get_identificador_profesor()) + "codigo:" + String.valueOf(ccc.get_codigo()));
                    lista_NIF_profesores.add(String.valueOf(ccc.get_codigo()));
                }
                ccc = fichero.read();
            }
        } catch (EOFException e) {
        }
        fichero.close();

        return lista_NIF_profesores.toArray(new String[lista_NIF_profesores.size()]);

    }

    public Estudio consulta_estudio_centro(int identificador_estudio) throws Exception {
        EstudioInOut fichero = new EstudioInOut(list_estudio);
        Estudio b = fichero.read(identificador_estudio);
        fichero.close();
        return b;

    }

    public Asignatura consulta_asignatura_centro(int identificador_asignatura) throws Exception {
        AsignaturaInOut fichero = new AsignaturaInOut(list_asignatura);
        Asignatura b = fichero.read(identificador_asignatura);
        fichero.close();
        return b;
    }

    public Asignatura consulta_nombre_asignatura_centro(String nombre_asignatura) throws Exception {
        AsignaturaInOut fichero = new AsignaturaInOut(list_asignatura);
        Asignatura ccc = fichero.read();
        try {
            while (ccc != null) {
                if (ccc.get_identificador_asignatura() != 0) {
                    if (ccc.get_nombre().equals(nombre_asignatura)) {

                        return ccc;

                    }
                }

                ccc = fichero.read();
            }
        } catch (EOFException e) {
        }
        fichero.close();

        return null;
    }

    public Aula consulta_aula_centro(int identificador_aula) throws Exception {
        AulaInOut fichero = new AulaInOut(list_aula);

        Aula b = fichero.read(identificador_aula);
//        System.out.print("**id**" + identificador_aula);
        fichero.close();
        return b;
    }

    public Profesor consulta_profesor_centro(int identificador_profesor) throws Exception {
        ProfesorInOut fichero = new ProfesorInOut(list_profesor);
        Profesor b = fichero.read(identificador_profesor);
//        System.out.print("**id**" + identificador_profesor);
        fichero.close();
        return b;
    }

// ESTUDIOS
    public String[] consulta_array_profesor_estudios(Estudio e) throws Exception { // devuelve la lista de profesores de un estudio
        ArrayList<String> sublista_profesor = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            if (e.get_lista_de_asignaturas2()[i] != 0) {

                sublista_profesor.add(String.valueOf(consulta_asignatura_centro(e.get_lista_de_asignaturas2()[i]).get_codigo_profesor()));
            }
        }
        return sublista_profesor.toArray(new String[sublista_profesor.size()]);
    }

    public String[] consulta_array_aulas_estudios(Estudio e) throws Exception { // devuelve la lista de aulas de un estudio
        ArrayList<String> sublista_aulas = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            if (e.get_lista_de_asignaturas2()[i] != 0) {

                sublista_aulas.add(String.valueOf(consulta_asignatura_centro(e.get_lista_de_asignaturas2()[i]).get_codigo_aula()));
            }
        }
        return sublista_aulas.toArray(new String[sublista_aulas.size()]);
    }

    public String[] consulta_array_asignaturas_estudios(Estudio e) throws Exception { // devuelve la lista de asignaturas de un estudio
        ArrayList<String> sublista_asignaturas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (e.get_lista_de_asignaturas2()[i] != 0) {
                sublista_asignaturas.add(String.valueOf(e.get_lista_de_asignaturas2()[i]));
            }
        }
        return sublista_asignaturas.toArray(new String[sublista_asignaturas.size()]);
    }

    //**************************
    // DEVUELVE EL ID DE UNA ASIGNATURA, BUSCADA POR NOMBRE
    public int consulta_ID_asignatura_centro(String nombre_asignatura) throws Exception {
        AsignaturaInOut fichero = new AsignaturaInOut(list_asignatura);
        Asignatura ccc = fichero.read();
        try {
            while (ccc != null) {
                if (ccc.get_identificador_asignatura() != 0 && ccc.get_identificador_asignatura() != -1) {
                    if (ccc.get_nombre().equals(nombre_asignatura)) {
                        return ccc.get_codigo();
                    }
                }
                ccc = fichero.read();
            }
        } catch (EOFException e) {
        }
        fichero.close();
        return -1;
    }

    // DEVUELVE EL ID DE UN PROFESOR, BUSCADA POR NOMBRE
    public String[] consulta_array_nombres_profesor_centro() throws Exception {
        ArrayList<String> lista_nombre_profesores = new ArrayList<>();
        ProfesorInOut fichero = new ProfesorInOut(list_profesor);
        Profesor ccc = fichero.read();
        while (ccc != null) {
            if (ccc.get_identificador_profesor() != 0 && ccc.get_identificador_profesor() != -1) {

                lista_nombre_profesores.add(String.valueOf(ccc.get_nombre()));
            }
            ccc = fichero.read();
        }

        fichero.close();

        return lista_nombre_profesores.toArray(new String[lista_nombre_profesores.size()]);
    }

    public int consulta_ID_profesor_centro(String nombre_profesor) throws Exception {
        ProfesorInOut fichero = new ProfesorInOut(list_profesor);
        Profesor ccc = fichero.read();
        try {
            while (ccc != null) {
                if (ccc.get_identificador_profesor() != 0 && ccc.get_identificador_profesor() != -1) {
                    if (ccc.get_nombre().equals(nombre_profesor)) {
                        return ccc.get_codigo();
                    }
                }
                ccc = fichero.read();
            }
        } catch (EOFException e) {
        }
        fichero.close();
        return -1;
    }
}
