/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp_practica;

import java.util.ArrayList;
import java.util.ListIterator;
import lp_practica.Estudio.Dia;

/**
 *
 * @author Ricardo Bibiloni
 */
public class Asignatura {

    public final static int DIM = 264; //132
    private static int numero_asignatura = 0;

    private int codigo = 0;
    private int identificador_asignatura = 1;
    private int max_cap;
    private int codigo_profesor=0;
    private int codigo_aula=0;
    private String nombre;
    private Boolean en_desarrollo; // si NO esta en en_desarrollo, se puede dar de baja
    private Aula.tipo_aula tipo_aula;
    private String fechaComienzo;
    private String fechaFinalizacion;

    private int horaComienzo;
    private int horaFinal;
    private int horaComienzo_pref; //rango horario de las hroas par, solo lo usas para asignar la asignatura.
    private int horasXdia; // Calendario

    private int dias_por_semana; //contador de los dias usados
    //eliminar dias
    private ArrayList<Dia> dias = new ArrayList<>(5);
    private int[] dias2 = new int[5];

    private int num_estudios_asignatura; // numero de Estudios en los que esta la Asignatura

    public Asignatura() {

    }

    public Asignatura(String n, Aula.tipo_aula tip, int mc, String fc, String ff, int p, int a, int hc, int hf, int phc, int hxd, ArrayList<Dia> diasxsemana) throws Exception {
        //identificador_asignatura = conseguir_numero_asignatura();
        this.identificador_asignatura = 1;
        this.nombre = n;
        this.tipo_aula = tip;
        this.max_cap = mc;
        this.fechaComienzo = fc;
        this.fechaFinalizacion = ff;

        codigo_profesor = p;
        codigo_aula = a;

        /*
         Una vez asignadas AULA y profesor -> marcarlas como asignadas como asignadas
         - Marcar Profesor: llamando al método asignar_asignatura_profesor de la clase Profesor
         - Marcar Aula: actualizando la variable booleana estaLibre de la clase Aula a FALSE. 
         */
        Main1.c.consulta_profesor_centro(codigo_profesor).asignar_asignatura_profesor(identificador_asignatura);

        Main1.c.consulta_profesor_centro(codigo_profesor).asignar_asignatura_profesor(identificador_asignatura);
        Main1.c.consulta_aula_centro(codigo_aula).set_estaLibre(false);

        horaComienzo = hc;
        horaFinal = hf;
        horaComienzo_pref = phc;
        horasXdia = hxd;
        dias = new ArrayList<>();
        dias.addAll(diasxsemana);
        dias2 = set_array_dias();

        dias_por_semana = dias.size();

        en_desarrollo = false;
        num_estudios_asignatura = 0;
    }

    public void set_num_estudios_asignatura(int n) {
        this.num_estudios_asignatura = n;
    }

    public int get_num_estudios_asignatura() {
        return this.num_estudios_asignatura;
    }

    public int get_codigo_profesor() {
        return this.codigo_profesor;
    }

    public Aula.tipo_aula get_tipo_aula() {
        return this.tipo_aula;
    }

    public int get_total_diasxsemana() {
        return this.dias_por_semana;
    }

    public void asignar_asignatura_centro() {
    }

    public void añadir_dia(Dia d) {
        dias.add(d);
    }

    public void añadir_dia2(Dia d) {
        if (d == d.LUNES) {
            dias2[1] = 1;
        }
        if (d == d.MARTES) {
            dias2[2] = 1;
        }
        if (d == d.MIERCOLES) {
            dias2[3] = 1;
        }
        if (d == d.JUEVES) {
            dias2[4] = 1;
        }
        if (d == d.VIERNES) {
            dias2[5] = 1;
        }

    }

    public void eliminar_dia(Dia d) {
        ListIterator<Dia> iterador = dias.listIterator();
        boolean encontrado = false;
        while (iterador.hasNext() && !encontrado) {
            if (iterador.next() == d) {
                dias.remove(iterador.previousIndex());
                encontrado = true;
            }
        }
        if (encontrado) {
            System.out.println("El dia se elimino de la asignatura.");
        } else {
            System.out.println("La asignatura no tenia asignado este dia, no se eliminó.");
        }
    }

    public void eliminar_dia2(Dia d) {
        if (d == d.LUNES) {
            dias2[1] = 0;
        }
        if (d == d.MARTES) {
            dias2[2] = 0;
        }
        if (d == d.MIERCOLES) {
            dias2[3] = 0;
        }
        if (d == d.JUEVES) {
            dias2[4] = 0;
        }
        if (d == d.VIERNES) {
            dias2[5] = 0;
        }

    }

