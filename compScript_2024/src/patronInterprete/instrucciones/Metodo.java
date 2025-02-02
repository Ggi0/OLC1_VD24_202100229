/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones;

import java.util.HashMap;
import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *  Es como un conjunto de instrucciones que tienen un nombre (id) 
 * 
 * 
 *  estructura de un metodo:
 *  VOID id ( ) { <instrucciones> }
 * 
 * donde:
 *      VOID -> palabra reservada
 *      id -> nombre del metodo 
 *      <instrucciones> -> instrucciones que vengan en el metodo
 * 
 * 
 * o
 * 
 * 
 *  VOID id ( <parametros> ) { <instrucciones> }
 * 
 * donde:
 *      VOID -> palabra reservada
 *      id -> nombre del metodo 
 *      <paramtros> -> 
 *      <instrucciones> -> instrucciones que vengan en el metodo
 * 
 * FLUJO DE COMO FUNCIONA
 * Return_stc -> Metodo -> Llamada ->
 * 
 * @author gio
 */
public class Metodo extends Instruccion {

    public String id; // nombre del metodo
    public LinkedList<HashMap> parametros;// paramtros
    public LinkedList<Instruccion> instrucciones; // instrucciones que puenden venir dentro del metodo
    private boolean returnEncontrado;
    private Object valorRetorno;

    // construcctor con parametros
    public Metodo(String id, LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.returnEncontrado = false;
        this.valorRetorno = null;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
    //System.out.println("DEBUG: Interpretando " + 
        //(this.tipo.getTipo() == tipoDato.VOID ? "método" : "función") + ": " + this.id);

    /*
    Obtenemos el valor de retorno
        Verificamos si ya está interpretado
            Si es una instrucción, la interpretamos
                Validamos el tipo
                    Retornamos el valor final
    */
    boolean esVoid = this.tipo.getTipo() == tipoDato.VOID;
    //System.out.println("la Funcion / metodo \"" + this.id + "\"es VOID = " + esVoid);

    this.returnEncontrado = false;
    
    for (Instruccion instruccion : this.instrucciones) {
        if (instruccion == null) continue;

        Object resultado = instruccion.interpretar(arbol, tabla);
       // System.out.println("DEBUG: Resultado de instrucción -> " + resultado);
        
        if (resultado instanceof Errores) {
            return resultado;
        }
        
        if (resultado instanceof Return_stc) {
            Return_stc ret = (Return_stc) resultado;
            //System.out.println("DEBUG: Return encontrado en " + this.id);
            
            if (esVoid) {
                if (ret.getValorRetorno() != null) {
                    return generarError("Los métodos void no pueden retornar valores.");
                }
                return null;
            }
            
            // Aquí está el cambio principal
            Object valorRetorno = ret.getValorRetorno();
            if (valorRetorno == null) {
                return generarError("La función debe retornar un valor de tipo " + this.tipo.getTipo());
            }
            
            // Si el valor ya está interpretado, úsalo directamente
            if (!(valorRetorno instanceof Instruccion)) {
                this.returnEncontrado = true;
                return valorRetorno;
            }
            
            // Si es una instrucción, interprétala
            Instruccion expresionRetorno = (Instruccion) valorRetorno;
            Object valor = expresionRetorno.interpretar(arbol, tabla);
            
            if (valor instanceof Errores) {
                return valor;
            }
            
            if (expresionRetorno.tipo.getTipo() != this.tipo.getTipo()) {
                return generarError("El tipo de retorno " + expresionRetorno.tipo.getTipo() +
                    " no coincide con el tipo declarado de la función " + this.tipo.getTipo());
            }
            
            this.returnEncontrado = true;
            return valor;
        }
    }

    if (!esVoid && !this.returnEncontrado) {
        return generarError("NO hay return y la función debe retornar un valor de tipo " + this.tipo.getTipo());
    }

    return null;
}

    /**
     * Genera un objeto de error semántico.
     */
    private Errores generarError(String mensaje) {
        return new Errores("Semantico", mensaje, this.linea, this.columna);
    }

}
