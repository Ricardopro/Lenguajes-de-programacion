package lp_practica;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Bibiloni
 */
public class AulaInOut {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    RandomAccessFile fichero1;
    Aula var = new Aula();

    public AulaInOut(String list_aula) throws Exception {
        fichero1 = new RandomAccessFile(list_aula, "rw");
    }

    public Aula read() throws IOException, java.text.ParseException {
        try {
            var.set_codigo(fichero1.readInt());
            var.set_identificador_aula(fichero1.readInt());
            var.set_capacidad(fichero1.readInt());
            var.set_estaLibre(var.cambiardesarrollointboolean(fichero1.readInt()));
            var.set_tipo_aula(var.cambiarinttipo(fichero1.readInt()));
            return var;
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No se pudo dar de baja el aula: el aula no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Aula read(int numero) {
        try {
            fichero1.seek(((numero - 1) * Aula.DIM));
            var.set_codigo(fichero1.readInt());
            var.set_identificador_aula(fichero1.readInt());
            var.set_capacidad(fichero1.readInt());
            var.set_estaLibre(var.cambiardesarrollointboolean(fichero1.readInt()));
            var.set_tipo_aula(var.cambiarinttipo(fichero1.readInt()));
            return var;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "No se pudo dar de baja el aula: el aula no fue encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
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

    public void write(Aula var) throws Exception {
        fichero1.seek(fichero1.length());
        var.set_codigo((int) (fichero1.length() / Aula.DIM) + 1);
        fichero1.writeInt(var.get_codigo());
        fichero1.writeInt(var.get_identificador_aula());
        fichero1.writeInt(var.get_capacidad());
        fichero1.writeInt(var.cambiardesarrollobooleanint(var.get_estaLibre()));
        fichero1.writeInt(var.cambiartipoint(var.get_tipo_aula()));
    }

    public void write(Aula var, int numero) throws Exception {
        fichero1.seek((numero - 1) * Aula.DIM);
        fichero1.writeInt(var.get_codigo());
        fichero1.writeInt(var.get_identificador_aula());
        fichero1.writeInt(var.get_capacidad());
        fichero1.writeInt(var.cambiardesarrollobooleanint(var.get_estaLibre()));
        fichero1.writeInt(var.cambiartipoint(var.get_tipo_aula()));

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

}
