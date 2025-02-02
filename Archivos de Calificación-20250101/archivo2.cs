
let numeros: int[] = [91, 10, 77, 24, 96, 79, 82, 38, 58, 1, 8, 65, 32, 51, 86];

RUN_MAIN main();

void vectores_1_Dim(){
    console.log("*************** Area de Vectores 1 Dim ***************");
    ordenamiento1();
    elementoMayor();
    InvetirVector();
    verificacionConstanteVector1();
    console.log("");
}

void BubbleSort(){
    let i: int = 0;
    let j: int = 0;
    for (i = 0; i < length(numeros); i++) {
        for (j = 0; j < length(numeros) - i - 1; j++) {
            if (numeros[j] > numeros[j + 1]) {
                let aux: int = numeros[j];
                numeros[j] = numeros[j + 1];
                numeros[j + 1] = aux;
            }
        }

    }
}

void ordenamiento1(){
    console.log("========= Ordenamiento =========");
    let salida: string = "vector desordenado: [ ";
    let i: int = 0;
    for (i = 0; i < length(numeros); i++) {
        salida = salida + numeros[i] + " ";
    }
    salida = salida + "]";
    console.log(salida);

    salida = "vector ordenado: [ ";
    BubbleSort();
    for (i = 0; i < length(numeros); i++) {
        salida = salida + numeros[i] + " ";
    }
    salida = salida + "]";
    console.log(salida);

}

void elementoMayor(){
    console.log("========= Elemento Mayor =========");
    let caracteres: char[] = ['X', 'k', 'm', 'A', 't', 'L', 'Z', 'y', 'B', 'q', 'R', 'j', 'C', 'n', 'F'];
    let i: int = 0;
    let mayor: char = caracteres[0];
    for (i = 0; i < length(caracteres); i++) {
        if (caracteres[i] > mayor) {
            mayor = caracteres[i];
        }
    }
    console.log("El mayor caracter es: " + mayor);
}

void InvetirVector(){
    console.log("========= Invertir Vector =========");
    let vector: double[] = [10.5, 20.245, 30.74, 40.175, 50.89, 108.5, 208.245, 308.74, 408.175, 508.89];
    let i: int = 0;
    let salida: string = "vector original: [ ";
    for (i = 0; i < length(vector); i++) {
        salida = salida + vector[i] + " ";
    }
    salida = salida + "]";
    console.log(salida);

    let inicio: int = 0;
    let fin: int = length(vector) - 1;

    while (inicio < fin) {
        // intercambiar los elementos
        let temp: double = vector[inicio];
        vector[inicio] = vector[fin];
        vector[fin] = temp;

        //actualizar los indices
        inicio++;
        fin--;
    }

    // imprimir el vector
    salida = "vector invertido: [ ";
    for (i = 0; i < length(vector); i++) {
        salida = salida + vector[i] + " ";
    }
    salida = salida + "]";
    console.log(salida);

}

void verificacionConstanteVector1(){
    console.log("========= Verificacion Constante =========");
    const vector: string[] = ["Yeinny", "Diego", "Angel", "Estuardo", "Javier", "Andre", "Fabian"];
    let i: int = 0;
    let salida: string = "vector Original: [ ";
    for (i = 0; i < length(vector); i++) {
        salida = salida + vector[i] + " ";
    }
    salida = salida + "]";
    console.log(salida);

    let inicio: int = 0;
    let fin: int = length(vector) - 1;

    while (inicio < fin) {
        // intercambiar los elementos
        let temp: string = vector[inicio];
        vector[inicio] = vector[fin];
        vector[fin] = temp;

        //actualizar los indices
        inicio++;
        fin--;
    }
    let bandera: bool = true;
    if (vector[0] != "Yeinny") {
        bandera = false;
    }
    else if (vector[1] != "Diego") {
        bandera = false;
    }
    else if (vector[2] != "Angel") {
        bandera = false;
    }
    else if (vector[3] != "Estuardo") {
        bandera = false;
    }
    else if (vector[4] != "Javier") {
        bandera = false;
    }
    else if (vector[5] != "Andre") {
        bandera = false;
    }
    else if (vector[6] != "Fabian") {
        bandera = false;
    }
    // validar si el vector es constante
    if (bandera) {
        console.log("El vector es constante");
    } else {
        console.log("El vector no es constante");
    }

}


void vectores_2_Dim(){
    console.log("*************** Area de Vectores 2 Dim ***************");
    console.log("========= Voltear Filas =========");
    console.log("Matriz Original");
    ImprimirMensaje();
    voltearFilas();
    console.log("Matriz Volteada");
    ImprimirMensaje();
    console.log("========= Multiplicacion de Matrices =========");
    multiplicacionMatrices();
    console.log("========= Validacion de constantes =========");
    verificacionConstanteMatriz();

}


void main(){
    console.log("-----------------CALIFICACION ARCHIVO 2-----------------\n");
    vectores_1_Dim();
    vectores_2_Dim();

}

