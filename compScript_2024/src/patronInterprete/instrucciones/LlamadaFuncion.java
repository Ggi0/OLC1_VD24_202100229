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
import patronInterprete.simbolo.Simbolo;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *       ->>>> NO SE UTILIZA <<<-----------
 * @author gio
 */
public class LlamadaFuncion extends Instruccion {
    private String id;
    private LinkedList<HashMap> parametros;

    public LlamadaFuncion(String id, LinkedList<HashMap> parametros, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
// Buscar la función en el árbol
        var busqueda = arbol.getFuncion(id);
        if (busqueda == null) {
            return new Errores("Semantico", "No existe una función con el nombre: " + this.id, this.linea, this.columna);
        }

        // Validar si la búsqueda es una función
        if (!(busqueda instanceof Metodo funcion)) {
            return new Errores("Semantico", "El identificador " + this.id + " no corresponde a una función.", this.linea, this.columna);
        }

        // Crear un nuevo entorno para la función
        var newTabla = new tablaSimbolos(arbol.getTablaGlobal());
        newTabla.setNombre("LlamadaFuncion" + this.id);

        // Validar parámetros
        if (funcion.parametros.size() != this.parametros.size()) {
            return new Errores("Semantico", "Número de parámetros no coincide para la función: " + this.id, this.linea, this.columna);
        }

        for (int i = 0; i < funcion.parametros.size(); i++) {
            var parametroDefinido = funcion.parametros.get(i);
            var parametroLlamado = this.parametros.get(i);

            String idParametro = (String) parametroDefinido.get("id");
            Tipo tipoParametro = (Tipo) parametroDefinido.get("tipo");
            Instruccion expresionParametro = (Instruccion) parametroLlamado.get("expresion");

            // Interpretar el valor del parámetro
            Object valorParametro = expresionParametro.interpretar(arbol, tabla);
            if (valorParametro instanceof Errores) {
                return valorParametro;
            }

            // Validar tipos
            if (expresionParametro.tipo.getTipo() != tipoParametro.getTipo()) {
                return new Errores("Semantico", "El tipo del parámetro no coincide en la función: " + this.id,
                        this.linea, this.columna);
            }

            // Declarar el parámetro en el nuevo entorno
            if (!newTabla.setVariable(new Simbolo(tipoParametro, idParametro, valorParametro))) {
                return new Errores("Semantico", "Error al declarar el parámetro: " + idParametro, this.linea, this.columna);
            }
        }

        // Interpretar la función en el nuevo entorno
        Object resultadoFuncion = funcion.interpretar(arbol, newTabla);

        // Manejar errores durante la ejecución
        if (resultadoFuncion instanceof Errores) {
            return resultadoFuncion;
        }

        // Si la función tiene un tipo de retorno, verificar que el resultado sea válido
        if (funcion.tipo.getTipo() != tipoDato.VOID) {
            if (resultadoFuncion == null) {
                return new Errores("Semantico", "La función " + this.id + " no retornó ningún valor.", this.linea, this.columna);
            }

            // Validar que el tipo del valor retornado coincida con el tipo declarado
            if (this.tipo.getTipo() != funcion.tipo.getTipo()) {
                return new Errores("Semantico", "El tipo del valor retornado no coincide con el tipo de la función: " + this.id,
                        this.linea, this.columna);
            }

            return resultadoFuncion; // Retornar el resultado al entorno de la llamada
        }

        // Si es void, no se espera ningún valor
        return null;    }
    
    
    
}
