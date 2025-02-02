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
 * E -> E == E
 *
 * @author gio
 */
public class Equals extends Instruccion {

    private Instruccion expIzq;
    private Instruccion expDer;

    public Equals(Instruccion exprIzq, Instruccion exprDer, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.expIzq = exprIzq;
        this.expDer = exprDer;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar el  == 
        var res1 = this.expIzq.interpretar(arbol, tabla);
        if (res1 instanceof Errores) {
            return res1;
        }

        var res2 = this.expDer.interpretar(arbol, tabla);
        if (res2 instanceof Errores) {
            return res1;
        }

        var tipo1 = this.expIzq.tipo.getTipo();
        var tipo2 = this.expDer.tipo.getTipo();

        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        return (int) res1 == (int) res2;
                    }
                    case DECIMAL -> {
                        return (int) res1 == (double) res2;
                    }
                    case CARACTER -> {
                        int valorCaracter = ((String) res2).charAt(0);
                        return (int) res1 == valorCaracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Convinacion de relacional == INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        return (double) res1 == (int) res2;
                    }
                    case DECIMAL -> {
                        return (double) res1 == (double) res2;
                    }
                    case CARACTER -> {
                        int valorCaracter = ((String) res2).charAt(0);
                        return (double) res1 == valorCaracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "COMBINACION DE RELACIONAL == INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        return (boolean) res1 == (boolean) res2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "COMBINACION DE RELACIONAL == INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        int valorCaracter = ((String) res1).charAt(0);
                        return valorCaracter == (int) res2;
                    }
                    case DECIMAL -> {
                        int valorCaracter = ((String) res1).charAt(0);
                        return valorCaracter == (double) res2;
                    }
                    case CARACTER -> {
                        int valorCaracter1 = ((String) res1).charAt(0);
                        int valorCaracter2 = ((String) res2).charAt(0);
                        return valorCaracter1 == valorCaracter2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "COMBINACION DE RELACIONAL == INVALIDA", this.linea, this.columna);
                    }
                }
            }case CADENA -> {
                switch (tipo2) {
                    case CADENA -> {
                        return ((String) res1).equals((String) res2);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "COMBINACION DE RELACIONAL == INVALIDA", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "TIPO NO SOPORTADO", this.linea, this.columna);
            }
        }
    }
}