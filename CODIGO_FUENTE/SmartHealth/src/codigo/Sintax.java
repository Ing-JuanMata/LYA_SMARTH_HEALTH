/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanj
 */
public class Sintax {

    private ArrayList<Token> tokens;
    private int cont;
    DefaultTableModel analisis;
    public static java.util.HashMap<Integer, String> gramas;
    public static java.util.HashMap<Integer, String> reglas;

    public Sintax(java.util.ArrayList<Token> tokens) {
        this.tokens = tokens;
        analisis = (DefaultTableModel) FrmPrincipal.tblAnalisis.getModel();
        analisis.setRowCount(0);
        cont = 0;
        gramas = new java.util.HashMap<>();
        reglas = new java.util.HashMap<>();
    }

    public void parse() {
        cont = 0;
        E0();
    }

    private boolean validarToken(String token, String lex) {
        Token t = tokens.get(cont++);
        if (t.tipo.equals(token)) {
            return true;
        }

        FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba (" + lex + ") en la posicion " + t.linea + ", " + t.columna);
        return false;
    }

    private boolean hayTokens() {
        return tokens.size() > cont;
    }

    private void E0() {
        String analisis = "E0:";

        Token t = tokens.get(cont);
        if (validarToken("Start", "start")) {
            analisis += "Avanza hacia el E1";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            E1(true);
            return;
        }
        analisis += "Avanzamos hacia el E1, pero no se acepta el analisis";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E1(false);
    }

    private void E1(boolean aceptado) {
        String analisis = "E1:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        t = tokens.get(cont);
        if (validarToken("identificador", "identificador")) {
            analisis += "Avanza hacia el E2";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            E2(aceptado);
            return;

        }

        analisis += "Avanzamos hacia el E2, pero no se acepta el analisis";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E2(false);
    }

    private void E2(boolean aceptado) {
        String analisis = "E2:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        t = tokens.get(cont);
        if (validarToken("Llave_a", "{")) {
            analisis += "Avanza hacia el E3";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            E3(aceptado);
            return;

        }

        analisis += "Avanzamos hacia el E3, pero no se acepta el analisis";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E3(false);
    }

    private void E3(boolean aceptado) {
        String analisis = "E3:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        t = tokens.get(cont);
        if (validarToken("Atlas", "Atlas")) {
            analisis += "Avanza hacia el E4";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            E4(aceptado);
            return;

        }

        analisis += "Avanzamos hacia el E4, pero no se acepta el analisis";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E4(false);
    }

    private void E4(boolean aceptado) {
        String analisis = "E4:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        t = tokens.get(cont);
        if (validarToken("Begin", "begin")) {
            analisis += "Avanza hacia el E5";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            E5(aceptado);
            return;

        }

        analisis += "Avanzamos hacia el E5, pero no se acepta el analisis";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E5(false);
    }

    private void E5(boolean aceptado) {
        String analisis = "E5:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        t = tokens.get(cont);
        if (validarToken("Parentesis_a", "(")) {
            analisis += "Avanza hacia el E6";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            E6(aceptado);
            return;

        }

        analisis += "Avanzamos hacia el E6, pero no se acepta el analisis";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E6(false);
    }

    private void E6(boolean aceptado) {
        String analisis = "E6:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        t = tokens.get(cont);
        if (validarToken("Parentesis_c", ")")) {
            analisis += "Avanza hacia el E7";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            E7(aceptado);
            return;

        }

        analisis += "Avanzamos hacia el E7, pero no se acepta el analisis";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E7(false);
    }

    private void E7(boolean aceptado) {
        String analisis = "E7:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        t = tokens.get(cont);
        if (validarToken("Llave_a", "{")) {
            analisis += "Avanza hacia el E8";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            E8(aceptado);
            return;

        }

        analisis += "Avanzamos hacia el E8, pero no se acepta el analisis";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E8(false);
    }

    private void E8(boolean aceptado) {
        String analisis = "E8:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        Gramaticas gramaticas = new Gramaticas(tokens);
        while (hayTokens()) {
            if (tokens.get(cont++).tipo.equals("Llave_c")) {
                cont--;
                break;
            }
            try {
                cont = gramaticas.evaluar(cont - 1);
            } catch (Exception ex) {
                FrmPrincipal.errores.add("ERROR SINTACTICO: Se han terminado los tokens para analizar");
                analisis += "No hay mas tokens para usar";
                this.analisis.addRow(new Object[]{tokens.get(tokens.size() - 1).lexema, analisis});
                return;
            }
        }
        t = tokens.get(cont);
        if (!validarToken("Llave_c", "}")) {
            analisis += "Se rechaza la estructura";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            return;
        }
        analisis += "Avanza hacia el E9";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E9(aceptado);
    }

    private void E9(boolean aceptado) {
        String analisis = "E9:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        t = tokens.get(cont);
        if (validarToken("Llave_c", "}")) {
            analisis += "Avanza hacia el E10";
            this.analisis.addRow(new Object[]{t.lexema, analisis});
            E10(aceptado);
            return;

        }

        analisis += "Avanzamos hacia el E10, pero no se acepta el analisis";
        this.analisis.addRow(new Object[]{t.lexema, analisis});
        E10(false);
    }

    private void E10(boolean aceptado) {
        String analisis = "E10:";
        Token t;
        if (!hayTokens()) {
            t = tokens.get(cont - 1);
            FrmPrincipal.errores.add("ERROR SINTACTICO: No se han encontrado mas tokens despues de " + t.lexema + " En la posicion (" + t.linea + "," + t.columna + ") y la estructura ha quedado incompleta");
            analisis += "No avanza y se rechaza";
            this.analisis.addRow(new Object[]{"Vacio", analisis});
            return;
        }
        t = tokens.get(cont);
        if (validarToken("End", "End")) {
            analisis += "Avanza hacia el E11 y se " + (aceptado ? "acepta la estructura" : "rechaza la estructura");
            this.analisis.addRow(new Object[]{t.lexema, analisis});
        }
    }
}
