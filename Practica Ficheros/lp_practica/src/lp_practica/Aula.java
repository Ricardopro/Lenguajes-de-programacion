/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.util.Scanner;

/**
 * @author Ricardo Bibiloni
 */
public class Aula {

    private static int numero_aula = 0;
    public final static int DIM = 20;
    private int codigo = 0;
    private int identificador_aula = 1;  //usado=1
    private int capacidad;
    private boolean aula_libre;
    private tipo_aula tipo; // A,B,C,D,E
    //private int usado=1;

    enum tipo_aula {

        A, B, C, D, E
    }

    public Aula() {

    }

    public Aula(tipo_aula tipo, int capacidad) {

        //this.identificador_aula = conseguir_numero_aula();
        this.identificador_aula = 1;
        this.tipo = tipo;
        this.capacidad = capacidad;
        aula_libre = true;

    }

    public void set_tipo_aula(tipo_aula tipo) {
        this.tipo = tipo;
    }

    public void set_capacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int conseguir_numero_aula() {
        numero_aula++;
        return numero_aula;
    }

    public int get_identificador() {

        return this.identificador_aula;
    }

    public int get_numero_aula() {

        return this.numero_aula;
    }

    public int get_capacidad() {

        return capacidad;
    }

    public tipo_aula get_tipo_aula() {

        return this.tipo;
    }

    public void set_estaLibre(boolean op) {
        this.aula_libre = op;
    }

    public boolean get_estaLibre() {
        return aula_libre;
    }

    public void set_codigo(int codigo) {

        this.codigo = codigo;
    }

    public int get_codigo() {

        return this.codigo;
    }

    public tipo_aula cambiarinttipo(int tipo) {
        if (1 == tipo) {
            return Aula.tipo_aula.A;
        }
        if (2 == tipo) {
            return Aula.tipo_aula.B;
        }
        if (3 == tipo) {
            return Aula.tipo_aula.C;
        }
        if (4 == tipo) {
            return Aula.tipo_aula.D;
        }
        if (5 == tipo) {
            return Aula.tipo_aula.E;
        }
        return null;
    }

    public int cambiartipoint(tipo_aula tipo) {
        if (Aula.tipo_aula.A == tipo) {
            return 1;
        }
        if (Aula.tipo_aula.B == tipo) {
            return 2;
        }
        if (Aula.tipo_aula.C == tipo) {
            return 3;
        }
        if (Aula.tipo_aula.D == tipo) {
            return 4;
        }
        if (Aula.tipo_aula.E == tipo) {
            return 5;
        }

        return 0;
    }

    public tipo_aula cambiarstringtipo(String tipo) {
//        

        if ("A" == tipo || " A" == tipo) {
            return Aula.tipo_aula.A;
        }
        if ("B" == tipo) {
            return Aula.tipo_aula.B;
        }
        if ("C" == tipo) {
            return Aula.tipo_aula.C;
        }
        if ("D" == tipo) {
            return Aula.tipo_aula.D;
        }
        if ("E" == tipo) {
            return Aula.tipo_aula.E;
        }
        System.out.print("fail1");
        return null;
    }

    public String cambiartipoastring(tipo_aula tipo) {
        System.out.print(" 222:" + tipo);
        String t = "--";
        if (Aula.tipo_aula.A == tipo) {
            return "A";
        }
        if (Aula.tipo_aula.B == tipo) {
            return "B";
        }
        if (Aula.tipo_aula.C == tipo) {
            return "C";
        }
        if (Aula.tipo_aula.D == tipo) {
            return "D";
        }
        if (Aula.tipo_aula.E == tipo) {
            return "E";
        }
        System.out.print("fail2");
        return t;
    }

    public int get_identificador_aula() {

        return this.identificador_aula;
    }

    public void set_identificador_aula(int identificador_aula) {
        this.identificador_aula = identificador_aula;
    }

    public boolean cambiardesarrollointboolean(int a) {
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
}
