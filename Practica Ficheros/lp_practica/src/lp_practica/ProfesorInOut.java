/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Bibiloni
 */
public class ProfesorInOut {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    RandomAccessFile fichero1;
    Profesor var = new Profesor();

    public ProfesorInOut(String lista_profesor) throws Exception {
        fichero1 = new RandomAccessFile(lista_profesor, "rw");
    }

    public Profesor read() throws IOException, java.text.ParseException {
        try {
            var.set_codigo(fichero1.readInt());
            var.set_identificador_profesor(fichero1.readInt());
            var.set_num_asignaturas_profesor(fichero1.readInt());
            //var.lista_asignaturas_profesor = (       readString(var.lista_asignaturas_profesor.size());
            var.set_nif(readString(32));
            var.set_nombre(readString(32));
            var.set_telefono(readString(32));

            var.set_lista_asignaturas_profesor2(readlistaint());
            return var;
        
        } catch (Exception e) {
            return null;
            //JOptionPane.showMessageDialog(null, "No se pudo dar de baja el profesor: el aula no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Profesor read(int numero) throws IOException, java.text.ParseException {
        try {
            fichero1.seek(((numero - 1) * Profesor.DIM));
            var.set_codigo(fichero1.readInt());
            var.set_identificador_profesor(fichero1.readInt());
            var.set_num_asignaturas_profesor(fichero1.readInt());
            //var.lista_asignaturas_profesor = (       readString(var.lista_asignaturas_profesor.size());
            var.set_nif(readString(32));
            var.set_nombre(readString(32));
            var.set_telefono(readString(32));
            var.set_lista_asignaturas_profesor2(readlistaint());
            return var;
        
        } catch (Exception e) {
            return null;
//            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el profesor: el aula no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private String readString(int dim) throws IOException {
        char campo[] = new char[dim];
        for (int i = 0; i < dim; i++) {
            campo[i] = fichero1.readChar();
        }
        return new String(campo).replace('\0', ' ');
    }

    public void write(Profesor var) throws Exception {
        fichero1.seek(fichero1.length());
        //2+2+2+32+32)*2=
        var.set_codigo((int) (fichero1.length() / Profesor.DIM) + 1);
        fichero1.writeInt(var.get_codigo());
        fichero1.writeInt(var.get_identificador_profesor());
        fichero1.writeInt(var.get_num_asignaturas_profesor());
        //arrayinteger   
        writeString(var.get_NIF(), 32);
        writeString(var.get_nombre(), 32);
        writeString(var.get_telefono(), 32);
        writelistaint(var.get_lista_asignaturas_profesor2());

    }

    public void write(Profesor var, int numero) throws Exception {
        fichero1.seek((numero - 1) * Profesor.DIM);
        //2+2+2+1)*2=
        //var.codigo=(int) (fichero1.length()/Aula.DIM)+1;
        fichero1.writeInt(var.get_codigo());
        fichero1.writeInt(var.get_identificador_profesor());
        fichero1.writeInt(var.get_num_asignaturas_profesor());
        //arrayinteger   
        writeString(var.get_NIF(), 32);
        writeString(var.get_nombre(), 32);
        writeString(var.get_telefono(), 32);
        writelistaint(var.get_lista_asignaturas_profesor2());

    }

    private void writeString(String str, int dim) throws IOException {
        StringBuilder buffer = new StringBuilder();
        if (str != null) {
            buffer.append(str);
        }
        buffer.setLength(dim);
        fichero1.writeChars(buffer.toString());
    }

    private void writelistaint(int[] lista_asignaturas_profesor2) throws IOException {
        for (int i = 0; i < 10; i++) {
            fichero1.writeInt(lista_asignaturas_profesor2[i]);
        }
    }

    private int[] readlistaint() throws IOException {
        int[] lista_asignaturas_profesor2 = new int[10];
        for (int i = 0; i < 10; i++) {
            lista_asignaturas_profesor2[i] = fichero1.readInt();
        }
        return lista_asignaturas_profesor2;
    }

    public void close() throws Exception {
        fichero1.close();
    }

}
