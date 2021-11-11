/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.csdam.pr.libreriaar;


/**
 *  Mensajes de la libreria con salida directa por consola.
 * @author Ar
 */
public class Salidas {
  
    /**
     *  Mensaje de error para cuando el valor está fuera del rango requerido, aunque coincida el tipo de dato
     */
    static void errorFueraRango() {
        System.out.println("Valor introducido fuera del rango requerido. ");
    }
    
    /**
     *  Mensaje de error para cuando el valor es null.
     */
    static void errorVacio() {
        System.out.println("Valor introducido no puede estár vacio.");
    }
    /**
     *  Mensaje de error para cuando el menu no tiene opciones.
     */
    static void errorMenuVacio() {
        System.out.println("Valor introducido no puede estár vacio.");
    }
    
    /**
     *  Mensaje de error para cuando se ha alcanzado un limite máximo.
     */
    static void errorLimite() {
        System.out.println("Limite máximo alcanzado.");
    }
    
    /**
     * Mensaje de error para cuando el tipo no coincide.
     */
    static void errorTipo() {
        System.out.println("Datos introducidos NO Válidos. El tipo no coincide.");
    }
    
    /**
     * Mensaje para indicar que el proceso se vuelve a repetir debido a un error previo.
     */
    static void errorReintentarIntroducir() {
        System.out.println("Vuelva a introducirlo.");
    }
    
    /**
     * Mensaje con instrucciones para volver al menú anterior.
     */
    static void repetirMenu(){
        System.out.println("\n Pulsa Enter para volver al menú.");
    }    
    static void saltoLinea(){
        System.out.println("\b");
    }
}
