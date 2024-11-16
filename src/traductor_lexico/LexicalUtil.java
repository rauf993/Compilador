/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package traductor_lexico;

/**
 *
 * @author fer-m
 */
public class LexicalUtil {
    
    public enum Token_values {
        ERROR,
        INTEGER,
        ID,
        STRING,
        REAL,
        ADD_SUB,
        DATA_TYPE,
        MULT_DIV,
        RELATION_OP,
        AND,
        OR,
        NOT,
        EQUAL,
        SEMICOLON,
        COMMA,
        OPEN_PARENTHESIS,
        CLOSE_PARENTHESIS,
        OPEN_BRACKETS,
        CLOSE_BRACKETS,
        ASSIGN,
        IF,
        WHILE,
        ELSE,
        RETURN
    }
    
    public static Boolean isType(String forAnalize){
        switch (forAnalize) {
            case "int":
                return true;
            case "float":
                return true;
            case "void":
                return true;
            default:
                return false;
        }
    }
    
    public static Boolean isRelType(String forAnalize){
        switch (forAnalize) {
            case "<":
                return true;
            case ">":
                return true;
            case "<=":
                return true;
            case ">=":
                return true;    
            default:
                return false;
        }
    }
    
    public static int isLogicOp(String forAnalize){
        switch (forAnalize) {
            case "&&":
                return 10;
            case "||":
                return 11;
            case "!":
                return 12;   
            default:
                return 0;
        }
    }
    
    public static Boolean isEqualOp(String forAnalize){
        switch (forAnalize) {
            case "==":
                return true;
            case "!=":
                return true;  
            default:
                return false;
        }
    }
    
    public static int isConditionalOp(String forAnalize){
        switch (forAnalize) {
            case "if":
                return 21;
            case "else":
                return 22;  
            default:
                return 0;
        }
    }
    
    public static int isReservedWord(String forAnalize){
        switch (forAnalize) {
            case "while":
                return 23;
            case "return":
                return 24;  
            default:
                return 0;
        }
    }
    
    public static Token_values getEnumValue(int numValue) {
        switch (numValue) {
            case 0:
                return Token_values.ID;
            case 1:
                return Token_values.INTEGER;
            case 2:
                return Token_values.REAL;
            case 3:
                return Token_values.STRING;
            case 4:
                return Token_values.DATA_TYPE;
            case 5:
                return Token_values.ADD_SUB;
            case 6:
                return Token_values.MULT_DIV;
            case 7:
                return Token_values.RELATION_OP;
            case 8:
                return Token_values.OR;
            case 9:
                return Token_values.AND;
            case 10:
                return Token_values.NOT;
            case 11:
                return Token_values.EQUAL;
            case 12:
                return Token_values.SEMICOLON;
            case 13:
                return Token_values.COMMA;
            case 14:
                return Token_values.OPEN_PARENTHESIS;
            case 15:
                return Token_values.CLOSE_PARENTHESIS;
            case 16:
                return Token_values.OPEN_BRACKETS;
            case 17:
                return Token_values.CLOSE_BRACKETS;
            case 18:
                return Token_values.ASSIGN;
            case 19:
                return Token_values.IF;
            case 20:
                return Token_values.WHILE;
            case 21:
                return Token_values.RETURN;
            case 22:
                return Token_values.ELSE;
            case -1:
                return Token_values.ERROR;
            default:
                return Token_values.ERROR;
        }
    }
    
    public static int getNumericalValue(int state) {
        switch (state) {
            case 0:
                return -1;
            case 1:
                return 0;
            case 2:
                return 0;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 2;
            case 6:
                return 5;
            case 7:
                return 4;
            case 8:
                return 6;
            case 9:
                return 7;
            case 10:
                return 9;
            case 11:
                return 8;
            case 12:
                return 10;
            case 13:
                return 11;
            case 14:
                return 12;
            case 15:
                return 13;
            case 16:
                return 14;
            case 17:
                return 15;
            case 18:
                return 16;
            case 19:
                return 17;
            case 20:
                return 18;
            case 21:
                return 19;
            case 22:
                return 22;
            case 23:
                return 20;
            case 24:
                return 21;
            case 25:
                return -1;
            case 26:
                return 3;
            case -1:
                return -1;
            default:
                return -1;
        }
    }
    public static String getType(int state) {
        switch (state) {
            case 0:
                return "Error";
            case 1:
                return "Identificador";
            case 2:
                return "Identificador";
            case 3:
                return "Entero";
            case 5:
                return "Número Real";
            case 6:
                return "Operador suma/resta";
            case 7:
                return "Tipo de dato";
            case 8:
                return "Operador mult/div";
            case 9:
                return "Operador de relación";
            case 10:
                return "Operador lógico AND";
            case 11:
                return "Operador lógico OR";
            case 12:
                return "Operador lógico NOT";
            case 13:
                return "Operador de igualdad";
            case 14:
                return "Operador de fin de instrucción";
            case 15:
                return "Operador de coma";
            case 16:
                return "Abrir parentesis";
            case 17:
                return "Cerrar parentesis";
            case 18:
                return "Abrir llave";
            case 19:
                return "Cerrar llave";
            case 20:
                return "Operador de asignación";
            case 21:
                return "Operador condicional";
            case 22:
                return "operador condicional Else";
            case 23:
                return "Ciclo condicional";
            case 24:
                return "Retorno de función";
            case 25:
                return "Error de sintaxis";
            case 26:
                return "Cadena";
            case -1:
                return "Error de sintaxis";
            default:
                return "Error de sintaxis";
        }
    }
}

