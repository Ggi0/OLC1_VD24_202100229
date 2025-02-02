/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *
 * @author gio
 */
public class Caso extends Instruccion {
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public Caso(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Implement interpretation logic if needed
        return null;
    }

    public Instruccion getCondicion() {
        return condicion;
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }


}