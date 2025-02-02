/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronInterprete.instrucciones.Structs_func;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.Simbolo;
import patronInterprete.simbolo.Tipo;
import patronInterprete.simbolo.tablaSimbolos;
import patronInterprete.simbolo.tipoDato;

/**
 *
 * @author gio
 */
public class InstanciaSTRUCT extends Instruccion{
    private final String nombreStruct;        // Tipo de struct (estudiante, persona, etc)
    private final String nombreVariable;      // Nombre de la variable a crear
    private final HashMap<String, Instruccion> valoresAtributos;  // Valores para cada atributo
    private final boolean esMutable;          // true para 'var', false para 'const'
    private String entorno;                   // Entorno actual (GLOBAL, etc)
    
        // Constructor para declaración completa: var/const id: tipo = { ... }
    public InstanciaSTRUCT(String nombreStruct, String nombreVariable, 
            HashMap<String, Instruccion> valoresAtributos, boolean esMutable, 
            int linea, int columna) {
        super(new Tipo(tipoDato.STRUCT), linea, columna);
        this.nombreStruct = nombreStruct;
        this.nombreVariable = nombreVariable;
        this.valoresAtributos = valoresAtributos;
        this.esMutable = esMutable;
        this.entorno = "GLOBAL";
    }

    // Constructor para struct anónimo: { ... }
    public InstanciaSTRUCT(HashMap<String, Instruccion> valoresAtributos, int linea, int columna) {
        super(new Tipo(tipoDato.STRUCT), linea, columna);
        this.nombreStruct = null;
        this.nombreVariable = null;
        this.valoresAtributos = valoresAtributos;
        this.esMutable = true;
        this.entorno = "GLOBAL";
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
return null;
        /*
        // logica para instanciar structs
    System.out.println("\n=== Iniciando interpretación de instancia de struct ===");
        
        // Si es un struct anónimo, solo retornamos los valores
        if (nombreStruct == null && nombreVariable == null) {
            System.out.println("Procesando struct anónimo para struct anidado");
            return this.valoresAtributos;
        }

        // Buscar la definición del struct
        var structDefinicion = arbol.getVariable(nombreStruct);
        if (structDefinicion == null) {
            return new Errores("Semántico", 
                "No se encontró la definición del struct '" + nombreStruct + "'", 
                this.linea, this.columna);
        }

        System.out.println("Struct encontrado: " + nombreStruct);

        // Validar que sea una definición de struct
        if (!(structDefinicion instanceof DeclaracionStructs)) {
            return new Errores("Semántico", 
                "El identificador '" + nombreStruct + "' no corresponde a un struct", 
                this.linea, this.columna);
        }

        DeclaracionStructs definicionStruct = (DeclaracionStructs) structDefinicion;

        // Validar cantidad de atributos
        if (definicionStruct.atributos.size() != this.valoresAtributos.size()) {
            return new Errores("Semántico", 
                "Cantidad incorrecta de atributos. Esperados: " + 
                definicionStruct.atributos.size() + ", Recibidos: " + 
                this.valoresAtributos.size(), 
                this.linea, this.columna);
        }

        System.out.println("Validando nombres de atributos...");
        
        // Validar nombres de atributos
        Set<String> atributosDefinidos = new HashSet<>();
        Set<String> atributosProporcionados = this.valoresAtributos.keySet();

        for (HashMap<String, Object> atributo : definicionStruct.atributos) {
            atributosDefinidos.add(atributo.get("id").toString());
        }

        if (!atributosDefinidos.equals(atributosProporcionados)) {
            return new Errores("Semántico", 
                "Los nombres de los atributos no coinciden con la definición", 
                this.linea, this.columna);
        }

        // Crear nueva instancia con valores
        LinkedList<HashMap> valoresFinales = new LinkedList<>();
        
        System.out.println("Procesando valores de atributos...");

        // Procesar cada atributo
        for (int i = 0; i < definicionStruct.atributos.size(); i++) {
            HashMap<String, Object> atributoActual = definicionStruct.atributos.get(i);
            String idAtributo = atributoActual.get("id").toString();
            
            System.out.println("Procesando atributo: " + idAtributo);

            // Interpretar el valor proporcionado
            var valorProporcionado = this.valoresAtributos.get(idAtributo)
                .interpretar(arbol, tabla);

            if (valorProporcionado instanceof Errores) {
                return valorProporcionado;
            }

            // Copiar atributo y actualizar su valor
            HashMap<String, Object> nuevoAtributo = new HashMap<>(atributoActual);
            
            // Validar tipo del valor
            Tipo tipoEsperado = (Tipo) atributoActual.get("tipo");
            Tipo tipoProporcionado = this.valoresAtributos.get(idAtributo).tipo;

            if (!tipoEsperado.getTipo().equals(tipoProporcionado.getTipo())) {
                return new Errores("Semántico", 
                    "Tipo incorrecto para el atributo '" + idAtributo + 
                    "'. Esperado: " + tipoEsperado.getTipo() + 
                    ", Recibido: " + tipoProporcionado.getTipo(), 
                    this.linea, this.columna);
            }

            nuevoAtributo.put("valor", valorProporcionado);
            valoresFinales.add(nuevoAtributo);
        }

        System.out.println("Creando símbolo para la nueva instancia...");
        
        // Crear símbolo para la nueva instancia
        Simbolo nuevaInstancia = new Simbolo(this.tipo, this.nombreVariable, valoresFinales);

        // Agregar a la tabla de símbolos
        if (!tabla.setVariable(nuevaInstancia)) {
            return new Errores("Semántico", 
                "La variable '" + this.nombreVariable + "' ya existe", 
                this.linea, this.columna);
        }

        System.out.println("Instancia de struct creada exitosamente: " + this.nombreVariable);
        return null;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }
    */
    }
    
    
}
