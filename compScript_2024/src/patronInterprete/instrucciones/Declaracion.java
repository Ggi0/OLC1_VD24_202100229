/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones;

import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Simbolo;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;

/**
 * MUTABILIDAD <ID> : <TIPO> ; 
 * MUTABILIDAD <ID> : <TIPO> = <EXPRESION> ;
 *
 * MUTABILIDA -> LET | CONST
 *
 * <TIPO> -> int 
 *          | double 
 *          | bool 
 *          | char 
 *          | string
 * 
 * @author gio
 */
public class Declaracion extends Instruccion {

    // atributos
    private String identificador; // Nombre de la variable
    private String mutabilidad;   // "let" o "const"
    private Instruccion valor;    // expresion
    private boolean constante;    // Indica si es constante o no

    // parametros -> MUTABILIDAD, ID, EXPR, TIPO, LINEA, COLUM
    public Declaracion(String mutabilidad, String identificador, Instruccion valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        this.valor = valor;
        this.constante = mutabilidad.equals("const"); // Si es "const", constante = true
    }
    
    // Constructor para declaración SIN valor inicial
    public Declaracion(String mutabilidad, String identificador, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        this.valor = null; // No hay valor inicial
        this.constante = mutabilidad.equals("const"); // Si es "const", constante = true
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // 1) validar el valor 
        //var valorInterpretado = this.valor.interpretar(arbol, tabla);
        
        // Si no tiene valor inicial, asignar un valor por defecto según el tipo
        Object valorInterpretado = null;
        if (this.valor != null) {
            valorInterpretado = this.valor.interpretar(arbol, tabla);
            if (valorInterpretado instanceof Errores) {
                return valorInterpretado;
            }
        } else {
            // Asignar un valor por defecto según el tipo de dato
            switch (this.tipo.getTipo()) {
                case ENTERO:
                    valorInterpretado = 0;
                    break;
                case DECIMAL:
                    valorInterpretado = 0.0;
                    break;
                case BOOLEANO:
                    valorInterpretado = true;
                    break;
                case CARACTER:
                    valorInterpretado = '\u0000'; // Carácter nulo
                    break;
                case CADENA:
                    valorInterpretado = ""; // Cadena vacía
                    break;
                default:
                    return new Errores("Semántico", "Tipo no soportado para asignación por defecto", this.linea, this.columna);
            }
        }

        
        // 2)  validar el tipo 
        //          -> el tipo que entra en el construcctor debe ser igual al de valorInterpretado
        if(this.valor != null && this.valor.tipo.getTipo() != this.tipo.getTipo()){
            //si son diferente es un ERROR
            return new Errores("Semantico", "Asignación incorrecta: tipos incompatibles", this.linea, this.columna);
        }
        
        
        Simbolo simbolo = new Simbolo(this.tipo, this.identificador, valorInterpretado);
        simbolo.setConstante(this.constante);
        
        // 3) validar si la varible ya existe y Declara la Variable
        if (tabla.setVariable(simbolo)){
            // (true) varible declarada correctamente 
            return null;
        }
        
        return new Errores("Semantico", "La variable: " + this.identificador + " ya fue declarada", this.linea, this.columna);
        
        

    }

}
