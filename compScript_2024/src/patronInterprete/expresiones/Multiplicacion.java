/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.expresiones;

import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 * E -> E * E
 *
 * @author gio
 */
public class Multiplicacion extends Instruccion {

    private Instruccion opIzq;
    private Instruccion opDer;

    public Multiplicacion(Instruccion opIzq, Instruccion opDer, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.opIzq = opIzq;
        this.opDer = opDer;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para la multiplicación:

        var valorIzq = this.opIzq.interpretar(arbol, tabla);
        if (valorIzq instanceof Errores) {
            return valorIzq;
        }

        var valorDer = this.opDer.interpretar(arbol, tabla);
        if (valorDer instanceof Errores) {
            return valorDer;
        }

        // obtener los tipos de los operadores:
        var tipoIzq = opIzq.tipo.getTipo();
        var tipoDer = opDer.tipo.getTipo();

        // validación de la tabla para la multiplicación:
        switch (tipoIzq) {
            case ENTERO -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) valorIzq * (int) valorDer;
                    }
                    case DECIMAL -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) valorIzq * (double) valorDer;
                    }
                    case CARACTER -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.ENTERO);
                        // Convertir el carácter a su valor ASCII
                        int valorCaracter = ((String) valorDer).charAt(0);
                        // Realizar la suma usando el valor ASCII del carácter
                        return (int) valorIzq * valorCaracter;
                    }
                    default -> {
                        return new Errores("Semantico", "Multiplicacion entre tipos invalidos :" + tipoIzq + " * " + tipoDer, this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq * (int) valorDer;
                    }
                    case DECIMAL -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq * (double) valorDer;
                    }
                    case CARACTER -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Convertir el carácter a su valor ASCII
                        int valorCaracter = ((String) valorDer).charAt(0);
                        // Realizar la suma usando el valor ASCII del carácter
                        return (double) valorIzq * valorCaracter;
                    }
                    default -> {
                        return new Errores("Semantico", "Multiplicacion entre tipos invalidos :" + tipoIzq + " * " + tipoDer, this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        int valorCaracter = ((String) valorIzq).charAt(0);
                        return valorCaracter * (int) valorDer;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        int valorCaracter = ((String) valorIzq).charAt(0);
                        return valorCaracter * (double) valorDer;
                    }
                    case CARACTER -> {
                        return new Errores("Semantico", "Multiplicacion entre CHAR * CHAR", this.linea, this.columna);
                    }
                    default -> {
                        return new Errores("Semantico", "Multiplicacion entre tipos invalidos :" + tipoIzq + " * " + tipoDer, this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Multiplicacion entre tipos invalida", this.linea, this.columna);
            }
        }

    }

}
