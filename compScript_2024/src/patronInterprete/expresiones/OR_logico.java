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
 * E -> E || E retorna bool
 *
 * compara expresiones lógicas y si al menos una es verdadera, entonces devuelve
 * verdadero y en otro caso retorna falso.
 *
 * @author gio
 */
public class OR_logico extends Instruccion {

    // atributos expresion izq(bool) y derecha(bool)
    private Instruccion expIzq;
    private Instruccion expDer;

    public OR_logico(Instruccion expIzq, Instruccion expDer, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.expIzq = expIzq;
        this.expDer = expDer;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar el || (OR)
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
            case BOOLEANO -> {
                switch (tipo2) {                    
                    case BOOLEANO -> {
                        return (boolean) res1 || ((boolean)res2);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Combinación logica para OR -> INVALIDA", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Combinación logica para OR -> INVALIDA", this.linea, this.columna);
            }
        }

    }

}
