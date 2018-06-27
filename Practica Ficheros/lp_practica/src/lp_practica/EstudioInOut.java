package lp_practica;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Ricardo Bibiloni
 */
public class EstudioInOut {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    RandomAccessFile fichero1;
    Estudio var = new Estudio();

    public EstudioInOut(String lista_estudi) throws Exception {
        fichero1 = new RandomAccessFile(lista_estudi, "rw");
    }

    public Estudio read() throws IOException, java.text.ParseException, Exception {
        try {
            var.set_codigo(fichero1.readInt());
            var.set_identificador_estudio(fichero1.readInt());
            var.set_nombre(readString(32));
            var.set_cursoAcademico(readString(32));
            var.set_desarrollo(var.cambiardesarrollointboolean(fichero1.readInt()));

            //writelistaint(var.get_lista_asignaturas_estudio());
//        writelistaint2(var.get_lista_de_asignaturas());
            var.set_tipo_estudio(var.cambiarinttipo(fichero1.readInt()));
            var.set_fecha_comienzo(readString(32));
            var.set_fecha_final(readString(32));
            

            var.set_num_asignaturas_estudio(fichero1.readInt());
            //var.set_lista_asignaturas(readlistaint2());
            var.set_lista_asignaturas_estudio(readlistaint());

            var.set_turno(var.cambiarintturno(fichero1.readInt()));

            return var;
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el aula: el aula no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Estudio read(int numero) throws IOException, java.text.ParseException, Exception {
        try {
            fichero1.seek(((numero - 1) * Estudio.DIM));
            var.set_codigo(fichero1.readInt());
            var.set_identificador_estudio(fichero1.readInt());

            var.set_nombre(readString(32));
            var.set_cursoAcademico(readString(32));
            var.set_desarrollo(var.cambiardesarrollointboolean(fichero1.readInt()));
            var.set_tipo_estudio(var.cambiarinttipo(fichero1.readInt()));
        //writelistaint(var.get_lista_asignaturas_estudio());
            //writelistaint2(var.get_lista_de_asignaturas());
            var.set_fecha_comienzo(readString(32));
            var.set_fecha_final(readString(32));
            var.set_num_asignaturas_estudio(fichero1.readInt());
            //var.set_lista_asignaturas(readlistaint2());
            var.set_lista_asignaturas_estudio(readlistaint());
            var.set_turno(var.cambiarintturno(fichero1.readInt()));

            return var;
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el aula: el aula no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private String readString(int dim) throws IOException {
        char campo[] = new char[dim];
        for (int i = 0; i < dim; i++) {
            campo[i] = fichero1.readChar();
        }
        return new String(campo).replace('\0', ' ');
    }

    public void write(Estudio var) throws Exception {
        fichero1.seek(fichero1.length());
        var.set_codigo((int) (fichero1.length() / Estudio.DIM) + 1);
        
        
        fichero1.writeInt(var.get_codigo());
        fichero1.writeInt(var.get_identificador_estudio());
        writeString(var.get_nombre(), 32);
        writeString(var.get_cursoAcademico(), 32);
        
        fichero1.writeInt(var.cambiardesarrollobooleanint(var.get_desarrollo()));
        fichero1.writeInt(var.cambiartipoint(var.get_tipo_estudio()));
        writeString(var.get_fecha_comienzo(), 32);
        writeString(var.get_fecha_final(), 32);
        fichero1.writeInt(var.get_num_asignaturas_estudio());
        //writelistaint2(var.get_lista_de_asignaturas());
        writelistaint(var.get_lista_asignaturas_estudio());
        fichero1.writeInt(var.cambiarturnoint(var.get_turno()));
    }

    public void write(Estudio var, int numero) throws Exception {
        fichero1.seek((numero - 1) * Estudio.DIM);
        fichero1.writeInt(var.get_codigo());
        fichero1.writeInt(var.get_identificador_estudio());
        writeString(var.get_nombre(), 32);
        writeString(var.get_cursoAcademico(), 32);
        fichero1.writeInt(var.cambiardesarrollobooleanint(var.get_desarrollo()));
        fichero1.writeInt(var.cambiartipoint(var.get_tipo_estudio()));
        writeString(var.get_fecha_comienzo(), 32);
        writeString(var.get_fecha_final(), 32);
        fichero1.writeInt(var.get_num_asignaturas_estudio());
        // writelistaint2(var.get_lista_de_asignaturas());
        writelistaint(var.get_lista_asignaturas_estudio());
        fichero1.writeInt(var.cambiarturnoint(var.get_turno()));

    }

    private void writeString(String str, int dim) throws IOException {
        StringBuilder buffer = new StringBuilder();
        if (str != null) {
            buffer.append(str);
        }
        buffer.setLength(dim);
        fichero1.writeChars(buffer.toString());
    }

    public void close() throws Exception {
        fichero1.close();
    }

    private void writelistaint(int[] lista_asignaturas_estudio) throws IOException {
        for (int i = 0; i < 50; i++) {
            fichero1.writeInt(lista_asignaturas_estudio[i]);
        }
    }

    private void writelistaint2(ArrayList<Integer> lista_asignatura) throws IOException {
        for (int i = 0; i < lista_asignatura.size(); i++) {
            fichero1.writeInt(lista_asignatura.get(i));
        }
    }

    private int[] readlistaint() throws IOException {
        int[] lista_asignaturas_estudio = new int[50];
        for (int i = 0; i < 50; i++) {
            lista_asignaturas_estudio[i] = fichero1.readInt();
        }
        return lista_asignaturas_estudio;
    }

    private ArrayList<Integer> readlistaint2() throws IOException {
        ArrayList<Integer> lista_asignaturas_estudio = new ArrayList<>(50);
        for (int i = 0; i < lista_asignaturas_estudio.size(); i++) {
            lista_asignaturas_estudio.set(i, fichero1.readInt());
        }
        return lista_asignaturas_estudio;
    }

}
