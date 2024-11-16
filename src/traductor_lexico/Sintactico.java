package traductor_lexico;

import java.util.List;
import traductor_lexico.LexicalUtil.Token_values;

public class Sintactico {
    List<Token> tokens;
    private int contador;

    public Sintactico(List<Token> tokens) {
        this.tokens = tokens;
        this.contador = 0;
    }
    
    public Node parse() {
        return parseProgram();
    }
    
    private Node parseProgram() {
        Node programNode = new Node("program", "");
        while (contador < tokens.size()) {
            Node statementNode = parseStatement();
            programNode.children.add(statementNode);
        }
        return programNode;
    }
    
    private Node parseStatement() {
        Token token = getNextToken();
        if (token != null) {
            if (token.enum_value == Token_values.ID) {
                // Assignment statement
                if (tokens.get(contador) != null && tokens.get(contador).enum_value == Token_values.ASSIGN) {
                    
                    Node assignmentNode = new Node("assignment", tokens.get(contador).value);
                    Node leftSideNode = parseExpression(contador-1);
                    contador ++;
                    Node expressionNode = parseExpression(); // Right-hand side of assignment
                    assignmentNode.children.add(leftSideNode);
                    assignmentNode.children.add(expressionNode);
                    
                    Node endOfLine = parseEndOfLine();
                    assignmentNode.children.add(endOfLine);
                    return assignmentNode;
                } else if (tokens.get(contador) != null && (tokens.get(contador).enum_value == Token_values.EQUAL || tokens.get(contador).enum_value == Token_values.RELATION_OP)){
                    Node comparisonNode = new Node("comparison", tokens.get(contador).value);
                    Node leftExpressionNode = parseExpression(contador-1);
                    contador++;
                    Node rightExpressionNode = parseExpression();
                    comparisonNode.children.add(leftExpressionNode);
                    comparisonNode.children.add(rightExpressionNode);
                    
                    Node endOfLine = parseEndOfLine();
                    comparisonNode.children.add(endOfLine);
                    return comparisonNode;
                }else{
                    // Handle error: Expected "="
                    Node errorNode = new Node("Error", "Expected =");
                    return errorNode;
                }
            } else if(token.enum_value == Token_values.IF){
                return parseIfStatement();
            } else if(token.enum_value == Token_values.WHILE){
                return parseWhileStatement();
            } else if(token.enum_value == Token_values.DATA_TYPE){
                return parseVariableDeclaration(token.value);
            }else {
                Node errorNode = new Node("Error", "Invalid statement");
                return errorNode;
            }
        }
        return null;
    }
    
    private Node parseIfStatement() {
        Node ifNode = new Node("ifStatement", "if");
        Node conditionNode = parseComparisonBlock();
        Node blockNode = parseBlock();
        ifNode.children.add(conditionNode);
        ifNode.children.add(blockNode);
        return ifNode;
    }
    
    
    
    
    private Node parseVariableDeclaration(String type){
        Node DataNode = new Node("Declaration", type);
        Token nextToken = getNextToken();
        if(nextToken != null && nextToken.enum_value == Token_values.ID){
            // CRear nuevo nodo con el id
            Node IDNode = new Node("Identifier", nextToken.value);
            // Asiganrselo a la lista del tipo de dato
            DataNode.children.add(IDNode);
            if(tokens.get(contador) != null && tokens.get(contador).enum_value == Token_values.ASSIGN){
                nextToken = getNextToken();
                Node expression = parseExpression();
                DataNode.children.add(expression);
                Node endOfLine = parseEndOfLine();
                DataNode.children.add(endOfLine);
                return DataNode;
            }else if(tokens.get(contador) != null && tokens.get(contador).enum_value == Token_values.SEMICOLON){
                Node endOfLine = parseEndOfLine();
                DataNode.children.add(endOfLine);
                return DataNode;
            }else{
                //Error
                Node errorNode = new Node("Error", "Invalid declaration");
                return errorNode;
            }
            
        }
        return DataNode;
    }
    
