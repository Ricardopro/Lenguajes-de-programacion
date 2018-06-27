package lp_practica;

/**
 *
 * @author Ricardo Bibiloni
 */
public enum Turno {

    MAÑANA(8, 15),
    TARDE(15, 22),
    COMPLETO(8, 22);

    //Campos tipo constante   
    private final double inicio;
    private final double fin;

    /**
     *
     * Constructor. Al asignarle uno de los valores posibles a una variable del
     * tipo enumerado el constructor asigna * automáticamente valores de los
     * campos
     *
     */
    Turno(int i, int f) {
        this.inicio = i;
        this.fin = f;

    } //Cierre del constructor

    //Métodos de la clase tipo Enum
    public double getInicio() {
        return inicio;
    }

    public double getFin() {
        return fin;
    }

} //Cierre del enum

