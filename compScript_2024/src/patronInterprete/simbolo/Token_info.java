/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.simbolo;

import java.util.ArrayList;

/**
 *
 * @author gio
 */
public class Token_info {
    // El lexema es la representación textual del token (por ejemplo, una palabra o símbolo).
    private String lexema;
    // El tipo de token, indicando qué representa en el lenguaje (por ejemplo, "IDENTIFICADOR" o "OPERADOR").
    private String token;
    // La línea en el archivo donde se encuentra el token.
    private int linea;
    // La columna en la línea donde comienza el token.
    private int columna;
    // Lista de tokens relacionados, útil si el token está compuesto por otros sub-tokens.
    private final ArrayList<Token_info> tokens;

    public Token_info(String lexema, String token, int linea, int columna) {
        this.lexema = lexema;
        this.token = token;
        this.linea = linea;
        this.columna = columna;
        this.tokens = new ArrayList<>();
    }

    public String getLexema() {
        return lexema;
    }

    public String getToken() {
        return token;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public ArrayList<Token_info> getTokens() {
        return tokens;
    }
    
    
    
    
    
}
