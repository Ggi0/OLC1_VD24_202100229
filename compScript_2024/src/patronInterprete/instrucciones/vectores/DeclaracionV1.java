/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.vectores;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Simbolo;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;

/**
 *  Mutabilida ID : tipo [] = [listaValores];
 * let vector1 : string[] = ["hola","este","es","un","vector de una dimesion"];
 * 
 * ------------ DECLARACION --------------------
 * _vector1 ::= MUTB ID COLON _tipos LBRACE RBRACE IGUAL LBRACE _listaValores RBRACE SEMICOLON
 *            | MUTB ID COLON _tipos LBRACE RBRACE SEMICOLON
 * 
 * Como manejar listaValores:
 * viene como:
 *      valor1, valor2, valor3 ...
 * 
 * _listaValores ::= expresion
 *                 | expresion COMMA _listaValores
 * 
 * dentro d ela clase DeclaracionV1 vericar que todos los valores del _listaValores sean del mismo tipo
 * 
 * @author gio
 */
public class DeclaracionV1 extends Instruccion {

    // atributos de un vector de una dimesion
    private String mutabilidad;              // let | const
    private String id;                       // identificador (nombre del vector)
    private LinkedList<Instruccion> valores; // lista de valores 
    private boolean constante;               // Indica si es constante o no

    // 2 constructores uno con valores, uno sin valores
    // mutab, id, lista valores (listaExpresiones)
    public DeclaracionV1(String mutabilidad, String id, LinkedList<Instruccion> valores, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.valores = valores;
        this.constante = mutabilidad.equals("const"); // Si es "const", constante = true    }
    }

    // mutab, id, sin valores inicializados
    public DeclaracionV1(String mutabilidad, String id, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.valores = null;
        this.constante = mutabilidad.equals("const"); // Si es "const", constante = true    }
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para declarar vectores de una dimension

        // 1) valores del vector
        LinkedList<Object> valoresVector1 = new LinkedList<>(); // aqui se almacenaran los valores del vector

        // Validar si la lista de valores está vacía o es nula
        if (valores == null || valores.isEmpty()) {
            return new Errores("Semántico", "Los vectores de 1D no pueden ser inicializados vacios, deben tener un tamanio fijo. VECTOR " + this.id + " no inicializado", this.linea, this.columna);
        }

        for (Instruccion valor : valores) {
            Object valorInterpretado = valor.interpretar(arbol, tabla);

            if (valorInterpretado instanceof Errores) {
                return valorInterpretado;
            }

            // 2) Validar que el tipo del valor interpretado sea compatible con el tipo del vector
            if (valor.tipo.getTipo() != this.tipo.getTipo()) {
                return new Errores("Semántico",
                        "El tipo del valor (" + valor.tipo.getTipo() + ") no coincide con el tipo del vector (" + this.tipo.getTipo() + ")",
                        this.linea, this.columna);
            }

            valoresVector1.add(valorInterpretado); // valor agregado a la lista del vector
        }

        // 3) es posible que el vector venga vacio
        if (valoresVector1.isEmpty()) {
            // Vector vacío: error?
        }

        // 4) crear la variable 
        Simbolo simbolo = new Simbolo(this.tipo, this.id, valoresVector1);
        simbolo.setConstante(this.constante); // verificar si la varible es constante
        simbolo.setEsVector(true);
        // 4) validar de el vector ya existe
        if (tabla.setVariable(simbolo)) {
            System.out.println("Se declaro el vector EXITOSAMENTE con valores");
            // si es TRUE la variable declarada correctamente
            return null;
        }
        return new Errores("Semantico", "El vector: " + this.id + " ya fue declarada", this.linea, this.columna);

    }

}
