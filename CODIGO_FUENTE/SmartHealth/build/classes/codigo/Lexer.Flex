package codigo;

import codigo.Token;
%%
%class Lexer
%type Token
%line
%column
L=[A-Za-zÑñ_ÁÉÍÓÚáéíóú]+
D=[0-9]+
espacio=[ \t\r]+
%{
    public Token token(String lexema, String tipo, int linea, int columna){
        return new Token(lexema, tipo, linea+1, columna+1);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Salto de linea */
( "\n" ) {/*Ignore*/}

/* Clase principal */
Atlas    {return token(yytext(), "Atlas", yyline, yycolumn);}
End      {return token(yytext(), "End", yyline, yycolumn);}
begin    {return token(yytext(), "Begin", yyline, yycolumn);}

/* Tipos de datos */
int     {return token(yytext(), "Int", yyline, yycolumn);}
char    {return token(yytext(), "Char", yyline, yycolumn);}
logic   {return token(yytext(), "Logic", yyline, yycolumn);}
float   {return token(yytext(), "Float", yyline, yycolumn);} 

/* Tipo de dato String */
( str ) {return token(yytext(), "str", yyline, yycolumn);}

/*WHILE*/
( while ) {return token(yytext(), "While", yyline, yycolumn);}

/*FOR*/
( for ) {return token(yytext(), "For", yyline, yycolumn);}

/* Palabra reservada If */
( condition ) {return token(yytext(), "Condition", yyline, yycolumn);}

/* Palabra reservada Else */
( else ) {return token(yytext(), "Else", yyline, yycolumn);}

/* Palabra reservada Ventilar */
( ventilate ) {return token(yytext(), "Ventilate", yyline, yycolumn);}

/* Palabra reservada Admit */
( admit ) {return token(yytext(), "admit", yyline, yycolumn);}

/* Palabra reservada EmptyRoom */
( emptyRoom ) {return token(yytext(), "EmptyRoom", yyline, yycolumn);}

/* Palabra reservada Dispense */
( dispense ) {return token(yytext(), "Dispense", yyline, yycolumn);}

/* Palabra reservada Distance  */
( distance  ) {return token(yytext(), "Distance", yyline, yycolumn);}

/* Palabra reservada DeviceControl  */
( deviceControl ) {return token(yytext(), "DeviceControl", yyline, yycolumn);}

/* Palabra reservada DriverLights  */
( driverLights ) {return token(yytext(), "DriverLights", yyline, yycolumn);}

/* Palabra reservada OpenDoor  */
( openDoor ) {return token(yytext(), "OpenDoor", yyline, yycolumn);}

/* Palabra reservada RegisterA  */
( registerA ) {return token(yytext(), "RegisterA", yyline, yycolumn);}

/* Palabra reservada Exit  */
( exit ) {return token(yytext(), "Exit", yyline, yycolumn);}


/* Operador Igual */
( "=" ) {return token(yytext(), "Asignacion", yyline, yycolumn);}

/* Operadores Aritmeticos */
( "+" | "-" | "*" | "/" ) {return token(yytext(), "Aritmetico", yyline, yycolumn);}

/* Operadores logicos */
( "&&" | "||" ) {return token(yytext(), "Op_Logico", yyline, yycolumn);}

/* Operador Negacion */
( "!" ) {return token(yytext(), "Negacion", yyline, yycolumn);}

/*Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=") {return token(yytext(), "Op_Relacional", yyline, yycolumn);}

/* Operadores Atribucion */
( "+=" | "-="  | "*=" | "/=" ) {return token(yytext(), "Op_Atribucion", yyline, yycolumn);}

/* Operadores Incremento y decremento */
( "++" | "--" ) {return token(yytext(), "Op_Incremento", yyline, yycolumn);}

/*Operadores Booleanos*/
(true | false)  {return token(yytext(), "Op_Booleano", yyline, yycolumn);}

/* Parentesis de apertura */
( "(" ) {return token(yytext(), "Parentesis_a", yyline, yycolumn);}

/* Parentesis de cierre */
( ")" ) {return token(yytext(), "Parentesis_c", yyline, yycolumn);}

/* Llave de apertura */
( "{" ) {return token(yytext(), "Llave_a", yyline, yycolumn);}

/* Llave de cierre */
( "}" ) {return token(yytext(), "Llave_c", yyline, yycolumn);}

/* Corchete de apertura */
( "[" ) {return token(yytext(), "Corchete_a", yyline, yycolumn);}

/* Corchete de cierre */
( "]" ) {return token(yytext(), "Corchete_c", yyline, yycolumn);}

/* Marcador de inicio de algoritmo */
( start ) {return token(yytext(), "Start", yyline, yycolumn);}

/* Punto y coma */
( ";" ) {return token(yytext(), "Punto_Coma", yyline, yycolumn);}

/* Coma */
( "," ) {return token(yytext(), "Coma", yyline, yycolumn);}

/* Declare y As */
Declare {return token(yytext(), "Declare", yyline, yycolumn);}
As  {return token(yytext(), "As", yyline, yycolumn);}

/* Identificador */
{L}({L}|{D})* {return token(yytext(), "identificador", yyline, yycolumn);}

/* Numero */
("(-"{D}+")")|{D}+ {return token(yytext(), "Numero", yyline, yycolumn);}

/* Numero Decimal */
("(-"{D}+\.{D}+")")|{D}+\.{D}+ {return token(yytext(), "Numero_Decimal", yyline, yycolumn);}

/* Error Identificador */
{D}+{L}+ {return token(yytext(), "ERROR", yyline, yycolumn);}

/* Texto */
\".*\" {return token(yytext(), "Texto", yyline, yycolumn);}

/* Caracter */
\'.\' {return token(yytext(), "Caracter", yyline, yycolumn);}

/* Error caracter */
\'.+\' {return token(yytext(), "ERROR", yyline, yycolumn);}

/* Error de analisis */
 . {return token(yytext(), "ERROR", yyline, yycolumn);}