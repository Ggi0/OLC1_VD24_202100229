/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones;

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
public class Return_stc extends Instruccion {

    private Instruccion valorRetorno;

    public Return_stc(Instruccion valorRetorno, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.valorRetorno = valorRetorno;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
    if (valorRetorno != null) {
      //  System.out.println("-- en el retorno SI hay un valor --");
        Object valor = valorRetorno.interpretar(arbol, tabla);
        if (valor instanceof Errores) {
           // System.out.println("-- el valor es un error :( --");
            return valor;
        }
        this.tipo = valorRetorno.tipo;
       // System.out.print("Este mensaje es desde la clase Return_stc: ");
       // System.out.println("el tipo del valor "+ valor + " es " + this.tipo.getTipo());
        
        /*
        En la clase Return_stc, ahora retornamos this en lugar del valor interpretado. 
        Esto permite que el objeto Return_stc llegue hasta la clase Metodo 
        con el valor de retorno intacto.
        */
        
        return this; // Cambiado de return valor a return this
    }
    return this;
}

    public Instruccion getValorRetorno() {
        //System.out.println("el valor de REtorno es: "+ valorRetorno );
        return valorRetorno;
    }

}
