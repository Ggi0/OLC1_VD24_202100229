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
 * ACCESO A UNA LISTA (get) id . get ( <expresion> )
 *
 * id -> nombre de la lista
 * <expresion> -> posicion en la lista (int)
 *
 * retorna el valor
 *
 * @author gio
 */
public class AccesoLista extends Instruccion {

    private String id;  //nombre de la variable 
    private Instruccion indice; // posicion en la lista que se desea encontrar (int)

    public AccesoLista(String id, Instruccion indice, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.indice = indice;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar el acceso al una listas en referencia de un indice

        // 1) verificar que la LISTA exista 
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("Semantico", "La LISTA " + this.id + " no ha sido declarado", this.linea, this.columna);
        }

        // 2) verificar que el id sea una LISTA
        if (!(variable.isEsLista())) {
            return new Errores("Semántico", "Metodo GET unicamente es para LISTAS, " + this.id + " no es una LISTA", this.linea, this.columna);
        }

        // 3) validar que indice no sea un ERROR
        Object index = this.indice.interpretar(arbol, tabla);
        if (index instanceof Errores) {
            return index;
        }

        // 4) validar que indice sea un entero
        var tipoIndex = indice.tipo.getTipo();
        if (tipoIndex != tipo.getTipo().ENTERO) {
            return new Errores("Semantico", "El indice " + index + " es " + this.indice.tipo.getTipo() + " y debe ser de tipo ENTERO", this.linea, this.columna);

        }

        // 5) actualizar el tipo de this.tipo al tipo de la LISTA
        this.tipo.setTipo(variable.getTipo().getTipo());

        // 6) buscar el indice en la LISTA, sino esta es un error
        LinkedList<Object> lista = (LinkedList<Object>) variable.getValor();
        int i = (int) index;

        // Validar que el índice esté dentro del rango de la lista
        if (i < 0 || i >= lista.size()) {
            return new Errores("Semántico", "Índice fuera de rango. El índice " + i + " no es válido para la LISTA " + this.id, this.linea, this.columna);
        }

        // Retornar el valor en la posición i
        return lista.get(i);
    }

}
