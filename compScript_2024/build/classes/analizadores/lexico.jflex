
// ---------------------------- PAQUETES E IMPORTACIONES ----------------------------
package analizadores;
import java_cup.runtime.*;
import java.util.LinkedList;
import patronInterprete.excepciones.Errores;
import patronInterprete.simbolo.Token_info;







%%
// ---------------------------- DIRECTIVAS ----------------------------
%cup
%class Lexico
%public
%line
%column
%char
%full
%ignorecase

%{

    public LinkedList<Errores> erroresLexicos = new LinkedList<>();

    // Lista para almacenar los tokens
    //public ArrayList<TokenInfo> tokens = new ArrayList<>();

%}

%init{
    yyline = 1;
    yycolumn = 1;

    erroresLexicos = new LinkedList<>(); //vaciar lista
    //tokens = new LinkedList<>(); //vaciar lista

%init}




// ---------------------------- EXPRESIONES REGULARES ----------------------------

// simbolos de sistema:
LPAREN    = "("
RPAREN    = ")"
LBRACE    = "{"
RBRACE    = "}"
LCORCH    = "["
RCORCH    = "]"
SEMICOLON = ";"
DOT       = "."
COLON     = ":"
COMMA     = ","

MAS       = "+"
MENOS     = "-"
MULTI     = "*"
POTENCIA  = "^"
RAIZ      = "$"
DIV       = "/"
MODULO    = "%"

IGUAL     = "="
EQUALS    = "=="
DIFERN    = "!="
MENOR     = "<"
MENIGUAL  = "<="
MAYOR     = ">"
MAYIGUAL  = ">="
ARROW    = "=>"


OR   = "||"
AND  = "&&"
NOT  = "!"

INCREMENTO = "++"
DECREMENTO = "--"


