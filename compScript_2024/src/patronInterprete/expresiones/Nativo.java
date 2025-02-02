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
 *
 * @author gio
 */
public class Nativo extends Instruccion {

    public Object valor;

    public Nativo(Object valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.valor = valor;

    }

    private String procesarSecuenciasDeEscape(String strValor) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < strValor.length(); i++) {
            if (strValor.charAt(i) == '\\' && i + 1 < strValor.length()) {
                char siguienteChar = strValor.charAt(i + 1);
                switch (siguienteChar) {
                    case 'n':
                        resultado.append('\n');
                        i++;
                        break;
                    case 't':
                        resultado.append('\t');
                        i++;
                        break;
                    case '"':
                        resultado.append('"');
                        i++;
                        break;
                    case '\\':
                        resultado.append('\\');
                        i++;
                        break;
                    case '\'':
                        resultado.append('\'');
                        i++;
                        break;
                    default:
                        resultado.append(strValor.charAt(i));
                }
            } else {
                resultado.append(strValor.charAt(i));
            }
        }
        return resultado.toString();
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Validar si el tipo es BOOLEANO y convertir el valor a un booleano real
        if (this.tipo.getTipo() == tipoDato.BOOLEANO) {
            if (this.valor.toString().equals("true")) {
                return true;
            } else if (this.valor.toString().equals("false")) {
                return false;
            } else {
                // Manejar un caso inesperado con un error semántico
                return new Errores("Semántico", "Valor no válido para booleano: " + this.valor, this.linea, this.columna);
            }
        }

        // Procesar el valor si el tipo es CARACTER
        if (this.tipo.getTipo() == tipoDato.CARACTER && this.valor instanceof String) {
            String strValor = (String) this.valor;

            // Manejar secuencias de escape
            String procesado = procesarSecuenciasDeEscape(strValor);

            // Verificar que la longitud sea 1 después del procesamiento
            if (procesado.length() != 1) {
                return new Errores("Semántico", "El carácter debe tener longitud 1: " + strValor, this.linea, this.columna);
            }

            // Actualizar el valor procesado
            this.valor = procesado;
            return this.valor;
        }

        // Procesar secuencias de escape para CADENA
        if (this.tipo.getTipo() == tipoDato.CADENA && this.valor instanceof String) {
            String strValor = (String) this.valor;
            // Procesar secuencias de escape para cadenas
            String procesado = procesarSecuenciasDeEscape(strValor);
            // Actualizar el valor procesado
            this.valor = procesado;
            return this.valor;
        }

        // Para otros tipos, devolver el valor tal como está
        return this.valor;
    }

}
