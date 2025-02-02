/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones;

import patronInterprete.abstracto.Instruccion;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 * puede detener la ejecución de la iteración actual y saltar a la siguiente.
 *
 * @author gio
 */
public class Continuar extends Instruccion {

    public Continuar(int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // El continue se usa para saltar el resto de las instrucciones 
        // en la iteración actual y pasar a la siguiente
        return this; // Devuelve la instancia actual como señal de continue

    }

}
