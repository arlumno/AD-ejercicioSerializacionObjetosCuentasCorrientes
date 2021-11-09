/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import java.util.ArrayList;

/**
 *
 * @author a20armandocb
 */
public class CuentaCorriente extends Cuenta{
    private ArrayList<Movimiento> movimientos;
    private double saldo;


    public CuentaCorriente(String numero, String sucursal,ArrayList<Cliente> clientes) {       
        super(numero, sucursal, clientes);        
        this.saldo = 0;
    }

    public double getSaldo() {
        return saldo;
    }
    
    @Override
    public String toString() {
        return super.toString() 
                + " Saldo: " + getSaldo();
    }
    
}
