grammar Calc;
stat:	expr NEWLINE  # printExpr
    | NEWLINE         # blank
    ;

expr:	expr op=('*'|'/') expr # MulDiv
    |	expr op=('+'|'-') expr # AddSub
    |	INT                    # int
    |	'(' expr ')'           # parents
    ;

NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;
WS : [ \r\n\t] + -> skip
   ;

MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
