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
public class CuentaBuilder {

    private String numero = null;
    private String sucursal = null;
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    private float intereses;
    private boolean interesesInicializado = false;
    private Date fechaVencimiento;
    private long depositoPlazo;
    private boolean depositoPlazoInicializado = false;

    private Cuenta cuenta;

    private String tipoCuenta;
    public static final String CUENTA_CORRIENTE = "Cuenta corriente";
    public static final String CUENTA_PLAZO = "Cuenta a plazo";
    private String error = "";

    public CuentaBuilder() {
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setIntereses(float intereses) {
        this.intereses = intereses;
        interesesInicializado = true;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setDepositoPlazo(long depositoPlazo) {
        this.depositoPlazo = depositoPlazo;
        depositoPlazoInicializado = true;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void addCliente(Cliente cliente) {
        if (!clientes.contains(cliente)) {
            clientes.add(cliente);
        }
    }

    public String getError() {
        return error;
    }
    
    
    /**
     * Construye y devuelve la cuenta con los parametros especificados
     * anteriormente.
     *
     * @return Cuenta del subttipo indicado o Null si da error.
     */
    public Cuenta getCuenta() {
        cuenta = null;

        if (clientes.size() == 0) {
            error = "No hay clientes en la cuenta -- ";
        } else if (numero == null) {
            error = "No hay se ha indicado un numero de cuenta -- ";
        } else if (sucursal == null) {
            error = "No hay se ha indicado una sucursal numero de cuenta -- ";
        } else {
            switch (tipoCuenta) {
                
                case CUENTA_CORRIENTE:
                    cuenta = new CuentaCorriente(numero, sucursal, clientes);
                    break;

                case CUENTA_PLAZO:
                    if (!interesesInicializado) {
                        error = "No hay clientes en la cuenta -- ";
                    } else if (fechaVencimiento == null) {
                        error = "No hay se ha indicado un numero de cuenta -- ";
                    } else if (!depositoPlazoInicializado) {
                        error = "No hay se ha indicado una sucursal numero de cuenta -- ";
                    } else {
                        cuenta = new CuentaPlazo(intereses, fechaVencimiento, depositoPlazo, numero, sucursal, clientes);
                    }
                    break;
                    
                default:
                    error += "No hay se ha indicado una sucursal numero de cuenta -- ";
                    break;
            }
        }
        return cuenta;
    }
}
