/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *
 * IF (<expresion>){<instruccion1>} ELSE {<instruccion2>}
 * 
 *      -> 1ro) <expresion> (tipo bool - de lo contrario error)
 *      -> 2do) <instruccion1>
 *      -> ELSE 3ro <instrucciones2>
 * 
 * @author gio
 */
public class IfElse_stcControl extends Instruccion{
    private Instruccion condicion; // <expresion>
    private LinkedList<Instruccion> instrucciones1; // { <instruccion1> }
    private LinkedList<Instruccion> instrucciones2; // { <instruccion2> }

    public IfElse_stcControl(Instruccion condicion, LinkedList<Instruccion> instrucciones1, LinkedList<Instruccion> instrucciones2, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones1 = instrucciones1;
        this.instrucciones2 = instrucciones2;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar el IF - ELSE
        
    // cond se evalua con la tabla del entorno del if 
        var cond = this.condicion.interpretar(arbol, tabla);
        
        if (cond instanceof Errores){
            return cond;  
        }
        
        // condicion tipo bool -> de lo contrario error
        if(this.condicion.tipo.getTipo() != tipoDato.BOOLEANO){
            return new Errores("Semantico", "la condición debe ser BOOLEANA", this.linea, this.columna);
        }
        
        // { <INSTRUCCIONES> } -> es un bloque de instrucciones
        //          -> necesita un nuevo entorno
        //          -> tabla es el entorno anterior (tabla padre)
        var nuevaTabla = new tablaSimbolos(tabla);
            
        if ((boolean)cond == true){
            // ejecutar <INSTRUCCIONES1>
            for (var i : this.instrucciones1){
                if (i instanceof Break_stc) {
                    return i;
                }
                if (i instanceof Continuar) {
                    return i;
                }
                if (i instanceof Return_stc) {
                    return i;
                }
                
                var resultado = i.interpretar(arbol, nuevaTabla);
                if (resultado instanceof Break_stc) {
                    return resultado;
                }
                if (resultado instanceof Continuar) {
                    return resultado;
                }
                if (resultado instanceof Return_stc) {
                    return resultado;
                }
                
                if (resultado instanceof Errores){
                    // guardar error y que la ejecución no muera
                    // return resultado; <- mata toda la ejecucion
                    arbol.AddErrores((Errores)resultado);
                    
                }
  
            }
        }else{ // 3) ejecutar el ELSE
            // ejecutar <INSTRUCCIONES2>
            for (var i : this.instrucciones2){
                if (i instanceof Break_stc) {
                    return i;
                }
                if (i instanceof Continuar) {
                    return i;
                }
                if (i instanceof Return_stc) {
                    return i;
                }
                var resultado = i.interpretar(arbol, nuevaTabla);
                if (resultado instanceof Break_stc) {
                    return resultado;
                }
                if (resultado instanceof Continuar) {
                    return resultado;
                }
                if (resultado instanceof Return_stc) {
                    return resultado;
                }
                if (resultado instanceof Errores){
                    // guardar error y que la ejecución no muera
                    // return resultado; <- mata toda la ejecucion
                    arbol.AddErrores((Errores)resultado);  
                }
            }
        }
        return null;
    }

    
    


    
    
}
