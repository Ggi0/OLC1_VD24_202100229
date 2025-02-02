/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Built_in_methodos;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *
 * length ( <expresion> )
 * 
 * _tamanio ::= length ( _expresion )
 * _tamanio ::= length ( VECTOR [ _expresion ] )

 * 
 * @author gio
 */
public class Length_func extends Instruccion{
    private String identificador;  // Para manejar identificadores (ID) -> lista, vector, Cadena
    private Instruccion valorE;    // expresion ( "hola" ) string
    private Instruccion indice;    // para manejar los indices de vectores 2D

    
// Constructor para expresiones
    public Length_func(Instruccion valorE, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.valorE = valorE;
        this.identificador = null;
        this.indice = null;

    }

    // Constructor para identificadores
    public Length_func(String identificador, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.identificador = identificador;
        this.valorE = null;
        this.indice = null;

    }
    
    // Nuevo constructor para manejar acceso a indices de vectores
    public Length_func(String identificador, Instruccion indice, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.identificador = identificador;
        this.valorE = null;
        this.indice = indice;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

    /*
    
    let vec1 = [1,2,3,4,5]
    lenght( vec1 ) -> retorna 5 que es la cantidad de valores que contiene
    
    let vec2 = [
                [1,2,3,4,5],
                [6,7,8,9,10],
                [11,12,13,14,15]
               ]
    
    lenght( vec1 ) -> retorna 3 que es la cantidad de valores que contiene 
    pero
    lenght( vec1[1] ) -> retorna 5 que es la cantidad de valores que contiene en esa posicion la fila

    */
    
        Object valor;
        
        // Caso 1: Si es un identificador (variable)
        if (this.identificador != null) {
            // Buscar la variable en la tabla de símbolos
            var variable = tabla.getVariable(this.identificador);
            if (variable == null) {
                return new Errores("Semántico", "La variable '" + this.identificador + "' no existe", 
                    this.linea, this.columna);
            }

            valor = variable.getValor();
            if (valor == null) {
                return new Errores("Semántico", "La variable '" + this.identificador + 
                    "' no tiene un valor asignado", this.linea, this.columna);
            }
            
            // Caso especial: Si hay un índice (para vectores 2D)
            if (this.indice != null) {
                if (!variable.isEsVector()) {
                    return new Errores("Semántico", "No se puede acceder al índice de '" + this.identificador + 
                        "' porque no es un vector", this.linea, this.columna);
                }

                // Verificar que el índice no sea un error
                Object indiceInterpretado = this.indice.interpretar(arbol, tabla);
                if (indiceInterpretado instanceof Errores) {
                    return indiceInterpretado;
                }

                // Verificar que el índice sea entero
                if (this.indice.tipo.getTipo() != tipoDato.ENTERO) {
                    return new Errores("Semántico", "El índice debe ser de tipo ENTERO, no " + 
                        this.indice.tipo.getTipo(), this.linea, this.columna);
                }

                int i = (int) indiceInterpretado;
                LinkedList<LinkedList<Object>> matrix = (LinkedList<LinkedList<Object>>) valor;

                // Validar que el índice esté dentro de los límites
                if (i < 0 || i >= matrix.size()) {
                    return new Errores("Semántico", "El índice " + i + " está fuera de los límites del vector", 
                        this.linea, this.columna);
                }

                // Retornar el tamaño de la fila específica
                this.tipo.setTipo(tipoDato.ENTERO);
                return (int)matrix.get(i).size();
            }

            // Verificar el tipo de la variable
            if (variable.isEsVector()) {
                // Para vector 1D: LinkedList<Object>
                // Para vector 2D: LinkedList<LinkedList<Object>>
                if (valor instanceof LinkedList) {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    LinkedList<?> lista = (LinkedList<?>) valor;
                    return (int)lista.size();
                }
            } else if (variable.isEsLista()) {
                // Para listas: LinkedList<Object>
                if (valor instanceof LinkedList) {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    LinkedList<?> lista = (LinkedList<?>) valor;
                    return (int)lista.size();
                }
            } else if (variable.getTipo().getTipo() == tipoDato.CADENA) {
                // Para cadenas
                if (valor instanceof String) {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    String cadena = (String) valor;
                    return (int)cadena.length();
                }
            } else {
                return new Errores("Semántico", 
                    "La función LENGTH solo puede ser aplicada a vectores, listas o cadenas", 
                    this.linea, this.columna);
            }
        }
        // Caso 2: Si es una expresión
        else {
            valor = this.valorE.interpretar(arbol, tabla);
            if (valor instanceof Errores) {
                return valor;
            }

            // Verificar el tipo de la expresión
            if (valor instanceof LinkedList) {
                this.tipo.setTipo(tipoDato.ENTERO);
                LinkedList<?> lista = (LinkedList<?>) valor;
                return (int)lista.size();
            } else if (valor instanceof String) {
                this.tipo.setTipo(tipoDato.ENTERO);
                String cadena = (String) valor;
                return (int)cadena.length();
            } else {
                return new Errores("Semántico", 
                    "La función LENGTH solo puede ser aplicada a vectores, listas o cadenas", 
                    this.linea, this.columna);
            }
        }

        return new Errores("Semántico", "Error inesperado en función LENGTH", 
            this.linea, this.columna);
    }
    
    
}
