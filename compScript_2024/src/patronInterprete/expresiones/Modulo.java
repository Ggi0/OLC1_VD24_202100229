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
 * E -> E % E
 *  a % n (n diferente a 0)
 * @author gio
 */
public class Modulo extends Instruccion{
    // operadores: 
    private Instruccion opIzq; // a
    private Instruccion opDer; // n -> divisor

    public Modulo(Instruccion opIzq, Instruccion opDer, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.opIzq = opIzq;
        this.opDer = opDer;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
// logica para raiz
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
        
        // validación de la tabla para el MODULO:
        switch (tipoIzq) {
            case ENTERO -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // Validar que el divisor no sea 0
                        if ((int) valorDer == 0) {
                            return new Errores("Semántico", "El Modulo entre 0 no es permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Convertir los operandos a double para realizar la división
                        return (double) (int) valorIzq % (double) (int) valorDer;

                    }
                    case DECIMAL -> {
                        // Validar que el divisor no sea 0
                        if ((double) valorDer == 0.0) {
                            return new Errores("Semántico", "El Modulo entre 0 no es permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) (int) valorIzq % (double) valorDer;

                    }
                    default -> {
                        return new Errores("Semantico", "MODULO entre tipos invalidos :" + tipoIzq + " * " + tipoDer, this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // Validar que el divisor no sea 0
                        if ((int) valorDer == 0) {
                            return new Errores("Semántico", "El Modulo entre 0 no es permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Convertir los operandos a double para realizar la división
                        return (double) valorIzq % (double) (int) valorDer;

                    }
                    case DECIMAL -> {
                        if ((double) valorDer == 0.0) {
                            return new Errores("Semántico", "El Modulo entre 0 no es permitida", this.linea, this.columna);
                        }
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq % (double) valorDer;

                    }
                    default -> {
                        return new Errores("Semantico", "El modulo entre tipos invalidos :" + tipoIzq + " * " + tipoDer, this.linea, this.columna);
                    }
                }
            }

            default -> {
                return new Errores("SEMANTICO", "MODULO entre tipos invalida", this.linea, this.columna);
            }
        }

        
        
        
    }
    
    

    
}
