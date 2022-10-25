
package codigo;

import compilerTools.Token;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author osmar
 */
public class Semantic {
    private ArrayList<Token> tokens = new ArrayList<>();
    private String erroresSintactico = "";
    private FrmPrincipal compilador;
    private String erroresSemantico = "";

    public Semantic(ArrayList<Token> tokens, FrmPrincipal compilador, String erroresSintactico) {
        this.compilador = compilador;
        this.tokens = tokens;
        this.erroresSintactico = erroresSintactico;
    }
    
    
    public void realizarAnalisisSemantico() {
        //Funcion que comprueba si hay errores, si los hay retorna un true
        //y evita que el analisis semantico se ejecute
        if (hayErrores(erroresSintactico)) {
           compilador.txtError.setText("ERROR! Existen errores sintacticos, por lo que no se realizo el analisis semantico");
            return;
        }
        
        revisarIdentificadoresDeVariables();
        
        revisarPrint();
        
        String txt = compilador.txtError.getText();
        if (erroresSemantico.isBlank()) {
            compilador.txtError.setText(txt + "\nAnalisis semantico: Sin errores");
        } else {
            compilador.txtError.setText(txt + "\n" + erroresSemantico);
        }

    }
    public boolean hayErrores (String errores){
            return errores.isBlank();
        }
    
    private void revisarPrint() {
        for (Iterator<Token> it = tokens.iterator(); it.hasNext();) {
            Token token = it.next();

            if (token.getLexicalComp().equals("pantalla")) {
                token = it.next();
                token = it.next();
                //Primero preguntar si el token es un identificador
                if (token.getLexicalComp().equals("Identificadores")) {
                    System.out.println("hola");

                    //Buscar la primera declaracion del identificador
                    System.out.println(tokens.get(tokens.indexOf(token)).getLexeme() + " En la linea: " + tokens.get(tokens.indexOf(token)).getLine());

                    //Preguntar el tipo de dato de ese identificador
                    //Si no es de tipo string entonces enviamos por pantalla un mensaje de error
                    if (!token.getLexeme().equals("string")) {
                        erroresSemantico += "IDENTIFICADOR TIPO DE DATO INCORRECTO!\n";
                        erroresSemantico += "El identificador: " + token.getLexeme() + " en la linea: " + token.getLine() + ", deberia ser de tipo string pero esta declarado como: \n";
                    }
                }
            }
        }
    }

    
    private void revisarIdentificadoresDeVariables() {

        ArrayList<Token> tokensAuxiliar = new ArrayList<>();
        Token tAux;
        for (Iterator<Token> it = tokens.iterator(); it.hasNext();) {
            Token token = it.next();

            if (token.getLexicalComp().equals("variable")) {
                token = it.next();
                //Primero preguntar si el token es un identificador
                if (token.getLexicalComp().equals("Identificadores")) {

                    //Buscar si ya existe este identificador
                    tAux = buscarDuplicado(tokensAuxiliar, token);

                    //si esta vacio entonces significa que no esta duplicado
                    //Por lo que se guarda el identificadoe en tokensAuxiliar
                    //Si esta duplicado entonces
                    //envia por consola el identificador "original", el duplicado y su renglon.
                    if (!(tAux == null)) {
                        erroresSemantico += "IDENTIFICADOR DUPLICADO!\n";
                        erroresSemantico += "Identificador original: " + tAux.getLexeme() + " en la linea: " + tAux.getLine() + "\n";
                        erroresSemantico += "Identificador duplicado: " + token.getLexeme() + " en la linea: " + token.getLine() + "\n";
                        //break;
                    } else {
                        tokensAuxiliar.add(token);
                    }
                }
            }

        }
    }
 private Token buscarDuplicado(ArrayList<Token> tokensAuxiliar, Token token) {
        for (int i = 0; i < tokensAuxiliar.size(); i++) {
            if (tokensAuxiliar.get(i).getLexeme().equals(token.getLexeme())) {
                return tokensAuxiliar.get(i);
            }
        }
        return null;
    }
}
