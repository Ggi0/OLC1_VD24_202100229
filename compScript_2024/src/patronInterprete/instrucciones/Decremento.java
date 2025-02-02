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
 *
 * @author gio
 */
public class Decremento extends Instruccion {
    private String id;

    public Decremento(String id, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // toda la logica del ingremento
        // 1) verificar si existe la variable
        var variable = tabla.getVariable(id);
        var nuevaVar = variable.getValor();
        
        // la varible no existe dentro de la tabla
        if (variable == null) {
            return new Errores("Semántico", "La variable no existe", this.linea, this.columna);
        }

        // es constante o no? validar aqui
        // 2) Validar si la variable es constante
        if (variable.isConstante()) {
            return new Errores("Semántico", "No se puede incrementar una Constante", this.linea, this.columna);
        }
        
        // Validar tipo de dato que sea entero o dobleano:
        if (variable.getTipo().getTipo() == tipoDato.ENTERO ){
            // 5) Actualizar el tipo y valor de la variable entero
            this.tipo.setTipo(variable.getTipo().getTipo());
            variable.setValor((int) nuevaVar - 1);
            return null; // asignación exitosa
        }
        else if(variable.getTipo().getTipo() == tipoDato.DECIMAL){
            // 5) Actualizar el tipo y valor de la variable decimal
            this.tipo.setTipo(variable.getTipo().getTipo());
            variable.setValor((double) nuevaVar - 1);
            return null; // asignación exitosa
        }
        
        
        return new Errores("Semántico", "No se puede incrementar el tipo de varible", this.linea, this.columna);


        

    }
    
}
