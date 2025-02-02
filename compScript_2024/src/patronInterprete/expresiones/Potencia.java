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
 * E -> E ^ E
 *
 * @author gio
 */
public class Potencia extends Instruccion {

    private Instruccion opIzq; // base
    private Instruccion opDer; // exponenete

    public Potencia(Instruccion opIzq, Instruccion opDer, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.opIzq = opIzq;
        this.opDer = opDer;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para la pontencia: 

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

        // validación de la tabla para la POTENCIA:
        switch (tipoIzq) {
            case ENTERO -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.ENTERO);
                        int base = (int) valorIzq;
                        int exp = (int) valorDer;
                        int resultado = (int) Math.pow(base, exp);
                        return resultado;
                    }
                    case DECIMAL -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        int base = (int) valorIzq;
                        double exp = (double) valorDer;
                        double resultado = Math.pow(base, exp);
                        return resultado;
                    }
                    default -> {
                        return new Errores("Semantico", "Potencia entre tipos invalidos :" + tipoIzq + " * " + tipoDer, this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        double base = (double) valorIzq;
                        int exp = (int) valorDer;
                        double resultado = Math.pow(base, exp);
                        return resultado;
                    }
                    case DECIMAL -> {
                        // representa el tipo de dato resultante de la instrucción
                        this.tipo.setTipo(tipoDato.DECIMAL);

                        double base = (double) valorIzq;
                        double exp = (double) valorDer;
                        double resultado = Math.pow(base, exp);
                        return resultado;
                    }
                    default -> {
                        return new Errores("Semantico", "Potencia entre tipos invalidos :" + tipoIzq + " * " + tipoDer, this.linea, this.columna);
                    }
                }
            }

            default -> {
                return new Errores("SEMANTICO", "Potencia entre tipos invalida", this.linea, this.columna);
            }
        }

    }

}
