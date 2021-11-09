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
public abstract class Cuenta implements Serializable {

    private String numero;
    private String sucursal;
    private ArrayList<Cliente> clientes;

    public Cuenta(String numero, String sucursal, ArrayList<Cliente> clientes) {
        this.numero = numero;
        this.sucursal = sucursal;
        this.clientes = clientes;
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

    public void addCliente(Cliente cliente) {
        if (!clientes.contains(cliente)) {
            clientes.add(cliente);
            cliente.addCuenta(this);
        }
    }
    public String getClientesToString(){
        StringBuilder resultado = new StringBuilder();
        for(Cliente cliente: clientes){
            resultado.append("\n  --(" + cliente.getNombre() + " - " + cliente.getDni()+ " - " + cliente.getDireccion());
        }
        return resultado.toString();
    }
    @Override
    public String toString() {
        return "Cuenta nº: " + getNumero() 
                + "Clientes:" + getClientesToString() + "\n"
                + " Sucursal: " + getSucursal();
    }

}
