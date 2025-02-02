/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Listas;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 * Similar al REMOVE al diferencia es que elimina el ultimo y retorna el valor
 *
 * id . pop ();
 *
 * @author gio
 */
public class PopListas extends Instruccion {

    private String id;  //nombre de la variable 

    public PopListas(String id, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar el pop en las LISTA
        // 1) Verificar que exista la LISTA
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("Semantico", "La LISTA " + this.id + " no ha sido declarado", this.linea, this.columna);
        }

        // 2) Verificar que sea una lista
        if (!(variable.isEsLista())) {
            return new Errores("Semántico", "Metodo POP unicamente es para LISTAS, " + this.id + " no es una LISTA", this.linea, this.columna);
        }

        // 3) actualizar el tipo 
        this.tipo.setTipo(variable.getTipo().getTipo());
        
        // 4) Acceder a la lista
        LinkedList<Object> lista = (LinkedList<Object>) variable.getValor();

        // 5) Validar si la lista está vacía
        if (lista.isEmpty()) {
            return new Errores("Semántico", "La LISTA " + this.id + " está vacía, no se puede realizar el POP", this.linea, this.columna);
        }

        // 6) Eliminar y obtener el último valor
        Object valorRetornar = lista.removeLast();

        // 7) Retornar el valor eliminado
        return valorRetornar;

    }

}
