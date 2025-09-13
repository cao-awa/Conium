grammar Molang;

import MolangRules;

program : defineStatement+ ;

invokeStatement : Identifier leftParen (invokeParam? multiInvokeParam?) rightParen ;

assignmentStatement : fullNameOrIdentifier equals defineReturnableStatement ;

returnStatement : return (defineReturnableStatement | fullNameOrIdentifier | Number) ;

defineStatement : (invokeStatement | assignmentStatement | returnStatement) Semicolon?;

defineReturnableStatement : invokeStatement | fullNameOrIdentifier | number | string | calculateStatement;

calculatableResultPresenting: calculateStatementWithParen | invokeStatement | constant | identifier | fullName ;

calculateStatementWithParen: leftParen ( calculateLeftStatementWithParen ) rightParen extraCalculateStatement* ;

calculateLeftStatementWithParen: calculateStatement | calculatableResultPresenting ;

calculateStatement: (calculateLeft extraCalculateStatement* ) | (calculateStatementWithTotalParen extraCalculateStatement*) | calculateStatementWithParen ;

calculateStatementWithTotalParen: leftParen calculateLeft extraCalculateStatement* rightParen;

calculateLeft: calculatableResultPresenting ;

extraCalculateStatement: operator calculatableResultPresenting ;

calculateStatementRight : (invokeStatement | fullNameOrIdentifier | number | string) ;

invokeParam : Identifier | Number | bool ;

multiInvokeParam : (Comma invokeParam) * ;

constant: ( String | number | bool | Null);
