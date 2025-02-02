/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Funciones_return;

import java.util.HashMap;
import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;

/**
 *
 * @author gio
 */
public class Funciones extends Instruccion{
    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;

    public Funciones(String id, LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        for (Instruccion i : this.instrucciones) {
            Object resultado = i.interpretar(arbol, tabla);
            if (resultado != null) {
                return resultado;
            }
        }
        return null;
    }

}
