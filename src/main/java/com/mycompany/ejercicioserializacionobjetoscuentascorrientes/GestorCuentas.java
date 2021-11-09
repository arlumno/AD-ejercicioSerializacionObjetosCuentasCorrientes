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
import java.text.SimpleDateFormat;
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
        cargarFicherosCuentas();
    }

    public Cliente crearCliente(String numeroCuenta) {
        String dniCliente = EntradasGui.pedirString("Indíca el dni del cliente");
        Cliente cliente = buscarClientePorDni(dniCliente);
        if (cliente == null) {
            //cliente nuevo
            JOptionPane.showMessageDialog(null, "El cliente No Existe.\n Creando nueva ficha.");
            String nombreCliente = EntradasGui.pedirString("Indíca el nombre del cliente");
            String direccionCliente = EntradasGui.pedirString("Indíca la dirección del cliente");
            cliente = new Cliente(dniCliente, nombreCliente, direccionCliente);
        }
        return cliente;
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

//    public void cuentaSetNumero(String numero) {
//        cuentaBuilder.setNumero(numero);
//    }
//
//    public void cuentaSetSucursal(String sucursal) {
//        cuentaBuilder.setSucursal(sucursal);
//    }
//
//    /**
//     *
//     * @param tipoCuenta Opciones Disponibles mediante constantes en
//     * CuentaBuilder.CUENTA_
//     */
//    public void cuentaSetTipoCuenta(String tipoCuenta) {
//        cuentaBuilder.setTipoCuenta(tipoCuenta);
//    }
//
//    public void cuentasetIntereses(Float intereses) {
//        cuentaBuilder.setIntereses(intereses);
//    }
//
//    public void cuentaSetDepositoPlazo(Long depositoPlazo) {
//        cuentaBuilder.setDepositoPlazo(depositoPlazo);
//    }
//
//    public void cuentaSetFechaVencimiento(Date fechaVencimiento) {
//        cuentaBuilder.setFechaVencimiento(fechaVencimiento);
//    }
//
//    public void cuentaAddCliente(Cliente cliente) {
//
//        cuentaBuilder.addCliente(cliente);
//    }
    public void addClientecuenta() {
        String numeroCuenta = EntradasGui.pedirString("Indíca un número de cuenta al que añadir el cliente");
        int index = buscarCuenta(numeroCuenta);
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "El numero de cuenta No Existe.");            
        }else{
            Cliente cliente = crearCliente(numeroCuenta);
            cuentas.get(index).addCliente(cliente);
        }
       
    }

    public void crearCuenta() {
        boolean comprobacion = false;
        String numeroCuenta = "";
        String tipoCuenta;
        Cliente cliente;

        //parte de cuenta
        //indicar nº de cuenta
        while (numeroCuenta.equals("")) {
            numeroCuenta = EntradasGui.pedirString("Indíca un número de cuenta válido (5 dígitos + letra mayúscula)");
            if (comprobarNumeroCuentaValido(numeroCuenta)) {
                if (buscarCuenta(numeroCuenta) != -1) {
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

        // seleccion tipo de cuenta
        ArrayList<String> listaOpciones = new ArrayList<String>();
        listaOpciones.add(CuentaBuilder.CUENTA_CORRIENTE);
        listaOpciones.add(CuentaBuilder.CUENTA_PLAZO);
        tipoCuenta = EntradasGui.pedirOpcion("Que tipo de cuenta", listaOpciones);

        //parte de cliente
        do {
            cliente = crearCliente(numeroCuenta);
            cuentaBuilder.addCliente(cliente);
        } while (EntradasGui.pedirBoolean("¿Quieres añadir otro cliente?"));

        //construir cuenta plazo
        if (tipoCuenta == CuentaBuilder.CUENTA_PLAZO) {
            cuentaBuilder.setDepositoPlazo(EntradasGui.pedirLong("Indíca el deposito que quieres ingresar"));
            cuentaBuilder.setIntereses(EntradasGui.pedirFloat("Indíca los intereses"));
            cuentaBuilder.setFechaVencimiento(EntradasGui.pedirFecha("Indíca la fecha de vencimiento"));
        }
        guardarCuenta();

    }

    private void guardarCuenta() {
        Cuenta cuenta = cuentaBuilder.getCuenta();
        if (cuenta == null) {
            JOptionPane.showMessageDialog(null, cuentaBuilder.getError());
            //    throw new Exception(cuentaBuilder.getError());
        } else {
            cuentas.add(cuenta);
        }
    }

    public void cuentaEliminar() {
        String numeroCuenta = EntradasGui.pedirString("Indíca un número de cuenta a eliminar");
        boolean encontrado = false;
        for (int i = 0; i < cuentas.size() && !encontrado; i++) {
            if (cuentas.get(i).getNumero().equals(numeroCuenta)) {
                encontrado = true;
                if (cuentas.get(i) instanceof CuentaCorriente) {
                    cuentas.remove(i);
                    JOptionPane.showMessageDialog(null, "Cuenta eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "La cuenta no se puede eliminar porque no es una Cuenta Corriente");
                }
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "El nº de cuenta no figura en nuestra base de datos.");
        }

    }

    public boolean comprobarNumeroCuentaValido(String numeroCuenta) {
        return Pattern.compile("^[0-9]{5}[A-Z]{1}").matcher(numeroCuenta).matches();
    }

    /**
     * 
     * @param numeroCuenta referencia dew cuenta a buscar
     * @return devuelve el indice dentro del ArrayList Cuentas, o -1 si no lo encuentra
     */
    public int buscarCuenta(String numeroCuenta) {
        int resultado = -1;
        for (int i = 0; i <cuentas.size();i++) {
            if (cuentas.get(i).getNumero().equals(numeroCuenta)) {
                resultado = i;
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
                for (int i = 1; i < cuentas.size(); i++) {
                    oosT.writeObject(cuentas.get(i));
                }
                JOptionPane.showMessageDialog(null, "-- Guardadas " + cuentas.size() + " cuentas. --");
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
                int counter = 0;
                try {
                    FileInputStream fi = new FileInputStream(fichero);
                    ois = new ObjectInputStream(fi);
                    Object entrada;
                    while (fi.available() > 0 && ((entrada = ois.readObject()) != null)) {
                        cuentas.add((Cuenta) entrada);
                        counter++;
                    }
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Error al leer el archivo:\n" + e.toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error:\n " + e.toString());
                    System.out.println("Error: \n" + e.toString());
                } finally {
                    JOptionPane.showMessageDialog(null, "-- Cargadas " + counter + " cuentas. --");
                    try {
                        ois.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al cerrar el flujo " + e.toString());
                    }
                }
            } else {
                //  JOptionPane.showMessageDialog(null, "El archivo " + rutaFicheros + nombreFichero + " NO existe ");

            }
        }
    }

    public void demo() {
        cuentaBuilder.addCliente(new Cliente("12345678Z", "john doe", "cualquier sitios"));
        cuentaBuilder.setNumero("12345A");
        cuentaBuilder.setSucursal("florida");
        cuentaBuilder.setTipoCuenta(CuentaBuilder.CUENTA_CORRIENTE);
        guardarCuenta();

        cuentaBuilder.addCliente(new Cliente("53170624Y", "Paco doe", "navarra"));
        cuentaBuilder.setNumero("32345J");
        cuentaBuilder.setSucursal("noruega");
        cuentaBuilder.setIntereses(2.3F);
        cuentaBuilder.setDepositoPlazo(20000L);
        try {
            cuentaBuilder.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy").parse("21/12/2021"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        cuentaBuilder.setTipoCuenta(CuentaBuilder.CUENTA_PLAZO);
        guardarCuenta();
    }

    public String cuentasToString() {
        StringBuilder resutado = new StringBuilder();
        for (Cuenta cuenta : cuentas) {
            resutado.append(cuenta.toString() + "\n");
        }
        return resutado.toString();
    }

}
