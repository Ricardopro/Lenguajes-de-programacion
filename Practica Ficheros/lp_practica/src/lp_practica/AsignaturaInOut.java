/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.io.*;

/**
 *
 * @author Ricardo Bibiloni
 */
public class AsignaturaInOut {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    RandomAccessFile fichero1;
    Asignatura var = new Asignatura();

    public AsignaturaInOut(String list_asignatura) throws Exception {
        fichero1 = new RandomAccessFile(list_asignatura, "rw");
    }

    public Asignatura read() throws IOException, java.text.ParseException {
        try {
            var.set_codigo(fichero1.readInt());
            var.set_identificador_asignatura(fichero1.readInt());
            var.set_max_cap(fichero1.readInt());
            var.set_codigo_profesor(fichero1.readInt());
            var.set_codigo_aula(fichero1.readInt());
            var.set_nombre(readString(32));
            var.set_desarrollo(var.cambiardesarrollointboolean(fichero1.readInt()));

            var.set_tipo_aula(var.cambiarinttipo(fichero1.readInt()));

            var.set_fecha_comienzo(readString(32));
            var.set_fecha_Finalizacion(readString(32));

            var.set_horaComienzo(fichero1.readInt());
            var.set_horaFinal(fichero1.readInt());
            var.set_horaComienzo_pref(fichero1.readInt());
            var.set_horasXdia(fichero1.readInt());

            var.set_dias_por_semana(fichero1.readInt());
            var.set_dias2(readlistaint());
            var.set_num_estudios_asignatura(fichero1.readInt());

            return var;
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el aula: el aula no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Asignatura read(int numero) throws IOException, java.text.ParseException {
        try {
            fichero1.seek(((numero - 1) * Asignatura.DIM));
            var.set_codigo(fichero1.readInt());
            var.set_identificador_asignatura(fichero1.readInt());
            var.set_max_cap(fichero1.readInt());
            var.set_codigo_profesor(fichero1.readInt());
            var.set_codigo_aula(fichero1.readInt());
            var.set_nombre(readString(32));
            var.set_desarrollo(var.cambiardesarrollointboolean(fichero1.readInt()));
            var.set_tipo_aula(var.cambiarinttipo(fichero1.readInt()));
            var.set_fecha_comienzo(readString(32));
            var.set_fecha_Finalizacion(readString(32));

            var.set_horaComienzo(fichero1.readInt());
            var.set_horaFinal(fichero1.readInt());
            var.set_horaComienzo_pref(fichero1.readInt());
            var.set_horasXdia(fichero1.readInt());
            var.set_dias_por_semana(fichero1.readInt());
            var.set_dias2(readlistaint());
            var.set_num_estudios_asignatura(fichero1.readInt());

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

    public void write(Asignatura var) throws Exception {
        fichero1.seek(fichero1.length());
        var.set_codigo((int) (fichero1.length() / Asignatura.DIM) + 1);
        fichero1.writeInt(var.get_codigo());
        fichero1.writeInt(var.get_identificador_asignatura());
        fichero1.writeInt(var.get_max_cap());
        fichero1.writeInt(var.get_codigo_profesor());
        fichero1.writeInt(var.get_codigo_aula());
        writeString(var.get_nombre(), 32);
        fichero1.writeInt(var.cambiardesarrollobooleanint(var.get_desarrollo()));
        fichero1.writeInt(var.cambiartipoint(var.get_tipo_aula()));
        writeString(var.get_fecha_comienzo(), 32);
        writeString(var.get_fecha_Finalizacion(), 32);
        fichero1.writeInt(var.get_hora_comienzo());
        fichero1.writeInt(var.get_horaFinal());
        fichero1.writeInt(var.get_horaComienzo_pref());
        fichero1.writeInt(var.get_horasXdia());
        fichero1.writeInt(var.get_dias_por_semana());
        writelistaint(var.get_dias2());
        fichero1.writeInt(var.get_num_estudios_asignatura());
    }

    public void write(Asignatura var, int numero) throws Exception {
        fichero1.seek((numero - 1) * Asignatura.DIM);
        fichero1.writeInt(var.get_codigo());
        fichero1.writeInt(var.get_identificador_asignatura());
        fichero1.writeInt(var.get_max_cap());
        fichero1.writeInt(var.get_codigo_profesor());
        fichero1.writeInt(var.get_codigo_aula());
        writeString(var.get_nombre(), 32);
        fichero1.writeInt(var.cambiardesarrollobooleanint(var.get_desarrollo()));
        fichero1.writeInt(var.cambiartipoint(var.get_tipo_aula()));
        writeString(var.get_fecha_comienzo(), 32);
        writeString(var.get_fecha_Finalizacion(), 32);
        fichero1.writeInt(var.get_dias_por_semana());
        fichero1.writeInt(var.get_hora_comienzo());
        fichero1.writeInt(var.get_horaFinal());
        fichero1.writeInt(var.get_horaComienzo_pref());
        fichero1.writeInt(var.get_horasXdia());
        writelistaint(var.get_dias2());
        fichero1.writeInt(var.get_num_estudios_asignatura());
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

    private void writelistaint(int[] dias2) throws IOException {
        for (int i = 0; i < 5; i++) {
            fichero1.writeInt(dias2[i]);
        }
    }

    private int[] readlistaint() throws IOException {
        int[] dias2 = new int[5];
        for (int i = 0; i < 5; i++) {
            dias2[i] = fichero1.readInt();
        }
        return dias2;
    }

}
