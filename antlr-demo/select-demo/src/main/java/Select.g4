grammar Select;

@header{
package select;
}

stat
: query
;

 query:
   'select'
    selectEles
    'from'
    tableSrc
    (where)?
//    (groupBy)?
//    (orderBy)?
//    (limit)?
    ;

selectEles
: (star='*' | selectEle ) (',' selectEle )*
;

selectEle
: commonName
;

tableSrc
: commonName
;

where
: 'where' logicExp
;

logicExp
: logicExp logicOp logicExp
| commonName cmpOp val
| commonName 'not'? 'in' '(' val (',' val)* ')'
| '(' logicExp ')'
;

val
: commonName
| textLiteral
| decimalLiteral
;

decimalLiteral
: DECIMAL_LITERAL
;

textLiteral
: TEXT_STRING
;

logicOp
: 'and' | 'or'
;

cmpOp
: '=' | '>' | '<' | '<' '=' | '>' '='
| '<' '>' | '!' '=' | '<' '=' '>'
;

commonName
: ID
;

ID : ( 'A'..'Z' | 'a'..'z' | '_' | '$') ( 'A'..'Z' | 'a'..'z' | '_' | '$' | '0'..'9' )*;
TEXT_STRING : (  '\'' ( ('\\' '\\') | ('\'' '\'') | ('\\' '\'') | ~('\'') )* '\''  );
ID_LITERAL:   '*'|('@'|'_'|LETTER)(LETTER|DEC_DIGIT|'_')*;
DECIMAL_LITERAL:  DEC_DIGIT+;

fragment HEX_DIGIT:                  [0-9A-F];
fragment DEC_DIGIT:                  [0-9];
fragment LETTER:                         [a-zA-Z];

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines