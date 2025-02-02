/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import analizadores.Lexico;
import analizadores.Sintactico;
import java.io.StringReader;
import static interfaz.Funcionalidades.*;
import java.io.BufferedReader;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import patronInterprete.abstracto.Instruccion;
import patronInterprete.excepciones.Errores;
import patronInterprete.instrucciones.Declaracion;
import patronInterprete.instrucciones.Listas.DeclaracionLista;
import patronInterprete.instrucciones.Metodo;
import patronInterprete.instrucciones.Run_Main;
import patronInterprete.instrucciones.Structs_func.DeclaracionStructs;
import patronInterprete.instrucciones.vectores.DeclaracionV1;
import patronInterprete.instrucciones.vectores.DeclaracionV2;
import patronInterprete.simbolo.Arbol;
import patronInterprete.simbolo.tablaSimbolos;

/**
 *
 * @author gio
 */
public class Ventana_principal extends javax.swing.JFrame {
    
    private LinkedList<Errores> listaErrores;

    /**
     * Creates new form Ventana_principal
     */
    public Ventana_principal() {
        initComponents();
        //[255,204,153]
        this.getContentPane().setBackground(new java.awt.Color(153, 204, 255)); // Color personalizado
        this.listaErrores = new LinkedList<>();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxt_entrada = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxt_consola = new javax.swing.JTextArea();
        btt_ejecutar = new javax.swing.JButton();
        btt_reportes = new javax.swing.JButton();
        cbx_archivo = new javax.swing.JComboBox<>();
        jlb_name = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Comp Script");
        setBackground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));

        jLabel1.setFont(new java.awt.Font("Kohinoor Bangla", 3, 18)); // NOI18N
        jLabel1.setText("Entrada:");

        jtxt_entrada.setColumns(20);
        jtxt_entrada.setFont(new java.awt.Font("Noto Nastaliq Urdu", 0, 14)); // NOI18N
        jtxt_entrada.setRows(5);
        jtxt_entrada.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jScrollPane1.setViewportView(jtxt_entrada);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 153));

        jtxt_consola.setEditable(false);
        jtxt_consola.setBackground(new java.awt.Color(255, 239, 219));
        jtxt_consola.setColumns(20);
        jtxt_consola.setFont(new java.awt.Font("Noto Nastaliq Urdu", 0, 14)); // NOI18N
        jtxt_consola.setRows(5);
        jtxt_consola.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Consola", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Kohinoor Bangla", 3, 18))); // NOI18N
        jScrollPane2.setViewportView(jtxt_consola);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        btt_ejecutar.setFont(new java.awt.Font("Kohinoor Bangla", 1, 24)); // NOI18N
        btt_ejecutar.setText("Ejecutar");
        btt_ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_ejecutarActionPerformed(evt);
            }
        });

        btt_reportes.setFont(new java.awt.Font("Kohinoor Bangla", 1, 24)); // NOI18N
        btt_reportes.setText("Reportes");
        btt_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_reportesActionPerformed(evt);
            }
        });

        cbx_archivo.setFont(new java.awt.Font("Kohinoor Bangla", 1, 20)); // NOI18N
        cbx_archivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nuevo archivo", "Abrir archivo", "Guardar archivo" }));
        cbx_archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_archivoActionPerformed(evt);
            }
        });

        jlb_name.setFont(new java.awt.Font("Kohinoor Bangla", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbx_archivo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_reportes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btt_ejecutar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btt_reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_archivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btt_ejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btt_ejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_ejecutarActionPerformed
        // Iniciar procesos de los analizadores:
        this.listaErrores = new LinkedList<>();
        System.out.println("EJECUTAR");
        
        try{
             // 1) tomar la información de jtxt_entrada
            String texto = jtxt_entrada.getText();
            jtxt_consola.setText("");
            
            
            // 2) pasar la información al analizador
            
            // Lexico lexer es una instancia del analizador léxico
            Lexico lexer = new Lexico(new BufferedReader(new StringReader(texto)));
            
            // Toma los tokens generados por el léxico y verifica 
            // si están organizados de acuerdo con las reglas del lenguaje (la gramática).
            Sintactico parser = new Sintactico(lexer);
            var resultado = parser.parse();
            
            this.listaErrores.addAll(lexer.erroresLexicos);
            this.listaErrores.addAll(parser.erroresSintacticos);
            
            for (var e: parser.erroresSintacticos){
                System.out.println(e.toString());
                
            }
            
            for (var l : lexer.erroresLexicos){
                System.out.println(l.toString());
            }
            
            // contiene la lista de instrucciones extraídas del texto.
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            // por la razon de que se puede llamar metodos y funciones antes de declararse
            // se realizarn 3 recorridos al ast
            // 1) almacenar fuciones, metodos y structs
            // 2) declaraciones y asignaciones
            // 3) funcion MAIN
            
            ast.setTablaGlobal(tabla); // almacenamiento de tabla global

            /*
            Primer recorrido: El objetivo es asegurar que todos los métodos, 
                              funciones y structs estén disponibles en la tabla de símbolos 
                              antes de intentar ejecutar cualquier código que los utilice.
            */
            // 1er recorrido en el ast
            for (var a : ast.getInstrucciones()) {
                
                if (a == null) {
                    continue;
                }
                
                if (a instanceof Metodo) { // metodos, funciones, structs
                    System.out.println(">>>>> se encontro un metodo <<<<<<");
                    ast.addFunciones(a);
                }
                 
                
                /*var res = a.interpretar(ast, tabla);
                if (res instanceof Errores){
                    System.out.println(res.toString());
                    listaErrores.add((Errores) res);
                }*/
                
                //System.out.println(res);
            }
            
            /*
            Segundo recorrido: En esta fase se garantiza que las declaraciones de variables 
                               y sus asignaciones sean correctas, lo que asegura que el código  
                               tiene un contexto bien definido para las operaciones posteriores.
            */
            
            // 2do recorrido en el ast
            for(var a : ast.getInstrucciones()){
                if (a instanceof Declaracion){
                    System.out.println("entra aqui? ----> variable");
                    var res = a.interpretar(ast, tabla);
                    if(res instanceof Errores){
                        ast.AddErrores((Errores)res);
                        
                    }         
                }
                if (a instanceof DeclaracionLista){
                    System.out.println("entra aqui? ----> lista");
                    var res = a.interpretar(ast, tabla);
                    if(res instanceof Errores){
                        ast.AddErrores((Errores)res);
                        
                    }         
                }
                if (a instanceof DeclaracionV1 || a instanceof DeclaracionV2){
                    System.out.println("entra aqui? ---> v");
                    var res = a.interpretar(ast, tabla);
                    if(res instanceof Errores){
                        ast.AddErrores((Errores)res);
                        
                    }         
                }
                if (a instanceof DeclaracionStructs){
                    System.out.println("entra aqui? ----> Struct");
                    var res = a.interpretar(ast, tabla);
                    if(res instanceof Errores){
                        ast.AddErrores((Errores)res);
                        
                    }         
                }
                /*if (a instanceof DeclaracionV2){
                    System.out.println("entra aqui? -----> v2");
                    var res = a.interpretar(ast, tabla);
                    if(res instanceof Errores){
                        ast.AddErrores((Errores)res);
                        
                    }         
                }*/
                
            }
            
            /*
            Tercer recorrido: Aquí finalmente se ejecuta el método main,
                              donde se interpreta el cuerpo del programa 
                              y se realizan todas las operaciones.
            */
            
            // 3er recorrido en el ast (main)
            for (var a : ast.getInstrucciones()){
                if (a instanceof Run_Main){
                    var res = a.interpretar(ast, tabla);
                    
                    if (res instanceof Errores){                    
                        System.out.println(">>>>> se encontro el METODO MAIN (ultimo recorrido del ast) <<<<<<");
                        ast.AddErrores((Errores) res);
                    }
                    
                    break;
                    
                }
            }
                
            
            
            
            listaErrores.addAll(ast.getErrores());
            
            // 3) pasar la información de ast.getConsola() a la consola jtxt_consola
            System.out.println(ast.getConsola());
            // Obtener el resultado de la consola y pasarlo al JTextArea
            String consolaSalida = ast.getConsola();
            jtxt_consola.setText(consolaSalida); // Aquí pasamos el resultado a la consola

            
            
        } catch (Exception e){
            System.out.println(e);
        }
 
        
    }//GEN-LAST:event_btt_ejecutarActionPerformed

    private void btt_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_reportesActionPerformed
        // TODO add your handling code here:
        System.out.println("REPOTES");
        
        Ventana_reportes re = new Ventana_reportes(listaErrores);
        re.setVisible(true);
    }//GEN-LAST:event_btt_reportesActionPerformed

    private void cbx_archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_archivoActionPerformed
        // TODO add your handling code here:

        Funcionalidades accionArchivo = new Funcionalidades();
        String funcion = cbx_archivo.getSelectedItem().toString();
        System.out.println("Selecionaste: " + funcion);
        
        switch (funcion) {
            case "Nuevo archivo":
                // Verificar si hay texto en el área de entrada
                if (!jtxt_entrada.getText().isEmpty()) {
                    int opcion = JOptionPane.showConfirmDialog(
                        null, 
                        "¿Desea guardar el archivo actual?", 
                        "Nuevo archivo", 
                        JOptionPane.YES_NO_CANCEL_OPTION
                    );

                    // Manejar las opciones del diálogo
                    switch (opcion) {
                        case JOptionPane.YES_OPTION:
                            System.out.println("Seleccionó: Sí");
                            // Guardar el archivo actual
                            String archivoGuardar = jtxt_entrada.getText();
                            guardarComo(archivoGuardar);
                            break;
                        case JOptionPane.NO_OPTION:
                            System.out.println("Seleccionó: No");
                            // Continuar sin guardar
                            break;
                        case JOptionPane.CANCEL_OPTION:
                            System.out.println("Seleccionó: Cancelar");
                            // Cancelar la operación y salir del caso
                            return;
                    }
                }
                // Vaciar el campo de texto
                jtxt_entrada.setText("");
                System.out.println("Nuevo archivo creado");
                break;

            case "Abrir archivo":
                // metodo para ABRIR UN ARCHIVO ya existente
                String contenido = accionArchivo.nuevoArchivo();
                jtxt_entrada.setText(contenido);
                
                // Actualizar el nombre del archivo en el label
                jlb_name.setText(getNombreArchivoAbierto());
                break;

            case "Guardar archivo":
                // metodo para GUARDAR un archivo
                System.out.println("Guardar archivo");
                String archivoGuardarComo = jtxt_entrada.getText();
                guardarComo(archivoGuardarComo);
                break;

            default:
                System.out.println("Opcion invalida");
        }
    }//GEN-LAST:event_cbx_archivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btt_ejecutar;
    private javax.swing.JButton btt_reportes;
    private javax.swing.JComboBox<String> cbx_archivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlb_name;
    private javax.swing.JTextArea jtxt_consola;
    private javax.swing.JTextArea jtxt_entrada;
    // End of variables declaration//GEN-END:variables
}
