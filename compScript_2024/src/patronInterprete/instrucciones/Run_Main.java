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
 *
 * run_main ID () ; run_main ID ( <PARAMETROS > ) ;
 *
 * los parametros vienen como: id = expr
 * 
 * 
 * Verifica si el método existe: 
 *            Busca el método en el árbol de funciones.
 * 
 * Validación de parámetros: 
 *            Verifica si los parámetros proporcionados en la llamada al 
 *            método coinciden en tipo con los parámetros definidos en el método.
 * 
 * Crea una nueva tabla de símbolos: 
 *            Cada vez que se llama un método, se crea un nuevo entorno (tabla de símbolos) 
 *            para gestionar las variables y parámetros de ese método.
 * 
 * Ejecuta el método: 
 *            Una vez que los parámetros son validados, se interpreta el método 
 *            y se ejecutan sus instrucciones.
 *
 * @author gio
 */
public class Run_Main extends Instruccion {

    private String id;
    private LinkedList<HashMap> parametros;

    // construcctor
    public Run_Main(String id, LinkedList<HashMap> parametros, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para manejar el RUIN MAIN 

        // 1) verificar si existe un UNA funcion con el ID
        var busqueda = arbol.getFuncion(id);
        if (busqueda == null) {
            return new Errores("Semantico", "No existe una funcion con el nombre: " + this.id, this.linea, this.columna);
        }

        // 2) validar si el ID es un METODO
        if (busqueda instanceof Metodo metodo) {
            // 3) nueva tabla de simbolos (entorno) para el metodo
            var newTabla = new tablaSimbolos(arbol.getTablaGlobal()); // tambien puede ser tabla
            newTabla.setNombre("MAIN" + this.id);

            // estrictamente los parametros no deben medir lo mismo
            // declaracion de paramtros
            
            /*
            clase Metodo -> parametros
            int miFuncion(int a = 10, int b){
            ...
            }
            
            [
                {
                    id: <String>,            // a
                    tipo:  <Tipo>,           // int 
                    expresion: <Instruccion> // 10
                },
                {
                    id: <String>,   // b
                    tipo:  <Tipo>,  // int
                    expresion: null
                }
            ]
             */
            
            for (int i = 0; i < metodo.parametros.size(); i++) { // recorrer los paramtros de la lista
                var identificador = (String) metodo.parametros.get(i).get("id");
                var tipo = (Tipo) metodo.parametros.get(i).get("tipo");
                var expresion = (Instruccion) metodo.parametros.get(i).get("expresion");

                Object valExp = null;

                // validar expresion 
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

                // si no se declaro el parametro
                if (!newTabla.setVariable(new Simbolo(tipo, identificador, valExp))) {
                    return new Errores("Semantico", "El parametro no se declaro", this.linea, this.columna);
                }

                // parametro ya declarado -----> 
            }

            /*
                RUIN_MAIN miFuncion(parametro1=10, parametro2=20);
                clase Execute -> parametros  
                [
                    {
                        id: <String>,            // paramtro1
                        expresion: <Instruccion> //10
                    },
                    {
                        id: <String>,            // paramatro2
                        expresion: <Instruccion> // 20
                    }
                ]
            */
            // reasignar el valor de los paramtros
            /*
                el run_main -> tiliza el entorno gobla (variables, listas, vectores, structs declarados goblamente)
                se interpreta con newTabla -> un entorno con acceso al gloval y al de los parametros
                
            */
            for (int i = 0; i < this.parametros.size(); i++) {
                var variable = newTabla.getVariable((String) this.parametros.get(i).get("id"));

                if (variable == null) {
                    return new Errores("Semantico", "El parametro no existe" + (String) variable.getId(), this.linea, this.columna);

                }

                var valor = (Instruccion) this.parametros.get(i).get("expresion");
                var resValor = valor.interpretar(arbol, newTabla);
                if (resValor instanceof Errores) {
                    return resValor;
                }

                // validar tipos:
                if (valor.tipo.getTipo() != variable.getTipo().getTipo()) {
                    return new Errores("Semantico", "El tipo del paramtro y su valor no coinciden", this.linea, this.columna);
                }

                variable.setValor(resValor);

            }

            // valida que no hayan parametros con valor null
            for (int i = 0; i < metodo.parametros.size(); i++) {
                var identificador = (String) metodo.parametros.get(i).get("id");
                var resultado = newTabla.getVariable(identificador);
                if (resultado == null) {
                    return new Errores("SEMANTICO", "Parametro no declarado",
                            this.linea, this.columna);
                }

                // si resultano sin VALOR
                if (resultado.getValor() == null) {
                    return new Errores("SEMANTICO", "Parametro sin valor declarado",
                            this.linea, this.columna);
                }

            }

            var resultadoMetodo = metodo.interpretar(arbol, newTabla);
            if (resultadoMetodo instanceof Errores) {
                return resultadoMetodo;
            }

        }
        return null;

    }

}
