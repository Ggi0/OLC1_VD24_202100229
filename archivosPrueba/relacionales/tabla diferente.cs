console.log("------------entero--------------");
console.log(10 != 10);
console.log(10 != 10.5);
console.log(97 != 'b');
console.log(1 != true);	 //error
console.log(1 != "hola");	 //error

console.log("------------decimal--------------");
console.log(10.4 != 10);
console.log(10.3 != 10.5);
console.log('a' != 97);
console.log(1.54 != true);	 //error
console.log(1.23 != "hola");	 //error

console.log("------------booleano--------------");
console.log(true != false);
console.log(true != true);
console.log(true!= 1);	 //error
console.log(false!= 4.8);	 //error
console.log(true!= 'a');	 //error
console.log(false!= "hola");	 //error

console.log("------------caracter--------------");
console.log('a' != 10);
console.log('a' != 10.5);
console.log('a' != 'a');
console.log('a' != true);	// error
console.log('a' != "hola");	//error

console.log("------------Cadena--------------");
console.log("hola"!= "hola");
console.log("hola" != "HOLA");
console.log("hola"!=1);	//error
console.log("hola"!= 2.4);	//error
console.log("hola" != true);	//error
console.log("hola"!= 'a');	//error