    private Node parseWhileStatement() {
        Node WhileNode = new Node("WhileStatement", "While");
        Node conditionNode = parseComparisonBlock();
        Node blockNode = parseBlock();
        WhileNode.children.add(conditionNode);
        WhileNode.children.add(blockNode);
        return WhileNode;
    }
    
    private Node parseComparisonBlock(){
        Node blockNode = new Node("Comparison Block", "");
        Token openParenthesis = getNextToken();
        if(openParenthesis != null && openParenthesis.enum_value == Token_values.OPEN_PARENTHESIS){
            Node compNode = parseComparison();
            blockNode.children.add(compNode);
            Token closeParenthesis = getNextToken();
            if (closeParenthesis != null && closeParenthesis.enum_value == Token_values.CLOSE_PARENTHESIS) {
                    return blockNode;
            } else {
                    // Handle error: Missing closing brace
                    Node errorNode = new Node("Error", "Expected )");
                    return errorNode;
            }
        } else {
            // Handle error: Missing opening brace
            Node errorNode = new Node("Error", "Expected (");
            return errorNode;
        }
    }
    
    private Node parseBlock() {
        Node blockNode = new Node("block", "");
        Token openBrace = getNextToken();
        if (openBrace != null && openBrace.enum_value == Token_values.OPEN_BRACKETS) {
            while (tokens.get(contador).enum_value != Token_values.CLOSE_BRACKETS) {
                Node statementNode = parseStatement();
                blockNode.children.add(statementNode);
            }
            Token closeBrace = getNextToken();
            if (closeBrace != null && closeBrace.enum_value == Token_values.CLOSE_BRACKETS) {
                return blockNode;
            } else {
                // Handle error: Missing closing brace
                Node errorNode = new Node("Error", "Expected }");
                return errorNode;
            }
        } else {
            // Handle error: Missing opening brace
            Node errorNode = new Node("Error", "Expected {");
            return errorNode;
        }
    }
    
    private Node parseExpression() {
        Token token = getNextToken();
        if (token != null && (token.enum_value == Token_values.ID || token.enum_value == Token_values.INTEGER || token.enum_value == Token_values.REAL)) {
            
            return new Node(token.type, token.value);
        } else if (token != null && token.enum_value == Token_values.OPEN_PARENTHESIS) {
            Node expressionNode = parseExpression();
            Token closingParenthesis = getNextToken();
            if (closingParenthesis != null && closingParenthesis.enum_value == Token_values.CLOSE_PARENTHESIS) {
                return expressionNode;
            } else {
                // Handle error: Expected ")"
                Node errorNode = new Node("Error", "Expected )");
                return errorNode;
            }
        } else {
            // Handle error: Invalid expression
            Node errorNode = new Node("Error", "Invalid expression");
            return errorNode;
        }
    }
    
    private Node parseExpression(int i) {
        Token token = tokens.get(i);
        if (token != null && (token.enum_value == Token_values.ID || token.enum_value == Token_values.INTEGER || token.enum_value == Token_values.REAL)) {
            
            return new Node(token.type, token.value);
        } else if (token != null && token.enum_value == Token_values.OPEN_PARENTHESIS) {
            Node expressionNode = parseExpression();
            Token closingParenthesis = tokens.get(i+1);
            if (closingParenthesis != null && closingParenthesis.enum_value == Token_values.CLOSE_PARENTHESIS) {
                return expressionNode;
            } else {
                // Handle error: Expected ")"
                Node errorNode = new Node("Error", "Expected )");
                return errorNode;
            }
        } else {
            // Handle error: Invalid expression
            Node errorNode = new Node("Error", "Invalid expression");
            return errorNode;
        }
    }
    
