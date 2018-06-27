/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Semana {

    enum Dias {

        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES
    };

    public static String cambiar_dia_a_string(Dias dia) {
        String t= " ";
        switch (dia) {
            case LUNES:
                return "L";
            case MARTES:
                return "M";
            case MIERCOLES:
                return "X";
            case JUEVES:
                return "J";
            case VIERNES:
                return "V";

        }
        return t;
    }
 }
