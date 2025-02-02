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
 *  DECLARACION DE VECTORES DE 2 DIMENSIONES
 * 
 * <MUTABILIDAD> ID : tipo [][] = [<listaListas>];
 * let | const nombreVector : int [][] = [[4],[9,6,7],[7,8,9],[1,2,3]];
 * 
 * <MUTABILIDAD> = let | const 
 * ID -> el nombre del vector
 * tipo -> int, string, double ...
 * listaListas -> una lista con listas de valores 
 * 
 * 
 * como manejar listaListas:
 *      viene como [[4],[9,6,7],[7,8,9],[1,2,3]]
 * 
 * validar que cada valor dentro de las listas coincida con el valor del vector
 * 
 * 
 * @author gio
 */
public class DeclaracionV2 extends Instruccion {

    private String mutabilidad;                          // let | const
    private String id;                                   // Nombre del vector
    private LinkedList<LinkedList<Instruccion>> valores; // Lista de listas de valores (lista de vectores de 1 dimension)
    private boolean constante;                           // Indica si es constante o no

    // Constructor con valores iniciales
    public DeclaracionV2(String mutabilidad, String id, LinkedList<LinkedList<Instruccion>> valores, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.valores = valores;
        this.constante = mutabilidad.equals("const");
    }

    // Constructor sin valores iniciales
    public DeclaracionV2(String mutabilidad, String id, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.valores = null;
        this.constante = mutabilidad.equals("const");
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // logica para  declarar vectores de 2 dimensiones
        
        LinkedList<LinkedList<Object>> matriz = new LinkedList<>(); // Lista para almacenar los valores finales interpretados

        // 1) Validar si la lista de valores está vacía o es nula
        if (valores == null || valores.isEmpty()) {
            return new Errores("Semántico", "Los vectores de 2D no pueden ser inicializados vacios, deben tener un tamanio fijo. VECTOR " + this.id + " no inicializado", this.linea, this.columna);
        }

        // 2) Validar y procesar cada lista interna
        int longitudFila = -1; // Para verificar que todas las filas tengan la misma longitud
        for (LinkedList<Instruccion> fila : valores) {
            LinkedList<Object> filaInterpretada = new LinkedList<>();

            for (Instruccion valor : fila) {
                Object valorInterpretado = valor.interpretar(arbol, tabla);

                if (valorInterpretado instanceof Errores) {
                    return valorInterpretado;
                }

                if (valor.tipo.getTipo() != this.tipo.getTipo()) {
                    return new Errores("Semántico",
                            "El tipo del valor (" + valor.tipo.getTipo() + ") no coincide con el tipo del vector (" + this.tipo.getTipo() + ")",
                            this.linea, this.columna);
                }

                filaInterpretada.add(valorInterpretado);
            }

            // Validar que todas las filas tengan la misma longitud
            if (longitudFila == -1) {
                longitudFila = filaInterpretada.size(); // Asignar la longitud de la primera fila
            } else if (filaInterpretada.size() != longitudFila) {
                return new Errores("Semántico",
                        "Las filas del vector de 2 dimensiones deben tener la misma longitud. Se encontró una fila con longitud distinta.",
                        this.linea, this.columna);
            }

            matriz.add(filaInterpretada); // Agregar la fila interpretada a la matriz
        }

        // 3) Crear la variable (matriz)
        Simbolo simbolo = new Simbolo(this.tipo, this.id, matriz);
        simbolo.setConstante(this.constante); // validar si es constante
        simbolo.setEsVector(true);

        // 4) Validar si el vector ya existe en la tabla de símbolos
        if (tabla.setVariable(simbolo)) {
            System.out.println("Se declaró el vector de 2 dimensiones EXITOSAMENTE con valores");
            return null;
        }
        // Si la variable ya existe, devolver un error
        return new Errores("Semántico", "El vector: " + this.id + " ya fue declarado", this.linea, this.columna);
    }

}
