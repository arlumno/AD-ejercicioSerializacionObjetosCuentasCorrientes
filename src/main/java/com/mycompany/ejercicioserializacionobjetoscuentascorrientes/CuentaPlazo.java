/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import java.util.Date;

/**
 *
 * @author a20armandocb
 */
public class CuentaPlazo extends Cuenta{
    private float intereses;
    private Date fechaVencimiento;
    private long depositoPlazo;

    public CuentaPlazo(float intereses, Date fechaVencimiento, long depositoPlazo, String numero, String sucursal, Cliente cliente) {
        super(numero, sucursal, cliente);
        this.intereses = intereses;
        this.fechaVencimiento = fechaVencimiento;
        this.depositoPlazo = depositoPlazo;
    }
    
}
