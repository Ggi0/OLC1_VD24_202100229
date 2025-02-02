let EntradaCola: List<int>;
let SalidaCola: List<int>;
//declaramos structs
struct Particion{
    status: char;
    tipo: char;
    ajuste: char;
    inicio: int;
    size: int;
    nombre: string;
    siguiente: bool;
};

struct Inodo {
    uid: int;
    gid: int;
    size: int;
    fechaCreacion: string;
    fechaModificacion: string;
    bloqueDirecto: int;
    tipo: char;
};

struct MBR {
    size: int;
    fechaCreacion: string;
    fechaModificacion: string;
    particion1: Particion;
    particion2: Particion;
    particion3: Particion;
    particion4: Particion;
};
void archivo3() {
    console.log("-----------------CALIFICACION ARCHIVO 3-----------------");
    console.log("");
    ListasDinamicas();
    FuncionesNativas();
}

void enqueue(valor: int) {
    EntradaCola.append(valor);
}

int dequeue() {
    if (length(SalidaCola) == 0) {
        while (length(EntradaCola) > 0) {
            SalidaCola.append(EntradaCola.pop());
        }
    }
    if (length(SalidaCola) > 0) {
        return SalidaCola.pop();
    } else {
        return -1;
    }
}

int peekCola() {
    if (length(SalidaCola) == 0) {
        while (length(EntradaCola) > 0) {
            SalidaCola.append(EntradaCola.remove(length(EntradaCola) - 1));
        }
    }
    if (length(SalidaCola) > 0) {
        return SalidaCola.get(length(SalidaCola) - 1);
    } else {
        return -1;
    }
}

void MostrarCola() {
    if (length(EntradaCola) == 0 && length(SalidaCola) == 0) {
        console.log("La cola esta vacia");
    } else {
        let resultado: string = "";
        let i: int;
        for (i = length(SalidaCola) - 1; i >= 0; i--) {
            resultado = resultado + SalidaCola.get(i) + " ";
        }
        for (i = 0; i < length(EntradaCola); i++) {
            resultado = resultado + EntradaCola.get(i) + " ";
        }
        console.log("La cola es: " + resultado);
    }
}

void ListasDinamicas() {
    console.log("========= Listas Dinamicas =========");
    // vamos a usar la cola con dos listas
    enqueue(valor = 10);
    enqueue(valor = 20);
    enqueue(valor = 30);
    enqueue(valor = 40);
    enqueue(valor = 50);
    // Mostramos la cola
    MostrarCola();
    // Sacamos un elemento
    let valor: int = dequeue();
    if (valor == -1 || valor != 10) {
        console.log("Error al sacar el valor de la cola");
    }
    // agregamos un valor
    enqueue(valor = 60);
    // Mostramos la cola
    MostrarCola();
    // Sacamos un elemento
    valor = dequeue();
    if (valor == -1 || valor != 20) {
        console.log("Error al sacar el valor de la cola");
    }
    // sacamos un elemento
    valor = dequeue();
    if (valor == -1 || valor != 30) {
        console.log("Error al sacar el valor de la cola");
    }
    // Mostramos la cola
    MostrarCola();
    // Sacamos un elemento
    valor = dequeue();
    if (valor == -1 || valor != 40) {
        console.log("Error al sacar el valor de la cola");
    }
    // mostramos la cola
    MostrarCola();

    // vamos a probar el metodo peek
    let peek: int = peekCola();
    if (peek == -1 || peek != 50) {
        console.log("Error al obtener el valor de la cola");
    } else {
        console.log("El valor de la cola es: " + peek);
    }

    // ahora vamos a probar el metodo set y reverse con otra lista
    let listaString: List<string>;
    listaString.append("compi1");
    listaString.append("de");
    listaString.append("aux");
    listaString.append("mejor");
    listaString.append("el");
    listaString.append(": ");
    listaString.append("Reyna");
    listaString.append("Fabian");

    // reverse
    listaString.reverse();
    let resultado: string = "";
    let i: int;
    for (i = 0; i < length(listaString); i++) {
        resultado = resultado + listaString.get(i) + " ";
    }
    console.log(resultado);

    // ahora vamos al set
    listaString.set(0, "Si");
    listaString.set(1, "sale");
    listaString.set(2, "compi1");
    listaString.set(3, "en");
    listaString.set(4, "escuela");
    listaString.set(5, "de");
    listaString.set(6, "diciembre");
    listaString.set(7, "2024");

    resultado = "";
    for (i = 0; i < length(listaString); i++) {
        resultado = resultado + listaString.get(i) + " ";
    }
    console.log(resultado);
}

void FuncionesNativas() {
    console.log("========= Funcion Round =========");
    let r1: int = round(2.5);
    let r2: int = round(2.4);
    let r3: int = round(-2.6);
    let r4: int = round(-2.2);
    let r5: int = round(10 / 3);
    console.log("El round de 2.5 es: " + r1);
    console.log("El round de 2.4 es: " + r2);
    console.log("El round de -2.6 es: " + r3);
    console.log("El round de -2.2 es: " + r4);
    console.log("El round de 10/3 es: " + r5);
    console.log("========= Funcion ToString =========");
    // tipos primitivos
    let numString: string = toString(123);
    let numString2: string = toString(123.456);
    let numString3: string = toString(true);
    let numString4: string = toString(false);
    let numString5: string = toString('F');
    let numString6: string = toString('R');
    console.log("El valor de 123 es: " + numString);
    console.log("El valor de 123.456 es: " + numString2);
    console.log("El valor de true es: " + numString3);
    console.log("El valor de false es: " + numString4);
    console.log("El valor de 'F' es: " + numString5);
    console.log("El valor de 'R' es: " + numString6);
    console.log("========= Funcion Length =========");
    let vector1: char[] = ['a', 'b', 'c', 'd', 'e'];
    let matriz1: char[][] = [['a', 'b', 'c'], ['d', 'e', 'f'], ['g', 'h', 'i']];
    let cadena1: string = "Fabian Reyna";
    let lista1: List<int>;
    let lista2: List<bool>;
    lista1.append(1);
    lista1.append(2);
    lista1.append(3);
    lista1.append(4);
    lista1.append(5);
    lista1.append(6);
    console.log("El tamaño del vector es: " + length(vector1));
    console.log("El tamaño de la matriz es: " + length(matriz1));
    console.log("El tamaño de la cadena es: " + length(cadena1));
    console.log("El tamaño de la lista1 es: " + length(lista1));
    console.log("El tamaño de la lista2 es: " + length(lista2));
}

RUN_MAIN archivo3();
