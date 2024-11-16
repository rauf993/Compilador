package traductor_lexico;

import java.util.HashMap;
import java.util.List;
import traductor_lexico.LexicalUtil.Token_values;

public class Semantico {

    private HashMap<String, Boolean> variables;

    public Semantico() {
        this.variables = new HashMap<>();
    }

    public void analizarSemantica(List<Token> tokens) {
        for (Token token : tokens) {
            // Agrega más casos según sea necesario
            if (token.type.equals("IDENTIFIER")) {
                analizarIdentificador(token.value);
            }
        }

        // Al final del análisis, verifica las variables no utilizadas
        for (String variable : variables.keySet()) {
            if (!variables.get(variable)) {
                System.out.println("Advertencia: La variable '" + variable + "' fue declarada pero no utilizada.");
            }
        }
    }

    private void analizarIdentificador(String identifier) {
        // Verifica si la variable ya ha sido declarada
        if (variables.containsKey(identifier)) {
            // Marca la variable como utilizada
            variables.put(identifier, true);
        } else {
            // Si la variable no ha sido declarada, agrégala a la lista de variables
            variables.put(identifier, false);
        }
    }
}
