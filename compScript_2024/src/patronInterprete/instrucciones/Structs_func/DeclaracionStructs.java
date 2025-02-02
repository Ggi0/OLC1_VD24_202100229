/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Structs_func;

import java.util.HashMap;
import java.util.LinkedList;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Simbolo;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 * ===== declaracion de structs ======= struct <ID>{
 * <LISTA_STRUCT>
 * };
 *
 *
 *
 * struct <NOMBRE_STRUCT> {
 * <CAMPO_1>: <TIPO_DATO>;
 * <CAMPO_2>: <TIPO_DATO>; ... };
 *
 * esto será un HASHMAP
 * <CAMPO_1>: <TIPO_DATO>; donde:
 * <CAMPO_1> ---> es el id del "atributo" del struct
 * <TIPO_DATO> ---> es el tipo de este atributo
 * <Valor> ---> el valor por ser inicializado sera en null
 *
 * _struct
 *
 * @author gio
 */
public class DeclaracionStructs extends Instruccion {

    private String id; // id
    public LinkedList<HashMap> atributos; // listaHashMap

    public DeclaracionStructs(String id, LinkedList<HashMap> atributos, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.atributos = atributos;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
// Validar si el struct ya existe
        if (tabla.getVariable(this.id) != null) {
            return new Errores("Semántico", "El struct con ID '" + this.id + "' ya fue declarado.", this.linea, this.columna);
        }

        // Validar los atributos del struct
        for (HashMap<String, Object> atributo : this.atributos) {
            // Verificar que el atributo tenga "id" y "tipo"
            if (!atributo.containsKey("id") || !atributo.containsKey("tipo")) {
                return new Errores("Semántico", "Atributo sin identificador o tipo en el struct '" + this.id + "'.", this.linea, this.columna);
            }

            String idAtributo = atributo.get("id").toString();
            Object tipoAtributo = atributo.get("tipo");

            // Si el tipo es una cadena, es un struct anidado
            if (tipoAtributo instanceof String) {
                String nombreStruct = (String) tipoAtributo;
                Simbolo structRef = tabla.getVariable(nombreStruct);
                if (structRef == null) {
                    return new Errores("Semántico", "El struct '" + nombreStruct + "' usado en el atributo '" + idAtributo + "' no está definido.", this.linea, this.columna);
                }
                if (structRef.getTipo().getTipo() != tipoDato.STRUCT) {
                    return new Errores("Semántico", "El identificador '" + nombreStruct + "' no corresponde a un struct.", this.linea, this.columna);
                }
            } // Si no es String, debe ser un Tipo que viene de _tipos
            else if (!(tipoAtributo instanceof Tipo)) {
                return new Errores("Semántico", "Tipo de dato no válido para el atributo '" + idAtributo + "'.", this.linea, this.columna);
            }
        }

        // Actualizar el tipo a STRUCT antes de crear el símbolo
        this.tipo.setTipo(tipoDato.STRUCT);

        // Crear el struct como un símbolo y agregarlo a la tabla
        Simbolo simbolo = new Simbolo(this.tipo, this.id, this.atributos);

        if (!tabla.setVariable(simbolo)) {
            return new Errores("Semántico", "El struct con ID '" + this.id + "' no pudo ser registrado.", this.linea, this.columna);
        }
        
        System.out.println("El Struct: " + this.id + " de tipo " + this.tipo.getTipo() + " fue declarado correctamente");
        return null;
    }

}
