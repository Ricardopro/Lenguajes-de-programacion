/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.util.ArrayList;

/**
 * @author Ricardo Bibiloni
 */
public class Profesor {

    private static int numero_profesor = 0;
    public final static int DIM = 244; //122
    private int codigo = 0;
    private int identificador_profesor=1;
    private String NIF;
    private String nombre;
    private String telefono;
    private int num_asignaturas_profesor=0;//contador de las asignadas; para comprobar si esta dando alguna asignatura antes de dar de baja
    // eliminar lista_asignaturas_profesor
    ArrayList<Integer> lista_asignaturas_profesor = new ArrayList<>(10);
    private int[] lista_asignaturas_profesor2 = new int[10];

    public Profesor(String n, String nif, String telf) {
//        identificador_profesor = conseguir_numero_profesor();
        identificador_profesor =1;
        num_asignaturas_profesor = 0;
        this.nombre = n;
        this.NIF = nif;
        this.telefono = telf;
        
        
        lista_asignaturas_profesor = new ArrayList<>(10); 
        lista_asignaturas_profesor2=set_array_asignatura();
        
        num_asignaturas_profesor=lista_asignaturas_profesor.size();
        
        
        //lista_asignaturas_profesor = new ArrayList<>();
    }

    public Profesor() {
    }

    public void asignar_asignatura_profesor(int identificador_asignatura) {
        if (!lista_asignaturas_profesor.contains(identificador_asignatura)) {
            lista_asignaturas_profesor.add(identificador_asignatura);
        } else {
            System.out.print(" Asignatura ya asignada a ese profesor");
        }
        num_asignaturas_profesor++;

    }
 

    public void desasignar_asignatura_profesor(int identificador_asignatura) { // FALTA PROBAR
        if (lista_asignaturas_profesor.contains(identificador_asignatura)) {
            lista_asignaturas_profesor.remove(identificador_asignatura);
        } else {
            System.out.print(" Asignatura ya borrada a ese profesor");
        }
        num_asignaturas_profesor--;

    }

    public int conseguir_numero_profesor() {
        numero_profesor++;
        return numero_profesor;
    }

    public void set_nombre(String nombre) {
        this.nombre = nombre;
    }

    public void set_nif(String NIF) {
        this.NIF = NIF;
    }

    public void set_identificador_profesor(int identificador_profesor) {
        this.identificador_profesor = identificador_profesor;
    }

    public void set_num_asignaturas_profesor(int profesor_libre) {
        this.num_asignaturas_profesor = profesor_libre;
    }

    public void set_codigo(int codigo) {
        this.codigo = codigo;
    }

    public void set_telefono(String telefono) {
        this.telefono = telefono;
    }

    public int get_identificador_profesor() {
        return this.identificador_profesor;
    }

    public int get_num_asignaturas_profesor() {
        return this.num_asignaturas_profesor;
    }

    public boolean get_estaLibre() {
        return num_asignaturas_profesor == 0;
    }

//    public boolean get_profesor_libre_boolean() {
//        if(this.num_asignaturas_profesor>0){
//        return false;
//        }
//        
//
//        return true;
//    }
    public int get_codigo() {
        return this.codigo;
    }

    public String get_nombre() {
        return this.nombre;
    }

    public String get_NIF() {
        return this.NIF;
    }

    public String get_telefono() {
        return telefono;
    }

    public void set_lista_asignaturas_profesor2(int[] lista_asignaturas_profesor2) {

        this.lista_asignaturas_profesor2 = lista_asignaturas_profesor2;
    }

    public int[] get_lista_asignaturas_profesor2() {

        return this.lista_asignaturas_profesor2;
    }
    
    public ArrayList<Integer> get_array_asignatura() {
        ArrayList<Integer> diasx = new ArrayList<>(10);
        for (int i = 0; i < lista_asignaturas_profesor2.length; i++) {

            diasx.add(i, lista_asignaturas_profesor2[i]);

        }

        return diasx;
    }
     public int[] set_array_asignatura() {
        int[] diasx = new int[10];

        //ArrayList<Dia> diasx = new ArrayList<>(5);
        for (int i = 0; i < lista_asignaturas_profesor.size(); i++) {

            diasx[i]=  lista_asignaturas_profesor.get(i);

        }

        return diasx;
    }
   
}