    private Node parseComparison() {
        Node leftExpressionNode = parseExpression();
        Token opToken = getNextToken();
        if (opToken != null && (opToken.enum_value == Token_values.EQUAL || opToken.enum_value == Token_values.RELATION_OP)) {
            Node comparisonNode = new Node("comparison", opToken.value);
            Node rightExpressionNode = parseExpression(); // Right-hand side of the comparison
            comparisonNode.children.add(leftExpressionNode);
            comparisonNode.children.add(rightExpressionNode);
            return comparisonNode;
        } else {
            // Handle error: Invalid comparison operator
            Node errorNode = new Node("Error", "Invalid comparision operator");
            System.out.println(opToken.enum_value);
            return errorNode;
        }
    }
    
    private Node parseEndOfLine(){
        Token end = getNextToken();
        if(end != null && end.enum_value == Token_values.SEMICOLON){
            Node endOfLine = new Node("Fin del linea", end.value);
            return endOfLine;
        }else {
            // Handle error: Expected end of line
            Node errorNode = new Node("Error", "Expected ;");
            return errorNode;
        }
    }
    
    
    private Token getNextToken() {
        if (contador < tokens.size()) {
            return tokens.get(contador++);
        }
        return null;
    }

    public boolean isLibreria(String token) {
        return token.equals("#include<iostream>");
    }
    public boolean isNumero(String token) {
    try {
         if (token.equals("main")) {
            return true;
        }
         if(token.equals("1")){
          //double parsedValue = Double.parseDouble(token); 
          return true;
         }
         else{
             System.out.println("error");
         }
        
       
    } catch (NumberFormatException e) {
       
    }
    return false;
}

    
    public boolean isOperador(String token) {
        String[] tipos = new String[]{"+", "-", "*", "/"};
        for (String tipo : tipos) {
            if (token.equals(tipo)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTipo(String token) {
        String[] tipos = new String[]{"Void", "bool", "char", "int", "float"};
        for (String tipo : tipos) {
            if (token.equals(tipo)) {
                return true;
            }
        }
        return false;
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
    
    public static Boolean  isReservedWord(String forAnalize){
        switch (forAnalize) {
            case "while":
                return true;
            case "return":
                return true;  
            default:
                return false;
        }
    }
    public void corchetes(String palabra,int cont){
        
    }
    public void parentesis(String palabra,int cont){
        cont++;
         var siguienteToken2=tokens.get(cont);
        if(palabra.equals("("))
            System.out.println("palabra correcta"+palabra);
            if(siguienteToken2.equals(")")){
                contador++;
                var siguienteToken3=tokens.get(contador);
                corchetes(siguienteToken3.value,contador);
                
            }
        
        
    }
    
   public static String printTree(Node node, String indent) {
    StringBuilder result = new StringBuilder();
    result.append(indent).append(node.type).append(" ").append(node.value).append("\n");
    
    for (Node child : node.children) {
        if (child != null) {
            result.append(printTree(child, indent + "  "));
        }
    }

    return result.toString();
}

    public void analizar() {
        
        for(int i=0;i<this.tokens.size();i++){
            System.out.println(this.tokens.get(i));
        }
        
        

        String tokenActual = tokens.get(contador).value;

        if (isLibreria(tokenActual)) {
            System.out.println("Libreria aceptada: " + tokenActual);
            contador++;

            if (contador >= tokens.size()) {
                System.err.println("Error: No se encontro un tipo de dato despues de la libreria");
                return;
            }

            String siguienteToken = tokens.get(contador).value;

            if (isTipo(siguienteToken)) {
                System.out.println("Tipo de dato aceptado: " + siguienteToken);
                contador++;
                String siguienteToken1 = tokens.get(contador).value;
                if(isNumero(siguienteToken1)){
                     System.out.println("Tipo de dato aceptado: " + siguienteToken1);
                     contador++;
                     String siguienteToken2 = tokens.get(contador).value;
                     parentesis(siguienteToken2,contador);
                }
            } else {
                System.err.println("Error: No se encontro un tipo de dato despues de la libreria");
            }
        } else {
            System.err.println("Error: No se encontro un tipo de dato despues de la libreria");
        }
    }
}