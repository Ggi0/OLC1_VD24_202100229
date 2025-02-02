/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.simbolo;

/**
 * manejo de tipos
 * @author gio
 */
public class Tipo {
    private tipoDato tipo;

    // ----- CONSTRUCTOR -----
    public Tipo(tipoDato tipo) {
        this.tipo = tipo;
    }
    
    // GETTERS AND SETTERS

    public tipoDato getTipo() {
        return tipo;
    }

    public void setTipo(tipoDato tipo) {
        this.tipo = tipo;
    }
    
    
}
