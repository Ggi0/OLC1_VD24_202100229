/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compscript_2024;
import interfaz.Ventana_principal;

/**
 *
 * @author gio
 */
public class main_CompScript {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        //System.out.println("hola esto es desde el main");
        
        // --------------- PARA VISUALIZAR LA INTERZAR ---------------
        
        Ventana_principal pantalla = new Ventana_principal();
        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
        
        // --------------- PRUEBAS ANALIZADORES ---------------
        /*
        try{
            String texto = """ 
                           // esto es un comentario
                           
                          console.log(\"esto es una prueba con el console.log\"); 
                           

                           console.log(2 + 2);
                           //console.log(2 + 2 + (3 + 6.2)); 
                           
                           
                           //console.log( 1.3 + (5.4 + 7.2) +6.3 );
                           //console.log(2+ (3 + (5+1.4)) + (4+3) );
                           //console.log(2-5 );
                           //console.log(5-5 );
                           //console.log(-5-5 );
                           //console.log(-5+5 );
                           console.log("hoAl" == "hoal");
                                                      
                         
            
            """;
            
            // Lexico lexer es una instancia del analizador léxico
            Lexico lexer = new Lexico(new BufferedReader(new StringReader(texto)));
            
            // Toma los tokens generados por el léxico y verifica 
            // si están organizados de acuerdo con las reglas del lenguaje (la gramática).
            Sintactico parser = new Sintactico(lexer);
            var resultado = parser.parse();
            
            // contiene la lista de instrucciones extraídas del texto.
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();

            for (var a : ast.getInstrucciones()) {
                var res = a.interpretar(ast, tabla);
                if (res instanceof Errores){
                    System.out.println(res.toString());
                }
                //System.out.println(res);
            }
            System.out.println(ast.getConsola());
            
            
        } catch (Exception e){
            System.out.println(e);
        }
        */
        
    }
    
}
