grammar MolangRules;

LINE_COMMENT : '//' .*? '\r'? [\n] -> skip ;
BLOCK_COMMENT: '/*' .*? '*/' -> skip       ;
WHITESPACES  : [ \r\t\n]+ -> skip          ;

String : ('\'' (~["] | '\\' .)* '\'') ;
string : String                       ;

// Point.
Point : '.'   ;
point : Point ;

// Braces.
LeftBrace  : '{'        ;
RightBrace : '}'        ;
leftBrace  : LeftBrace  ;
rightBrace : RightBrace ;

LeftAngleBracket  : '<'               ;
RightAngleBracket : '>'               ;
leftAngleBracket  : LeftAngleBracket  ;
rightAngleBracket : RightAngleBracket ;

LeftBracket  : '['          ;
RightBracket : ']'          ;
leftBracket  : LeftBracket  ;
rightBracket : RightBracket ;

LeftParenthesis  : '('              ;
RightParenthesis : ')'              ;
leftParenthesis  : LeftParenthesis  ;
rightParenthesis : RightParenthesis ;
leftParen        : LeftParenthesis  ;
rightParen       : RightParenthesis ;

Comma     : ',' ;
Semicolon : ';' ;

Return : 'return' ;
return : Return   ;

Identifier : [a-zA-Z_][a-zA-Z_0-9]*         ;
FullName   : (Identifier '.')+ (Identifier) ;
Number     : [0-9]+('.'[0-9]+)?             ;
True       : 'true'                         ;
False      : 'false'                        ;

fullNameOrIdentifier : FullName | Identifier ;
identifier           : Identifier            ;
fullName             : FullName              ;
number               : Number                ;
bool                 : True | False          ;

AdditionAssignment       : '+='                                   ;
SubtractionAssignment    : '-='                                   ;
MultiplicationAssignment : '*='                                   ;
DivisionAssignment       : '/='                                   ;
PowAssignment            : '^='                                   ;
additionAssignment       : AdditionAssignment                     ;
subtractionAssignment    : SubtractionAssignment                  ;
multiplicationAssignment : MultiplicationAssignment               ;
divisionAssignment       : DivisionAssignment                     ;
powAssignment            : PowAssignment                          ;
Plus                     : '+'                                    ;
Minus                    : '-'                                    ;
Multiply                 : '*'                                    ;
Divide                   : '/'                                    ;
Pow                      : '^'                                    ;
plus                     : Plus                                   ;
minus                    : Minus                                  ;
multiply                 : Multiply                               ;
divide                   : Divide                                 ;
pow                      : Pow                                    ;
operator                 : arithmetic | comparing | not           ;

arithmetic : AdditionAssignment       |
             SubtractionAssignment    |
             MultiplicationAssignment |
             DivisionAssignment       |
             PowAssignment            |
             Plus                     |
             Minus                    |
             Multiply                 |
             Divide                   |
             Pow                      ;

// Connect sign(s).
AndSign : '&'     ;
OrSign  : '|'     ;
and     : AndSign ;
or      : OrSign  ;

// Comparator sign(s).
BreakingAndSign : '&&'              ;
BreakingOrSign  : '||'              ;
Not             : '!'               ;
breakingAnd     : BreakingAndSign   ;
breakingOr      : BreakingOrSign    ;
not             : Not               ;
lessThan        : LeftAngleBracket  ;
moreThan        : RightAngleBracket ;

Equals : '='    ;
equals : Equals ;

comparingAnd : and | breakingAnd ;
comparingOr  : or  | breakingOr  ;
comparing : ( comparingAnd | comparingOr | moreThan | lessThan | Equals ) ;

Null : 'null' ;
null : Null   ;
