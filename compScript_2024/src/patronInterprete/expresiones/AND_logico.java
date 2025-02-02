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
 * E -> E && E
 *
 * Compara expresiones lógicas y si ambas son verdaderas, devuelve verdadero; en
 * caso contrario, retorna falso.
 *
 * @author gio
 */
public class AND_logico extends Instruccion {

    // Atributos: expresiones izquierda (bool) y derecha (bool)
    private Instruccion expIzq;
    private Instruccion expDer;

    public AND_logico(Instruccion expIzq, Instruccion expDer, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.expIzq = expIzq;
        this.expDer = expDer;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
// Interpretar la expresión izquierda
        var res1 = this.expIzq.interpretar(arbol, tabla);
        if (res1 instanceof Errores) {
            return res1; // Si hay un error, retornarlo
        }

        // Interpretar la expresión derecha
        var res2 = this.expDer.interpretar(arbol, tabla);
        if (res2 instanceof Errores) {
            return res2; // Si hay un error, retornarlo
        }

        // Validar que los resultados sean de tipo BOOLEANO
        var tipo1 = this.expIzq.tipo.getTipo();
        var tipo2 = this.expDer.tipo.getTipo();

        switch (tipo1) {
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        // Realizar operación lógica AND
                        return (boolean) res1 && (boolean) res2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Operación && inválida: el segundo operando no es booleano", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Operación && inválida: el primer operando no es booleano", this.linea, this.columna);
            }
        }
    }

}
