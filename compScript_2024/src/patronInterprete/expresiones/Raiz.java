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
 *
 * @author gio
 */
public class Raiz extends Instruccion {

    // operadores: 
    private Instruccion opIzq; // Radicando
    private Instruccion opDer; // indice

    // constructor
    public Raiz(Instruccion opIzq, Instruccion opDer, int linea, int columna) {
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

        // validación de la tabla para la POTENCIA:
        switch (tipoIzq) {
            case ENTERO -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        int radicando = (int) valorIzq;
                        int indice = (int) valorDer;

                        if (radicando < 0 && indice % 2 == 0) {
                            return new Errores("Semantico", "Raiz invalida por radicando e indice" + tipoDer, this.linea, this.columna);
                        }

                        this.tipo.setTipo(tipoDato.DECIMAL);
                        double resultado = Math.pow(Math.abs(radicando), 1.0 / indice);

                        // Si el radicando es negativo y el índice es impar, el resultado debe ser negativo 
                        if (radicando < 0) {
                            resultado = -resultado;
                        }
                        return resultado;
                    }
                    case DECIMAL -> {
                        int radicando = (int) valorIzq;
                        double indice = (double) valorDer;

                        if (radicando < 0 && indice % 2 == 0) {
                            return new Errores("Semantico", "Raiz invalida por radicando e indice" + tipoDer, this.linea, this.columna);
                        }

                        this.tipo.setTipo(tipoDato.DECIMAL);
                        double resultado = Math.pow(Math.abs(radicando), 1.0 / indice);

                        // Si el radicando es negativo y el índice es impar, el resultado debe ser negativo 
                        if (radicando < 0) {
                            resultado = -resultado;
                        }
                        return resultado;
                    }
                    default -> {
                        return new Errores("Semantico", "Raiz entre tipos invalidos :" + tipoIzq + " * " + tipoDer, this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipoDer) {
                    case ENTERO -> {
                        double radicando = (double) valorIzq;
                        int indice = (int) valorDer;

                        if (radicando < 0 && indice % 2 == 0) {
                            return new Errores("Semantico", "Raiz invalida por radicando e indice" + tipoDer, this.linea, this.columna);
                        }

                        this.tipo.setTipo(tipoDato.DECIMAL);
                        double resultado = Math.pow(Math.abs(radicando), 1.0 / indice);

                        // Si el radicando es negativo y el índice es impar, el resultado debe ser negativo 
                        if (radicando < 0) {
                            resultado = -resultado;
                        }
                        return resultado;
                    }
                    case DECIMAL -> {
                        double radicando = (double) valorIzq;
                        double indice = (double) valorDer;

                        if (radicando < 0 && indice % 2 == 0) {
                            return new Errores("Semantico", "Raiz invalida por radicando e indice" + tipoDer, this.linea, this.columna);
                        }

                        this.tipo.setTipo(tipoDato.DECIMAL);
                        double resultado = Math.pow(Math.abs(radicando), 1.0 / indice);

                        // Si el radicando es negativo y el índice es impar, el resultado debe ser negativo 
                        if (radicando < 0) {
                            resultado = -resultado;
                        }
                        return resultado;
                    }
                    default -> {
                        return new Errores("Semantico", "Raiz entre tipos invalidos :" + tipoIzq + " * " + tipoDer, this.linea, this.columna);
                    }
                }
            }

            default -> {
                return new Errores("SEMANTICO", "RAIZ entre tipos invalida", this.linea, this.columna);
            }
        }

    }

}
