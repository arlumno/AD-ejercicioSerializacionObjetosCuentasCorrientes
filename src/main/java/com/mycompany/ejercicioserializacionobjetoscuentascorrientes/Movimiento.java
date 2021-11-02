/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author a20armandocb
 */
public class Movimiento implements Serializable{
    String numeroCuenta;
    Date fechaOperacion;
    Time hora;
    float cantidad;
    double saldoActual;
}
