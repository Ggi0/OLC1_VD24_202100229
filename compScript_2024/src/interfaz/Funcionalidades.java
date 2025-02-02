/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Clase para las funciones basicas de un archivo
 * -> Nuevo Archivo
 * -> Abrir Archivo
 * -> Guardar Archivo
 * 
 * @author gio
 */
public class Funcionalidades {
    
    private static File archivoAbierto;

    /**
     * Para ABRIR un archivo .cs
     */
    public String nuevoArchivo() {
        JFileChooser fc = new JFileChooser();
        // Crear un filtro de archivos para archivos .cs
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos .cs", "cs");
        fc.setFileFilter(filter);

        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            archivoAbierto = fc.getSelectedFile(); // Guardar la referencia al archivo abierto
            StringBuilder contenido = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(archivoAbierto))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
            } catch (Exception e) {
                System.out.println("Error al leer el archivo: " + e);
            }
            JOptionPane.showMessageDialog(null, "Archivo cargado correctamente");
            return contenido.toString();
        } else {
            System.out.println("No se seleccionó ningún archivo.");
            return "";
        }
    }

    /**
     * -> Devuelve el nombre del archivo abierto.
     * 
     * @return El nombre del archivo abierto
     */
    public static String getNombreArchivoAbierto() {
        if (archivoAbierto != null) {
            return archivoAbierto.getName();
        } else {
            return "No hay archivo abierto";
        }
    }

    /**
     * Guarda el contenido en el archivo previamente abierto.
     * @param contenido El contenido a guardar en el archivo.cs
     */
    public static void guardar(String contenido) {
        if (archivoAbierto != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoAbierto))) {
                bw.write(contenido);
                System.out.println("Archivo guardado exitosamente.");
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e);
            }
        } else {
            System.out.println("No hay ningún archivo abierto para guardar.");
        }
    }

    /**
     *  Para GUARDAR un archivo.cs
     * @param contenido El contenido del textArea a guardar en el archivo
     */
    public static void guardarComo(String contenido) {
        JFileChooser fc = new JFileChooser();
        // Crear un filtro de archivos para archivos .ca
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos .cs", "cs", "txt");
        fc.setFileFilter(filter);
        
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            
            // Asegurarse de que la extensión del archivo sea .cs
            if (!archivo.getAbsolutePath().endsWith(".ca")) {
                archivo = new File(archivo + ".cs");
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                bw.write(contenido);
                System.out.println("Archivo guardado exitosamente.");
                
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e);
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }
    
}