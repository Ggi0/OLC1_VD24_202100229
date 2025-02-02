#!/bin/bash

# Ejecutar CUP
java -jar /Users/gio/Desktop/compi1_V2S24/LAB_compi1_2S24V/proyecto/olc1_vd24_202100229/compScript_2024/lib/java-cup-11b.jar -parser Sintactico sintactico.cup

# Ejecutar JFlex
java -jar /Users/gio/Desktop/compi1_V2S24/LAB_compi1_2S24V/proyecto/olc1_vd24_202100229/compScript_2024/lib/jflex-full-1.9.1.jar lexico.jflex