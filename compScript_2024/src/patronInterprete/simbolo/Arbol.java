/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.simbolo;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.instrucciones.Metodo;

/**
 *      árbol sintáctico abstracto
 * 
 * -> resultado del análisis sintáctico.
 * 
 *  lista de instrucciones: el árbol tiene una raiz con el arreglo de todas las instrucciones
 * @author gio
 */
public class Arbol {
    private LinkedList<Instruccion> instrucciones; // lista de instruccion
    private String consola;               // salida en consola
    private LinkedList<Errores> errores;  // lista de errores
    private tablaSimbolos tablaGlobal;    // tabla de simbolos (global)
    private LinkedList<Instruccion> funciones; // al contexto se le agrego la capacidad de guardar las funciones
    
    // ----- CONSTRUCCTOR -----
    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones; // Inicializa el árbol con las instrucciones obtenidas del análisis sintáctico.
        consola = ""; // Inicializa la consola vacía
        this.errores = new LinkedList<>(); // Inicializa la lista de errores vacía
        this.funciones = new LinkedList<>(); 
        
    }
    
    // GETTERS and SETTERS

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }

    public tablaSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public void setTablaGlobal(tablaSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public LinkedList<Instruccion> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<Instruccion> funciones) {
        this.funciones = funciones;
    }
    
    //  
    public void addFunciones (Instruccion funcion){
        // hacer busqueda o si se guarda
        this.funciones.add(funcion);
    }
    
    // Busca una función por su nombre (id) y la devuelve si existe.
    public Instruccion getFuncion(String id){
        for(var i : this.funciones){
            if (i instanceof Metodo metodo){
                // castear i de instruccion a Metodo
                if(metodo.id.equalsIgnoreCase(id)){
                    return i;
                }
                
            }
        }
        return null;
    }
    
    
    
    
    
    
    // ----- METODO PARA IMPRIMIR -----
    public void Imprimir(String valor){
        // el IMPRIMIR es una instruccion
        this.consola += valor  + "\n";
    }
    
    // -------- METODO PARA AGREGAR ERRORES A LA LISTA DE ERRORES------------
    public void AddErrores(Errores e){
        System.out.println("se agrego un error");
        this.errores.add(e);
    }
    
    
}
