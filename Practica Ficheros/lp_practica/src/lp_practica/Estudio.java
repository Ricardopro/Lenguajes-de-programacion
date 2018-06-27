/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.util.ArrayList;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Estudio {

    public final static int DIM = 480; //240
    private static int numero_estudio = 0;
    private int codigo = 0;
    private int identificador_estudio = 1;
    private String nombre;
    private String cursoAcademico;
    private boolean en_desarrollo; // si NO esta en en_desarrollo, se puede dar de baja
    private tipo_estudio tipo;
    private String fechaComienzo;
    private String fechaFinalizacion;
    private ArrayList<Integer> lista_asignatura = new ArrayList<>(50);
    private int num_asignaturas_estudio;
    private int[] lista_asignaturas_estudio = new int[50];
    private Turno turno;

    enum tipo_estudio {

        master, curso_de_especializacion, taller, conferencia, mesaredonda
    }

    enum Dia {

        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES
    }

    Estudio() {

    }

    // constructores
    public Estudio(String n, tipo_estudio tip, String ca, String fc, String ff, ArrayList<Integer> asig, Turno t) throws Exception {
        identificador_estudio = 1;
        this.nombre = n;
        this.tipo = tip;
        this.cursoAcademico = ca;
        this.fechaComienzo = fc;
        this.fechaFinalizacion = ff;
        lista_asignatura = new ArrayList<>(50);
        this.set_lista_asignaturas(asig);
        lista_asignaturas_estudio = set_array_asignatura();
        num_asignaturas_estudio = lista_asignatura.size();
        this.turno = t;
        en_desarrollo = false;
        
    }

    public int conseguir_numero_estudio() {
        numero_estudio++;
        return numero_estudio;
    }


    public double set_horaComienzo(Dia dia, double hora_preferente) {
        return 1;
    }

    public static String cambiar_estudio_a_string(Estudio.tipo_estudio e) {
        String t = " ";
        switch (e) {
            case master:
                return "Máster";
            case curso_de_especializacion:
                return "Curso de Esp.";
            case taller:
                return "Taller";
            case conferencia:
                return "Conferencia";
            case mesaredonda:
                return "Mesa redonda";

        }
        return t;
    }

    public tipo_estudio getTipo() {
        return this.tipo;
    }

    public String toString_getTipo() {
        switch (this.tipo) {
            case master:
                return "Máster";
            case curso_de_especializacion:
                return "Curso de Especialización";
            case taller:
                return "Taller";
            case conferencia:
                return "Conferencia";
            case mesaredonda:
                return "Mesa redonda";
            default:
                return "";
        }
    }

    public int get_identificador() {
        return this.identificador_estudio;
    }

    public void set_codigo(int codigo) {
        this.codigo = codigo;
    }

    public int get_codigo() {
        return this.codigo;
    }

    public void set_identificador_estudio(int identificador_estudio) {
        this.identificador_estudio = identificador_estudio;
    }

    public int get_identificador_estudio() {
        return this.identificador_estudio;
    }

    public void set_desarrollo(Boolean desarrollo) {
        this.en_desarrollo = desarrollo;
    }

    public Boolean get_desarrollo() {
        return this.en_desarrollo;
    }

    public String get_nombre() {
        return this.nombre;
    }

    public void set_nombre(String nombre) {
        this.nombre = nombre;
    }

    public String get_cursoAcademico() {
        return this.cursoAcademico;
    }

    public void set_cursoAcademico(String cursoAcademico) {
        this.cursoAcademico = cursoAcademico;
    }

    public ArrayList<Integer> get_lista_de_asignaturas() {
        return lista_asignatura;
    }

    public int[] get_lista_de_asignaturas2() {
        return lista_asignaturas_estudio;
    }

    public Turno get_Turno() {
        return this.turno;
    }

    public String getTurno() {
        switch (this.turno) {
            case MAÑANA:
                return "Turno de mañana";
            case TARDE:
                return "Turno de tarde";
            case COMPLETO:
                return "Turno completo";
            default:
                return "";
        }
    }

    public void set_fecha_comienzo(String fc) {
        fechaComienzo = fc;
    }

    public void set_fecha_final(String ff) {
        fechaFinalizacion = ff;
    }

    public String get_fecha_comienzo() {
        return this.fechaComienzo;
    }

    public String get_fecha_final() {
        return this.fechaFinalizacion;
    }

    public void set_tipo_estudio(tipo_estudio t) {
        this.tipo = t;
    }

    public void set_turno(Turno tur) {
        this.turno = tur;
    }

    public Turno get_turno() {
        return this.turno;
    }

    public void set_lista_asignaturas(ArrayList<Integer> list_asig) throws Exception {
        // decrementamos el contador del numero de estudios de las asignaturas actuales del estudio en 1 al quitarlas del estudio
        System.out.println("ENTRA");
        int n_estudios;
        for (Integer asig : lista_asignatura) {
            n_estudios = Main1.c.consulta_asignatura_centro(asig).get_num_estudios_asignatura();
            n_estudios--;
            Main1.c.consulta_asignatura_centro(asig).set_num_estudios_asignatura(n_estudios);
            if (n_estudios > 0) { // actualizamos variable en_desarrollo de cada asignatura
                Main1.c.consulta_asignatura_centro(asig).set_desarrollo(true);
            } else {
                Main1.c.consulta_asignatura_centro(asig).set_desarrollo(false);
            }
        }

        lista_asignatura.clear();
        lista_asignatura.addAll(list_asig);

        n_estudios = 0;
        // incrementamos el contador del numero de estudios de las asignaturas añadidas al estudio en 1 al añadirlas al estudio
        for (Integer asig : lista_asignatura) {
            n_estudios = Main1.c.consulta_asignatura_centro(asig).get_num_estudios_asignatura();
            n_estudios++;
            Main1.c.consulta_asignatura_centro(asig).set_num_estudios_asignatura(n_estudios);
            if (n_estudios > 0) { // actualizamos variable en_desarrollo de cada asignatura
                System.out.println("TRUE");
                Main1.c.consulta_asignatura_centro(asig).set_desarrollo(true);
            } else {
                System.out.println("FALSE");
                Main1.c.consulta_asignatura_centro(asig).set_desarrollo(false);
            }
        }
    }

    public Boolean cambiardesarrollointboolean(int a) {
        if (a == 1) {
            return true;
        }
        return false;
    }

    public int cambiardesarrollobooleanint(boolean a) {
        if (a == true) {
            return 1;
        }
        return 0;
    }

    public tipo_estudio cambiarinttipo(int tipo) {
        if (1 == tipo) {
            return tipo_estudio.master;
        }
        if (2 == tipo) {
            return tipo_estudio.curso_de_especializacion;
        }
        if (3 == tipo) {
            return tipo_estudio.taller;
        }
        if (4 == tipo) {
            return tipo_estudio.conferencia;
        }
        if (5 == tipo) {
            return tipo_estudio.mesaredonda;
        }
        return null;
    }

    public int cambiartipoint(tipo_estudio tipo) {
        if (tipo_estudio.master == tipo) {
            return 1;
        }
        if (tipo_estudio.curso_de_especializacion == tipo) {
            return 2;
        }
        if (tipo_estudio.taller == tipo) {
            return 3;
        }
        if (tipo_estudio.conferencia == tipo) {
            return 4;
        }
        if (tipo_estudio.mesaredonda == tipo) {
            return 5;
        }

        return 0;
    }

    public tipo_estudio get_tipo_estudio() {

        return this.tipo;
    }

    public Turno cambiarintturno(int tipo) {
        if (1 == tipo) {
            return Turno.MAÑANA;
        }
        if (2 == tipo) {
            return Turno.TARDE;
        }
        if (3 == tipo) {
            return Turno.COMPLETO;
        }
        return null;
    }

    public int cambiarturnoint(Turno tipo) {
        if (Turno.MAÑANA == tipo) {
            return 1;
        }
        if (Turno.TARDE == tipo) {
            return 2;
        }
        if (Turno.COMPLETO == tipo) {
            return 3;
        }

        return 0;
    }

    public ArrayList<Integer> get_array_asignatura() {
        ArrayList<Integer> diasx = new ArrayList<>(50);
        for (int i = 0; i < lista_asignaturas_estudio.length; i++) {

            diasx.add(i, lista_asignaturas_estudio[i]);

        }

        return diasx;
    }

    public int[] set_array_asignatura() {
        int[] diasx = new int[50];

        //ArrayList<Dia> diasx = new ArrayList<>(5);
        for (int i = 0; i < lista_asignatura.size(); i++) {

            diasx[i] = lista_asignatura.get(i);

        }

        return diasx;
    }

    public void set_lista_asignaturas_estudio(int[] lista_asignaturas_estudio) {

        this.lista_asignaturas_estudio = lista_asignaturas_estudio;
    }

    public int[] get_lista_asignaturas_estudio() {

        return this.lista_asignaturas_estudio;
    }

    public void set_num_asignaturas_estudio(int num_asignaturas_estudio) {
        this.num_asignaturas_estudio = num_asignaturas_estudio;
    }

    public int get_num_asignaturas_estudio() {
        return this.num_asignaturas_estudio;
    }
}
