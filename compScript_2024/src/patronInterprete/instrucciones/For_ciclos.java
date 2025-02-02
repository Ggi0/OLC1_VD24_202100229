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
 * FOR (<ASIGNACION>;<CONDICION>;<ACTUALIZACION>){<INSTRUCCIONES>}
 *
 * @author gio
 */
public class For_ciclos extends Instruccion {

    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;

    public For_ciclos(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar el ciclo FOR
        // recorrido a la izquierda -> 
        // post orden -> 1) asignacion
        var asig = this.asignacion.interpretar(arbol, tabla);
        if (asig instanceof Errores) {
            return asig;
        }

        // 2) condicion
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        // la condicion debe ser booleano
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("Semantico", "la condicion (FOR) debe ser booleana", this.linea, this.columna);

        }

        //3) Entorno nuevo para {<INSTRUCCIONES>}
        var nuevaTabla = new tablaSimbolos(tabla);
        while ((boolean) this.condicion.interpretar(arbol, nuevaTabla)) {
            
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
                
                // -------- intermpretar resultado respueta -----------------
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
                if (resIns instanceof Errores){
                    // guardar error y que la ejecución no muera
                    // return resultado; <- mata toda la ejecucion
                    arbol.AddErrores((Errores)resIns);
                    
                }

            }
            var act = this.actualizacion.interpretar(arbol, nuevaTabla);
            if (act instanceof Errores) {
                return act;
            }
        }

        return null;

    }

}
