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
 *
 * ASIGNACION VECTORES DE 2 DIMENSIONEs
 * <VECTOR> [EXPRESION] [EXPRESION] = <EXPRESION> ;
 *     id     i(int)         j(int)      valor
 * 
 * el tipo de id (vector) debe ser igual al tipo de valor (expresion)
 * 
 * _asignacionV2 ::= ID LCORCH _expresion1 RCORCH LCORCH _expresion2 RCORCH IGUAL _expresion SEMICOLON
 * @author gio
 */
public class AsignacionV2 extends Instruccion {

    // atributos para asignar valores al vector 2D
    private String id;                  // nombre del vector
    private Instruccion filaMatriz;     // expresion1
    private Instruccion columnaMatriz;  // expresion2 
    private Instruccion valor;          // valor que se agregara en dicha ubicacion

    public AsignacionV2(String id, Instruccion filaMatriz, Instruccion columnaMatriz, Instruccion valor, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.filaMatriz = filaMatriz;
        this.columnaMatriz = columnaMatriz;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar la asignacion a vectores de 2D
        // 1) verificar que el vector exista
        var v = tabla.getVariable(id);
        if (v == null) {
            return new Errores("Semantico", "El vector " + this.id + " no ha sido declarado", this.linea, this.columna);
        }
        
        // 1.2) verificar que el id sea un VECTOR
        if (!(v.isEsVector())) {
            return new Errores("Semántico", "Este tipo de asignacion es unicamente es para VECTORES, " + this.id + " no es un VECTOR", this.linea, this.columna);
        }

        // 2) validar si el vector es constante
        if (v.isConstante()) {
            return new Errores("Semántico", "No se puede asignar un nuevo valor a lun VECTOR constante '" + id + "'", this.linea, this.columna);
        }

        // a partir de aca la varible existe -----> 
        // 3) verificar que fila y columna no sean errores
        Object indice1 = this.filaMatriz.interpretar(arbol, tabla); // fila
        if (indice1 instanceof Errores) {
            return indice1;
        }

        Object indice2 = this.columnaMatriz.interpretar(arbol, tabla); // columna
        if (indice2 instanceof Errores) {
            return indice2;
        }

        // 4) verificar que fila y columna sean enteros
        var tipoIndice1 = filaMatriz.tipo.getTipo();
        if (tipoIndice1 != tipo.getTipo().ENTERO) {
            return new Errores("Semantico", "El indice " + indice1 + " es " + this.filaMatriz.tipo.getTipo() + " y debe ser de tipo ENTERO", this.linea, this.columna);

        }
        var tipoIndice2 = columnaMatriz.tipo.getTipo();
        if (tipoIndice2 != tipo.getTipo().ENTERO) {
            return new Errores("Semantico", "El indice " + indice2 + " es " + this.columnaMatriz.tipo.getTipo() + " y debe ser de tipo ENTERO", this.linea, this.columna);

        }

        // 5) verificar que valor no sea un error
        var nuevoValor = this.valor.interpretar(arbol, tabla);
        if (nuevoValor instanceof Errores) {
            return nuevoValor;
        }

        // 6) veficicar que valor sea el mismo tipo que el vector
        if (v.getTipo().getTipo() != this.valor.tipo.getTipo()) {
            return new Errores("Semantico", "Tipo de valor que desea asignar al VECTOR 2D es incompatible", this.linea, this.columna);

        }
        // 7) actualizar el this.tipo        
        this.tipo.setTipo(v.getTipo().getTipo());

        // 8) buscar la posicion (i, j)
        int i = (int) indice1; // Fila
        int j = (int) indice2; // Columna
        
        // 9) agregar el valor en la posición deseada
        LinkedList<LinkedList<Object>> matrix = (LinkedList<LinkedList<Object>>) v.getValor();

        // Validar que el índice de fila esté dentro de los límites
        if (i < 0 || i >= matrix.size()) {
            return new Errores("Semántico", "El índice de fila " + i + " está fuera de los límites.", this.linea, this.columna);
        }

        LinkedList<Object> fila = matrix.get(i);

        // Validar que el índice de columna esté dentro de los límites
        if (j < 0 || j >= fila.size()) {
            return new Errores("Semántico", "El índice de columna " + j + " está fuera de los límites.", this.linea, this.columna);
        }

        // 8) Asignar el valor en la posición deseada
        fila.set(j, nuevoValor);

        System.out.println("El valor en la posición (" + i + ", " + j + ") del vector ha sido actualizado a: " + nuevoValor);
        return null;
    }

}
