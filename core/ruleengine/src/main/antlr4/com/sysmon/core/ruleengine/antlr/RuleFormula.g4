grammar RuleFormula;

ruleFormula
    :   expression
    ;

statement
    :   block_statement
    |   if_statement
    |   expression
    ;

block_statement
    :   LBRACE statement RBRACE
    ;

if_statement
    :   IF par_expression statement (ELSE statement)?
    ;

par_expression
    :   LPAREN expression RPAREN
    ;

expression
    :   literal
    |   par_expression
    |   measure_query_expression
    |   BANG expression
    |   expression AND expression
    |   expression OR expression
    |   expression (MUL|DIV|MOD) expression
    |   expression (ADD|SUB) expression
    |   expression (GT|LT|GE|LE) expression
    |   expression (EQUAL|NOTEQUAL) expression
    ;

measure_query_expression
    :   metricIdentifier DOT measure_query_function
    ;

measure_query_function
    :   CurrentFunctionName (LPAREN RPAREN)?
    |   LastFunctionName (LPAREN RPAREN)?
    |   AvgFunctionName LPAREN interval RPAREN
    |   MinFunctionName LPAREN interval RPAREN
    |   MaxFunctionName LPAREN interval RPAREN
    |   PercentileFunctionName LPAREN interval COMMA FloatingPointLiteral RPAREN
    ;

interval
    :   IntegerLiteral
    ;

metricIdentifier
    :   instanceIdentifier DOT metricName
    ;

instanceIdentifier
    :   Identifier
    ;

metricName
    :   Identifier
    ;

CurrentFunctionName
    :   'CURRENT'
    ;

LastFunctionName
    :   'LAST'
    ;

AvgFunctionName
    :   'AVG'
    ;

MinFunctionName
    :   'MIN'
    ;

MaxFunctionName
    :   'MAX'
    ;

PercentileFunctionName
    :   'PERCENTILE'
    ;

primary
    :   literal
    |   Identifier
    ;

literal
    :   BooleanLiteral
    |   IntegerLiteral
    |   FloatingPointLiteral
    ;

IntegerLiteral
    : SUB? DecimalIntegerLiteral
    ;

fragment
DecimalIntegerLiteral
    :   DecimalNumeral IntegerTypeSuffix?
    ;

fragment
IntegerTypeSuffix
    :   [lL]
    ;

fragment
DecimalNumeral
    :   '0'
    |   NonZeroDigit (Digits? | Underscores Digits)
    ;

fragment
Digits
    :   Digit (DigitOrUnderscore* Digit)?
    ;

fragment
Digit
    :   '0'
    |   NonZeroDigit
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;

fragment
DigitOrUnderscore
    :   Digit
    |   '_'
    ;

fragment
Underscores
    :   '_'+
    ;

FloatingPointLiteral
    :   SUB? DecimalFloatingPointLiteral
    ;

fragment
DecimalFloatingPointLiteral
    :   Digits '.' Digits? ExponentPart? FloatTypeSuffix?
    |   '.' Digits ExponentPart? FloatTypeSuffix?
    |   Digits ExponentPart FloatTypeSuffix?
    |   Digits FloatTypeSuffix
    ;

fragment
ExponentPart
    :   ExponentIndicator SignedInteger
    ;

fragment
ExponentIndicator
    :   [eE]
    ;

fragment
SignedInteger
    :   Sign? Digits
    ;

fragment
Sign
    :   [+-]
    ;

fragment
FloatTypeSuffix
    :   [fFdD]
    ;

//Boolean Literals

BooleanLiteral
    :   TRUE
    |   FALSE
    ;

TRUE            : 'true';
FALSE           : 'false';

//Identifiers

Identifier      : Letter LetterOrDigit*;

fragment
Letter          : [a-zA-Z0-9$_];

fragment
LetterOrDigit   : [a-zA-Z0-9$_];

//Keywords

IF              : 'if';
ELSE            : 'else';


//Separators

LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
LBRACK          : '[';
RBRACK          : ']';
SEMI            : ';';
COMMA           : ',';
DOT             : '.';


//Operators

ASSIGN          : '=';
GT              : '>';
LT              : '<';
BANG            : '!';
TILDE           : '~';
QUESTION        : '?';
COLON           : ':';
EQUAL           : '==';
LE              : '<=';
GE              : '>=';
NOTEQUAL        : '!=';
AND             : '&&';
OR              : '||';
INC             : '++';
DEC             : '--';
ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';
BITAND          : '&';
BITOR           : '|';
CARET           : '^';
MOD             : '%';

//Whitespace and comments

WS              :  [ \t\r\n\u000C]+ -> skip;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;