RUN_MAIN metodoMain();

void metodoMain(){
    console.log("Errores Lexicos");
    ErroresLexicos();
    console.log("Errores Sintacticos");
    erroresSintacticos();
    console.log("Errores Semanticos");
    erroresSemanticos();
}

// errores lexicos
let i: ?int = 10;

let nota: double = ¬ 100.0;

void ErroresLexicos(){
    if (nota >= ´ 61) {
        console.log("Aprobado");
    }else ¡ {
        console.log("Reprobado"); ~
    }

    while (i < 12) {
        console.log(° i);
        i = i + ? 1;
    }

}


void erroresSintacticos(){
    console.log("Errores Sintacticos");
    //console();
    console.log("F");
    //if (10 + 10 == 20) {
        console.log("Veamos dentro de un if");
        console.log("Me falta el ;")
        console.log("F");
        console.log("Nos recuperamos?");
    //}
    while (10 + 10) {
        console.log("No me recupere de errores semanticos :'v");
    }

}


void erroresSemanticos(){
    metodo1();
    retornoErroneo1();
    retornoErroneo2();
    parametroErroneo();

}

void metodo1(){
    if (3 * 6 + 7) {
        console.log("No me recupere de errores semanticos :'v");
    } else {
        console.log("No me recupere de errores semanticos :'v");
    }

    const constante: double = 61.0001;
    constante++;

    while (10 + 10) {
        console.log("No me recupere de errores semanticos :'v");
    }

    let error2: int = "true";
    const error2: string = "true";
    let error3: bool = 10;
    const error4: char = "Hola Mundo";

    let miVariable: int = 10;
    miVariable = "Hola Mundo";
    miVariable = true;
    miVariable = 'a';

    let contador: int = 0;
    while (contador < 5) {

        if (contador == 2) {
            contador = "Hola Mundo";
        } else if (contador == 3) {
            contador = true;
        } else {
            console.log("contador");
        }
        contador++;
    }

}

int retornoErroneo1(){
    console.log("Esta funcion retorna un valor erroneo");
    return "No sale compi :(";
}

void retornoErroneo2(){
    console.log("Esta funcion no retorna nada");
    return "Mejor me paso a Industrial";
}


void parametroErroneo(param1: string = 10){
    console.log("Mi parametro esta mal definido");
    console.log("Soy un error semantico");
}

void masErrores(){
    // vector mal definido
    let vector: int[] = [true, false, 10, "Hola Mundo"];
    console.log("Mi vector esta mal definido");

    // matriz mal definida
    let matriz: char[][] = [[true, false, 10, "Hola Mundo"], [true, false, 10, "Hola Mundo"]];
    console.log("Mi matriz esta mal definida");

    // struct mal definido
    let estudiante: estudiante = { nombre: 1, edad: "20", promedio: "80", estado: false };
    let estudiante2: estudiante = {
        nombre: "Javier", edad: 20,
        promedio: 8.0,
        estado: "No gano compi y se salio de la carrera"
    };
    let estudiante3: estudiante = {
        nombre: "Rodolfo",
        edad: 20, promedio: 80,
        estado: "Decidio mejor sacar compi1 en semestre sin el mejor aux de sistemas"
    };


    // lista
    const lista: List<int>;

    let lista2: list<double>;
    lista2.add(true);
    lista2.add(10);
    lista2.add("Sistemas muy dificil");
    lista2.add(8.0);


}

//struct
struct estudiante{
    nombre: string;
    edad: int;
    promedio: double;
    estado: string;
}
