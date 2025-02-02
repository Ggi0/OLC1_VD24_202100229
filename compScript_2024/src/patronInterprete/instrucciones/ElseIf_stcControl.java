/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *
 * if ( <expresion> ) { <instruccionesIF> } else if ( <expresion2> ) {
 * <instrucciones2> }
 *
 *
 *
 * @author gio
 */
public class ElseIf_stcControl extends Instruccion {

    private Instruccion condicion;// <expresion> principal
    private LinkedList<Instruccion> instruccionesIf; //Instrucciones dentro del if
    private Instruccion elseIf; //  Otra condición if (puede ser otro ElseIf)
    private LinkedList<Instruccion> instruccionesElse; // Instrucciones para else

    public ElseIf_stcControl(Instruccion condicion, LinkedList<Instruccion> instruccionesIf, Instruccion elseIf, LinkedList<Instruccion> instruccionesElse, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.elseIf = elseIf;
        this.instruccionesElse = instruccionesElse;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        /*
        Motivo del Fallo Anterior:
        En el código anterior, elseIf se evaluaba incorrectamente como 
        si fuera una simple expresión booleana:

        var condElseIf = this.elseIf.interpretar(arbol, tabla);

        Esto no funcionaba porque elseIf es en realidad una instrucción 
        completa (ElseIf_stcControl o If_stcControl) que ya maneja su propia 
        lógica de evaluación y ejecución. La solución fue tratar elseIf como una 
        instrucción y propagar directamente su resultado, lo que incluye el manejo de break y errores.

         */
        
        // cond se evalua con la tabla del entorno del if
        // Evaluar la condición principal
        var cond = this.condicion.interpretar(arbol, tabla);

        if (cond instanceof Errores) {
            return cond;
        }

        // Verificar que la condición principal sea booleana
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("Semantico", "La expresion debe ser de tipo booleana",
                    this.linea, this.columna);
        }

        var newTabla = new tablaSimbolos(tabla); // Crear nuevo entorno para instrucciones del if

        // Si la condición del if es verdadera
        if ((boolean) cond) {
            for (var i : this.instruccionesIf) {
                if (i instanceof Break_stc) {
                    return i;
                }

                if (i instanceof Continuar) {
                    return i;
                }
                
                if (i instanceof Return_stc) {
                    return i;
                }

                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Break_stc) {
                    return resultado;
                }
                if (resultado instanceof Continuar) {
                    return resultado;
                }
                if (resultado instanceof Return_stc) {
                    return resultado;
                }
                if (resultado instanceof Errores) {
                    arbol.AddErrores((Errores) resultado);
                }
            }
        } // Si la condición es falsa y hay un else if
        else if (this.elseIf != null) {
            // Aquí está la clave: el elseIf es una instrucción completa, 
            // no necesitamos evaluar su condición por separado
            var resultado = this.elseIf.interpretar(arbol, tabla);
            if (resultado instanceof Break_stc) {
                return resultado;
            }

            if (resultado instanceof Continuar) {
                return resultado;
            }
            
             if (resultado instanceof Return_stc) {
                return resultado;
            }

            if (resultado instanceof Errores) {
                arbol.AddErrores((Errores) resultado);
            }
            return resultado; // Propagar el resultado del elseIf, incluyendo cualquier break
        }

        return null;

    }

}
