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
 *  // APPEND ID . append ( expresion ) ; donde: id es el nombre de la lista
 * expresion es el valor que se desea agregar a la lista (debe ser el mismo TIPO
 * que ID)
 *
 * @author gio
 */
public class AppendList extends Instruccion {

    // atributos para append 
    private String id; //nombre de la variable 
    private Instruccion expresion; // valor agragado a la lista

    public AppendList(String id, Instruccion expresion, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar los appends de las listas

        // 1) verificar que la variable exista 
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("Semantico", "El vector " + this.id + " no ha sido declarado", this.linea, this.columna);
        }

        // 2) vefificar que variable sea una lista
        if (!(variable.isEsLista())) {
            return new Errores("Semántico", "Metodo unicamente para LISTAS, " + id + " no es una LISTA", this.linea, this.columna);
        }

        // 3) validar que la expresion no sea un error
        var newValor = this.expresion.interpretar(arbol, tabla);
        if (newValor instanceof Errores) {
            return newValor;
        }

        // 4) validar que la expresion sea del mismo tipo que la LISTA
        if (variable.getTipo().getTipo() != this.expresion.tipo.getTipo()) {
            return new Errores("Semantico", "Tipo de valores incompatibles, a la LISTA" + this.id + " NO se le puede agregar un valor tipo: " + " this.expresion.tipo.getTipo()", this.linea, this.columna);

        }

        // Actualizar el tipo de `this.tipo` al tipo de la variable
        this.tipo.setTipo(variable.getTipo().getTipo());

        // 5) Acceder a la lista del ID y agregar el nuevo valor
        LinkedList<Object> lista = (LinkedList<Object>) variable.getValor();
        lista.add(newValor);

        // Confirmar que el valor fue agregado
        System.out.println("El valor " + newValor + " ha sido agregado a la LISTA " + this.id);

        // 6) Retornar null para indicar que la operación fue exitosa
        return null;

    }

}
