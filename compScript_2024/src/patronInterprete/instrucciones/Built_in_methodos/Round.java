/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Built_in_methodos;

import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *
 * round ( <EXPRESION> )
 *
 * _round ::= ROUND ( <expresion> )
 *
 * < expresion > puede ser un ID o un valor (double para ambos casos) returna un
 * int
 *
 * @author gio
 */
public class Round extends Instruccion {

    private String identificador; // Para manejar identificadores (ID)
    private Instruccion valorE;    // expresion (2+2 | 3.4 ) numericos

    // Constructor para expresiones
    public Round(Instruccion valorE, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.valorE = valorE;
        this.identificador = null;
    }

    // Constructor para identificadores
    public Round(String identificador, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.identificador = identificador;
        this.valorE = null;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        // logica para redondear
        Object valor;
        tipoDato tipoValor;

        // Caso 1: Identificador
        if (this.identificador != null) {
            // Buscar la variable en la tabla de símbolos
            var variable = tabla.getVariable(this.identificador);

            if (variable == null) {
                return new Errores("Semántico", "La variable '" + this.identificador + "' no existe", this.linea, this.columna);
            }

            /*// 2) Validar si la variable es una lista o vector
            if (variable.isEsLista()) {
                return new Errores("Semántico", "No se puede REDONDEAR una Lista: '" + this.identificador + "'", this.linea, this.columna);
            }
            if (variable.isEsVector()) {
                return new Errores("Semántico", "No se puede REDONDEAR una Vector: '" + this.identificador + "'", this.linea, this.columna);
            }*/
            // Obtener el valor y tipo de la variable
            valor = variable.getValor();
            tipoValor = variable.getTipo().getTipo();

            if (valor == null) {
                return new Errores("Semántico", "La variable '" + this.identificador + "' no tiene un valor asignado", this.linea, this.columna);
            }
        } // Caso 2: Expresión
        else {
            valor = this.valorE.interpretar(arbol, tabla);
            if (valor instanceof Errores) {
                return valor;
            }
            tipoValor = valorE.tipo.getTipo();
        }

        // Realizar el casteo
        switch (tipoValor) { // valor que se quiere redondear
            case ENTERO -> {
                this.tipo.setTipo(tipoDato.ENTERO); // actuliazo el tipo a entero
                return (int) valor;

            }
            case DECIMAL -> {
                this.tipo.setTipo(tipoDato.ENTERO); // actuliazo el tipo a entero
                // logica para redondear
                double decimalValor = (double) valor;
                int redondeado = (int) Math.round(decimalValor); // Usar Math.round para redondear correctamente
                return redondeado;

            }
            default -> {
                return new Errores("Semántico", "El metodo REDONDEO no es aplicable para el tipo '" + tipoValor + "'", this.linea, this.columna);
            }

        }

    }

}
