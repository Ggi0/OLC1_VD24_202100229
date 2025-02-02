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
 * <VECTOR> [EXPRESION1][EXPRESION2]
 * 
 * Donde: 
 * vector es el id (nombre con el que fue declarado)
 * expresion1 = el valor de la fila     (int)
 * expresion2 = el valor de la columna  (int)
 * 
 * @author gio
 */
public class AccesoV2 extends Instruccion {
    //  atributos para acceder a la posicion del vector
    private String id;            // nombre del vector
    private Instruccion filaMatriz;     // expresion1
    private Instruccion columnaMatriz;  // expresion2 

    public AccesoV2(String id, Instruccion filaMatriz, Instruccion columnaMatriz, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.filaMatriz = filaMatriz;
        this.columnaMatriz = columnaMatriz;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para acceder a un vector de 2 dimensiones
        // 1) verificar que el vector exista
        var variable = tabla.getVariable(id);
        if (variable == null){
            return new Errores("Semantico", "El vector "+ this.id + " no ha sido declarado", this.linea, this.columna);
        }
        
        // 1.2) verificar que el id sea un VECTOR
        if (!(variable.isEsVector())) {
            return new Errores("Semántico", "Este tipo de acceso es unicamente es para VECTORES, " + this.id + " no es un VECTOR", this.linea, this.columna);
        }

        // 2) actualizar el tipo this.tipo al tipo de la variable
        this.tipo.setTipo(variable.getTipo().getTipo());

        // 3) validad que fila ni columna sean un error
        Object indice1 = this.filaMatriz.interpretar(arbol, tabla); // fila
        if (indice1 instanceof Errores) {
            return indice1;
        }
        
        Object indice2 = this.columnaMatriz.interpretar(arbol, tabla); // columna
        if (indice2 instanceof Errores) {
            return indice2;
        }

        // 4) validar que fila y columna sean enteros 
        var tipoIndice1 = filaMatriz.tipo.getTipo(); // fila
        if (tipoIndice1 != tipo.getTipo().ENTERO){
            return new Errores("Semantico", "El indice " + indice1 + " es "+ this.filaMatriz.tipo.getTipo() + " y debe ser de tipo ENTERO", this.linea, this.columna);

        }
        
        var tipoIndice2 = columnaMatriz.tipo.getTipo(); // columna
        if (tipoIndice2 != tipo.getTipo().ENTERO){
            return new Errores("Semantico", "El indice " + indice2 + " es "+ this.columnaMatriz.tipo.getTipo() + " y debe ser de tipo ENTERO", this.linea, this.columna);

        }

        // 5) bucar el valor con dicha corrdinada (i, j)
        int i = (int) indice1;
        int j = (int) indice2;
        LinkedList<LinkedList<Object>> matrix = (LinkedList<LinkedList<Object>>) variable.getValor();
        
        // 6) retornar el valor del indice.
        
        // Validar que el índice de fila esté dentro de los límites
    if (i < 0 || i >= matrix.size()) {
        return new Errores("Semántico", "El índice de fila " + i + " está fuera de los límites.", this.linea, this.columna);
    }

    LinkedList<Object> fila = matrix.get(i);

    // Validar que el índice de columna esté dentro de los límites
    if (j < 0 || j >= fila.size()) {
        return new Errores("Semántico", "El índice de columna " + j + " está fuera de los límites.", this.linea, this.columna);
    }

    // 7) Retornar el valor encontrado
    Object valor = fila.get(j);

    System.out.println("-- Valor del índice (" + i + ", " + j + ") --> " + valor);
    return valor;



    }
    
    

    
    
    
}
