/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.excepciones;

/**
 * clase que almacena los errores
 *      -> tipo de error
 *      -> descripcion
 *      -> linea y columana
 * 
 * para alamacenar el error bajo un mismo estandar
 * 
 * @author gio
 */
public class Errores {
    private String tipo;
    private String descripcion;
    private int linea;
    private int columna;

    
    // ----- CONSTRUCTOR -----
    public Errores(String tipo, String descripcion, int linea, int columna) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Errores{" + "tipo=" + tipo + ", descripcion=" + descripcion + ", linea=" + linea + ", columna=" + columna + '}';
    }
    
    public Object[] getError(){
        return new Object[]{0, this.tipo, this.descripcion, this.linea, this.columna};
    }
    
    
    
}
