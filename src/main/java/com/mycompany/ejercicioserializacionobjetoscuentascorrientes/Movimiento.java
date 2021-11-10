/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author a20armandocb
 */
public class Movimiento implements Serializable{

    
    Date fechaOperacion;
    //Time hora;
    double cantidad;    
    double saldoResultante;

    public Movimiento(double cantidad, double saldoResultante) {        
        //fechaOperacion = new Date(new java.util.Date().getTime());
        fechaOperacion = new Date();
        this.cantidad = cantidad;
        this.saldoResultante = saldoResultante;
            }
    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getSaldoResultante() {
        return saldoResultante;
    }
    
    @Override
    public String toString(){   
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
        return "[" + formato.format(fechaOperacion) + "] || "+ cantidad + "€  || " + saldoResultante + "€";
    }
}
