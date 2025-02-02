console.log("----------------conbinaciones posibles--------------------");
console.log(false && false);
console.log(false && true);
console.log(true && false);
console.log(true && true);
console.log("----------------con operadores--------------------");
console.log("hola" == "holA"&& 3<1 || false);
console.log('a' < 'Z' && 5 != 6);
console.log((true || 0.1<-8) && false);	//false
console.log(true || 0.1<-8 && false);	//true
console.log("la respuesta es:  " +  (true && true || false));
console.log("hola" == "hola" || 0.1<-8);
console.log("----------------errores--------------------");
console.log(1 && false);
console.log(false && 3.4);
console.log((4>=3)+1 && false);
console.log(false && "hola");
console.log('a' && false);
console.log("-------------------------------------------");