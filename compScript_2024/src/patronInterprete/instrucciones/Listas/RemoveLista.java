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
 * REMOVE para eliminar un elemento de la LISTA en una posicion especifica
 *
 * id . REMOVE ( <expresion> ) ;
 *
 * donde: id -> es el nombre de la LISTA
 * <expresion> -> es el indice del elemento que se quiere borrar (int)
 *
 * Retorna: el valor que se elimino puede ser una instruccion o una expresion
 *
 * @author gio
 */
public class RemoveLista extends Instruccion {

    private String id;           //nombre de la LISTA
    private Instruccion indice;  // posicion en la lista que se desea borrar (int)

    public RemoveLista(String id, Instruccion indice, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.indice = indice;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar el Remove de una LISTA
        // 1) Verificar que exista la LISTA
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("Semantico", "La LISTA " + this.id + " no ha sido declarado", this.linea, this.columna);
        }

        // 2) Verificar que sea una lista
        if (!(variable.isEsLista())) {
            return new Errores("Semántico", "Metodo REMOVE unicamente es para LISTAS, " + this.id + " no es una LISTA", this.linea, this.columna);
        }

        // 3) validar que el indice no sea un error
        Object index = this.indice.interpretar(arbol, tabla);
        if (index instanceof Errores) {
            return index;
        }

        // 4) validar que el indice sea ENTERO
        var tipoIndex = indice.tipo.getTipo();
        if (tipoIndex != tipo.getTipo().ENTERO) {
            return new Errores("Semantico", "El indice " + index + " es " + this.indice.tipo.getTipo() + " y debe ser de tipo ENTERO", this.linea, this.columna);

        }

        // 5) actualizar el tipo 
        this.tipo.setTipo(variable.getTipo().getTipo());

        // 6) buscar el indice en la LISTA
        LinkedList<Object> lista = (LinkedList<Object>) variable.getValor();
        int i = (int) index;

        // Validar que el índice esté dentro del rango de la lista
        if (i < 0 || i >= lista.size()) {
            return new Errores("Semántico", "Índice fuera de rango. El índice " + i + " no es válido para la LISTA " + this.id, this.linea, this.columna);
        }
        var valorRetornar = lista.get(i);

        lista.remove(i);

        // Retornar el valor en la posición i
        return valorRetornar;

    }

}
