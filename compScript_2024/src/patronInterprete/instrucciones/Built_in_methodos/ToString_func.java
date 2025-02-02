/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Built_in_methodos;

import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 * toString  ( <expresion> )
 * 
 * unicamente para tipos:
 *              numerico
 *              caracter 
 *              bool 
 *              struct // no validado aun
 * 
 * de lo contrario sera un error
 * @author gio
 */
public class ToString_func extends Instruccion {

    private String identificador; // Para manejar identificadores (ID)
    private Instruccion valorE;    // expresion (2+2 | 3.4 ) numericos

    // Constructor para expresiones
    public ToString_func(Instruccion valorE, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.valorE = valorE;
        this.identificador = null;
    }

    // Constructor para identificadores
    public ToString_func(String identificador, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.identificador = identificador;
        this.valorE = null;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
Object valor;
        
        // Caso 1: Si es un identificador (variable)
        if (this.identificador != null) {
            var variable = tabla.getVariable(this.identificador);
            if (variable == null) {
                return new Errores("Semántico", "La variable '" + this.identificador + "' no existe", 
                    this.linea, this.columna);
            }

            valor = variable.getValor();
            if (valor == null) {
                return new Errores("Semántico", "La variable '" + this.identificador + 
                    "' no tiene un valor asignado", this.linea, this.columna);
            }

            if (variable.isEsLista() || variable.isEsVector()) {
                return new Errores("Semántico", 
                    "No se puede convertir una lista o vector a cadena directamente", 
                    this.linea, this.columna);
            }

            // Marcar el tipo de retorno como CADENA
            this.tipo.setTipo(tipoDato.CADENA);

            // Realizar la conversión según el tipo
            return convertirACadena(valor, variable.getTipo().getTipo());
        }
        // Caso 2: Si es una expresión
        else {
            valor = this.valorE.interpretar(arbol, tabla);
            if (valor instanceof Errores) {
                return valor;
            }

            if (valor instanceof LinkedList) {
                return new Errores("Semántico", 
                    "No se puede convertir una lista o vector a cadena directamente", 
                    this.linea, this.columna);
            }

            // Marcar el tipo de retorno como CADENA
            this.tipo.setTipo(tipoDato.CADENA);

            // Realizar la conversión según el tipo de la expresión
            return convertirACadena(valor, this.valorE.tipo.getTipo());
        }
    }

    private Object convertirACadena(Object valor, tipoDato tipo) {
        try {
            switch (tipo) {
                case ENTERO:
                    if (valor instanceof Integer) {
                        return String.valueOf((int)valor);
                    } else if (valor instanceof String) {
                        // Intenta convertir la cadena a entero y luego de vuelta a cadena
                        return String.valueOf(Integer.parseInt((String)valor));
                    }
                    break;
                    
                case DECIMAL:
                    if (valor instanceof Double) {
                        return String.valueOf((double)valor);
                    } else if (valor instanceof String) {
                        // Intenta convertir la cadena a double y luego de vuelta a cadena
                        return String.valueOf(Double.parseDouble((String)valor));
                    }
                    break;
                    
                case BOOLEANO:
                    if (valor instanceof Boolean) {
                        return String.valueOf((boolean)valor);
                    } else if (valor instanceof String) {
                        // Intenta convertir la cadena a boolean y luego de vuelta a cadena
                        return String.valueOf(Boolean.parseBoolean((String)valor));
                    }
                    break;
                    
                case CARACTER:
                    if (valor instanceof Character) {
                        return String.valueOf((char)valor);
                    } else if (valor instanceof String) {
                        // Si es una cadena de un solo carácter, retornamos esa cadena
                        String str = (String)valor;
                        if (str.length() == 1) {
                            return str;
                        }
                    }
                    break;
                    
                case CADENA:
                    // Si ya es una cadena, la retornamos directamente
                    return valor.toString();
            }
            
            // Si llegamos aquí, hubo un problema con la conversión
            return new Errores("Semántico", 
                "No se puede convertir el valor al tipo cadena", 
                this.linea, this.columna);
            
        } catch (NumberFormatException e) {
            return new Errores("Semántico", 
                "Error al convertir el valor a cadena: " + e.getMessage(), 
                this.linea, this.columna);
        }
    }
    
}
