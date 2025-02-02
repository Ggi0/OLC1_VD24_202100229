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
 * puede funcionar como instruccion o como expresion por ejemplo:
 *
 * funtion void main(){ vectores(); } // instruccion
 *
 * int retorno = factorial(a-1) * a // expresion
 * 
 * Entorno de la Llamada (Donde se llama a la función)


    
    * Es el entorno donde se encuentra el código que hace la llamada a la función
    
    * Este entorno mantiene sus propias variables y estado
    
    * Cuando se hace una asignación con el resultado de una función, 
    * este entorno es donde debe guardarse el valor
 *
 * @author gio
 */
public class Llamada extends Instruccion {

    private String id;
    private LinkedList<HashMap> parametros;

    public Llamada(String id, LinkedList<HashMap> parametros, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Buscar la función o método en el árbol
        var busqueda = arbol.getFuncion(id);
        if (busqueda == null) {
            //System.out.println("==x=> No existe la funcion/metodo" + this.id);
            return new Errores("Semantico", "No existe una funcion/metodo con el nombre: " + this.id, this.linea, this.columna);
        }

        //System.out.println("==x=> Si existe la funcion/metodo " + this.id + " y es de tipo : " + busqueda.tipo.getTipo());

        // Validar si la búsqueda es un método o función
        if (busqueda instanceof Metodo metodo) {
            //System.out.println("====> El ID de la llamada esta en la clase Metodos");

            // Crear un nuevo entorno (tabla de símbolos) para la ejecución
            var newTabla = new tablaSimbolos(arbol.getTablaGlobal());
            newTabla.setNombre("Llamada" + this.id);

            // Validar cantidad de parámetros
            //if (metodo.parametros.size() != this.parametros.size()) {
              // return new Errores("Semantico", "Número de parámetros no coincide para el método/función: " + this.id, this.linea, this.columna);
            //}

            // PRIMER FOR: Declaración inicial de parámetros
            for (int i = 0; i < metodo.parametros.size(); i++) {
                var identificador = (String) metodo.parametros.get(i).get("id");
                var tipo = (Tipo) metodo.parametros.get(i).get("tipo");
                var expresion = (Instruccion) metodo.parametros.get(i).get("expresion");

                Object valExp = null;

                // Validar expresión si existe
                if (expresion != null) {
                    valExp = expresion.interpretar(arbol, newTabla);
                    if (valExp instanceof Errores) {
                        return valExp;
                    }
                    if (tipo.getTipo() != expresion.tipo.getTipo()) {
                        return new Errores("SEMANTICO", "Error en el parametro No son del mismo tipo",
                                this.linea, this.columna);
                    }
                }

                // Declarar el parámetro
                if (!newTabla.setVariable(new Simbolo(tipo, identificador, valExp))) {
                    return new Errores("Semantico", "El parametro no se declaro", this.linea, this.columna);
                }
            }

            // SEGUNDO FOR: Reasignación de valores
            for (int i = 0; i < this.parametros.size(); i++) {
                var variable = newTabla.getVariable((String) this.parametros.get(i).get("id"));

                if (variable == null) {
                    return new Errores("Semantico", "El paramtro no existe: " + this.parametros.get(i).get("id"), 
                            this.linea, this.columna);
                }

                var valor = (Instruccion) this.parametros.get(i).get("expresion");
                var resValor = valor.interpretar(arbol, tabla);
                if (resValor instanceof Errores) {
                    return resValor;
                }

                // Validar tipos
                if (valor.tipo.getTipo() != variable.getTipo().getTipo()) {
                    return new Errores("Semantico", "El tipo del paramtro y su valor no coinciden", 
                            this.linea, this.columna);
                }

                variable.setValor(resValor);
            }

            // TERCER FOR: Validación final de parámetros
            for (int i = 0; i < metodo.parametros.size(); i++) {
                var identificador = (String) metodo.parametros.get(i).get("id");
                var resultado = newTabla.getVariable(identificador);
                if (resultado == null) {
                    return new Errores("SEMANTICO", "Parametro no declarado",
                            this.linea, this.columna);
                }

                if (resultado.getValor() == null) {
                    return new Errores("SEMANTICO", "Parametro sin valor declarado",
                            this.linea, this.columna);
                }
            }

            // Ejecutar el método/función
            var resultadoMetodo = metodo.interpretar(arbol, newTabla);

            //System.out.println("----- esto esta interpretando metodo (esto esta en clase llamada) ------>" + resultadoMetodo);
            if (resultadoMetodo instanceof Errores) {
                System.out.println("---> el Metodo es un error (instanceado desde clase llamada)");
                return resultadoMetodo;
            }

            // Si es una función (no void), debemos retornar el valor
            if (metodo.tipo.getTipo() != tipoDato.VOID) {
              //  System.out.println("-------> el tipo de metodo es: " + metodo.tipo.getTipo());
                this.tipo = metodo.tipo; // Actualizamos el tipo de la llamada

                // Validamos que el resultado no sea null
                if (resultadoMetodo == null) {
                    return new Errores("Semantico", "La función " + this.id + " no retornó ningún valor.", 
                            this.linea, this.columna);
                }

                return resultadoMetodo;
            }

            // Si es void, no se espera ningún valor
            return null;
        }

        return null;
    }
}