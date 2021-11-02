/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author a20armandocb
 */
public class GestorCuentas {

    private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
    //private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    //private ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();        
    public GestorCuentas() {
    }

    public void crearCliente(String dni, String nombre, String direccion){
        Cliente cliente = new Cliente(dni, nombre, direccion);                        
    }
    
//    public boolean clienteExistePorDni(String dni){
//        ArrayList<Cliente> clientes = clientes();
//        boolean resultado = false;
//        for(int i = 0; i < clientes.size() && !resultado; i++){            
//            if(clientes.get(i).getDni().equals(dni)){
//               resultado = true;
//            };
//        }
//        return resultado;
//    }
    
    public Cliente buscarClientePorDni(String dni){
        ArrayList<Cliente> clientes = clientes();        
        Cliente cliente = null;
        for(int i = 0; i < clientes.size() && cliente == null; i++){            
            if(clientes.get(i).getDni().equals(dni)){
               cliente = clientes.get(i);               
            };
        }
        return cliente;
    }
    
    public ArrayList<Cliente> pedirClientes(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
                       
        return clientes;
    }
    
    public boolean crearCuenta(String numeroCuenta, ArrayList<Cliente> clientes) {
        boolean comprobacion = false;
        Cuenta cuenta;
        /**
         * Para poder dar de alta una cuenta ,tendremos que comprobar que el
         * número de cuenta
         */
        comprobacion = comprobarNumeroCuentaValido(numeroCuenta) && comprobarNumeroCuentaNoExiste(numeroCuenta);        
        
        return comprobacion;
    }
    
    private void guardarCuenta(Cuenta cuenta) throws Exception{
        if(cuenta.getClientes().size() == 0){
            throw new Exception("La cuenta no tiene clientes asoiciados");
        }
        cuentas.add(cuenta);
    }
    
    public boolean comprobarNumeroCuentaValido(String numeroCuenta) {                
        return Pattern.compile("^[0-9]{5}[A-Z]{1}").matcher(numeroCuenta).matches();        
    }

    public boolean comprobarNumeroCuentaNoExiste(String numeroCuenta) {
        boolean resultado = true;
        for(Cuenta cuenta: cuentas){
            if (cuenta.getNumero() == numeroCuenta){
                resultado = false;
            }
        }
        return resultado;
    }
    
    public ArrayList<Cliente> clientes(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for(Cuenta cuenta: cuentas){
            for(Cliente cliente: cuenta.getClientes()){
                clientes.add(cliente);
            }
        }
        return clientes;
    }


}
