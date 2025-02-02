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
 * DO { <instrucciones> } WHILE ( <expresion> ) ;
 *
 * @author gio
 */
public class DoWhile_ciclos extends Instruccion {

    private Instruccion expresion; //condicion (booleano)
    private LinkedList<Instruccion> instrucciones; //{ <instrucciones> }

    public DoWhile_ciclos(Instruccion expresion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.expresion = expresion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        var cond = this.expresion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.expresion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("Semantico", "La condicion del do while debe ser de tipo booleano", this.linea, this.columna);
        }
        // Crear un entorno local para el ciclo

        var newTabla = new tablaSimbolos(tabla);

        // Ejecutar las instrucciones una vez antes 
        do {
            // Crear un nuevo entorno para esta iteración

            var TablaENTORNO = new tablaSimbolos(newTabla);

            // Ejecutar las instrucciones
            for (Instruccion instruccion : this.instrucciones) {

                if (instruccion instanceof Break_stc) { // verificar que la instruccion no sea un break;
                    return null;
                }

                if (instruccion instanceof Continuar) {
                    break;
                }
                
                if (instruccion instanceof Return_stc) {
                    return instruccion;
                }

                var resultado = instruccion.interpretar(arbol, TablaENTORNO);
                if (resultado instanceof Break_stc) { // verificar que la instruccion no sea un break;
                    return null;
                }

                if (resultado instanceof Continuar) {
                    break;
                }
                if (resultado instanceof Return_stc) {
                    return resultado;
                }
                if (resultado instanceof Errores) {
                    arbol.AddErrores((Errores) resultado);
                }

                cond = this.expresion.interpretar(arbol, tabla);
            }

        } while ((boolean) cond);

        return null;

    }

}
