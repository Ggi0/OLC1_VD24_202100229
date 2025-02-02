/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.simbolo;

import java.util.HashMap;

/**
 *
 * @author gio
 */
public class tablaSimbolos {

    private HashMap<String, Simbolo> tablaActual;
    private String nombre;
    private tablaSimbolos tablaAnterior; // ver la tabla del padre en donde estamos trabajando

    // primer construcctor sin tabla anterior
    public tablaSimbolos() {
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    // 2do constructor con tabla anterior
    public tablaSimbolos(tablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior; // acceso a la tabla padre
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public HashMap<String, Simbolo> getTablaActual() {
        return tablaActual;
    }

    public void setTablaActual(HashMap<String, Simbolo> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public tablaSimbolos getTablaAnterior() {
        return tablaAnterior;
    }

    public void setTablaAnterior(tablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
    }

    // es este metodo recibira un atributo Simbolo de la tabla de simbolos:
    public boolean setVariable(Simbolo simbolo) {
        //                            -> en la tabla actual tenes este id ignorando las mayusculas
        Simbolo busqueda = (Simbolo) this.tablaActual.get(simbolo.getId().toLowerCase());

        // para no almacenar simbolos repetidos
        if (busqueda == null) {
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
            // para saber si se inserto
            return true;
        }
        // no se inserto
        return false;
    }

    // con este metodo se accedera al atributo Simbolo de la tabla de simbolos:
    public Simbolo getVariable(String id) {
        //buscando la variable:
        //System.out.println("-> esta buscando la variable");

        // el ciclo empieza en la tabla actual, y se actualiza yendonos a la tabla anterior
        for (tablaSimbolos i = this; i != null; i = i.getTablaAnterior()) {
            //System.out.println("-> esta buscando la variable dentro del for");

            // 1) buscar el simbolo
            Simbolo busqueda = (Simbolo) i.getTablaActual().get(id.toLowerCase());

            // si no es null ya existe
            if (busqueda != null) {
                //System.out.println("-> Si existe la varible");

                // si el simbolo existe lo retornamos
                return busqueda; // simbolo

            }

        }
        //System.out.println("-> NO existe la varible");

        return null;

    }

}
