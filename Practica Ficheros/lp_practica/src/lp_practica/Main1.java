/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Main1 {

    public static Centro c;
    static String botones[] = {"Estudios", "Asignatura", "Aula", "Profesor", "Alta", "Baja", "Modificar", "Consultar", "Atras"};
    static String path = "UIB";

    public static void main(String args[]) throws Exception {
        c = new Centro(path);

        File fichero1 = new File(c.list_estudio);
        if (!fichero1.exists()) {

            Main1.c.crear_aula_centro(Aula.tipo_aula.A, 100);
            Main1.c.crear_aula_centro(Aula.tipo_aula.B, 120);

            Main1.c.crear_profesor_centro("Carmen K.", "95687423W", "666584627");
            Main1.c.crear_profesor_centro("Eustaquio J.", "9582361254T", "666587451");
            

            // creacion de asignaturas
            ArrayList<Estudio.Dia> diasxsemana1 = new ArrayList<>();
            ArrayList<Estudio.Dia> diasxsemana2 = new ArrayList<>();
            ArrayList<Estudio.Dia> diasxsemana3 = new ArrayList<>();
            diasxsemana1.add(Estudio.Dia.LUNES);
            diasxsemana1.add(Estudio.Dia.JUEVES);
            diasxsemana1.add(Estudio.Dia.VIERNES);
            diasxsemana2.add(Estudio.Dia.LUNES);
            diasxsemana2.add(Estudio.Dia.MIERCOLES);
            diasxsemana3.add(Estudio.Dia.LUNES);
            diasxsemana3.add(Estudio.Dia.MARTES);
            diasxsemana3.add(Estudio.Dia.VIERNES);

            ArrayList<Integer> asig = new ArrayList<>();

            Main1.c.crear_asignatura_centro("Calculo ", Aula.tipo_aula.A, 100, "01-01-01", "03-08-15", 1, 1, 8, 11, 8, 2, diasxsemana1);
            Main1.c.crear_asignatura_centro("Algebra ", Aula.tipo_aula.B, 120, "01-01-01", "04-08-15", 2, 2, 17, 21, 8, 2, diasxsemana2);

            asig.add(1);
            //asig.add(2);
            Main1.c.crear_estudio_centro("L.P", Estudio.tipo_estudio.master, "2014-2015", "01-01-01", "02-01-01", asig, Turno.TARDE);

        }
        menuPrincipal();
    }

    public static void menuPrincipal() { // MENU PRINCIPAL: estudios, asignatura, aula, profesor
        new Menu(path, Arrays.copyOfRange(botones, 0, 4));
    }

    public static void menuSecundario(String nombre) { // MENU SECUNDARIO: alta, baja, mod, cons
        new Menu(path + ">" + nombre, Arrays.copyOfRange(Main1.botones, 4, 9));
    }

    //******** AULA
    public static void crear_ventana_aula_alta(String nombre) {
        new Menu_aula_alta(path + ">" + nombre);
    }

    public static void crear_ventana_aula_modificar(String nombre) {
        new Menu_aula_modificar(path + ">" + nombre);
    }

    public static void crear_ventana_aula_consultar(String nombre) {
        new Menu_aula_consultar(path + ">" + nombre);
    }

    public static void crear_ventana_bajas(String nombre) {
        new Menu_bajas(path + ">" + nombre);
    }

    //******** MENUS PROFESORES
    public static void crear_ventana_profesor_alta(String nombre) {
        new Menu_profesor_alta(path + ">" + nombre);
    }

    public static void crear_ventana_profesor_consultar(String nombre) throws Exception {
        new Menu_profesor_consultar(path + ">" + nombre);
    }

    public static void crear_ventana_profesor_modificar(String nombre) throws Exception {
        new Menu_profesor_modificar(path + ">" + nombre);
    }

    //******* MENUS ASIGNATURA
    public static void crear_ventana_asignatura_alta(String nombre) throws Exception {
        new Menu_asignatura_alta(path + ">" + nombre);
    }

    public static void crear_ventana_asignatura_modificar(String nombre) {
        new Menu_asignatura_modificar(path + ">" + nombre);
    }

    public static void crear_ventana_asignatura_consultar(String nombre) {
        new Menu_asignatura_consultar(path + ">" + nombre);
    }

    //***** MENUS ESTUDIOS
    public static void crear_ventana_estudios_alta(String nombre) {
        new Menu_estudios_alta(path + ">" + nombre);
    }

    public static void crear_ventana_estudios_modificar(String nombre) {
        new Menu_estudios_modificar(path + ">" + nombre);
    }

    public static void crear_ventana_estudios_consultar(String nombre) {
        new Menu_estudios_consultar(path + ">" + nombre);
    }

}