    public int conseguir_numero_asignatura() {
        numero_asignatura++;
        return numero_asignatura;
    }

    public void set_desarrollo(Boolean desarrollo) {
        this.en_desarrollo = desarrollo;
    }

    public Boolean get_desarrollo() {
        return this.en_desarrollo;
    }

    public int get_identificador_asignatura() {

        return this.identificador_asignatura;
    }

    public void set_identificador_asignatura(int identificador_asignatura) {
        this.identificador_asignatura = identificador_asignatura;
    }

    public String get_fecha_comienzo() {
        return fechaComienzo;
    }

    public void set_fecha_comienzo(String fc) {
        this.fechaComienzo = fc;
    }

    public void set_fecha_final(String ff) {
        this.fechaFinalizacion = ff;
    }

    public String get_fecha_final() {
        return fechaFinalizacion;
    }

    public String get_fecha_Finalizacion() {
        return fechaFinalizacion;
    }

    public void set_fecha_Finalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;

    }

    public String get_nombre() {
        return this.nombre;
    }

    public void set_nombre(String nombre) {
        this.nombre = nombre;
    }
    //crear asignatura, comprobar horario se solapa

    public void set_codigo(int codigo) {
        this.codigo = codigo;
    }

    public int get_codigo() {
        return this.codigo;
    }

    public void set_max_cap(int max_cap) {
        this.max_cap = max_cap;
    }

    public int get_max_cap() {
        return this.max_cap;
    }

    public void set_codigo_profesor(int codigo_profesor) {
        this.codigo_profesor = codigo_profesor;
    }

    public void set_codigo_aula(int codigo_aula) {
        this.codigo_aula = codigo_aula;
    }

    public int get_codigo_aula() {
        return this.codigo_aula;
    }

    public ArrayList<Dia> get_array_dias() {
        ArrayList<Dia> diasx = new ArrayList<>(5);
        for (int i = 0; i < dias2.length; i++) {

            diasx.add(i, cambiarintdia(dias2[i]));

        }

        return diasx;
    }

    public int[] set_array_dias() {
        int[] diasx = new int[5];

        for (int i = 0; i < dias.size(); i++) {

            diasx[i] = cambiardiaint(dias.get(i));

        }

        return diasx;
    }

    public int get_hora_comienzo_pref() {
        return this.horaComienzo_pref;
    }

    public int get_horaComienzo_pref() {
        return this.horaComienzo_pref;
    }

    public void set_horaComienzo_pref(int horaComienzo_pref) {
        this.horaComienzo_pref = horaComienzo_pref;
    }

    public int get_hora_comienzo() {
        return this.horaComienzo;
    }

    public int get_horaComienzo() {
        return this.horaComienzo;
    }

    public void set_horaComienzo(int horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public int get_hora_final() {
        return this.horaFinal;
    }

    public int get_horaFinal() {
        return this.horaFinal;
    }

    public void set_horaFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int get_horas_dia() {
        return this.horasXdia;
    }

    public int get_horasXdia() {
        return this.horasXdia;
    }

    public void set_horasXdia(int horasXdia) {
        this.horasXdia = horasXdia;
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

    public Aula.tipo_aula cambiarinttipo(int tipo) {
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

    public int cambiartipoint(Aula.tipo_aula tipo) {
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

    public void set_dias2(int[] dias2) {

        this.dias2 = dias2;
    }

    public int[] get_dias2() {

        return this.dias2;
    }

    public void set_dias_por_semana(int dias_por_semana) {
        this.dias_por_semana = dias_por_semana;
    }

    public int get_dias_por_semana() {
        return this.dias_por_semana;
    }

    public Dia cambiarintdia(int tipo) {
        if (1 == tipo) {
            return Dia.LUNES;
        }
        if (2 == tipo) {
            return Dia.MARTES;
        }
        if (3 == tipo) {
            return Dia.MIERCOLES;
        }
        if (4 == tipo) {
            return Dia.JUEVES;
        }
        if (5 == tipo) {
            return Dia.VIERNES;
        }

        return null;
    }

    public int cambiardiaint(Dia tipo) {
        if (Dia.LUNES == tipo) {
            return 1;
        }
        if (Dia.MARTES == tipo) {
            return 2;
        }
        if (Dia.MIERCOLES == tipo) {
            return 3;
        }
        if (Dia.JUEVES == tipo) {
            return 4;
        }
        if (Dia.VIERNES == tipo) {
            return 5;
        }

        return 0;
    }

    public void set_tipo_aula(Aula.tipo_aula tipo) {
        this.tipo_aula = tipo;
    }

}
