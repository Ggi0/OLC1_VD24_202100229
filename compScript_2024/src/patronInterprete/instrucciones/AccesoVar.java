/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones;

import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 * cuando se accese a una varible generalmente es unicamente con el
 * identificador en: 
 * 
 *  let num : int = 10; 
 *  console.log(num)
 *
 * -> buscamos acceder unicamente a num
 *
 * @author gio
 */
public class AccesoVar extends Instruccion {

    private String id;

    public AccesoVar(String id, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para acceder al valor de la varible
        var valor = tabla.getVariable(id);
        
        // la variable aun no existe dentro del entorno
        if (valor == null) {
            return new Errores("Semantico", "La variable no existe", this.linea, this.columna);

        }
        
        //acesso -> getTipo() -> Simbolo
        //       -> getTipo() -> Tipo
        this.tipo.setTipo(valor.getTipo().getTipo());
        return valor.getValor();
    }

}
