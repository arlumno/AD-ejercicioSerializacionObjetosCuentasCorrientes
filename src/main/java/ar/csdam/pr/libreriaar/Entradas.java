/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.csdam.pr.libreriaar;

import java.io.File;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Conjunto de metodos para para trabajar en clase de DAM.
 *
 * @author a20armandocb
 */
public class Entradas {
    /**
     * Pide por consola un valor del tipo y rango especificado. valorMin y
     * valorMax opcionales.
     *
     * @param lector - objeto scanner
     * @param valorMin - valor mínimo, opcional
     * @param valorMax - valor máximo opcional, (requiere valorMin).
     * @return devuelve tipo byte
     */
    public static byte pedirByte(Scanner lector, byte valorMin, byte valorMax) {
        byte resultado = 0;
        boolean fin = false;
        do {
            try {
                resultado = lector.nextByte();
                lector.nextLine();//limpiamos consola.
                if (resultado < valorMin || resultado > valorMax) {
                    Salidas.errorFueraRango();
                    Salidas.errorReintentarIntroducir();
                } else {
                    fin = true;
                }
            } catch (InputMismatchException error) {
                Salidas.errorTipo();
                Salidas.errorReintentarIntroducir();
                lector.nextLine();//limpiamos consola.
            }
        } while (!fin);
        return resultado;
    }

    /**
     * valorMax con máximo valor por defecto
     *
     * @param lector - objeto scanner
     *
     * @param valorMin - valor mínimo, opcional
     * @return devuelve tipo byte
     */
    public static byte pedirByte(Scanner lector, byte valorMin) {
        return pedirByte(lector, valorMin, Byte.MAX_VALUE);
    }

