/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.csdam.pr.libreriaar;

import javax.swing.JOptionPane;


/**
 *  Mensajes de la libreria con salida directa por consola.
 * @author Ar
 */
public class SalidasGui {
  
    /**
     *  Mensaje de error para cuando el valor está fuera del rango requerido, aunque coincida el tipo de dato
     */
    static void errorFueraRango() {
        JOptionPane.showMessageDialog(null, "Valor introducido fuera del rango requerido. ");
    }
    
    /**
     *  Mensaje de error para cuando el valor es null.
     */
    static void errorVacio() {
        JOptionPane.showMessageDialog(null, "Valor introducido no puede estár vacio.");
    }
    /**
     *  Mensaje de error para cuando el menu no tiene opciones.
     */
    static void errorMenuVacio() {
        JOptionPane.showMessageDialog(null, "Valor introducido no puede estár vacio.");
    }
    
    /**
     *  Mensaje de error para cuando se ha alcanzado un limite máximo.
     */
    static void errorLimite() {
        JOptionPane.showMessageDialog(null, "Limite máximo alcanzado.");
    }
    
    /**
     * Mensaje de error para cuando el tipo no coincide.
     */
    static void errorTipo() {
        JOptionPane.showMessageDialog(null, "Datos introducidos NO Válidos. El tipo no coincide.");
    }
    
    /**
     * Mensaje para indicar que el proceso se vuelve a repetir debido a un error previo.
     */
    static void errorReintentarIntroducir() {
        JOptionPane.showMessageDialog(null, "Vuelva a introducirlo.");
    }
    
    /**
     * Mensaje con instrucciones para volver al menú anterior.
     */
    static void repetirMenu(){
        JOptionPane.showMessageDialog(null, "\n Pulsa Enter para volver al menú.");
    }    
    static void saltoLinea(){
        JOptionPane.showMessageDialog(null, "\b");
    }
}
