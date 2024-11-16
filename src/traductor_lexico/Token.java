/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package traductor_lexico;

/**
 *
 * @author raufa
 */

import traductor_lexico.LexicalUtil.Token_values;

/**
 *
 * @author fer-m
 */
public class Token {
    
    public String type;
    public String value;
    public int num;
    public Token_values enum_value;

    public Token(String type, String value, int num, Token_values enum_value) {
        this.type = type;
        this.value = value;
        this.num = num;
        this.enum_value = enum_value;
    }
}
