/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.expresiones;

import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;

import patronInterprete.simbolo.tipoDato;
import static patronInterprete.simbolo.tipoDato.*;

/**
 *  De mayor precedencia
 *  Instruccion <Tipo, linea, columna>
 * E -> -E
 * @author gio
 */
public class NegacionUnaria extends Instruccion {
    private Instruccion expresion;

    public NegacionUnaria(Instruccion expresion, int linea, int columna) {
        super(new  Tipo(tipoDato.VOID), linea, columna);
        this.expresion = expresion;
    }

    // METODO ABSTRACTO
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        //logica para una negacion unitaria
        var resultado = this.expresion.interpretar(arbol, tabla);
        if (resultado instanceof Errores){
            return resultado;
        }
        
        switch (this.expresion.tipo.getTipo()) {
            case ENTERO -> {
                this.tipo.setTipo(ENTERO);
                return -(int)resultado;
            }
            case DECIMAL -> {
                this.tipo.setTipo(DECIMAL);
                return -(double)resultado;
            }
            default -> {
                return new Errores("Semantico: ", "Negación unaria invalida", this.linea,this.columna);
            }
        
        }
        
    }
    
    


    
    
}
