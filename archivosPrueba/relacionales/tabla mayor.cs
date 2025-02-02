console.log("------------entero--------------");
console.log(10 >10);
console.log(10 >10.5);
console.log(100 > 'b');
console.log(4 > true);
console.log(1 > "hola");	 //error

console.log("------------decimal--------------");
console.log(10.4 > 10);
console.log(10.3 > 10.5);
console.log('a' > 97);
console.log(1.54 > true);	 
console.log(1.23 >"hola");	 //error

console.log("------------booleano--------------");
console.log(true > false);	// true
console.log(true > true);	//false
console.log(true> 2);	// false
console.log(false> 4.8);	// false
console.log(true>' ');	//false
console.log(false> "hola");	 //error

console.log("------------caracter--------------");
console.log('a' >10);	//true
console.log('a' >10.5);	//true
console.log('a' > 'a');	//false
console.log('a' > true);	//true
console.log('a' > "hola");	//error

console.log("------------Cadena--------------");
console.log("hola"> "hola");	//error
console.log("hola" >"HOLA");	//error
console.log("hola">1);	//error
console.log("hola">2.4);	//error
console.log("hola" > true);	//error
console.log("hola"> 'a');	//error



