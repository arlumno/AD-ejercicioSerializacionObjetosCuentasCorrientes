/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.csdam.pr.libreriaar;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Conjunto de metodos para para trabajar en clase de DAM.
 *
 * @author a20armandocb
 */
public class EntradasGui {

    /**
     * Pide por GUI un valor del tipo y rango especificado. valorMin y valorMax
     * opcionales.
     *
     * @param mensaje - texto de la ventana
     * @param valorMin - valor mínimo, opcional
     * @param valorMax - valor máximo opcional, (requiere valorMin).
     * @return devuelve tipo byte
     */
    public static byte pedirByte(String mensaje, byte valorMin, byte valorMax) {
        byte resultado = 0;
        boolean fin = false;
        do {
            try {
                String aux = JOptionPane.showInputDialog(mensaje);
                if (aux == null) {
                    SalidasGui.errorReintentarIntroducir();
                } else {
                    resultado = Byte.parseByte(aux);
                    if (resultado < valorMin || resultado > valorMax) {
                        SalidasGui.errorFueraRango();
                        SalidasGui.errorReintentarIntroducir();
                    } else {
                        fin = true;
                    }
                }
            } catch (InputMismatchException error) {
                SalidasGui.errorTipo();
                SalidasGui.errorReintentarIntroducir();
            }
        } while (!fin);
        return resultado;
    }

    /**
     * valorMax con máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     *
     * @param valorMin - valor mínimo, opcional
     * @return devuelve tipo byte
     */
    public static byte pedirByte(String mensaje, byte valorMin) {
        return pedirByte(mensaje, valorMin, Byte.MAX_VALUE);
    }