let matrizMensaje: char[][] = [
    ['.', '.', '.', '.', '.', '.', '4', '.', '.', '1'],
    ['.', '.', '.', '.', '.', '2', '.', '.', 'I', '.'],
    ['.', '.', '.', '.', '.', '.', '.', 'P', '.', '.'],
    ['.', '.', '.', '.', '.', '.', 'M', '.', '.', '.'],
    ['.', '.', 'C', '.', '.', 'O', '.', '.', 'E', '.'],
    ['.', 'I', '.', '.', 'C', '.', '.', 'L', '.', '.'],
    ['D', '.', '.', '.', '.', '.', 'A', '.', '.', '.'],
    ['.', '.', 'B', '.', '.', 'S', '.', '.', 'I', '.'],
    ['.', 'A', '.', '.', '.', '.', '.', 'S', '.', '.'],
    ['L', '.', '.', '.', '.', '.', '.', '.', '.', '.']
];

void voltearFilas(){
    //logitud de la matriz
    const longitud: int = length(matrizMensaje);

    let x: int;
    //tomamos hasta la mitad del arreglo
    for (x = 0; x < CAST(longitud / 2 as int); x++) {
        //almacenamos temporal
        let temporal: char = matrizMensaje[x];
        // calcular el indice contrario
        let indiceContrario: int = longitud - x - 1;
        // en el actual ahora esta el del otro lado
        matrizMensaje[x] = matrizMensaje[indiceContrario];
        // y en el otro lado, el que estaba originalmente en actual
        matrizMensaje[indiceContrario] = temporal;
    }
}

void ImprimirMensaje(){
    let i: int;
    let j: int;
    for (i = 0; i < length(matrizMensaje); i++) {
        let linea: string = "";
        for (j = 0; j < length(matrizMensaje[i]); j++) {
            linea = linea + " " + matrizMensaje[i][j] + " ";
        }
        console.log(linea);
    }
}

void multiplicacionMatrices(){
    let matrizA: int[][] = [
        [5, 3, -4, -2],
        [8, -1, 0, -3]
    ];

    let matrizB: int[][] = [
        [1, 4, 0],
        [-5, 3, 7],
        [0, -9, 5],
        [5, 1, 4]
    ];

    let matrizResultado: int[][] = [
        [0, 0, 0],
        [0, 0, 0]
    ];

    let i: int;
    let j: int;
    let k: int;

    // imprimir la matriz a
    let matrizAImpresion: string = "Matriz A:\n";
    for (i = 0; i < length(matrizA); i++) {
        let linea: string = "[";
        for (j = 0; j < length(matrizA[i]); j++) {
            linea = linea + " " + matrizA[i][j] + " ";
        }
        linea = linea + "]";
        matrizAImpresion = matrizAImpresion + linea + "\n";
    }
    console.log(matrizAImpresion);

    // imprimir la matriz b
    let matrizBImpresion: string = "Matriz B:\n";
    for (i = 0; i < length(matrizB); i++) {
        let linea: string = "[";
        for (j = 0; j < length(matrizB[i]); j++) {
            linea = linea + " " + matrizB[i][j] + " ";
        }
        linea = linea + "]";
        matrizBImpresion = matrizBImpresion + linea + "\n";
    }
    console.log(matrizBImpresion);


    // multiplicar las matrices
    for (i = 0; i < length(matrizA); i++) {
        for (j = 0; j < length(matrizB[0]); j++) {
            for (k = 0; k < length(matrizA[0]); k++) {
                matrizResultado[i][j] = matrizResultado[i][j] + matrizA[i][k] * matrizB[k][j];
            }
        }
    }

    // imprimir la matriz resultado
    let resultadoImpresion: string = "Matriz Resultado:\n";
    for (i = 0; i < length(matrizResultado); i++) {
        let linea: string = "[";
        for (j = 0; j < length(matrizResultado[i]); j++) {
            linea = linea + " " + matrizResultado[i][j] + " ";
        }
        linea = linea + "]";
        resultadoImpresion = resultadoImpresion + linea + "\n";

    }

    console.log(resultadoImpresion);
}


void verificacionConstanteMatriz(){
    const matrizConstante: bool[][] = [
        [true, true, true, true],
        [false, false, false, false],
        [true, true, true, true],
        [false, false, false, false]
    ];

    let i: int;
    let j: int;
    // impresion de matriz original
    let matrizImpresion: string = "Matriz Original:\n";
    for (i = 0; i < length(matrizConstante); i++) {
        let linea: string = "[";
        for (j = 0; j < length(matrizConstante[i]); j++) {
            linea = linea + " " + matrizConstante[i][j] + " ";
        }
        linea = linea + "]";
        matrizImpresion = matrizImpresion + linea + "\n";
    }
    console.log(matrizImpresion);

    // intentar cambiar matriz
    let bandera: bool = true;
    for (i = 0; i < length(matrizConstante); i++) {
        for (j = 0; j < length(matrizConstante[i]); j++) {
            let valor: bool = matrizConstante[i][j];
            matrizConstante[i][j] = !matrizConstante[i][j];
            if (matrizConstante[i][j] != valor) {
                bandera = false;
                break;
            }
        }
        if (!bandera) {
            break;
        }
    }
    console.log("");
    // imprimirmos el resultado
    matrizImpresion = "Matriz Resultado:\n";
    for (i = 0; i < length(matrizConstante); i++) {
        let linea: string = "[";
        for (j = 0; j < length(matrizConstante[i]); j++) {
            linea = linea + " " + matrizConstante[i][j] + " ";
        }
        linea = linea + "]";
        matrizImpresion = matrizImpresion + linea + "\n";
    }
    console.log(matrizImpresion);

    if (bandera) {
        console.log("La matriz es constante");
    } else {
        console.log("La matriz no es constante");
    }
}