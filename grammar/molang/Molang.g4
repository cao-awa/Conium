grammar Molang;

import MolangRules;

program : defineStatement+ ;

invokeStatement : identifier leftParen (invokeParam? multiInvokeParam?) rightParen ;

assignmentStatement : fullNameOrIdentifier equals defineReturnableStatement ;

returnStatement : return (defineReturnableStatement | fullNameOrIdentifier | Number) ;

defineStatement : (invokeStatement | assignmentStatement | returnStatement) Semicolon?;

defineReturnableStatement : invokeStatement | fullNameOrIdentifier  | number | string | bool | calculateStatement ;

calculatableResultPresenting: calculateStatementWithParen | invokeStatement | constant | identifier | fullName ;

calculateStatementWithParen: leftParen ( calculateLeftStatementWithParen ) rightParen extraCalculateStatement* ;

calculateLeftStatementWithParen: calculateStatement | calculatableResultPresenting ;

calculateStatement: (calculateLeft extraCalculateStatement* ) | (calculateStatementWithTotalParen extraCalculateStatement*) | calculateStatementWithParen ;

calculateStatementWithTotalParen: leftParen calculateLeft extraCalculateStatement* rightParen;

calculateLeft: calculatableResultPresenting ;

extraCalculateStatement: operator calculatableResultPresenting ;

calculateStatementRight : (invokeStatement | fullNameOrIdentifier | number | string) ;

invokeParam : defineReturnableStatement ;

multiInvokeParam : (Comma invokeParam) * ;

constant : ( string | number | bool | Null);
