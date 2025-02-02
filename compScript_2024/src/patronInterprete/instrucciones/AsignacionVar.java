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
 * para la asignación se validara que no sea constante, de lo contrario sera un
 * error ID = EXPRESION ;
 *
 * @author gio
 */
public class AsignacionVar extends Instruccion {

    // atributos de la asignacion:
    private String id;
    private Instruccion expr;

    public AsignacionVar(String id, Instruccion expr, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.expr = expr;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para majenar la asignacion a variables

        //System.out.println("Antes de buscar la vaiable en asignacion");
        // 1) verificar si existe la variable
        var variable = tabla.getVariable(id);
        //System.out.println("Despues de buscar la vaiable en asignacion");

        if (variable == null) {
            //System.out.println("-> (asig) la variable no existe");
            return new Errores("Semantico", "La variable no existe", this.linea, this.columna);
        }

        // es constante o no? validar aqui
        // 2) Validar si la variable es constante
        if (variable.isConstante()) {
            return new Errores("Semántico", "No se puede asignar un nuevo valor a la variable constante '" + id + "'", this.linea, this.columna);
        }

        // a partir de aqui la varible si existe:
        // 3) Evaluar el nuevo valor de la expresión
        var newValor = this.expr.interpretar(arbol, tabla);
        // validar que no sea un error
        if (newValor instanceof Errores) {
            return newValor;
        }

        // 4) Validar que el tipo del nuevo valor sea compatible con el tipo de la variable
        if (variable.getTipo().getTipo() != this.expr.tipo.getTipo()) {
            return new Errores("Semantico", "Tipo de valores incompatibles", this.linea, this.columna);

        }

        // 5) Actualizar el tipo y valor de la variable
        this.tipo.setTipo(variable.getTipo().getTipo());
        variable.setValor(newValor);
        return null; // asignación exitosa

    }

}
