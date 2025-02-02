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
 * E -> E + E
 *
 * @author gio
 */
public class Suma extends Instruccion {

    // atributos
    private Instruccion opIzq;
    private Instruccion opDer;

    public Suma(Instruccion opIzq, Instruccion opDer, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.opIzq = opIzq;
        this.opDer = opDer;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valorIzq = this.opIzq.interpretar(arbol, tabla);
        if (valorIzq instanceof Errores) {
            return valorIzq;
        }

        var valorDer = this.opDer.interpretar(arbol, tabla);
        if (valorDer instanceof Errores) {
            return valorDer;
        }

        var tipoIzq = opIzq.tipo.getTipo();
        var tipoDer = opDer.tipo.getTipo();

        switch (tipoIzq) {
            case ENTERO -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) valorIzq + (int) valorDer;
                    }
                    case DECIMAL -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) valorIzq + (double) valorDer;
                    }
                    case BOOLEANO -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.ENTERO);
                        // Convertir el valor booleano a 1 (true) o 0 (false)
                        int valorBooleano = (boolean) valorDer ? 1 : 0;
                        return (int) valorIzq + valorBooleano;
                    }
                    case CARACTER -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.ENTERO);
                        // Convertir el carácter a su valor ASCII
                        int valorCaracter = ((String) valorDer).charAt(0);
                        // Realizar la suma usando el valor ASCII del carácter
                        return (int) valorIzq + valorCaracter;
                    }
                    case CADENA -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.CADENA);
                        // Convertir valorIzq a cadena y concatenar con valorDer
                        return String.valueOf(valorIzq) + valorDer;
                    }
                    default -> {
                        return new Errores("Semantico", "SUMA ENTRE TIPOS INVALIDA", this.linea, this.columna);
                    }
                }
            }

            case DECIMAL -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq + (int) valorDer;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) valorIzq + (double) valorDer;
                    }
                    case BOOLEANO -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Convertir el valor booleano a 1 (true) o 0 (false)
                        int valorBooleano = (boolean) valorDer ? 1 : 0;
                        return (double) valorIzq + valorBooleano;
                    }
                    case CARACTER -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Convertir el carácter a su valor ASCII
                        int valorCaracter = ((String) valorDer).charAt(0);
                        // Realizar la suma usando el valor ASCII del carácter
                        return (double) valorIzq + valorCaracter;
                    }
                    case CADENA -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.CADENA);
                        // Convertir valorIzq a cadena y concatenar con valorDer
                        return String.valueOf(valorIzq) + valorDer;
                    }
                    default -> {
                        return new Errores("Semantico: ", "SUMA ENTRE TIPOS INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        // Convertir el valor booleano a 1 (true) o 0 (false)
                        int valorBooleano = (boolean) valorIzq ? 1 : 0;
                        return valorBooleano + (int) valorDer;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Convertir el valor booleano a 1 (true) o 0 (false)
                        int valorBooleano = (boolean) valorIzq ? 1 : 0;
                        return (double) valorBooleano + (double) valorDer;
                    }
                    case BOOLEANO -> {
                        // ERROR
                        return new Errores("Semántico", "Booleano + Booleano no esta definido", this.linea, this.columna);

                    }
                    case CARACTER -> {
                        // error:
                        return new Errores("Semántico", "Booleano + char no esta definido", this.linea, this.columna);

                    }
                    case CADENA -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.CADENA);
                        // Convertir valorIzq a cadena y concatenar con valorDer
                        return String.valueOf(valorIzq) + valorDer;
                    }
                    default -> {
                        return new Errores("Semantico: ", "SUMA ENTRE TIPOS INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        // Convertir el carácter a su valor ASCII
                        int valorCaracter = ((String) valorIzq).charAt(0);
                        return valorCaracter + (int) valorDer;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        // Convertir el carácter a su valor ASCII
                        int valorCaracter = ((String) valorIzq).charAt(0);
                        return valorCaracter + (double) valorDer;
                    }
                    case BOOLEANO -> {
                        // ERROR
                        return new Errores("Semántico", "Char + Booleano no esta definido", this.linea, this.columna);

                    }
                    case CARACTER -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.CADENA);
                        // Convertir valorIzq a cadena y concatenar con valorDer
                        return String.valueOf(valorIzq) + String.valueOf(valorDer);
                    }
                    case CADENA -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.CADENA);
                        // Convertir valorIzq a cadena y concatenar con valorDer
                        return String.valueOf(valorIzq) + String.valueOf(valorDer);
                    }
                    default -> {
                        return new Errores("Semantico: ", "SUMA ENTRE TIPOS INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case CADENA -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(valorIzq) + String.valueOf(valorDer);
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(valorIzq) + String.valueOf(valorDer);
                    }
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(valorIzq) + String.valueOf(valorDer);
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(valorIzq) + String.valueOf(valorDer);
                    }
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(valorIzq) + valorDer;
                    }
                    default -> {
                        return new Errores("Semantico: ", "SUMA ENTRE TIPOS INVALIDA", this.linea, this.columna);
                    }
                }
            }

            default -> {
                return new Errores("SEMANTICO", "SUMA ENTRE TIPOS INVALIDA",
                        this.linea, this.columna);
            }
        }

    }

}
