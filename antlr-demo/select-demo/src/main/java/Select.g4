grammar Select;

@header{
package select;
}

stat
: query
;

 query:
   'select'
    selectElse
    'from'
    tableSrc
    (where)?
//    (groupBy)?
    (orderBy)?
    (limit)?
    ;

selectElse
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
: logicExp logicOp logicExp # expr
| commonName cmpOp val      # cmpExpr
| commonName inOp '(' val (',' val)* ')' # otherExpr
| '(' logicExp ')'          # parentsExpr
;

inOp
: 'not in'
| 'in'
;

orderBy:
'order by'
commonName
sort=('desc'|'asc')*
;

limit:
'limit'
offset (',' size)*
;

offset:
DECIMAL_LITERAL
;

size:
DECIMAL_LITERAL
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
: '='
| '>'
| '<'
| '<='
| '>='
| '!' '='
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