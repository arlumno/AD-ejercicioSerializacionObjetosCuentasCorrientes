/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author a20armandocb
 */
public class GestorCuentas {

    private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

    public GestorCuentas() {
    }

    public boolean crearCuenta(String numeroCuenta) {
        boolean resultado = false;
        /**
         * Para poder dar de alta una cuenta ,tendremos que comprobar que el
         * número de cuenta
         */
        resultado = comprobarNumeroCuentaValido(numeroCuenta) && comprobarNumeroCuentaNoExiste(numeroCuenta);
        return resultado;
    }

    public boolean comprobarNumeroCuentaValido(String numeroCuenta) {                
        return Pattern.compile("^[0-9]{5}[A-Z]{1}").matcher(numeroCuenta).matches();        
    }

    public boolean comprobarNumeroCuentaNoExiste(String numeroCuenta) {
        boolean resultado = true;
        for(Cuenta cuenta: cuentas){
            if (cuenta.getNumero() == numeroCuenta){
                resultado = false;
            }
        }
        return resultado;
    }
}
