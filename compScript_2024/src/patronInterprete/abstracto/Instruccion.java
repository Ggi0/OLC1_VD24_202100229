/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.abstracto;

import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;

/**
 * Esta clase está diseñada para ser una base o plantilla común 
 *      para otras clases que representen diferentes tipos de instrucciones
 * 
 * if, suma, while... -> dentro de este estandar
 * 
 * @author gio
 */
public abstract class Instruccion {
    // ATRIBUTOS que todas las clase deben de tener:
    public Tipo tipo; // ya que pueden incluir expresiones\
    public int linea;
    public int columna;
    
    // ----- CONSTRUCTOR -----
    public Instruccion(Tipo tipo, int linea, int columna) {
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }
    
    /**
     * metodo abstracto (contexto) -> "molde"
     * interpretar y hacer semantica
     * 
     * -> Este método define la operación principal de las instrucciones: interpretar.
     * 
     * @param arbol
     *      Representa el contexto global donde se ejecutan las instrucciones, 
     *      como la lista de instrucciones o la salida en consola.
     * 
     * @param tabla
     *      Es una estructura que almacena las variables, constantes y sus valores. 
     *      Se usa para mantener y acceder a los datos definidos en el programa.
     * 
     * @return 
     *      El método retorna un Object porque las instrucciones pueden devolver 
     *      diferentes tipos de valores
     */
    public abstract Object interpretar(Arbol arbol, tablaSimbolos tabla);
    
    
}
