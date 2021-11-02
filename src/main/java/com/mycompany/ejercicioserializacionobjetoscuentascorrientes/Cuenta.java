/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author a20armandocb
 */
public abstract class Cuenta implements Serializable{
    private String numero;
    private String sucursal;
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    public Cuenta(String numero, String sucursal, Cliente cliente) {
        this.numero = numero;
        this.sucursal = sucursal;
        addCliente(cliente);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    private void addCliente(Cliente cliente) {
        if(!clientes.contains(cliente)){            
            clientes.add(cliente);
            cliente.addCuenta(this);
        }
    }
    
}
