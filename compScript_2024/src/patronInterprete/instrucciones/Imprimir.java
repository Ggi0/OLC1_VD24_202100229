
package patronInterprete.instrucciones;

import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;


/**
 *
 * Instrucción que genera una salida en la consola
 * 
 * -> su propósito es interpretar una expresión, 
 *    convertirla a texto y enviarla a la consola
 * 
 * @author gio
 */
public class Imprimir extends Instruccion{
    private Instruccion expresion;

    public Imprimir(Instruccion expresion, int linea, int columna) {
        // Un tipo de dato VOID, porque la instrucción Imprimir no devuelve un valor como resultado; 
        super(new Tipo(tipoDato.VOID), linea, columna);
        
        // expresión que se evaluará y cuyo valor será impreso.
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valor = this.expresion.interpretar(arbol, tabla);
        if (valor instanceof Errores){ // verificar si valor es una instacia de Errores
            return valor; // Si la expresión genera un error, se retorna el error.
        }
        arbol.Imprimir(valor.toString());
        return null; // No devuelve nada porque es una instrucción de tipo VOID.
        
    }


}
