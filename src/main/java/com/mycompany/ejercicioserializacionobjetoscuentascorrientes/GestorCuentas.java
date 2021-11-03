/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import ar.csdam.pr.libreriaar.EntradasGui;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author a20armandocb
 */
public class GestorCuentas {

    private CuentaBuilder cuentaBuilder = new CuentaBuilder();
    private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
    private String rutaFicheros = "src/main/java/com/mycompany/ejercicioserializacionobjetoscuentascorrientes/ficheros/";
    private String nombreFichero = "save.dat";
    //private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    //private ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();        

    public GestorCuentas() {
    }

    public void crearCliente(String dni, String nombre, String direccion) {
        Cliente cliente = new Cliente(dni, nombre, direccion);
    }

    /**
     * Comprueba si el cliente existe
     *
     * @param dni DNI del cliente a buscar.
     * @return El primer cliente encontrado o null si no lo encuentra.
     */
    public Cliente buscarClientePorDni(String dni) {
        ArrayList<Cliente> clientes = clientes();
        Cliente cliente = null;
        for (int i = 0; i < clientes.size() && cliente == null; i++) {
            if (clientes.get(i).getDni().equals(dni)) {
                cliente = clientes.get(i);
            };
        }
        return cliente;
    }

    public void cuentaSetNumero(String numero) {
        cuentaBuilder.setNumero(numero);
    }

    public void cuentaSetSucursal(String sucursal) {
        cuentaBuilder.setSucursal(sucursal);
    }

    /**
     *
     * @param tipoCuenta Opciones Disponibles mediante constantes en
     * CuentaBuilder.CUENTA_
     */
    public void cuentaSetTipoCuenta(String tipoCuenta) {
        cuentaBuilder.setTipoCuenta(tipoCuenta);
    }

    public void cuentasetIntereses(Float intereses) {
        cuentaBuilder.setIntereses(intereses);
    }

    public void cuentaSetDepositoPlazo(Long depositoPlazo) {
        cuentaBuilder.setDepositoPlazo(depositoPlazo);
    }

    public void cuentaSetFechaVencimiento(Date fechaVencimiento) {
        cuentaBuilder.setFechaVencimiento(fechaVencimiento);
    }

    public void cuentaAddCliente(Cliente cliente) {

        cuentaBuilder.addCliente(cliente);
    }

    public boolean crearCuenta() {
        boolean comprobacion = false;
        String numeroCuenta = "";
        String dniCliente;
        String nombreCliente;
        String direccionCliente;
        String tipoCuenta;
        Cliente cliente;

        //parte de cuenta
        while (numeroCuenta.equals("")) {
            numeroCuenta = EntradasGui.pedirString("Indíca un número de cuenta válido (5 dígitos + letra mayúscula)");
            if (comprobarNumeroCuentaValido(numeroCuenta)) {
                if (!comprobarNumeroCuentaNoExiste(numeroCuenta)) {
                    JOptionPane.showMessageDialog(null, "El número de cuenta YA existe.");
                    numeroCuenta = "";
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de cuenta NO válido.");
                numeroCuenta = "";
            }
        }
        cuentaBuilder.setNumero(numeroCuenta);
        cuentaBuilder.setSucursal(EntradasGui.pedirString("Indíca la ref de la sucursal"));
        
        ArrayList<String> listaOpciones = new ArrayList<String>();
        listaOpciones.add(CuentaBuilder.CUENTA_CORRIENTE);
        listaOpciones.add(CuentaBuilder.CUENTA_PLAZO);
        
        tipoCuenta = EntradasGui.pedirOpcion("Que tipo de cuenta", listaOpciones);
        JOptionPane.showMessageDialog(null, tipoCuenta);
        
        //parte de cliente
        do {
            dniCliente = EntradasGui.pedirString("Indíca el dni del cliente");
            cliente = buscarClientePorDni(dniCliente);
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "El cliente No Existe.\n Creando nueva ficha.");
                nombreCliente = EntradasGui.pedirString("Indíca el nombre del cliente");
                direccionCliente = EntradasGui.pedirString("Indíca el nombre del cliente");
                cliente = new Cliente(dniCliente, nombreCliente, direccionCliente);
            }
            cuentaBuilder.addCliente(cliente);
        } while (EntradasGui.pedirBoolean("¿Quieres añadir otro cliente?"));

        /**
         * Para poder dar de alta una cuenta ,tendremos que comprobar que el
         * número de cuenta
         */
        return comprobacion;
    }

    private void guardarCuenta() throws Exception {
        Cuenta cuenta = cuentaBuilder.getCuenta();
        if (cuenta == null) {
            throw new Exception(cuentaBuilder.getError());
        }
        cuentas.add(cuenta);
    }

    public boolean comprobarNumeroCuentaValido(String numeroCuenta) {
        return Pattern.compile("^[0-9]{5}[A-Z]{1}").matcher(numeroCuenta).matches();
    }

    public boolean comprobarNumeroCuentaNoExiste(String numeroCuenta) {
        boolean resultado = true;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumero() == numeroCuenta) {
                resultado = false;
            }
        }
        return resultado;
    }

    public ArrayList<Cliente> clientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for (Cuenta cuenta : cuentas) {
            for (Cliente cliente : cuenta.getClientes()) {
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void guardarFicheroCuentas() {
        if (cuentas.size() != 0) {
            File fichero = new File(rutaFicheros + nombreFichero);
            ObjectOutputStream oos = null;
            ObjectOutputStream oosT = null;

            try {
                oos = new ObjectOutputStream(new FileOutputStream(fichero));
                oos.writeObject(cuentas.get(0));

                //sin cabecera
                oosT = new ObjectOutputStream(new FileOutputStream(fichero, true)) {
                    @Override
                    protected void writeStreamHeader() {
                        try {
                            reset();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                };
                for (int i = 0; i < cuentas.size(); i++) {
                    oosT.writeObject(cuentas.get(i));
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el fichero\n" + e.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error:\n" + e.toString());
            } finally {
                try {
                    oos.close();
                    oosT.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el flujo " + e.toString());
                }
            }

        }
    }

    public void cargarFicherosCuentas() {
        if (cuentas == null || cuentas.size() == 0) {
            File fichero = new File(rutaFicheros + nombreFichero);
            ObjectInputStream ois = null;
            if (fichero.exists()) {
                try {
                    ois = new ObjectInputStream(new FileInputStream(fichero));
                    Object entrada;
                    int counter = 0;
                    while ((entrada = ois.readObject()) != null) {
                        cuentas.add((Cuenta) entrada);
                        counter++;
                    }
                    System.out.println("-- Añadidas " + counter + " cuentas. --");
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Error al leer el archivo:\n" + e.toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error:\n " + e.toString());
                    System.out.println("Error: \n" + e.toString());
                } finally {
                    try {
                        ois.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al cerrar el flujo " + e.toString());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo " + rutaFicheros + nombreFichero + " NO existe ");

            }
        }
    }
}
