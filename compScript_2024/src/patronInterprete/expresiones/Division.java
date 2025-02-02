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
 * E -> E / E
 *
 * @author gio
 */
public class Division extends Instruccion {

    // valores de las expresiones:
    private Instruccion opIzq;
    private Instruccion opDer;

    public Division(Instruccion opIzq, Instruccion opDer, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.opIzq = opIzq;
        this.opDer = opDer;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica de la division
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

        // validación de la tabla para la division:
        switch (tipoIzq) {
            case ENTERO -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // Validar que el divisor no sea 0
                        if ((int) valorDer == 0) {
                            return new Errores("Semántico", "División entre 0 no permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Convertir los operandos a double para realizar la división
                        return (double) (int) valorIzq / (double) (int) valorDer;
                    }
                    case DECIMAL -> {
                        // Validar que el divisor no sea 0
                        if ((double) valorDer == 0.0) {
                            return new Errores("Semántico", "División entre 0 no permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) (int) valorIzq / (double) valorDer;
                    }
                    case CARACTER -> {
                        // Convertir el carácter a su valor ASCII
                        int valorCaracter = ((String) valorDer).charAt(0);

                        if (valorCaracter == 0) {
                            return new Errores("Semántico", "División entre 0 no permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Realizar la suma usando el valor ASCII del carácter
                        return (double) (int) valorIzq / (double) valorCaracter;
                    }
                    default -> {
                        return new Errores("Semantico", "Division entre tipos invalidos :" + tipoIzq + " / " + tipoDer, this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // Validar que el divisor no sea 0
                        if ((int) valorDer == 0) {
                            return new Errores("Semántico", "División entre 0 no permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Convertir los operandos a double para realizar la división
                        return (double) valorIzq / (double) (int) valorDer;
                    }
                    case DECIMAL -> {
                        // Validar que el divisor no sea 0
                        if ((double) valorDer == 0.0) {
                            return new Errores("Semántico", "División entre 0 no permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq / (double) valorDer;
                    }
                    case CARACTER -> {
                        // Convertir el carácter a su valor ASCII
                        int valorCaracter = ((String) valorDer).charAt(0);

                        if (valorCaracter == 0) {
                            return new Errores("Semántico", "División entre 0 no permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Realizar la suma usando el valor ASCII del carácter
                        return (double) valorIzq / (double) valorCaracter;
                    }
                    default -> {
                        return new Errores("Semantico", "Division entre tipos invalidos :" + tipoIzq + " / " + tipoDer, this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // Validar que el divisor no sea 0
                        if ((int) valorDer == 0) {
                            return new Errores("Semántico", "División entre 0 no permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        int valorCaracter = ((String) valorIzq).charAt(0);
                        // Convertir los operandos a double para realizar la división
                        return (double) valorCaracter / (double) (int) valorDer;
                    }
                    case DECIMAL -> {
                        // Validar que el divisor no sea 0
                        if ((double) valorDer == 0.0) {
                            return new Errores("Semántico", "División entre 0 no permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        int valorCaracter = ((String) valorIzq).charAt(0);
                        // Convertir los operandos a double para realizar la división
                        return (double) valorCaracter / (double) valorDer;
                    }
                    case CARACTER -> {
                        return new Errores("Semántico", "División entre CHAR/CHAR no permitida", this.linea, this.columna);
                    }
                    default -> {
                        return new Errores("Semantico", "Division entre tipos invalidos :" + tipoIzq + " / " + tipoDer, this.linea, this.columna);
                    }
                }
            }

            default -> {
                return new Errores("SEMANTICO", "Divison entre tipos invalida", this.linea, this.columna);
            }
        }

    }

}
