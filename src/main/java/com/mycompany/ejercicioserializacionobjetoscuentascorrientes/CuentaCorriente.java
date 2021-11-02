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

    public CuentaCorriente(String numero, String sucursal, Cliente cliente) {       
        super(numero, sucursal, cliente);        
        this.saldo = 0;
    }
    
}
