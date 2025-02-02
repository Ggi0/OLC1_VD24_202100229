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
 *  ASIGNACION VECTORES DE UNA DIMENSION
 * <VECTOR> [EXPRESION] = <EXPRESION> ;
 *     id     i(int)         valor
 * 
 * el tipo de id debe ser igual al tipo de valor 
 * 
 * _asignacionV1 ::= ID LCORCH _expresion RCORCH IGUAL _expresion SEMICOLON
 * 
 * @author gio
 */
public class AsignacionV1 extends Instruccion {
    private String id;             // el vector al cual le agregaremos el valor
    private Instruccion i;         // indice que indica en que posición queremos agregar el valor
    private Instruccion expresion; // valor que se agregará

    public AsignacionV1(String id, Instruccion i, Instruccion expresion, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.i = i;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar al asignacion de valores a vectores de una unidad
        
        // 0) verificar que VECTOR exista
        var v = tabla.getVariable(id);
        if (v == null){
            return new Errores("Semantico", "El vector "+ this.id + " no ha sido declarado", this.linea, this.columna);
        }
        
        // 0.1.1) verificar que el id sea un VECTOR
        if (!(v.isEsVector())) {
            return new Errores("Semántico", "Este tipo de asignacion es unicamente es para VECTORES, " + this.id + " no es un VECTOR", this.linea, this.columna);
        }
        
        // 0.1.2) Validar si el vector es constante
        if (v.isConstante()) {
            return new Errores("Semántico", "No se puede asignar un nuevo valor a lun VECTOR constante '" + id + "'", this.linea, this.columna);
        }

        // a partir de aca la varible existe -----> 
        
        // 1) verificar que i no sea un error 
        Object indice = this.i.interpretar(arbol, tabla);
        if (indice instanceof Errores) {
            return indice;
        }

        
        // 2) verificar que i sea entero 
        var tipoUbicacion = i.tipo.getTipo();
        if (tipoUbicacion != tipo.getTipo().ENTERO){
            return new Errores("Semantico", "El indice " + indice + " es "+ this.i.tipo.getTipo() + " y debe ser de tipo ENTERO", this.linea, this.columna);

        }
        
        // 3) verificar que expresion no sea un error 
        var nuevoValor = this.expresion.interpretar(arbol, tabla);
        if (nuevoValor instanceof Errores) {
            return nuevoValor;
        }

        // 4) verificar que expresion sea del mismo tipo que ID 
        if(v.getTipo().getTipo() != this.expresion.tipo.getTipo()){
            return new Errores("Semantico", "Tipo de valor que desea asignar al VECTOR es incompatible", this.linea, this.columna);
            
        }
        
        // Actualizar el tipo de "this.tipo" al tipo de la variable
        this.tipo.setTipo(v.getTipo().getTipo());

        // 5) buscar la posicion de i
        int ubicacion = (int) indice;
        LinkedList<Object> vector = (LinkedList<Object>) v.getValor(); // lista de los valores
        
        if (ubicacion < 0 || ubicacion >= vector.size()){
            return new Errores("Semantico", "El indice "+ indice + " esta fuera del limite", this.linea, this.columna);
        }

        // 6) agregar el valor 
        vector.set(ubicacion, nuevoValor);
        System.out.println("El valor en la posición " + ubicacion + " del vector ha sido actualizado a: " + nuevoValor);
        return null;
    }
    
    
    
}
