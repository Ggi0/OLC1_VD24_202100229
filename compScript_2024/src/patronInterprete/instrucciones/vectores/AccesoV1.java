/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.vectores;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *  <VECTOR> [<EXPRESION>]
 * donde:
 * Vector = id
 * expresion = int 
 * @author gio
 */
public class AccesoV1 extends Instruccion{
    private String id; // nombre del vector
    private Instruccion ubicacion; // [ubicacion] int

    public AccesoV1(String id, Instruccion ubicacion, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.ubicacion = ubicacion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para acceder al valor de un vector de una dimension
        
        // 1) verificar que el vector exista
        var variable = tabla.getVariable(id);
        if (variable == null){
            return new Errores("Semantico", "El vector "+ this.id + " no ha sido declarado", this.linea, this.columna);
        }
        
        // 1.2) verificar que el id sea un VECTOR
        if (!(variable.isEsVector())) {
            return new Errores("Semántico", "Este tipo de acceso es unicamente es para VECTORES, " + this.id + " no es un VECTOR", this.linea, this.columna);
        }

        
        // Actualizar el tipo de `this.tipo` al tipo de la variable
        this.tipo.setTipo(variable.getTipo().getTipo());
        
        // 2) validad que ubicacion no sea un ERROR:
        Object indice = this.ubicacion.interpretar(arbol, tabla);
        if (indice instanceof Errores) {
            return indice;
        }
        
        // 3) validar que ubicación sea un ENTERO
        var tipoUbicacion = ubicacion.tipo.getTipo();
        if (tipoUbicacion != tipo.getTipo().ENTERO){
            return new Errores("Semantico", "El indice " + indice + " es "+ this.ubicacion.tipo.getTipo() + " y debe ser de tipo ENTERO", this.linea, this.columna);

        }
        
        
        // 4) buscar el valor de ubicacion en el vector, sino está es un error.
        int i = (int) indice;
        LinkedList<Object> vector = (LinkedList<Object>) variable.getValor();
        
        if (i < 0 || i >= vector.size()){
            return new Errores("Semantico", "El indice "+ indice + " esta fuera del limite", this.linea, this.columna);
        }
        
        // 5) actualizar el tipo
        
        
        System.out.println("-- valor del indice --> " + vector.get(i));
        return vector.get(i); // retornar el valor del indice deseado
    }
    
    
    
    
}
