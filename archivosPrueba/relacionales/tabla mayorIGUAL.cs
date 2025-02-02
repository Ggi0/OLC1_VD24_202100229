console.log("------------entero--------------");
console.log(4>4);	//false
console.log(10 >=10);	//true
console.log(10 >10);	//false
console.log(10 >=10.5);
console.log(97 >='b');
console.log(2/9 >= true);
console.log(1 >="hola");	 //error

console.log("------------decimal--------------");
console.log('a' >97);
console.log(10.4 >= 10);
console.log(10.3 >=10.5);
console.log('a' >=97);
console.log(1.54 >= true);	 
console.log(1.23 >="hola");	 //error

console.log("------------booleano--------------");
console.log(true >= false);
console.log(true >= true);
console.log(true>= 2);
console.log(false>= 4.8);	
console.log(true>= 'a');	
console.log(false>= "hola");	 //error

console.log("------------caracter--------------");
console.log('a' >'a');	//false
console.log('a' >=10);	
console.log('a' >= 3.2);
console.log('a' >= 'a');	//true
console.log('a' >= true);	
console.log('a' >= "hola");	//error

console.log("------------Cadena--------------");
console.log("hola">= "hola");	//error
console.log("hola" >="HOLA");	//error
console.log("hola">=1);	//error
console.log("hola">=2.4);	//error
console.log("hola" >= true);	//error
console.log("hola">= 'a');	//error




