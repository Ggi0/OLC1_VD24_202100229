/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Listas;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Simbolo;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;

/**
 *  DECLARACION DE LISTAS DINAMICAS
 * 
 *  MUTABILIDAD id : List < tipo >;
 *  Si es const es error
 * 
 * id nombre del la LISTA
 * List palabra reservada 
 * tipo : double, int, char... 
 *  
 * @author gio
 */
public class DeclaracionLista extends Instruccion {
    // atributos
    private String identificador; // Nombre de la variable
    private String mutabilidad;   // "let" o "const"

    public DeclaracionLista(String identificador, String mutabilidad, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar las declaraciones de las listas
        
        // 0) validar si es constante (error)
        if (this.mutabilidad.equalsIgnoreCase("const")){
            return new Errores("Semantico", "Declacarion incorrecta: las listas dinamicas no pueden ser CONST", this.linea, this.columna);
        }
                
        // 1) asignar lista vacia para la LISTA
        LinkedList<Object> listaVacia = new LinkedList<>(); // aqui se almacenaran los valores de la LISTA


        // 2) crear lista
        Simbolo simbolo = new Simbolo(this.tipo, this.identificador, listaVacia);
        simbolo.setEsLista(true);
        // 3) validar si la varible ya existe y Declara la Variable
        if (tabla.setVariable(simbolo)){
            // (true) varible declarada correctamente 
            System.out.println("la LISTA  " + this.identificador +"  ha sido declarada correctamente");
            return null;
        }
        
        return new Errores("Semantico", "La LISTA: " + this.identificador + " ya fue declarada", this.linea, this.columna);
        

    }

    
}
