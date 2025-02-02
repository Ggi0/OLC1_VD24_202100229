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
 * E -> E>E compara ambos lados y verifica si el izquierdo es mayor que el
 * derecho.
 *
 * para los booleanos se toma true como 1 y false como 0 para las cadenas no se
 * toma en cuenta el resto se toma su valor numerico o valor en la tabla
 *
 * @author gio
 */
public class Mayor extends Instruccion {

    // atributos expresion izq y derecha
    private Instruccion expIzq;
    private Instruccion expDer;

    public Mayor(Instruccion expIzq, Instruccion expDer, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.expIzq = expIzq;
        this.expDer = expDer;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para interpretar >
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
                        return (int) res1 > (int) res2;
                    }
                    case DECIMAL -> {
                        return (int) res1 > (double) res2;
                    }
                    case BOOLEANO -> {
                        int valorBooleano = (boolean) res2 ? 1 : 0;
                        return (int) res1 > valorBooleano;
                    }
                    case CARACTER -> {
                        int valorCaracter = ((String) res2).charAt(0);
                        return (int) res1 > valorCaracter;
                    }
                    case CADENA -> {
                        return new Errores("SEMANTICO", "Combinación de relacional > INVALIDA: ENTERO > CADENA", this.linea, this.columna);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Combinación de relacional > INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        return (double) res1 > (int) res2;
                    }
                    case DECIMAL -> {
                        return (double) res1 > (double) res2;
                    }
                    case BOOLEANO -> {
                        int valorBooleano = (boolean) res2 ? 1 : 0;
                        return (double) res1 > valorBooleano;
                    }
                    case CARACTER -> {
                        int valorCaracter = ((String) res2).charAt(0);
                        return (double) res1 > valorCaracter;
                    }
                    case CADENA -> {
                        return new Errores("SEMANTICO", "Combinación de relacional > INVALIDA: DECIMAL > CADENA", this.linea, this.columna);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Combinación de relacional > INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        int valorBooleano = (boolean) res1 ? 1 : 0;
                        return valorBooleano > (int) res2;
                    }
                    case DECIMAL -> {
                        int valorBooleano = (boolean) res1 ? 1 : 0;
                        return valorBooleano > (double) res2;
                    }
                    case BOOLEANO -> {
                        int valorBooleano1 = (boolean) res1 ? 1 : 0;
                        int valorBooleano2 = (boolean) res2 ? 1 : 0;
                        return valorBooleano1 > valorBooleano2;
                    }
                    case CARACTER -> {
                        int valorBooleano = (boolean) res1 ? 1 : 0;
                        int valorCaracter = ((String) res2).charAt(0);
                        return valorBooleano > valorCaracter;
                    }
                    case CADENA -> {
                        return new Errores("SEMANTICO", "Combinación de relacional > INVALIDA: BOOLEANO > CADENA", this.linea, this.columna);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Combinación de relacional > INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        int valorCaracter = ((String) res1).charAt(0);
                        return valorCaracter > (int) res2;
                    }
                    case DECIMAL -> {
                        int valorCaracter = ((String) res1).charAt(0);
                        return valorCaracter > (double) res2;
                    }
                    case BOOLEANO -> {
                        int valorCaracter = ((String) res1).charAt(0);
                        int valorBooleano = (boolean) res2 ? 1 : 0;
                        return valorCaracter > valorBooleano;
                    }
                    case CARACTER -> {
                        int valorCaracter1 = ((String) res1).charAt(0);
                        int valorCaracter2 = ((String) res2).charAt(0);
                        return valorCaracter1 > valorCaracter2;
                    }
                    case CADENA -> {
                        return new Errores("SEMANTICO", "Combinación de relacional > INVALIDA: CARACTER > CADENA", this.linea, this.columna);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Combinación de relacional > INVALIDA", this.linea, this.columna);
                    }
                }
            }
            case CADENA -> {
                return new Errores("SEMANTICO", "Combinación de relacional > INVALIDA: CADENA no puede compararse con otro tipo", this.linea, this.columna);
            }
            default -> {
                return new Errores("SEMANTICO", "TIPO NO SOPORTADO PARA RELACIONAL >", this.linea, this.columna);
            }
        }

    }

}