    /**
     * valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param lector - objeto scanner
     * @return devuelve tipo byte
     */
    public static byte pedirByte(Scanner lector) {
        return pedirByte(lector, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * Pide por consola un valor del tipo y rango especificado. valorMin y
     * valorMax opcionales.
     *
     * @param lector - objeto scanner
     * @param valorMin - valor mínimo, opcional
     * @param valorMax - valor máximo opcional, (requiere valorMin).
     * @return tipo int
     */
    public static int pedirInt(Scanner lector, int valorMin, int valorMax) {
        int resultado = 0;
        boolean fin = false;
        do {
            try {
                resultado = lector.nextInt();
                lector.nextLine();//limpiamos consola.
                if (resultado < valorMin || resultado > valorMax) {
                    Salidas.errorFueraRango();
                    Salidas.errorReintentarIntroducir();
                } else {
                    fin = true;
                }
            } catch (InputMismatchException error) {
                Salidas.errorTipo();
                Salidas.errorReintentarIntroducir();
                lector.nextLine();//limpiamos consola.
            }
        } while (!fin);
        return resultado;
    }

    /**
     * valorMax con máximo valor por defecto
     *
     * @param lector - objeto scanner
     * @param valorMin - valor mínimo, opcional
     * @return tipo int
     */
    public static int pedirInt(Scanner lector, int valorMin) {
        return pedirInt(lector, valorMin, Integer.MAX_VALUE);
    }

    /**
     * valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param lector - objeto scanner
     * @return tipo int
     */
    public static int pedirInt(Scanner lector) {
        return pedirInt(lector, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Pide por consola un valor del tipo y rango especificado. valorMin y
     * valorMax opcionales.
     *
     * @param lector - objeto scanner
     * @param valorMin - valor mínimo, opcional
     * @param valorMax - valor máximo opcional, (requiere valorMin).
     * @return tipo long
     */
    public static long pedirLong(Scanner lector, long valorMin, long valorMax) {
        long resultado = 0;
        boolean fin = false;
        do {
            try {
                resultado = lector.nextLong();
                lector.nextLine();//limpiamos consola.

                if (resultado < valorMin || resultado > valorMax) {
                    Salidas.errorFueraRango();
                    Salidas.errorReintentarIntroducir();
                } else {
                    fin = true;
                }
            } catch (InputMismatchException error) {
                Salidas.errorTipo();
                Salidas.errorReintentarIntroducir();
                lector.nextLine();//limpiamos consola.
            }
        } while (!fin);
        return resultado;
    }

    /**
     * valorMax con máximo valor por defecto
     *
     * @param lector - objeto scanner
     * @param valorMin - valor mínimo, opcional
     * @return tipo long
     */
    public static long pedirLong(Scanner lector, long valorMin) {
        return pedirLong(lector, valorMin, Long.MAX_VALUE);
    }

    /**
     * valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param lector - objeto scanner
     * @return tipo long
     */
    public static long pedirLong(Scanner lector) {
        return pedirLong(lector, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Pide por consola un valor del tipo y rango especificado. valorMin y
     * valorMax opcionales.
     *
     * @param lector - objeto scanner
     * @param valorMin - valor mínimo, opcional
     * @param valorMax - valor máximo opcional, (requiere valorMin).
     * @return tipo double
     */
    public static double pedirDouble(Scanner lector, double valorMin, double valorMax) {
        double resultado = 0;
        boolean fin = false;
        do {
            try {
                resultado = lector.nextDouble();
                lector.nextLine();//limpiamos consola.                
                if (resultado < valorMin || resultado > valorMax) {
                    Salidas.errorFueraRango();
                    Salidas.errorReintentarIntroducir();
                } else {
                    fin = true;
                }
            } catch (InputMismatchException error) {
                Salidas.errorTipo();
                Salidas.errorReintentarIntroducir();
                lector.nextLine();//limpiamos consola.
            }
        } while (!fin);
        return resultado;
    }
    
    /**
     * Metodo valorMin especificado y valorMax con máximo valor por defecto
     *
     * @param lector - objeto scanner
     * @param valorMin - valor mínimo, opcional
     * @return tipo double
     */
    public static double pedirDouble(Scanner lector, double valorMin) {
        return pedirDouble(lector, valorMin, Double.MAX_VALUE);
    }

    /**
     * Metodo valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param lector - objeto scanner
     * @return tipo double
     */
    public static double pedirDouble(Scanner lector) {
        // el MIN_VALUE de double no es negativo, indica el numero POSITIVO mas pequeño con decimales
        return pedirDouble(lector, Double.MAX_VALUE * -1, Double.MAX_VALUE);
    }

    /**
     * Pide por consola un valor del tipo y rango especificado. valorMin y
     * valorMax opcionales.
     *
     * @param lector - objeto scanner
     * @param valorMax - Longitud máxima del String
     * @param valorMin - Longitud mínima del String, opcional (requiere
     * valorMax).
     * @return tipo String
     */
    public static String pedirString(Scanner lector, Integer valorMax, int valorMin) {
        String resultado = "";
        boolean fin = false;
        do {
            try {                
                resultado = lector.nextLine();
                if (resultado.length() < valorMin || (valorMax != null && resultado.length() > valorMax)) {
                    if (valorMax == null) {
                        Salidas.errorVacio();
                    } else {
                        Salidas.errorFueraRango();
                    }
                    Salidas.errorReintentarIntroducir();
                } else {
                    fin = true;
                }
            } catch (InputMismatchException error) {
                Salidas.errorTipo();
                Salidas.errorReintentarIntroducir();
                lector.nextLine();//limpiamos consola.
            }
        } while (!fin);
        return resultado;
    }

    /**
     * Metodo valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param lector - objeto scanner
     * @return tipo String
     */
    public static String pedirString(Scanner lector) {
        return pedirString(lector, null, 0);
    }

    /**
     * Metodo valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param lector - objeto scanner
     * @param valorMax - Longitud máxima del String
     * @return tipo String
     */
    public static String pedirString(Scanner lector, int valorMax) {
        return pedirString(lector, valorMax, 0);
    }

    /**
     * Pide por consola escribrir entre dos textos, para devolver TRUE o FALSE
     * Sigue pidiendo el texto hasta que coincida. Insensible a mayúsculas y
     * minúsculas
     *
     * @param lector - objeto scanner
     * @param si texto para TRUE, por defecto "SI", insensible a mayúsculas
     * @param no texto para FALSE, por defecto "NO", insensible a mayúsculas
     * @return TRUE o FALSE
     */
    public static boolean pedirBoolean(Scanner lector, String si, String no) {
        String texto = "";
        boolean resultado = false;
        boolean fin = true;
        do {
            texto = lector.nextLine();
            texto = texto.toUpperCase();
            if (texto.equals(si.toUpperCase())) {
                resultado = true;
            } else if (texto.equals(no.toUpperCase())) {
                resultado = false;
            } else {
                fin = false;
                Salidas.errorReintentarIntroducir();
            }

        } while (!fin);
        return resultado;
    }

    /**
     * Pide por consola escribrir entre dos textos, para devolver TRUE o FALSE
     * texto "SI" para TRUE y "NO" para FALSE Sigue pidiendo el texto hasta que
     * coincida.
     *
     * @param lector - objeto scanner
     * @return TRUE o FALSE
     */
    public static boolean pedirBoolean(Scanner lector) {
        return pedirBoolean(lector, "SI", "NO");
    }
    
}
