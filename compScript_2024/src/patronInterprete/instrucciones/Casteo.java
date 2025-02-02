/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones;

import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 * CAST (<ESPRESION> AS <TIPO>) 
 * CAST (ID AS <TIPO>)
 *
 * TIPO -> INT | DOUBLE | CHAR
 *
 * | CAST LPAREN _expresion:expr AS _tipos:t RPAREN 
 * | CAST LPAREN ID:id AS _tipos:t RPAREN
 *
 *
 * @author gio
 */
public class Casteo extends Instruccion {

    // Atributos
    private String identificador; // Para manejar identificadores
    private Instruccion valorE;    // expresion (2+2 | 3.4 | 'a')

    // id es un variable -> por lo tanto se debe buscar en la tabla
    // valor es una expresión por lo tanto se debe manejar ver cual es el valor preciso
    // 2 constructores uno en donde se maneje el ID y otro en donde se maneje si es una expresion
    // si es una variable:
    // parametros -> valor, TIPO, LINEA, COLUM
    
    // Constructor para expresiones
    public Casteo(Instruccion valorE, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.valorE = valorE;
        this.identificador = null;
    }

    // Constructor para identificadores
    public Casteo(String identificador, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.valorE = null;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valor;
        tipoDato tipoValor;

        // Caso 1: Identificador
        if (this.identificador != null) {
            // Buscar la variable en la tabla de símbolos
            var variable = tabla.getVariable(this.identificador);

            if (variable == null) {
                return new Errores("Semántico", "La variable '" + this.identificador + "' no existe", this.linea, this.columna);
            }

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
        var tipoCasteo = this.tipo.getTipo();
        switch (tipoValor) { // valor que se quiere castear
            case ENTERO -> {
                switch (tipoCasteo) { // valor al que se va castear -> cast(a as INT)
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) valor; // valor casteado de Entero a entero (no es error)
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) ((int) valor); // de entero a Decimal
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        return String.valueOf((char) ((int) valor)); // de entero a CHAR
                    }
                    default -> {
                        return new Errores("Semántico", "Casteo inválido desde ENTERO hacia " + tipoCasteo, this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> { // -> cast( 19.5 as INT)
                switch (tipoCasteo) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) ((double) valor);
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valor;
                    }
                    default -> {
                        return new Errores("Semántico", "Casteo inválido desde DECIMAL hacia " + tipoCasteo, this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipoCasteo) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) ((String) valor).charAt(0);
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) ((String) valor).charAt(0);
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CARACTER);
                        return ((String) valor).charAt(0);
                    }
                    default -> {
                        return new Errores("Semántico", "Casteo inválido desde CARACTER hacia " + tipoCasteo, this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("Semántico", "Casteo no soportado para el tipo '" + tipoValor + "'", this.linea, this.columna);
            }
        }
    }

}
