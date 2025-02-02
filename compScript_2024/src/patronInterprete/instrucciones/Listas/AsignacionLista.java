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
 *  ASIGNACION A UNA LISTA ( .SET )
 * 
 *  ID . SET ( <expresion1> , <expresion2> ) ;
 * 
 * donde:
 * ID -> es el nombre de la lista
 * <expresion1> -> es el indice en donde se desea agregar el valor (int)
 * <expresion2> -> es el nuevo Valor que se desea agregar (tipo igual al tipo de la lista)
 * 
 * @author gio
 */
public class AsignacionLista extends Instruccion{
    private String id;             // la LISTA en donde agregaremos el neuvo valor
    private Instruccion indice;    // indice que indica en que posición queremos agregar el nuevo valor
    private Instruccion expresion; // nuevo valor que se agregará

    public AsignacionLista(String id, Instruccion indice, Instruccion expresion, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.indice = indice;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar la alsignancion de valores a una lista (set)
        // 1) verificar que la LISTA exista
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("Semantico", "La LISTA " + this.id + " no ha sido declarado", this.linea, this.columna);
        }

        // 2) verificar que sea una LISTA
        if (!(variable.isEsLista())) {
            return new Errores("Semántico", "Metodo SET unicamente es para LISTAS, " + this.id + " no es una LISTA", this.linea, this.columna);
        }

        // 3) validar que el indice no sea un error
        Object index = this.indice.interpretar(arbol, tabla);
        if (index instanceof Errores) {
            return index;
        }

        // 4) validar que el indice sea entero
        var tipoIndex = indice.tipo.getTipo();
        if (tipoIndex != tipo.getTipo().ENTERO) {
            return new Errores("Semantico", "El indice " + index + " es " + this.indice.tipo.getTipo() + " y debe ser de tipo ENTERO", this.linea, this.columna);

        }

        // 5) validar que la expresion no sea un error
        var nuevoValor = this.expresion.interpretar(arbol, tabla);
        if (nuevoValor instanceof Errores) {
            return nuevoValor;
        }

        // 6) validar que la expresion sea del mismo tipo que la LISTA
        if(variable.getTipo().getTipo() != this.expresion.tipo.getTipo()){
            return new Errores("Semantico", "Tipo de valor que desea asignar a la LISTA es incompatible", this.linea, this.columna);      
        }

        // 7) actualizar el tipo de this.tipo al tipo de  la LISTA
        this.tipo.setTipo(variable.getTipo().getTipo());
        
        // 8) buscar el indice en la LISTA, sino esta es un error
        LinkedList<Object> lista = (LinkedList<Object>) variable.getValor();
        int i = (int) index;
        
        // Validar que el índice esté dentro del rango de la lista
        if (i < 0 || i >= lista.size()) {
            return new Errores("Semántico", "Índice fuera de rango. El índice " + i + " no es válido para la LISTA " + this.id, this.linea, this.columna);
        }
        
        lista.set(i, nuevoValor);
        System.out.println("El valor en la posición " + i + " de la LISTA ha sido actualizado a: " + nuevoValor);
        return null;


        
        
    }
    
    
    
    
    
    
    
}