    /**
     * valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @return devuelve tipo byte
     */
    public static byte pedirByte(String mensaje) {
        return pedirByte(mensaje, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * Pide por GUI un valor del tipo y rango especificado. valorMin y valorMax
     * opcionales.
     *
     * @param mensaje - texto de la ventana
     * @param valorMin - valor mínimo, opcional
     * @param valorMax - valor máximo opcional, (requiere valorMin).
     * @return tipo int
     */
    public static int pedirInt(String mensaje, int valorMin, int valorMax) {
        int resultado = 0;
        boolean fin = false;
        do {
            try {
                String aux = JOptionPane.showInputDialog(mensaje);
                if (aux == null) {
                    SalidasGui.errorReintentarIntroducir();
                } else {
                    resultado = Integer.parseInt(aux);

                    if (resultado < valorMin || resultado > valorMax) {
                        SalidasGui.errorFueraRango();
                        SalidasGui.errorReintentarIntroducir();
                    } else {
                        fin = true;
                    }
                }
            } catch (InputMismatchException error) {
                SalidasGui.errorTipo();
                SalidasGui.errorReintentarIntroducir();
            }
        } while (!fin);
        return resultado;
    }

    /**
     * valorMax con máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @param valorMin - valor mínimo, opcional
     * @return tipo int
     */
    public static int pedirInt(String mensaje, int valorMin) {
        return pedirInt(mensaje, valorMin, Integer.MAX_VALUE);
    }

    /**
     * valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @return tipo int
     */
    public static int pedirInt(String mensaje) {
        return pedirInt(mensaje, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Pide por GUI un valor del tipo y rango especificado. valorMin y valorMax
     * opcionales.
     *
     * @param mensaje - texto de la ventana
     * @param valorMin - valor mínimo, opcional
     * @param valorMax - valor máximo opcional, (requiere valorMin).
     * @return tipo long
     */
    public static long pedirLong(String mensaje, long valorMin, long valorMax) {
        long resultado = 0;
        boolean fin = false;
        do {
            try {
                String aux = JOptionPane.showInputDialog(mensaje);
                if (aux == null) {
                    SalidasGui.errorReintentarIntroducir();
                } else {
                    resultado = Long.parseLong(aux);
                    if (resultado < valorMin || resultado > valorMax) {
                        SalidasGui.errorFueraRango();
                        SalidasGui.errorReintentarIntroducir();
                    } else {
                        fin = true;
                    }
                }
            } catch (InputMismatchException error) {
                SalidasGui.errorTipo();
                SalidasGui.errorReintentarIntroducir();
            }
        } while (!fin);
        return resultado;
    }

    /**
     * valorMax con máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @param valorMin - valor mínimo, opcional
     * @return tipo long
     */
    public static long pedirLong(String mensaje, long valorMin) {
        return pedirLong(mensaje, valorMin, Long.MAX_VALUE);
    }

    /**
     * valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @return tipo long
     */
    public static long pedirLong(String mensaje) {
        return pedirLong(mensaje, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Pide por GUI un valor del tipo y rango especificado. valorMin y valorMax
     * opcionales.
     *
     * @param mensaje - texto de la ventana
     * @param valorMin - valor mínimo, opcional
     * @param valorMax - valor máximo opcional, (requiere valorMin).
     * @return tipo double
     */
    public static double pedirDouble(String mensaje, double valorMin, double valorMax) {
        double resultado = 0;
        boolean fin = false;
        do {
            try {
                String aux = JOptionPane.showInputDialog(mensaje);
                if (aux == null) {
                    SalidasGui.errorReintentarIntroducir();
                } else {
                    resultado = Double.parseDouble(aux);
                    if (resultado < valorMin || resultado > valorMax) {
                        SalidasGui.errorFueraRango();
                        SalidasGui.errorReintentarIntroducir();
                    } else {
                        fin = true;
                    }
                }
            } catch (InputMismatchException error) {
                SalidasGui.errorTipo();
                SalidasGui.errorReintentarIntroducir();
            }
        } while (!fin);
        return resultado;
    }

    /**
     * Metodo valorMin especificado y valorMax con máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @param valorMin - valor mínimo, opcional
     * @return tipo double
     */
    public static double pedirDouble(String mensaje, double valorMin) {
        return pedirDouble(mensaje, valorMin, Double.MAX_VALUE);
    }

    /**
     * Metodo valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @return tipo double
     */
    public static double pedirDouble(String mensaje) {
        // el MIN_VALUE de double no es negativo, indica el numero POSITIVO mas pequeño con decimales
        return pedirDouble(mensaje, Double.MAX_VALUE * -1, Double.MAX_VALUE);
    }

    /**
     * Pide por GUI un valor del tipo y rango especificado. valorMin y valorMax
     * opcionales.
     *
     * @param mensaje - texto de la ventana
     * @param valorMax - Longitud máxima del String
     * @param valorMin - Longitud mínima del String, opcional (requiere
     * valorMax).
     * @return tipo String
     */
    public static String pedirString(String mensaje, Integer valorMax, int valorMin) {
        String resultado = "";
        boolean fin = false;
        do {
            try {
                resultado = JOptionPane.showInputDialog(mensaje);
                if (resultado == null) {
                    SalidasGui.errorReintentarIntroducir();
                } else {
                    if (resultado.length() < valorMin || (valorMax != null && resultado.length() > valorMax)) {
                        if (valorMax == null) {
                            SalidasGui.errorVacio();
                        } else {
                            SalidasGui.errorFueraRango();
                        }
                        SalidasGui.errorReintentarIntroducir();
                    } else {
                        fin = true;
                    }
                }
            } catch (InputMismatchException error) {
                SalidasGui.errorTipo();
                SalidasGui.errorReintentarIntroducir();
            }
        } while (!fin);
        return resultado;
    }
/**
     * Pide por GUI un valor del tipo y rango especificado. valorMin y valorMax
     * opcionales.
     *
     * @param mensaje - texto de la ventana
     * @param valorMin - valor mínimo, opcional
     * @param valorMax - valor máximo opcional, (requiere valorMin).
     * @return tipo float
     */
    public static float pedirFloat(String mensaje, float valorMin, float valorMax) {
        float resultado = 0;
        boolean fin = false;
        do {
            try {
                String aux = JOptionPane.showInputDialog(mensaje);
                if (aux == null) {
                    SalidasGui.errorReintentarIntroducir();
                } else {
                    resultado = Float.parseFloat(aux);
                    if (resultado < valorMin || resultado > valorMax) {
                        SalidasGui.errorFueraRango();
                        SalidasGui.errorReintentarIntroducir();
                    } else {
                        fin = true;
                    }
                }
            } catch (InputMismatchException error) {
                SalidasGui.errorTipo();
                SalidasGui.errorReintentarIntroducir();
            }
        } while (!fin);
        return resultado;
    }

    /**
     * Metodo valorMin especificado y valorMax con máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @param valorMin - valor mínimo, opcional
     * @return tipo float
     */
    public static float pedirFloat(String mensaje, float valorMin) {
        return pedirFloat(mensaje, valorMin, Float.MAX_VALUE);
    }

    /**
     * Metodo valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @return tipo float
     */
    public static float pedirFloat(String mensaje) {
        // el MIN_VALUE de float no es negativo, indica el numero POSITIVO mas pequeño con decimales
        return pedirFloat(mensaje, Float.MAX_VALUE * -1, Float.MAX_VALUE);
    }
    /**
     * Metodo valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @return tipo String
     */
    public static String pedirString(String mensaje) {
        return pedirString(mensaje, null, 0);
    }

    /**
     * Metodo valorMin y valorMax con mínimo y máximo valor por defecto
     *
     * @param mensaje - texto de la ventana
     * @param valorMax - Longitud máxima del String
     * @return tipo String
     */
    public static String pedirString(String mensaje, int valorMax) {
        return pedirString(mensaje, valorMax, 0);
    }

    /**
     * Pide por GUI escribrir entre dos textos, para devolver TRUE o FALSE Sigue
     * pidiendo el texto hasta que coincida. Insensible a mayúsculas y
     * minúsculas
     *
     * @param mensaje - texto de la ventana
     * @param si texto para TRUE, por defecto "SI", insensible a mayúsculas
     * @param no texto para FALSE, por defecto "NO", insensible a mayúsculas
     * @return TRUE o FALSE
     */
    public static boolean pedirBoolean(String mensaje, String si, String no) {
        String texto = "";
        boolean resultado = false;
        boolean fin = true;
        do {
            int aux = JOptionPane.showConfirmDialog(null, mensaje, null, JOptionPane.YES_NO_OPTION);

            if (aux == 0) {
                resultado = true;
            } else if (aux == 1 || aux == 2) { // 1 es No y 2 Cancelar
                resultado = false;
            } else {
                fin = false;
                SalidasGui.errorReintentarIntroducir();
            }
        } while (!fin);
        return resultado;
    }

    /**
     * Pide por GUI escribrir entre dos textos, para devolver TRUE o FALSE texto
     * "SI" para TRUE y "NO" para FALSE Sigue pidiendo el texto hasta que
     * coincida.
     *
     * @param mensaje - texto de la ventana
     * @return TRUE o FALSE
     */
    public static boolean pedirBoolean(String mensaje) {
        return pedirBoolean(mensaje, "SI", "NO");
    }

    /**
     * Gui para seleccionar archivos o directorios
     *
     * @param archivos true para admitir archivos
     * @param directorios true para admitir directorios
     * @return devuelve archivo tipo File
     */
    public static File pedirArchivo(boolean archivos, boolean directorios) {
        String resultado = "";
        boolean fin = false;
        JFileChooser selector = new JFileChooser();
        if (archivos && directorios) {
            selector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            selector.setDialogTitle("Selecciona un Archivo o Directorio");
        } else if (archivos) {
            selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
            selector.setDialogTitle("Selecciona un Archivo");
        } else if (directorios) {
            selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            selector.setDialogTitle("Selecciona un Directorio");
        }

        File archivo;
        do {
            selector.showOpenDialog(selector);
            archivo = selector.getSelectedFile();

            if ((archivo == null) || archivo.getName().equals("")) {
                SalidasGui.errorVacio();
            } else {
                fin = true;
            }
        } while (!fin);
        return archivo;
    }

    public static String pedirOpcion(String mensaje, ArrayList<String> listaOpciones) {
        Object resultado;
        resultado = JOptionPane.showInputDialog(null, mensaje,
                null, JOptionPane.QUESTION_MESSAGE, null,
                (Object[]) listaOpciones.toArray(), listaOpciones.get(0));
        return resultado.toString();
    }
    
    /**
     * Pide por GUI un valor del tipo y rango especificado. valorMin y valorMax
     * opcionales.
     *
     * @param mensaje - texto de la ventana
     * @param valorMax - Longitud máxima del String
     * @param valorMin - Longitud mínima del String, opcional (requiere
     * valorMax).
     * @return tipo String
     */
    public static Date pedirFecha(String mensaje) {
        String fechaString;
        Date resultado = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        mensaje = mensaje + Textos.FORMATO_FECHA;
        boolean fin = false;
        do {
            try {
                fechaString = JOptionPane.showInputDialog(mensaje);
                    resultado = formato.parse(fechaString);
                if(resultado != null){
                    fin = true;  
                }
            } catch (InputMismatchException error) {
                SalidasGui.errorReintentarIntroducir();
            } catch (ParseException error) {
                SalidasGui.errorTipo();
                SalidasGui.errorReintentarIntroducir();
            }
        } while (!fin);
        return resultado;
    }
}
