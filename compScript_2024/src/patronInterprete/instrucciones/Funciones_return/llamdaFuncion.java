/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Funciones_return;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.instrucciones.Declaracion;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 * MALO -> NO SE UTILIZA
 * @author gio
 */
public class llamdaFuncion extends Instruccion{
    private String id;
    private LinkedList<Instruccion> parametros;

    public llamdaFuncion(String id, LinkedList<Instruccion> parametros, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
    
}
