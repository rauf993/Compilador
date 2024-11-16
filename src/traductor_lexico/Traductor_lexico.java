    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
     */
    package traductor_lexico;
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;

/*
public class Traductor_lexico {

    public static void main(String[] args) {
        if (args.length > 0) {
            String contenidoDelTextArea = args[0];
            
            List<String> tokens = new ArrayList<>();
            Sintactico sintactico;

            String[] palabras = contenidoDelTextArea.split(" ");
            for (String palabra : palabras) {
                if (palabra.equals("(true)")) {
                    tokens.add("(");
                    tokens.add("true");
                    tokens.add(")");
                } else if (palabra.equals("return;")) {
                    tokens.add("return");
                    tokens.add(";");
                } else {
                    tokens.add(palabra);
                }
            }

            sintactico = new Sintactico(tokens);
            sintactico.analizar();
            
             /* var lexical = new Lexical(tokens);

                    lexical.analize();
                    lexical.getResults();

        } else {
            System.out.println("No se proporcionó contenido desde el TextArea.");
        }
    }
}
/*

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fer-m
 */
public class Traductor_lexico {
   

    public static void main(String[] args) throws IOException {
        String Entrada = "C:/Users/raufa/OneDrive/Documentos/NetBeansProjects/fichero1.txt";
        String Salida = "resources/fichero1.txt";
       
        List<String> tokens = new ArrayList<>();
        Lexical lexical;
        List<Token> tokens_analized;
        Sintactico sintactico;
        String contenidoDelTextArea = args[0];
        String bandera="true";
        
        try (BufferedReader lectura = new BufferedReader(new FileReader(Entrada))) {
            String linea;

           //while ((linea = lectura.readLine()) != null) {

                //String[] palabras = linea.split(" ");
                String[] palabras= contenidoDelTextArea.split(" ");
                System.out.println("hola");
                
                for (String palabra : palabras) {
                    if(!"".equals(palabra)){
                        tokens.add(palabra);
                    }
                    
                }
                
            //}
            
            if(args.length>1){
                 bandera=args[1];
                if(bandera=="false"){
                     lexical = new Lexical(tokens);
            
                    lexical.analize();
                    

                     tokens_analized = lexical.getResults();
                     
                     
                    
                }
                
                
            }else{
                lexical = new Lexical(tokens);
            
                lexical.analize();

                tokens_analized = lexical.getResults();
                sintactico = new Sintactico(tokens_analized);
                Node root = sintactico.parse();
           
          
                 sintactico.printTree(root, "");
                 String syntaxTree = sintactico.printTree(root, "");
                interfazCompilador interfaz = new interfazCompilador();
                 
                 interfaz.setTextArea3(syntaxTree);
                 System.out.println(syntaxTree);
                     
                 
                   
            }
            

           
            
            
            
            
             //sintactico = new Sintactico(tokens_analized);
            
            
            
            
           
            
            
            

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
