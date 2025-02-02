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
 * while ( <expresion> ) { <INSTRUCCIONES> }
 * <expresion> -> similar a condicion del FOR -> debe ser bool
 *
 * @author gio
 */
public class While_ciclos extends Instruccion {

    private Instruccion expresion; //condicion (booleano)
    private LinkedList<Instruccion> instrucciones;

    public While_ciclos(Instruccion expresion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.expresion = expresion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para el WHILE
        // 1) VALIDAR condicion
        var condicion = this.expresion.interpretar(arbol, tabla);
        if (condicion instanceof Errores) {
            return condicion;
        }

        // 2)  condicion debe ser de tipo booleano
        if (this.expresion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("Semantico", "la condicion (WHILE) debe ser booleana", this.linea, this.columna);

        }

        //3) Entorno nuevo para {<INSTRUCCIONES>}
        var nuevaTabla = new tablaSimbolos(tabla);
        while ((boolean) this.expresion.interpretar(arbol, nuevaTabla)) {

            // para cada iteracion se debe manejar un nuevo entorno
            var nuevaTabla2 = new tablaSimbolos(nuevaTabla);

            // recorrer para todas las instrucciones
            for (var i : this.instrucciones) {
                
                if (i instanceof Break_stc) { // verificar que la instruccion no sea un break;
                    return null;
                }
                
                if (i instanceof Continuar) {
                    break;
                }
                
                if (i instanceof Return_stc) {
                    return i;
                }
                
                var resIns = i.interpretar(arbol, nuevaTabla2);
                if (resIns instanceof Break_stc) {
                    return null;
                }
                
                if (resIns instanceof Continuar) {
                    break;
                }
                
                if (resIns instanceof Return_stc) {
                    return resIns;
                }
                
                // guardar error y que la ejecución no muera
                // errores como en el if
                if (resIns instanceof Errores) {
                    // guardar error y que la ejecución no muera
                    // return resultado; <- mata toda la ejecucion
                    arbol.AddErrores((Errores) resIns);

                }

            }
        }

        return null;

    }

}
