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
 *
 * match <expresion>{
 *      <listaCasos>
 *          <default>
 * 
 * }
 * 
 * match <expresionINICIAL>{
 *      <expresion1> => {
 *         <instrucciones>
 *      }
 *      <expresion2> => {
 *         <instrucciones>
 *      }
 *      <expresion4> =>{ 
 *         <instrucciones>
 *      }
 *      <default> => {
 *         <instrucciones>
 *      }
 * }
 * 
 * MATCH <expresionINICIAL> { <expresionN> => { <instrucciones> }  }
 * 
 * @author gio
 */
public class Match_stc extends Instruccion {

    private Instruccion expresionInicial; // La expresión del MATCH
    private LinkedList<Caso> casos; // Lista de casos

    public Match_stc(Instruccion expresionInicial, LinkedList<Caso> casos, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.expresionInicial = expresionInicial;
        this.casos = casos;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Interpretar la expresión inicial
        var valorInicial = expresionInicial.interpretar(arbol, tabla);

        // Validar si la expresión inicial es un error
        if (valorInicial instanceof Errores) {
            return valorInicial;
        }

        // Recorrer los casos para buscar una coincidencia
        for (Caso caso : casos) {
            // Si el caso es DEFAULT, se ejecuta al final si no hay coincidencias
            if (caso.getCondicion() == null) {
                continue;
            }

            // Evaluar la condición del caso actual
            var valorCondicion = caso.getCondicion().interpretar(arbol, tabla);

            // Validar si la condición del caso es un error
            if (valorCondicion instanceof Errores) {
                return valorCondicion;
            }

            // Verificar si la condición coincide con la expresión inicial
            if (valorInicial.equals(valorCondicion)) {
                // Ejecutar las instrucciones del caso
                return ejecutarInstrucciones(caso.getInstrucciones(), arbol, tabla);
            }
        }

        // Si no hubo coincidencias, ejecutar DEFAULT si existe
        for (Caso caso : casos) {
            if (caso.getCondicion() == null) {
                // Ejecutar las instrucciones del DEFAULT
                return ejecutarInstrucciones(caso.getInstrucciones(), arbol, tabla);
            }
        }

        // Si no hay DEFAULT y no hay coincidencias, no hacer nada
        return null;
    }

    // Método auxiliar para ejecutar las instrucciones de un caso
    private Object ejecutarInstrucciones(LinkedList<Instruccion> instrucciones, Arbol arbol, tablaSimbolos tabla) {
        var newTabla = new tablaSimbolos(tabla); // Crear un nuevo entorno
        for (var instruccion : instrucciones) {
            var resultado = instruccion.interpretar(arbol, newTabla);

            // Si hay errores, los añadimos al árbol
            if (resultado instanceof Errores) {
                arbol.AddErrores((Errores) resultado);
            }

            // Manejo de interrupciones (break, continue, etc.)
            if (resultado instanceof Break_stc || resultado instanceof Continuar) {
                return resultado;
            }
        }
        return null;
    }
}