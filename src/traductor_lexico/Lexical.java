/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package traductor_lexico;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author raufa
 */
import java.util.ArrayList;
import java.util.List;


public class Lexical {

    private List<String> tokens;
    private List<Integer> states;
    private int state;

    public Lexical(List<String> tokens) {
        this.tokens = tokens;
        this.states = new ArrayList<>();
        this.state = 0;
    }

    public void generateState(String toAnalize) {
        this.state = 0;
        for (char i : toAnalize.toCharArray()) {
            
            if(LexicalUtil.isType(toAnalize)){
                this.states.add(7);
                return;
            }
            
            if(LexicalUtil.isRelType(toAnalize)){
                this.states.add(9);
                return;
            }
            
            if(LexicalUtil.isLogicOp(toAnalize) != 0){
                this.states.add(LexicalUtil.isLogicOp(toAnalize));
                return;
            }
            
            if(LexicalUtil.isEqualOp(toAnalize)){
                this.states.add(13);
                return;
            }
            
            if(LexicalUtil.isConditionalOp(toAnalize) != 0){
                this.states.add(LexicalUtil.isConditionalOp(toAnalize));
                return;
            }
            
            if(LexicalUtil.isReservedWord(toAnalize) != 0){
                this.states.add(LexicalUtil.isReservedWord(toAnalize));
                return;
            }
            
            switch (this.state) {
                case 0:
                    if (Character.isAlphabetic(i)) {
                        this.state = 1;
                    } else if (Character.isDigit(i)) {
                        this.state = 3;
                    } else if (i == '+' || i == '-') {
                        this.state = 6;
                    } else if (i == '*' || i == '/') {
                        this.state = 8;
                    } else if (i == ';') {
                        this.state = 14;
                    } else if (i == ',') {
                        this.state = 15;
                    } else if (i == '(') {
                        this.state = 16;
                    } else if (i == ')') {
                        this.state = 17;
                    } else if (i == '{') {
                        this.state = 18;
                    } else if (i == '}') {
                        this.state = 19;
                    } else if (i == '=') {
                        this.state = 20;
                    }else if(i == '"'){
                        this.state = 25;
                    }
                    break;
                case 1:
                    if (Character.isAlphabetic(i)) {
                        this.state = 1;
                    } else if (Character.isDigit(i)) {
                        this.state = 2;
                    } else {
                        this.state = -1;
                    }
                    break;
                case 2:
                    if (Character.isAlphabetic(i)) {
                        this.state = 1;
                    } else if (Character.isDigit(i)) {
                        this.state = 2;
                    } else {
                        this.state = -1;
                    }
                    break;
                case 3:
                    if (Character.isDigit(i)) {
                        this.state = 3;
                    } else if (i == '.') {
                        this.state = 4;
                    } else {
                        this.state = -1;
                    }
                    break;
                case 4:
                    if (Character.isDigit(i)) {
                        this.state = 5;
                    } else {
                        this.state = -1;
                    }
                    break;
                case 5:
                    if (Character.isDigit(i)) {
                        this.state = 5;
                    } else {
                        this.state = -1;
                    }
                    break;
                case 25:
                    if(i=='"'){
                        this.state = 26;
                    }else{
                        this.state = 25;
                    }
                    break;
                default:
                    this.state = -1;
            }
        }
        this.states.add(this.state);
    }

    public void analize() {
        for (int i = 0; i < this.tokens.size(); i++) {
            this.generateState(this.tokens.get(i));
        }
    }

    public List<Token> getResults() {
        List<Token> new_tokens = new ArrayList<>();
        int i = 0;
        int num_val;
        for (int elem : this.states) {
            num_val = LexicalUtil.getNumericalValue(elem);
            System.out.println("El token " + this.tokens.get(i) + " es de tipo " + LexicalUtil.getType(elem) + " " + LexicalUtil.getNumericalValue(elem));
            new_tokens.add(new Token(LexicalUtil.getType(elem), this.tokens.get(i), num_val, LexicalUtil.getEnumValue(num_val)));
            i++;
        }
        return new_tokens;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public List<Integer> getStates() {
        return states;
    }

    public void setStates(List<Integer> states) {
        this.states = states;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
