/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.simbolo;

/**
 * clases para representar variables o símbolos, asociando un tipo, un
 * identificador, y un valor (definido como Object para permitir almacenar
 * cualquier tipo de dato).
 *
 * @author gio
 */
public class Simbolo {

    private Tipo tipo;    // Representa el tipo del símbolo (ENTERO, CADENA, etc.)
    private String id;    // Identificador único del símbolo (nombre de la variable).
    private Object valor; // Valor asociado al símbolo, que puede ser de cualquier tipo.
    private boolean constante; // si la variable es constante o no
    private boolean esLista;   // si la variable es una lista
    private boolean esVector;  // si la variable es un VECTOR

    // ----- CONSTRUCTOR -----
    // para las variables es posible que no venga valor (sin valor)
    public Simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
        this.valor = null;
        this.constante = false;
        this.esLista = false;
        this.esVector = false;
    }

    // (con valor)
    public Simbolo(Tipo tipo, String id, Object valor) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.constante = false;
        this.esLista = false;
        this.esVector = false;

    }

    // getters y setters
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public boolean isConstante() {
        return constante;
    }

    public void setConstante(boolean constante) {
        this.constante = constante;
    }

    public boolean isEsLista() {
        return esLista;
    }

    public void setEsLista(boolean esLista) {
        this.esLista = esLista;
    }

    public boolean isEsVector() {
        return esVector;
    }

    public void setEsVector(boolean esVector) {
        this.esVector = esVector;
    }

}
