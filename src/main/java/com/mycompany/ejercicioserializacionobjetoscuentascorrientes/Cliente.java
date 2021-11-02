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
public class Cliente implements Serializable{
    private String dni;
    private String nombre;
    private String direccion;
    private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

    public Cliente(String dni, String nombre, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;        
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    public void addCuenta(Cuenta cuenta){
        if(!cuentas.contains(cuenta)){
            cuentas.add(cuenta);
        }
    }
    
}
