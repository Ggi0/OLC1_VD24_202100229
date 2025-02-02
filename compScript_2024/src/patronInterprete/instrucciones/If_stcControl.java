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
 * if ( <EXPRESIONES>) { <INSTRUCCIONES> }
 * 
 * recorrido post orden
 * -> 1ero EXPRESION    (tipo BOOLEANO)
 * -> 2do INSTRUCCIONES
 * @author gio
 */
public class If_stcControl extends Instruccion{
    private Instruccion condicion; // <EXPRESIONES>
    private LinkedList<Instruccion> instrucciones; // { <INSTRUCCIONES> }
    
    // constructor
    public If_stcControl(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar IF
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
            
        if ((boolean)cond){
            // ejecutar <INSTRUCCIONES>
            for (var i : this.instrucciones){
                
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
