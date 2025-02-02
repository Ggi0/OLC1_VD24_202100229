/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Listas;

import java.util.Collections;
import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *
 * metodo REVERSE
 * invierte el orden de los elementos de la lista
 * 
 * id . REVERSE ( ) ;
 * 
 * @author gio
 */
public class ReverseLista extends Instruccion{
        private String id;  //nombre de la variable 

    public ReverseLista(String id, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para invertir los valores de una LISTA
        
        // 1) Verificar que la LISTA exista
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("Semántico", "La LISTA " + this.id + " no ha sido declarada", this.linea, this.columna);
        }

        // 2) Verificar que sea una lista
        if (!(variable.isEsLista())) {
            return new Errores("Semántico", "Método REVERSE únicamente es para LISTAS, " + this.id + " no es una LISTA", this.linea, this.columna);
        }

        // 3) Acceder a la lista
        LinkedList<Object> lista = (LinkedList<Object>) variable.getValor();

        /* // 4) Validar si la lista está vacía
        if (lista.isEmpty()) {
            return new Errores("Semántico", "La LISTA " + this.id + " está vacía, no se puede realizar el REVERSE", this.linea, this.columna);
        }*/

        // 5) Invertir el orden de los elementos de la lista
        Collections.reverse(lista);

        // 6) Actualizar la lista en la variable
        variable.setValor(lista);

        return null; // No retorna ningún valor
        
    }


    
    
}
