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
public class CuentaCorriente extends Cuenta {

    private ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
    //  private double saldo;
    public final static String OPERACION_RETIRAR = "Retirar";
    public final static String OPERACION_INGRESAR = "Ingresar";

    public CuentaCorriente(String numero, String sucursal, ArrayList<Cliente> clientes) {
        super(numero, sucursal, clientes);
//        this.saldo = 0;
    }

    public double getSaldo() {
        double saldo = 0;
        for (Movimiento movimiento : movimientos) {
            saldo += movimiento.getCantidad();
        }
        return saldo;
    }

    public void ingresar(double importe) {
        if (importe > 0) {
            double cantidad = importe;
            Movimiento movimiento = new Movimiento(cantidad, getSaldo() + cantidad);
            movimientos.add(movimiento);
            System.out.println(movimiento.toString());
        }
    }

    public void retirar(double importe) {
        if (importe > 0) {
            double cantidad = importe * -1; //pasamos el nº a negativo.
            Movimiento movimiento = new Movimiento(cantidad, getSaldo() + cantidad);
            movimientos.add(movimiento);
        }
    }
    
    public ArrayList<Movimiento> getMovimientos(){
        return movimientos;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\n  Saldo: " + getSaldo();
    }

}
