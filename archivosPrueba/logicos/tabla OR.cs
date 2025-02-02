console.log("---------------conbinaciones posible-------------------");
console.log(false||false);
console.log(false||true);
console.log(true || false);
console.log(true||true);

console.log("---------------con operadores-------------------");
console.log((1==2||  2 != 2));
console.log(20<8 ||50>=3);
console.log('a'=='a' || false);
console.log("hola" != "azul" || 3.3 > 2.1);
console.log("el resultado es: "+ (1==2||  2 != 2));
console.log("El resultado entre:  "+(1==2||  2 != 2) + "  OR  " + ("hOlA" != "azul" || 3.3 > 2.1) + '\n'+'\t' + " es " + ('a'=='a' || false));

console.log("---------------ERRORES-------------------");
console.log((1||  2));
console.log("hola" ||true);
console.log(false ||'a');
console.log("-------------------------------------------");