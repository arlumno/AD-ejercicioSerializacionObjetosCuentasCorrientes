/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

/**
 *
 * @author a20armandocb
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorCuentas gestor = new GestorCuentas();
        String numero;
        numero = "12345A";
        System.out.println(numero + " = " + gestor.comprobarNumeroCuentaValido(numero));
        numero = "12345s";
        System.out.println(numero + " = " + gestor.comprobarNumeroCuentaValido(numero));
        numero = "345G";
        System.out.println(numero + " = " + gestor.comprobarNumeroCuentaValido(numero));
        numero = "1234s5s";
        System.out.println(numero + " = " + gestor.comprobarNumeroCuentaValido(numero));
        numero = "123245J";
        System.out.println(numero + " = " + gestor.comprobarNumeroCuentaValido(numero));
        numero = "00000A";
        System.out.println(numero + " = " + gestor.comprobarNumeroCuentaValido(numero));
    }
    
}
