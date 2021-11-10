/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author a20armandocb
 */
public class CuentaPlazo extends Cuenta {

    private float intereses;
    private Date fechaVencimiento;

    private long depositoPlazo;

    public CuentaPlazo(float intereses, Date fechaVencimiento, long depositoPlazo, String numero, String sucursal, ArrayList<Cliente> clientes) {
        super(numero, sucursal, clientes);
        this.intereses = intereses;
        this.fechaVencimiento = fechaVencimiento;
        this.depositoPlazo = depositoPlazo;
    }

    public float getIntereses() {
        return intereses;
    }

    public long getDepositoPlazo() {
        return depositoPlazo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        return super.toString() 
                + "\n  Deposito: " + getDepositoPlazo()
                + "\n  Intereses: " + getIntereses()
                + "\n  Vencimiento: " + getFechaVencimiento().toString();
    }
}