ENTERO   = [0-9]+
DECIMAL  = [0-9]+"."[0-9]+
CADENA   = [\"]([^\"\\\n\t]|\\.)*[\"]
//CARACTER = [\']([^\'\\]|\\(n|\\|\"|t|\'))[\'] // solo un caracter
CARACTER = [\']([^\'\\]|\\(n|\\|\"|t|\'))+[\'] // varios caracteres pero guarda el error 
ID       = [a-zA-Z][a-zA-Z0-9_]*  
 
COMENTARIO  = "//"[^\n]*
MCOMENTARIO = [\/][\*]([^\*]|(\*+[^\*\/]))*\*\/
WHITESPACE  = [\ \r\t\f\n]+

IMPRIMIR = "console" "." "log"
TRUE     = "true"
FALSE    = "false"
INT      = "int"
DOUBLE   = "double"
BOOL     = "bool"
CHARV    = "char"
STRING   = "string"
MUTB     = "let" | "const"

CAST     = "cast"
AS       = "as"
ROUND    = "round"
LENGTH   = "length"
toSTRING = "toString"

IF       = "if"
ELSE     = "else"
FOR      = "for"
WHILE    = "while"
DO       = "do"
BREAKK   = "break"
MATCH    = "match"
DEFAULT  = "default"
CONTINUAR = "continue"

STRUCT    = "struct"

LISTA      = "List"
APPEND     = "append"
GETLIST    = "get"
SETLIST    = "set"
REMVLIST   = "remove"
POPLIST      = "pop"
REVERSELIST  = "reverse"

MMMAIN = "RUN_MAIN"

METODO = "void"

RETURNSTC  = "return"



%%



// ---------------------------- REGLAS LEXICAS ----------------------------
// TOKENS
<YYINITIAL> {IMPRIMIR}  { return new Symbol(sym.IMPRIMIR,  yyline, yycolumn, yytext()); }
<YYINITIAL> {TRUE}      { return new Symbol(sym.TRUE,      yyline, yycolumn, yytext()); }
<YYINITIAL> {FALSE}     { return new Symbol(sym.FALSE,     yyline, yycolumn, yytext()); }
<YYINITIAL> {INT}       { return new Symbol(sym.INT,       yyline, yycolumn, yytext()); }
<YYINITIAL> {DOUBLE}    { return new Symbol(sym.DOUBLE,    yyline, yycolumn, yytext()); }
<YYINITIAL> {BOOL}      { return new Symbol(sym.BOOL,      yyline, yycolumn, yytext()); }
<YYINITIAL> {CHARV}     { return new Symbol(sym.CHARV,     yyline, yycolumn, yytext()); }
<YYINITIAL> {STRING}    { return new Symbol(sym.STRING,    yyline, yycolumn, yytext()); }
<YYINITIAL> {MUTB}      { return new Symbol(sym.MUTB,      yyline, yycolumn, yytext()); }

<YYINITIAL> {CAST}      { return new Symbol(sym.CAST,      yyline, yycolumn, yytext()); }
<YYINITIAL> {AS}        { return new Symbol(sym.AS,        yyline, yycolumn, yytext()); }
<YYINITIAL> {ROUND}     { return new Symbol(sym.ROUND,     yyline, yycolumn, yytext()); }
<YYINITIAL> {LENGTH}    { return new Symbol(sym.LENGTH,    yyline, yycolumn, yytext()); }
<YYINITIAL> {toSTRING}  { return new Symbol(sym.toSTRING,  yyline, yycolumn, yytext()); }

<YYINITIAL> {IF}        { return new Symbol(sym.IF,        yyline, yycolumn, yytext()); }
<YYINITIAL> {ELSE}      { return new Symbol(sym.ELSE,      yyline, yycolumn, yytext()); }

<YYINITIAL> {FOR}       { return new Symbol(sym.FOR,       yyline, yycolumn, yytext()); }
<YYINITIAL> {WHILE}     { return new Symbol(sym.WHILE,     yyline, yycolumn, yytext()); }
<YYINITIAL> {DO}        { return new Symbol(sym.DO,        yyline, yycolumn, yytext()); }

<YYINITIAL> {BREAKK}    { return new Symbol(sym.BREAKK,    yyline, yycolumn, yytext()); }
<YYINITIAL> {MATCH}     { return new Symbol(sym.MATCH,     yyline, yycolumn, yytext()); }
<YYINITIAL> {ARROW}     { return new Symbol(sym.ARROW,     yyline, yycolumn, yytext()); }
<YYINITIAL> {DEFAULT}   { return new Symbol(sym.DEFAULT,   yyline, yycolumn, yytext()); }
<YYINITIAL> {CONTINUAR} { return new Symbol(sym.CONTINUAR, yyline, yycolumn, yytext()); }

<YYINITIAL> {STRUCT}    { return new Symbol(sym.STRUCT,    yyline, yycolumn, yytext()); }

<YYINITIAL> {LISTA}     { return new Symbol(sym.LISTA,     yyline, yycolumn, yytext()); }
<YYINITIAL> {APPEND}    { return new Symbol(sym.APPEND,    yyline, yycolumn, yytext()); }
<YYINITIAL> {GETLIST}   { return new Symbol(sym.GETLIST,   yyline, yycolumn, yytext()); }
<YYINITIAL> {SETLIST}   { return new Symbol(sym.SETLIST,   yyline, yycolumn, yytext()); }
<YYINITIAL> {REMVLIST}  { return new Symbol(sym.REMVLIST,  yyline, yycolumn, yytext()); }
<YYINITIAL> {POPLIST}   { return new Symbol(sym.POPLIST,   yyline, yycolumn, yytext()); }
<YYINITIAL> {REVERSELIST} { return new Symbol(sym.REVERSELIST,   yyline, yycolumn, yytext()); }

<YYINITIAL> {METODO}      { return new Symbol(sym.METODO,   yyline, yycolumn, yytext()); }
<YYINITIAL> {MMMAIN}      { return new Symbol(sym.MMMAIN,   yyline, yycolumn, yytext()); }

<YYINITIAL> {RETURNSTC}   { return new Symbol(sym.RETURNSTC,yyline, yycolumn, yytext()); }

<YYINITIAL> {ID}        { return new Symbol(sym.ID,        yyline, yycolumn, yytext()); }


<YYINITIAL> {DECIMAL}   { return new Symbol(sym.DECIMAL,   yyline, yycolumn, yytext()); }
<YYINITIAL> {ENTERO}    { return new Symbol(sym.ENTERO,    yyline, yycolumn, yytext()); }
<YYINITIAL> {CADENA}    { String cadena = yytext(); 
                           cadena = cadena.substring(1, cadena.length() - 1);
                           return new Symbol(sym.CADENA,   yyline, yycolumn, cadena   ); }
<YYINITIAL> {CARACTER}  { String caracter = yytext(); 
                           caracter = caracter.substring(1, caracter.length() - 1);
                           return new Symbol(sym.CARACTER, yyline, yycolumn, caracter ); }

<YYINITIAL> {INCREMENTO}     { return new Symbol(sym.INCREMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {DECREMENTO}     { return new Symbol(sym.DECREMENTO, yyline, yycolumn, yytext()); }

<YYINITIAL> {COLON}     { return new Symbol(sym.COLON,     yyline, yycolumn, yytext()); }
<YYINITIAL> {SEMICOLON} { return new Symbol(sym.SEMICOLON, yyline, yycolumn, yytext()); }
<YYINITIAL> {LPAREN}    { return new Symbol(sym.LPAREN,    yyline, yycolumn, yytext()); }
<YYINITIAL> {RPAREN}    { return new Symbol(sym.RPAREN,    yyline, yycolumn, yytext()); }
<YYINITIAL> {LCORCH}    { return new Symbol(sym.LCORCH,    yyline, yycolumn, yytext()); }
<YYINITIAL> {RCORCH}    { return new Symbol(sym.RCORCH,    yyline, yycolumn, yytext()); }
<YYINITIAL> {LBRACE}    { return new Symbol(sym.LBRACE,    yyline, yycolumn, yytext()); }
<YYINITIAL> {RBRACE}    { return new Symbol(sym.RBRACE,    yyline, yycolumn, yytext()); }
<YYINITIAL> {DOT}       { return new Symbol(sym.DOT,       yyline, yycolumn, yytext()); }
<YYINITIAL> {COMMA}     { return new Symbol(sym.COMMA,     yyline, yycolumn, yytext()); }

<YYINITIAL> {MAS}       { return new Symbol(sym.MAS,       yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOS}     { return new Symbol(sym.MENOS,     yyline, yycolumn, yytext()); }
<YYINITIAL> {MULTI}     { return new Symbol(sym.MULTI,     yyline, yycolumn, yytext()); }
<YYINITIAL> {DIV}       { return new Symbol(sym.DIV,       yyline, yycolumn, yytext()); }
<YYINITIAL> {POTENCIA}  { return new Symbol(sym.POTENCIA,  yyline, yycolumn, yytext()); }
<YYINITIAL> {RAIZ}      { return new Symbol(sym.RAIZ,      yyline, yycolumn, yytext()); }
<YYINITIAL> {MODULO}    { return new Symbol(sym.MODULO,    yyline, yycolumn, yytext()); }

<YYINITIAL> {EQUALS}    { return new Symbol(sym.EQUALS,    yyline, yycolumn, yytext()); }
<YYINITIAL> {DIFERN}    { return new Symbol(sym.DIFERN,    yyline, yycolumn, yytext()); }
<YYINITIAL> {MENIGUAL}  { return new Symbol(sym.MENIGUAL,  yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYIGUAL}  { return new Symbol(sym.MAYIGUAL,  yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOR}     { return new Symbol(sym.MENOR,     yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYOR}     { return new Symbol(sym.MAYOR,     yyline, yycolumn, yytext()); }
<YYINITIAL> {IGUAL}     { return new Symbol(sym.IGUAL,     yyline, yycolumn, yytext()); }

<YYINITIAL> {OR}        { return new Symbol(sym.OR,        yyline, yycolumn, yytext()); }
<YYINITIAL> {AND}       { return new Symbol(sym.AND,       yyline, yycolumn, yytext()); }
<YYINITIAL> {NOT}       { return new Symbol(sym.NOT,       yyline, yycolumn, yytext()); }

<YYINITIAL> {WHITESPACE}  { /* ignorar */ }  
<YYINITIAL> {COMENTARIO}  { /* ignorar */ }
<YYINITIAL> {MCOMENTARIO} { /* ignorar */ }


// ---------------------------- ERRORES LEXICOS ----------------------------
<YYINITIAL> . {erroresLexicos.add(new Errores("LEXICO",
                "El caracter " +yytext()+" no pertenece al lenguaje",
                yyline, yycolumn));}