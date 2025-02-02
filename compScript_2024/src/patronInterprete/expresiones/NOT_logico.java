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

/**
 *
 *  * E -> !E retorna bool
 *
 * Devuelve el valor inverso de una expresión lógica.
 * Si es verdadera, devolverá falso; si es falsa, devolverá verdadero.
 * 
 * @author gio
 */
public class NOT_logico extends Instruccion{
    // Atributo: expresión (bool)
    private Instruccion expresion;

    public NOT_logico(Instruccion expresion, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        
        // Interpretar la expresión
        var resultado = this.expresion.interpretar(arbol, tabla);
        if (resultado instanceof Errores) {
            return resultado; // Si hay un error, retornarlo
        }

        // Validar que el resultado sea de tipo BOOLEANO
        var tipoResultado = this.expresion.tipo.getTipo();
        
        if (tipoResultado == tipoDato.BOOLEANO) {
            // Realizar operación lógica NOT
            return !(boolean) resultado;
        } else {
            return new Errores("SEMANTICO", "Operación ! inválida: el operando no es booleano", this.linea, this.columna);
        }

    }
    
    
    
    
    
    
    
}
