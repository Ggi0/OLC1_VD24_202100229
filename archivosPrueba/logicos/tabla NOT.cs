console.log("----------------conbinaciones posibles--------------------");
console.log(!false);
console.log(!true);
console.log("----------------con operadores--------------------");
console.log(!true==!false);
console.log(!((true || 0.1<-8) && false));	//true
console.log(!(true || 0.1<-8 && false));	//false
console.log("la respuesta es con !:   " +  (true && !true|| !true)); //false
console.log("hola" == "hoLa" || !!0.1<-8); //true
console.log(!!!!!true);
console.log(('a')>4!= !true);
console.log("----------------errores--------------------");
console.log(!1 && false);
console.log(false && !3.4);
console.log(!(4>=3)+1 && false);
console.log(false && !"hola");
console.log('a' &&! false);
console.log("-------------------------------------------");
