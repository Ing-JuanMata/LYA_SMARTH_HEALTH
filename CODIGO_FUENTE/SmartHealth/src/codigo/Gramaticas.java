/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

/**
 *
 * @author juanj
 */
public class Gramaticas {

    private java.util.ArrayList<Token> tokens;
    private String regla;
    private int gramaticas = 0;

    public Gramaticas(java.util.ArrayList<Token> tokens) {
        this.tokens = tokens;
        FrmPrincipal.txtGramaticas.setText("");
    }

    public int evaluar(int cont) throws Exception {
        if (!Sintax.reglas.containsKey(1)) {
            Sintax.reglas.put(1, "GRAMATICAS->INCDEC | identificador Declare DECLARACION | identificador Asignacion ASIGNACION | identificador Op_Atribucion OP_ATRIBUCION | Condition CONDITION | Ventilate VENTILATE | admit ADMIT | EmptyRoom EMPTY_ROOM | Dispense DISPENSE | Distance DISTANCE | DeviceControl DEVICE_CONTROL | DriverLights DRIVER_LIGHTS | OpenDoor OPEN_DOOR | RegisterA REGISTER_A  | WHILE | FOR | EXIT");
        }
        switch (tokens.get(cont++).tipo) {
            case "Punto_Coma", "Llave_c" -> {
                return cont;
            }
            case "Op_Incremento" -> {
                if (!Sintax.reglas.containsKey(2)) {
                    Sintax.reglas.put(2, "INCDEC->identificador Op_Incremento Punto_Coma | Op_Incremento identificador Punto_Coma");
                }
                if (tokens.get(cont++).tipo.equals("identificador")) {
                    if (tokens.get(cont).tipo.equals("Punto_Coma")) {
                        //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + ++gramaticas + " INCDEC::= Op_Incremento identificador Punto_Coma");
                        Sintax.gramas.put(++gramaticas, " INCDEC::= Op_Incremento identificador Punto_Coma");
                        return ++cont;
                    } else {
                        FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
                        //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + ++gramaticas + " INCDEC::= Op_Incremento identificador ERROR");
                        Sintax.gramas.put(++gramaticas, " INCDEC::= Op_Incremento identificador ERROR");
                        return cont;
                    }
                }
            }
            case "identificador" -> {
                switch (tokens.get(cont++).tipo) {
                    case "Declare" -> {
                        if (!Sintax.reglas.containsKey(3)) {
                            Sintax.reglas.put(3, "DECLARACION->identificador Declare As TIPO_DATO Punto_Coma | identificador Declare As TIPO_DATO Punto_Coma Asignacion CAMPO_TEXTO Punto_Coma | identificador Declare As TIPO_DATO Punto_Coma Asignacion OP_ARITMETICA Punto_Coma");
                        }
                        return declaracion(cont);
                    }
                    case "Asignacion" -> {
                        if (!Sintax.reglas.containsKey(4)) {
                            Sintax.reglas.put(4, "ASIGNACION->identificador Asignacion OP_ARITMETICA Punto_Coma");
                        }
                        return asignacion(cont);
                    }
                    case "Op_Atribucion" -> {
                        if (!Sintax.reglas.containsKey(5)) {
                            Sintax.reglas.put(5, "OP_ATRIBUCION->identificador Op_Atribucion OP_ARITMETICA Punto_Coma");
                        }
                        return opAtribucion(cont);
                    }
                    case "Op_Incremento" -> {
                        if (!Sintax.reglas.containsKey(2)) {
                            Sintax.reglas.put(2, "INCDEC->identificador Op_Incremento Punto_Coma | Op_Incremento identificador Punto_Coma");
                        }
                        if (tokens.get(cont).tipo.equals("Punto_Coma")) {
                            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + ++gramaticas + " INCDEC::= identificador Op_Incremento Punto_Coma");
                            Sintax.gramas.put(++gramaticas, " INCDEC::= Op_Incremento identificador Punto_Coma");
                            return ++cont;
                        } else {
                            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
                            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + ++gramaticas + " INCDEC::= identificador Op_Incremento Punto_Coma");
                            Sintax.gramas.put(++gramaticas, " INCDEC::= Op_Incremento identificador ERROR");
                            return ++cont;
                        }

                    }
                    default -> {
                        FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( =, [++, --], [+=, *=, /= -=], Declare  ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
                        //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + "NO SE HA PODIDO DEFINIR UNA GRAMATICA");
                        Sintax.gramas.put(++gramaticas, "NO SE HA PODIDO DEFINIR UNA GRAMATICA");
                        return cont;
                    }
                }
            }
            case "Condition" -> {
                if (!Sintax.reglas.containsKey(6)) {
                    Sintax.reglas.put(6, "CONDITION->Condition Parentesis_a SENTENCIA_BOOLEANA Parentesis_c Llave_a GRAMATICAS Llave_c");
                }
                return condition(cont);
            }
            case "Ventilate" -> {
                if (!Sintax.reglas.containsKey(7)) {
                    Sintax.reglas.put(7, "VENTILATE->Ventilate Parentesis_a OP_ARITMETICA Coma SENTENCIA_BOOLEANA Coma SENTENCIA_BOOLEANA Coma SENTENCIA_BOOLEANA Parentesis_c Punto_Coma");
                }
                return ventilate(cont);
            }
            case "admit" -> {
                if (!Sintax.reglas.containsKey(8)) {
                    Sintax.reglas.put(8, "ADMIT->Admit Parentesis_a SENTENCIA_BOOLEANA Coma OP_ARITMETICA Coma OP_ARITMETICA Parentesis_c Punto_Coma");
                }
                return admit(cont);
            }
            case "EmptyRoom" -> {
                if (!Sintax.reglas.containsKey(9)) {
                    Sintax.reglas.put(9, "EMPTY_ROOM->EmptyRoom Parentesis_a SENTENCIA_BOOLEANA Coma OP_ARITMETICA Coma SENTENCIA_BOOLEANA Coma SENTENCIA_BOOLEANA Parentesis_c Punto_Coma");
                }
                return emptyRoom(cont);
            }
            case "Dispense" -> {
                if (!Sintax.reglas.containsKey(10)) {
                    Sintax.reglas.put(10, "DISPENSE->Dispense Parentesis_a SENTENCIA_BOOLEANA Coma OP_ARITMETICA Coma SENTENCIA_BOOLEANA Parentesis_c Punto_Coma");
                }
                return dispense(cont);
            }
            case "Distance" -> {
                if (!Sintax.reglas.containsKey(11)) {
                    Sintax.reglas.put(11, "DISTANCE->Distance Parentesis_a Corchete_a DEVICE Corchete_c Parentesis_c Punto_Coma");
                }
                return distance(cont);
            }
            case "DeviceControl" -> {
                if (!Sintax.reglas.containsKey(12)) {
                    Sintax.reglas.put(12, "DEVICE_CONTROL->DeviceControl Parentesis_a SENTENCIA_BOOLEANA Coma Corchete_a");
                }
                return deviceControl(cont);
            }
            case "DriverLights" -> {
                if (!Sintax.reglas.containsKey(13)) {
                    Sintax.reglas.put(13, "DRIVER_LIGHTS->DriverLights Parentesis_a OP_ARITMETICA Coma Corchete_a LIGHT Corchete_c Parentesis_c Punto_Coma");
                }
                return driverLights(cont);
            }
            case "OpenDoor" -> {
                if (!Sintax.reglas.containsKey(14)) {
                    Sintax.reglas.put(14, "OPEN_DOOR->OpenDoor Parentesis_a OP_ARITMETICA Coma SENTENCIA_BOOLEANA Parentesis_c Punto_Coma");
                }
                return openDoor(cont);
            }
            case "RegisterA" -> {
                if (!Sintax.reglas.containsKey(15)) {
                    Sintax.reglas.put(15, "REGISTER_A->registerA Parentesis_a CAMPO_TEXTO Parentesis_c Punto_Coma");
                }
                return registerA(cont);
            }
            case "While" -> {
                if (!Sintax.reglas.containsKey(16)) {
                    Sintax.reglas.put(16, "WHILE->While Parentesis_a SENTENCIA_BOOLEANA Parentesis_c Llave_a GRAMATICAS llave_c");
                }
                return While(cont);
            }
            case "For" -> {
                if (!Sintax.reglas.containsKey(17)) {
                    Sintax.reglas.put(17, "FOR->For Parentesis_a DECLARACION Punto_Coma SENTENCIA_BOOLEANA Punto_Coma OP_ATRIBUCION Parentesis_c Llave_a GRAMATICAS Llave_c | For Parentesis_a ASIGNACION Punto_Coma SENTENCIA_BOOLEANA Punto_Coma INCDEC Parentesis_c Llave_a GRAMATICAS Llave_c");
                }
                return For(cont);
            }
            case "Exit" -> {
                if (!Sintax.reglas.containsKey(18)) {
                    Sintax.reglas.put(18, "EXIT->Exit Punto_Coma");
                }
                regla = ++gramaticas + " EXIT::= Exit ";
                if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
                    regla += "Punto_Coma";
                    //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + ++gramaticas + regla);
                    Sintax.gramas.put(++gramaticas, regla);
                    return cont;
                } else {
                    regla += "ERROR";
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
                    //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                    Sintax.gramas.put(++gramaticas, regla);
                    return cont;
                }
            }
            default -> {
                FrmPrincipal.errores.add("ERROR SINTACTICO: lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna + " fuera de contexto");
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + ++gramaticas + "NO SE PUDO FORMAR UNA REGLA GRAMATICA");
                Sintax.gramas.put(++gramaticas, "NO SE PUDO FORMAR UNA REGLA GRAMATICA");
                return cont;
            }
        }

        return cont;
    }

    private int opAtribucion(int cont) throws Exception {
        regla = "OP_ATRIBUCION::= identificador Op_Atribucion ";
        int n = ++gramaticas;
        String r = regla + "OP_ARITMETICA ";
        cont = opAritmetica(cont);
        regla = r;
        if (tokens.get(cont).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma";
            Sintax.gramas.put(n, regla);
            return cont;
        } else {
            regla += "ERROR";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
            Sintax.gramas.put(n, regla);
            return cont;
        }

    }

    private boolean campoDec(int cont) throws Exception {
        if (!Sintax.reglas.containsKey(26)) {
            Sintax.reglas.put(26, "CAMPO_DEC->Numero | Numero_Decimal | identificador");
        }
        switch (tokens.get(cont).tipo) {
            case "Numero_Decimal" -> {
                regla += "Numero_Decimal ";
                return true;
            }
            case "Numero" -> {
                regla += "Numero ";
                return true;
            }
            case "identificador" -> {
                regla += "identificador ";
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    private boolean campoTexto(int cont) throws Exception {
        if (!Sintax.reglas.containsKey(27)) {
            Sintax.reglas.put(27, "CAMPO_TEXTO->identificador | Texto | Caracter");
        }
        switch (tokens.get(cont).tipo) {
            case "Texto" -> {
                regla += "Texto ";
                return true;
            }
            case "Caracter" -> {
                regla += "Caracter ";
                return true;
            }
            case "identificador" -> {
                regla += "identificador ";
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    private int asignacion(int cont) throws Exception {
        regla = "ASIGNACION::= identificador Asignacion ";
        int n = ++gramaticas;
        //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
        String r = regla + "OP_ARITMETICA ";
        cont = opAritmetica(cont);
        regla = r;
        if (tokens.get(cont).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma";
            Sintax.gramas.put(n, regla);
            return cont;
        } else {
            regla += "ERROR";
            Sintax.gramas.put(n, regla);
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
            return cont;
        }
    }

    private int opAritmetica(int cont) throws Exception {
        if (!Sintax.reglas.containsKey(19)) {
            Sintax.reglas.put(19, "OP_ARITMETICA->Parentesis_a OP_ARITMETICA Parentesis_c | Parentesis_a OP_ARITMETICA Parentesis_c Aritmetico OP_ARITMETICA |  CAMPO_DEC Aritmetico OP_ARITMETICA | CAMPO_DEC");
        }
        regla = "OP_ARITMETICA::= ";
        int n = ++gramaticas;
        if (tokens.get(cont).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a OP_ARITMETICA ";
            String r = regla;
            cont = opAritmetica(++cont);
            regla = r;
            if (tokens.get(cont).tipo.equals("Parentesis_c")) {
                regla += "Parentesis_c";
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                if (tokens.get(++cont).tipo.equals("Aritmetico")) {
                    regla += "Aritmetico OP_ARITMETICA";
                    return opAritmetica(++cont);
                }
                Sintax.gramas.put(n, regla);
                return cont;
            } else {
                regla += "ERROR";
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
                return cont;
            }
        }

        if (campoDec(cont)) {
            if (tokens.get(++cont).tipo.equals("Aritmetico")) {
                regla += "Aritmetico OP_ARITMETICA";
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                return opAritmetica(++cont);
            } else {
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                return cont;
            }
        } else {
            regla += "ERROR";
            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
            Sintax.gramas.put(n, regla);
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba [ (, Numero, Numero_Dec, Identificador ] en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
            FrmPrincipal.errores.add("OMITIENDO CARACTERES...");
            while (!tokens.get(cont).tipo.equals("Parentesis_c") && !tokens.get(cont).tipo.equals("Coma") && !tokens.get(cont).tipo.equals("Punto_Coma")) {
                cont++;
            }
            return cont;
        }
    }

    private int tipoDato(int cont) {
        if (!Sintax.reglas.containsKey(20)) {
            Sintax.reglas.put(20, "TIPO_DATO->Int | Char | Logic | Float | str");
        }
        regla = "TIPO_DATO ::= ";
        int n = ++gramaticas;
        switch (tokens.get(cont++).tipo) {
            case "Int" -> {
                regla += "Int ";
            }
            case "Char" -> {
                regla += "Char ";
            }
            case "Logic" -> {
                regla += "Logic ";
            }
            case "Float" -> {
                regla += "Float ";
            }
            case "str" -> {
                regla += "str ";
            }
            default -> {
                regla += "ERROR";
                FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( int, char, logic, float, str ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            }
        }
        //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
        Sintax.gramas.put(n, regla);
        return cont;
    }

    private int declaracion(int cont) throws Exception {
        regla = "DECLARACION::= identificador Declare ";
        int n = ++gramaticas;
        if (!tokens.get(cont++).tipo.equals("As")) {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( As ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
        }

        String r = regla + "TIPO_DATO ";
        cont = tipoDato(cont);
        regla = r;
        switch (tokens.get(cont).tipo) {
            case "Punto_Coma" -> {
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla + "Punto_Coma");
                Sintax.gramas.put(n, regla + "Punto_Coma");
                return ++cont;
            }
            case "Asignacion" -> {
                regla += "Asignacion ";
                if (campoTexto(++cont)) {
                    if (tokens.get(++cont).tipo.equals("Punto_Coma")) {
                        // FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla + "Punto_Coma");
                        Sintax.gramas.put(n, regla + "Punto_Coma");
                        return ++cont;
                    } else {
                        FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
                        Sintax.gramas.put(n, regla + "Punto_Coma");
                        //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla + "Punto_Coma");
                        return cont;
                    }
                }
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla + "OP_ARITMETICA Punto_Coma");
                r = regla + "OP_ARITMETICA ";
                cont = opAritmetica(cont);
                regla = r;
                if (tokens.get(cont).tipo.equals("Punto_Coma")) {
                    regla += "Punto_Coma";
                    Sintax.gramas.put(n, regla);
                    return ++cont;
                } else {
                    regla += "ERROR";
                    Sintax.gramas.put(n, regla);
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
                    return cont;
                }
            }
            default -> {
                regla += "ERROR";
                FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; | = ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla + "Punto_Coma");
                Sintax.gramas.put(n, regla);
                return cont;
            }
        }
    }

    private int condition(int cont) throws Exception {
        regla = " CONDITION::= Condition ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += " Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla;
        r += "SENTENCIA_BOOLEANA ";
        cont = sentenciaBooleana(cont);
        regla = r;
        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Llave_a")) {
            regla += "Llave_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( { ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (!tokens.get(cont).tipo.equals("Llave_c")) {
            r = regla;
            r += "GRAMATICAS ";
            cont = evaluar(cont);
            regla = r;
        }
        while (!tokens.get(cont).tipo.equals("Llave_c")) {
            r = regla;
            cont = evaluar(cont);
            regla = r;
        }
        if (tokens.get(cont++).tipo.equals("Llave_c")) {
            regla += "Llave_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( } ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Else")) {
            regla += "ELSE";
            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
            Sintax.gramas.put(n, regla);
            return graElse(cont);
        }
        //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
        Sintax.gramas.put(n, regla);
        return --cont;
    }

    private int sentenciaBooleana(int cont) throws Exception {
        if (!Sintax.reglas.containsKey(21)) {
            Sintax.reglas.put(21, "SENTENCIA_BOOLEANA->Parentesis_a SENTENCIA_BOOLEANA Parentesis_c | Negacion identificador | Negacion identificador Op_Logico SENTENCIA_BOOLEANA | Negacion Parentesis_a SENTENCIA_BOOLEANA Parentesis_c | Negacion Parentesis_a SENTENCIA_BOOLEANA Parentesis_c Op_Logico SENTENCIA_BOOLEANA | Op_Booleano | Op_Booleano Op_Logico SENTENCIA_BOOLEANA | identificador | identificador Op_Logico SENTENCIA_BOOLEANA | identificador Op_Relacional OP_ARITMETICA | identificador Op_Relacional OP_ARITMETICA Op_Logico SENTENCIA_BOOLEANA");
        }
        regla = " SENTENCIA_BOOLEANA ::= ";
        int n = ++gramaticas;
        switch (tokens.get(cont++).tipo) {
            case "Parentesis_a" -> {
                regla += "Parentesis_a SENTENCIA_BOOLEANA";
                String r = regla;
                cont = sentenciaBooleana(cont);
                regla = r;
                if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
                    regla += " Parentesis_c";
                } else {
                    regla += " ERROR";
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                }

                if (tokens.get(cont).tipo.equals("Parentesis_c") || tokens.get(cont).tipo.equals("Coma")) {
                    //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                    Sintax.gramas.put(n, regla);
                    return cont;
                }
            }
            case "Negacion" -> {
                return booleanaNegacion(cont, n);
            }
            case "Op_Booleano" -> {
                regla += "Op_Booleano ";
                if (tokens.get(cont).tipo.equals("Parentesis_c") || tokens.get(cont).tipo.equals("Coma")) {
                    //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                    Sintax.gramas.put(n, regla);
                    return cont;
                }
                if (tokens.get(cont++).tipo.equals("Op_Logico")) {
                    regla += "Op_Logico SENTENCIA_BOOLEANA";
                    //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                    Sintax.gramas.put(n, regla);
                    return sentenciaBooleana(cont);
                } else {
                    regla += "ERROR SENTENCIA_BOOLEANA";
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ||, && ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                }
            }

            case "identificador" -> {
                return booleanaIdent(cont, n);
            }
            default -> {
                regla += "ERROR";
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( !, true, false, identificador ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                FrmPrincipal.errores.add("OMITIENDO CARACTERES...");
                cont--;
                while (!tokens.get(cont).tipo.equals("Parentesis_c") && !tokens.get(cont).tipo.equals("Coma") && !tokens.get(cont).tipo.equals("Punto_Coma")) {
                    cont++;
                }
                return cont;

            }
        }
        return cont;
    }

    private int booleanaIdent(int cont, int n) throws Exception {
        regla += "identificador ";
        switch (tokens.get(cont++).tipo) {
            case "Op_Logico" -> {
                regla += "Op_Logico SENTENCIA_BOOLEANA";
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                return sentenciaBooleana(cont);
            }

            case "Op_Relacional" -> {
                regla += "Op_Relacional OP_ARITMETICA";
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                String r = regla;
                cont = opAritmetica(cont);
                regla = r;

            }
            default -> {
                regla += "ERROR";
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ||, &&, >, <, ==, !=, >=, <= ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                FrmPrincipal.errores.add("OMITIENDO CARACTERES...");
                while (!tokens.get(cont).tipo.equals("Parentesis_c") && !tokens.get(cont).tipo.equals("Coma") && !tokens.get(cont).tipo.equals("Punto_Coma")) {
                    cont++;
                }
                return cont;
            }
        }

        if (tokens.get(cont).tipo.equals("Parentesis_c") || tokens.get(cont).tipo.equals("Coma")) {
            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
            Sintax.gramas.put(n, regla);
            return cont;
        }
        if (tokens.get(cont++).tipo.equals("Op_Logico")) {
            regla += "Op_Logico SENTENCIA_BOOLEANA";
            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
            Sintax.gramas.put(n, regla);
            return sentenciaBooleana(cont);
        } else {
            regla += "ERROR SENTENCIA_BOOLEANA";
            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
            Sintax.gramas.put(n, regla);
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( { ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            return sentenciaBooleana(cont - 1);
        }
    }

    private int booleanaNegacion(int cont, int n) throws Exception {
        regla += "Negacion ";
        switch (tokens.get(cont++).tipo) {
            case "identificador" -> {
                regla += "identificador ";
            }
            case "Parentesis_a" -> {
                regla += "Parentesis_a ";
                String r = regla;
                r += "SENTENCIA_BOOLEANA ";
                cont = sentenciaBooleana(cont);
                regla = r;
                if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
                    regla += "Parentesis_c ";
                } else {
                    regla += "ERROR";
                    cont--;
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                }
            }
            default -> {
                regla += "ERROR ";
                FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( identificador, ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            }
        }

        if (tokens.get(cont).tipo.equals("Parentesis_c") || tokens.get(cont).tipo.equals("Coma")) {
            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
            Sintax.gramas.put(n, regla);
            return cont;
        }
        if (tokens.get(cont++).tipo.equals("Op_Logico")) {
            regla += "Op_Logico SENTENCIA_BOOLEANA";
            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
            Sintax.gramas.put(n, regla);
            return sentenciaBooleana(cont);
        } else {
            regla += "ERROR SENTENCIA_BOOLEANA";
            //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
            Sintax.gramas.put(n, regla);
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( { ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            return sentenciaBooleana(cont - 1);
        }
    }

    private int graElse(int cont) throws Exception {
        if (!Sintax.reglas.containsKey(22)) {
            Sintax.reglas.put(22, "ELSE->Else CONDITION | Else Llave_a GRAMATICAS Llave_c");
        }
        regla = "ELSE::= Else ";
        int n = ++gramaticas;
        switch (tokens.get(cont++).tipo) {
            case "Condition" -> {
                regla += "CONDITION";
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                return condition(--cont);
            }
            case "Llave_a" -> {
                regla += "Llave_a GRAMATICAS ";
                while (!tokens.get(cont).tipo.equals("Llave_c")) {
                    String r = regla;
                    cont = evaluar(cont);
                    regla = r;
                }
                regla += "Llave_c";
                cont++;
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                return cont;

            }
            default -> {
                regla += "ERROR GRAMATICAS ";
                cont--;
                FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( { ) en el lexema ( " + tokens.get(cont).lexema + " ) en la posicion " + tokens.get(cont).linea + ", " + tokens.get(cont).columna);
                while (!tokens.get(cont).tipo.equals("Llave_c")) {
                    String r = regla;
                    cont = evaluar(cont);
                    regla = r;
                }
                regla += "Llave_c";
                //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
                Sintax.gramas.put(n, regla);
                return ++cont;
            }
        }
    }

    private int ventilate(int cont) throws Exception {
        regla = "VENTILATE::= Ventilate ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla + "OP_ARITMETICA ";
        cont = opAritmetica(cont);
        regla = r;
        if (tokens.get(cont++).tipo.equals("Coma")) {
            regla += "Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        for (int i = 0; i < 3; i++) {
            r = regla + "SENTENCIA_BOOLEANA ";
            cont = sentenciaBooleana(cont);
            regla = r;
            if (i != 2) {
                if (tokens.get(cont++).tipo.equals("Coma")) {
                    regla += "Coma ";
                } else {
                    regla += "ERROR ";
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                }
            }
        }

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
            Sintax.gramas.put(n, regla);
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            Sintax.gramas.put(n, regla);
        }

        return --cont;
    }

    private int admit(int cont) throws Exception {
        regla = "ADMIT::= Admit ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla + "SENTENCIA_BOOLEANA ";
        cont = sentenciaBooleana(cont);
        regla = r;
        if (tokens.get(cont++).tipo.equals("Coma")) {
            regla += "Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        for (int i = 0; i < 2; i++) {
            r = regla + "OP_ARITMETICA ";
            cont = opAritmetica(cont);
            regla = r;
            if (i != 1) {
                if (tokens.get(cont++).tipo.equals("Coma")) {
                    regla += "Coma ";
                } else {
                    regla += "ERROR ";
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                }
            }
        }

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
            Sintax.gramas.put(n, regla);
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            Sintax.gramas.put(n, regla);
        }

        return --cont;
    }

    private int emptyRoom(int cont) throws Exception {
        regla = "EMPTY_ROOM::= EmptyRoom ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla + "SENTENCIA_BOOLEANA ";
        cont = sentenciaBooleana(cont);
        regla = r;
        if (tokens.get(cont++).tipo.equals("Coma")) {
            regla += "Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        r = regla + "OP_ARITMETICA ";
        cont = opAritmetica(cont);
        regla = r;
        if (tokens.get(cont++).tipo.equals("Coma")) {
            regla += "Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        for (int i = 0; i < 2; i++) {
            r = regla + "SENTENCIA_BOOLEANA ";
            cont = sentenciaBooleana(cont);
            regla = r;
            if (i != 1) {
                if (tokens.get(cont++).tipo.equals("Coma")) {
                    regla += "Coma ";
                } else {
                    regla += "ERROR ";
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                }
            }
        }

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
            Sintax.gramas.put(n, regla);
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            Sintax.gramas.put(n, regla);
        }

        return --cont;
    }

    private int dispense(int cont) throws Exception {
        regla = "DISPENSE::= Dispense ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla + "OP_ARITMETICA ";
        cont = opAritmetica(cont);
        regla = r;

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
            Sintax.gramas.put(n, regla);
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            Sintax.gramas.put(n, regla);
        }

        return --cont;
    }

    private int distance(int cont) throws Exception {
        regla = "DISTANCE::= Distance ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Corchete_a")) {
            regla += "Corchete_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( [ ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla + "DEVICE ";
        cont = device(cont);
        regla = r;

        if (tokens.get(cont++).tipo.equals("Corchete_c")) {
            regla += "Corchete_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ] ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }
        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
            Sintax.gramas.put(n, regla);
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            Sintax.gramas.put(n, regla);
        }

        return --cont;
    }

    private int device(int cont) throws Exception {
        if (!Sintax.reglas.containsKey(23)) {
            Sintax.reglas.put(23, "DEVICE->CAMPO_TEXTO | CAMPO_TEXTO Coma DEVICE");
        }
        regla = "DEVICE::=  ";
        int n = ++gramaticas;
        if (!campoTexto(cont++)) {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( Texto, Caracter, identificador ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }
        if (tokens.get(cont).tipo.equals("Coma")) {
            regla += "Coma DEVICE";
            Sintax.gramas.put(n, regla);
            return device(++cont);
        }
        Sintax.gramas.put(n, regla);
        return cont;
    }

    private int deviceControl(int cont) throws Exception {
        regla = "DEVICE_CONTROL::= DeviceControl ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (!campoTexto(cont++)) {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( Texto, Caracter o identificador ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }
        if (tokens.get(cont++).tipo.equals("Coma")) {
            regla += "Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla + "SENTENCIA_BOOLEANA ";
        cont = sentenciaBooleana(cont);
        regla = r;

        if (tokens.get(cont++).tipo.equals("Coma")) {
            regla += "Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Corchete_a")) {
            regla += "Corchete_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( [ ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            FrmPrincipal.errores.add("OMITIENDO CARACTERES...");
            cont--;
            while (!tokens.get(cont).tipo.equals("Parentesis_c") && !tokens.get(cont).tipo.equals("Punto_Coma")) {
                cont++;
            }
            return ++cont;
        }

        r = regla + "DEVICE_CONFIG ";
        cont = deviceConfig(cont);
        regla = r;

        if (tokens.get(cont++).tipo.equals("Corchete_c")) {
            regla += "Corchete_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ] ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
            Sintax.gramas.put(n, regla);
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            Sintax.gramas.put(n, regla);
        }

        return --cont;
    }

    private int deviceConfig(int cont) throws Exception {
        if (!Sintax.reglas.containsKey(24)) {
            Sintax.reglas.put(24, "DEVICE_CONFIG->VACIO | Parentesis_a CAMPO_TEXTO Asignacion CAMPO_TEXTO Parentesis_c | Parentesis_a CAMPO_TEXTO Asignacion CAMPO_TEXTO Parentesis_c Coma DEVICE_CONFIG | Parentesis_a CAMPO_TEXTO Asignacion CAMPO_TEXTO Parentesis_c | Parentesis_a CAMPO_TEXTO Asignacion CAMPO_TEXTO Parentesis_c Coma DEVICE_CONFIG");
        }
        regla = "DEVICE_CONFIG::= ";
        int n = ++gramaticas;
        if (tokens.get(cont).tipo.equals("Corchete_c")) {
            Sintax.gramas.put(n, regla + "VACIO");
            return cont;
        }
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (!campoTexto(cont++)) {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( Texto, Caracter, identificador ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Asignacion")) {
            regla += "Asignacion ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( = ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        switch (tokens.get(cont++).tipo) {
            case "Texto", "Caracter", "identificador" -> {
                campoTexto(cont++);
            }
            default -> {
                String r = regla + "OP_ARITMETICA ";
                cont = opAritmetica(--cont);
                regla = r;

            }
        }

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            cont--;
        }

        if (tokens.get(cont).tipo.equals("Coma")) {
            regla += "Coma DEVICE_CONFIG";
            Sintax.gramas.put(n, regla);
            return deviceConfig(++cont);
        } else if (tokens.get(cont).tipo.equals("Parentesis_a")) {
            regla += "ERROR DEVICE_CONFIG";
            Sintax.gramas.put(n, regla);
            return deviceConfig(cont);
        }
        Sintax.gramas.put(n, regla);
        return cont;
    }

    private int driverLights(int cont) throws Exception {
        regla = "DRIVER_LIGHTS::= DriverLights ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla + "OP_ARITMETICA ";
        cont = opAritmetica(cont);
        regla = r;
        if (tokens.get(cont++).tipo.equals("Coma")) {
            regla += "Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Corchete_a")) {
            regla += "Corchete_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( [ ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            FrmPrincipal.errores.add("OMITIENDO CARACTERES...");
            cont--;
            while (!tokens.get(cont).tipo.equals("Parentesis_c") && !tokens.get(cont).tipo.equals("Punto_Coma")) {
                cont++;
            }
            return ++cont;
        }
        r = regla + "LIGHT ";
        cont = light(cont);
        regla = r;

        if (tokens.get(cont++).tipo.equals("Corchete_c")) {
            regla += "Corchete_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ] ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }
        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
            Sintax.gramas.put(n, regla);
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            Sintax.gramas.put(n, regla);
        }

        return --cont;
    }

    private int light(int cont) throws Exception {
        if (!Sintax.reglas.containsKey(25)) {
            Sintax.reglas.put(25, "LIGHT->VACIO | Parentesis_a OP_ARITMETICA Coma OP_ARITMETICA Parentesis_c | Parentesis_a OP_ARITMETICA Coma OP_ARITMETICA Parentesis_c Coma LIGHT");
        }
        regla = "LIGHT::= ";
        int n = ++gramaticas;
        if (tokens.get(cont).tipo.equals("Corchete_c")) {
            Sintax.gramas.put(n, regla + "VACIO");
            return cont;
        }
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla + "OP_ARITMETICA ";
        cont = opAritmetica(cont);
        regla = r;

        if (tokens.get(cont++).tipo.equals("Coma")) {
            regla += "Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        r = regla + "OP_ARITMETICA ";
        cont = opAritmetica(cont);
        regla = r;

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            cont--;
        }

        if (tokens.get(cont).tipo.equals("Coma")) {
            regla += "Coma LIGHT";
            Sintax.gramas.put(n, regla);
            return light(++cont);
        } else if (tokens.get(cont).tipo.equals("Parentesis_a")) {
            regla += "ERROR LIGHT";
            Sintax.gramas.put(n, regla);
            return light(cont);
        }
        Sintax.gramas.put(n, regla);
        return cont;
    }

    private int openDoor(int cont) throws Exception {
        regla = "OPEN_DOOR::= openDoor ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla + "OP_ARITMETICA ";
        cont = opAritmetica(cont);
        regla = r;
        if (tokens.get(cont++).tipo.equals("Coma")) {
            regla += "Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( , ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        r = regla + "SENTENCIA_BOOLEANA ";
        cont = sentenciaBooleana(cont);
        regla = r;

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
            Sintax.gramas.put(n, regla);
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            Sintax.gramas.put(n, regla);
        }

        return --cont;
    }

    private int registerA(int cont) throws Exception {
        regla = "REGISTER_A::= RegisterA ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += "Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (!campoTexto(cont++)) {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( Texto ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
            Sintax.gramas.put(n, regla);
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ; ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
            Sintax.gramas.put(n, regla);
        }

        return --cont;
    }

    private int While(int cont) throws Exception {
        regla = " WHILE::= While ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += " Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla;
        r += "SENTENCIA_BOOLEANA ";
        cont = sentenciaBooleana(cont);
        regla = r;
        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Llave_a")) {
            regla += "Llave_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( { ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (!tokens.get(cont).tipo.equals("Llave_c")) {
            r = regla;
            r += "GRAMATICAS ";
            cont = evaluar(cont);
            regla = r;
        }
        while (!tokens.get(cont).tipo.equals("Llave_c")) {
            r = regla;
            cont = evaluar(cont);
            regla = r;
        }
        if (tokens.get(cont++).tipo.equals("Llave_c")) {
            regla += "Llave_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( } ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
        Sintax.gramas.put(n, regla);
        return cont;
    }

    private int For(int cont) throws Exception {
        regla = " FOR::= For ";
        int n = ++gramaticas;
        if (tokens.get(cont++).tipo.equals("Parentesis_a")) {
            regla += " Parentesis_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont).tipo.equals("identificador") && tokens.get(cont + 1).tipo.equals("Declare")) {
            String r = regla + "DECLARACION ";
            cont = declaracion(cont);
            regla = r;
        } else if (tokens.get(cont + 1).tipo.equals("Asignacion")) {
            String r = regla + "ASIGNACION ";
            cont = asignacion(cont);
            regla = r;
        } else {

        }

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        String r = regla;
        r += "SENTENCIA_BOOLEANA ";
        cont = sentenciaBooleana(cont);
        regla = r;

        if (tokens.get(cont++).tipo.equals("Punto_Coma")) {
            regla += "Punto_Coma ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ( ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        switch (tokens.get(cont++).tipo) {
            case "identificador" -> {
                if (!tokens.get(cont).tipo.equals("Op_Incremento")) {
                    r = regla + "OP_ATRIBUCION ";
                    cont = opAritmetica(cont - 1);
                    regla = r;
                    break;
                }
                r = "INCDEC::= identificador ";
                if (tokens.get(cont++).tipo.equals("Op_Incremento")) {
                    r += "Op_Incremento";
                } else {
                    r += "ERROR";
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ++, -- ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                    cont = tokens.get(cont).tipo.equals("Parentesis_c") ? cont : --cont;
                }
                Sintax.gramas.put(++gramaticas, r);
            }
            case "Op_Incremento" -> {
                r = "INCDEC::= Op_Incremento ";
                if (tokens.get(cont++).tipo.equals("identificador")) {
                    r += "identificador";
                } else {
                    r += "ERROR";
                    FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( identificador ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                    cont = tokens.get(cont).tipo.equals("Parentesis_c") ? cont : --cont;
                }
                Sintax.gramas.put(++gramaticas, r);
            }
            default -> {
                regla += "ERROR ";
                FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( identificador, ++, -- ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
                FrmPrincipal.errores.add("OMITIENDO CARACTERES...");
                while (!tokens.get(cont).tipo.equals("Parentesis_c")) {
                    cont++;
                }
            }
        }

        if (tokens.get(cont++).tipo.equals("Parentesis_c")) {
            regla += "Parentesis_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( ) ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (tokens.get(cont++).tipo.equals("Llave_a")) {
            regla += "Llave_a ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( { ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }

        if (!tokens.get(cont).tipo.equals("Llave_c")) {
            r = regla;
            r += "GRAMATICAS ";
            cont = evaluar(cont);
            regla = r;
        }
        while (!tokens.get(cont).tipo.equals("Llave_c")) {
            r = regla;
            cont = evaluar(cont);
            regla = r;
        }
        if (tokens.get(cont++).tipo.equals("Llave_c")) {
            regla += "Llave_c ";
        } else {
            regla += "ERROR ";
            FrmPrincipal.errores.add("ERROR SINTACTICO: Se esperaba ( } ) en el lexema ( " + tokens.get(cont - 1).lexema + " ) en la posicion " + tokens.get(cont - 1).linea + ", " + tokens.get(cont - 1).columna);
        }
        //FrmPrincipal.txtGramaticas.setText(FrmPrincipal.txtGramaticas.getText() + "\n" + regla);
        Sintax.gramas.put(n, regla);
        return cont;
    }
}
