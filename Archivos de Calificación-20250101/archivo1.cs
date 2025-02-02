const var1: int = 1;
let nota: double = 0.0;

RUN_MAIN principal();

void principal(){
    console.log("-----------------CALIFICACION ARCHIVO 1-----------------\n");

    //declaracion
    declaracion();

    // ambitos 1
    console.log("========= Metodo Ambitos =========");
    let var1: int = 10 + 10;
    if (var1 != 20) {
        let var1: int = 61;
        console.log("Manejo de entornos erroneo :(");
    } else {
        if (var1 == 61) {
            console.log("Manejo de entornos erroneo :(");
        } else {
            console.log("Manejo de ambitos correcto :D");
        }
    }


    //ambitos 2
    let miVariable: string = ":D";
    ambitos();

    //parametros
    parametros1(mensaje = "Si sale compi1", valor = 7);
    
    console.log("========= Metodo Parametros2 =========");
    parametros2(carnet = "202004765", mensaje = "Si sale compi1");
    parametros2(carnet = "202003959", nota = 60.0, mensaje = "No sale compi1");
    parametros2(nota = 100.0, mensaje = "Ya salio compi1", carnet = "202003894");
    
    console.log("========= Metodo Parametros3 =========");
    parametros3(nombre = "Edin", nota = 61.0);
    parametros3(nombre = "Alvaro", nota = 59.9, estado = 59.9 > 60);
    parametros3(nombre = "Jorge", nota = (10.25 * 6));
    parametros3(nombre = "Javier", nota = 45.0, estado = false);

    // recursividad
    recursividad();

}

void declaracion(){
    console.log("========= Metodo Declaracion =========");
    let num1: int;
    const num2: int;
    let num3: int;

    let cadena1: string = "Si sale compi1 en vacas";
    const cadena2: string = "No sale compi2 en vacas";
    let cadena3: string = "No sale compi2 en vacas";
    const cadena4: string = "Si sale compi1 en vacas";

    if (cadena1 == cadena4 && cadena2 == cadena3 && num1 == num2) {
        console.log("Declaracion Correcta");
        nota = nota + 1;
    } else {
        console.log("Declaracion de variables erronea :(");
    }

}

void ambitos(){
    
    const miVariable: string = ":(";
    if (miVariable == ":(") {
        console.log("Entornos correctos");
    } else {
        console.log("Entornos erroneos :(");
    }
}



void parametros1(valor: int, mensaje: string){
    console.log("========= Metodo Parametros1 =========");
    let i: int;
    for (i = 0; i <= 11; i++) {
        if (i == 0) {
            continue;
            console.log("Sigue mal el continue :(");
            console.log("Asi no sale compi1 :(");
        }
        console.log(valor + " * " + i + " = " + (valor * i));
        if (i == 10) {
            break;
            console.log("Sigue mal el break :(");
            console.log("Asi no sale compi1 :(");
        }
    }
    console.log(mensaje);
}

void parametros2(nota: double = 61.0, carnet: string, mensaje: string){
    console.log("El carnet " + carnet + " tiene nota de " + nota + " en compi1");
    console.log(mensaje);
}

void parametros3(nombre: string, nota: double = 0.0, estado: bool = 1 == 1){
    
    if (estado) {
        console.log("El estudiante " + nombre + " aprobo lab con una nota de " + nota);
    } else {
        console.log("El estudiante " + nombre + " reprobo lab con una nota de " + nota);
    }
}

void recursividad(){
    console.log("========= Metodo Recursividad =========");
    let numSumDigitos: int = 123456789;
    let numFibonacci: int = 20;
    let numAckermann1: int = 3;
    let numAckermann2: int = 4;
    parImpar();
    console.log("");
    console.log("Suma de digitos de " + numSumDigitos + ": " + sumDigitos(num = numSumDigitos));
    console.log("");
    console.log("Fibonacci de " + numFibonacci + ": " + fibonacci(n = numFibonacci));
    console.log("");
    console.log("Ackermann de " + numAckermann1 + " y " + numAckermann2 + ": " + ackermann(m = numAckermann1, n = numAckermann2));
}

int par(nump: int) {
    let contador: int = 0; // Contador para evitar bucles infinitos
    while (true) {
        if (contador > 1000) { // Límite arbitrario de iteraciones
            console.log("Error: Se alcanzó el límite en la función par.");
            break;
        }
        if (nump == 0) {
            return 1;
        }
        contador++;
        return impar(numi = (nump - 1));
    }
    return -1; // Valor de error en caso de bucle infinito
}

int impar(numi: int) {
    let contador: int = 0; // Contador para evitar bucles infinitos
    while (true) {
        if (contador > 1000) { // Límite arbitrario de iteraciones
            console.log("Error: Se alcanzó el límite en la función impar.");
            break;
        }
        if (numi == 0) {
            return 0;
        }
        contador++;
        return par(nump = (numi - 1));
    }
    return -1; // Valor de error en caso de bucle infinito
}

void parImpar(){
    let numero:int = 70;
    if(par(nump=numero)==1){
        console.log("El numero "+numero+" es par");
    }else{
        console.log("El numero "+numero+" es impar");
    }
}

int sumDigitos(num: int = 0){
    if (num != 0) {
        console.log("Iterando " + num + " en " + (num % 10));
        return CAST(num % 10 as int) + sumDigitos(num = CAST (num / 10 as int));
    }
    return 0;
}

int fibonacci(n: int = 0){
    if (n > 1) {
        return fibonacci(n = n - 1) + fibonacci(n = n - 2);
    } else if (n == 1) {
        return 1;
    } else if (n == 0) {
        return 0;
    } else {
        console.log("Error en el calculo de fibonacci");
        return -1;
    }
}

int ackermann(m: int = 0, n: int = 0){
    if (m == 0) {
        return n + 1;
    } else if (n == 0 && m > 0) {
        return ackermann(m = m - 1,n = 1);
    } else {
        return ackermann(m = m - 1, n = ackermann(m = m,n = n - 1));
    }
}