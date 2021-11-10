/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import ar.csdam.pr.libreriaar.EntradasGui;
import ar.csdam.pr.libreriaar.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * Comprueba si el cliente existe
     *
     * @param dni DNI del cliente a buscar.
     * @return El primer cliente encontrado o null si no lo encuentra.
     */
    public Cliente buscarClientePorDni(String dni) {
        ArrayList<Cliente> clientes = clientes();
        Cliente cliente = null;
        dni = Utils.validarYFormatearDni(dni);
        if (dni == null) {
            JOptionPane.showMessageDialog(null, "Formato de DNI NO Válido.");
        } else {
            for (int i = 0; i < clientes.size() && cliente == null; i++) {
                if (clientes.get(i).getDni().equals(dni)) {
                    cliente = clientes.get(i);
                };
            }
        }
        return cliente;
    }

    public void modificarCliente() {
        String dniCliente = EntradasGui.pedirString("Indíca el dni del cliente");
        dniCliente = Utils.validarYFormatearDni(dniCliente);
        if (dniCliente == null) {
            JOptionPane.showMessageDialog(null, "Formato de DNI NO Válido");
        } else {
            Cliente cliente = buscarClientePorDni(dniCliente);
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "El cliente No Existe.");
            } else {
                String direccion = EntradasGui.pedirString("Dirección actual:" + cliente.getDireccion() + "\nIndique la nueva dirección:");
                cliente.setDireccion(direccion);
            }
        }
    }

    /**
     * Añade un cliente nuevo o existente a un nº de cuenta
     */
    public void addClienteCuenta() {
        String numeroCuenta = EntradasGui.pedirString("Indíca un número de cuenta al que añadir el cliente");
        int index = buscarCuenta(numeroCuenta);
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "El numero de cuenta No Existe.");
        } else {
            Cliente cliente = crearCliente();
            cuentas.get(index).addCliente(cliente);
            JOptionPane.showMessageDialog(null, "Se ha añadido el títular al nº de cuenta ." + numeroCuenta);
        }

    }

    /**
     * Pide un DNI Válido y busca si ya existe, si no lo encuentra, crea uno
     * nuevo.
     *
     * @return devuelve el Cliente encontrado o el Cliente creado
     */
    public Cliente crearCliente() {
        String dniCliente = null;
        boolean pedirDni = true;
        while (pedirDni) {
            dniCliente = EntradasGui.pedirString("Indíca el dni del cliente");
            dniCliente = Utils.validarYFormatearDni(dniCliente);
            if ((dniCliente == null)) {
                JOptionPane.showMessageDialog(null, "Formato de DNI NO Válido, Vuelva a intentarlo");
            } else {
                pedirDni = false;
            }
        }
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
            cliente = crearCliente();
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
            cuentaBuilder = new CuentaBuilder(); // para destruir el anterior objeto
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

    public void altaMovimiento() {
        String numeroCuenta = EntradasGui.pedirString("Indíca un número de cuenta para operar.");
        boolean encontrado = false;
        for (int i = 0; i < cuentas.size() && !encontrado; i++) {
            if (cuentas.get(i).getNumero().equals(numeroCuenta)) {
                encontrado = true;
                if (cuentas.get(i) instanceof CuentaCorriente) {
                    String opcion = EntradasGui.pedirOpcion(numeroCuenta, new ArrayList<>(Arrays.asList(CuentaCorriente.OPERACION_INGRESAR, CuentaCorriente.OPERACION_RETIRAR)));
                    switch (opcion) {
                        case CuentaCorriente.OPERACION_INGRESAR:
                            ((CuentaCorriente) cuentas.get(i)).ingresar(EntradasGui.pedirDouble("Importe a ingresar", 0));
                            break;
                        case CuentaCorriente.OPERACION_RETIRAR:
                            double importe = EntradasGui.pedirDouble("Importe a retirar", 0);
                            if (((CuentaCorriente) cuentas.get(i)).getSaldo() >= importe) {
                                ((CuentaCorriente) cuentas.get(i)).retirar(importe);
                            } else {
                                JOptionPane.showMessageDialog(null, "No se puede realizar la operación. SALDO INSUFICIENTE");
                            }
                            break;
                    }
                    //JOptionPane.showMessageDialog(null, "Cuenta eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "Las cuentas a plazo no permiten operaciones de saldo");
                }
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "El nº de cuenta no figura en nuestra base de datos.");
        }
    }

    /**
     * Valida si el nº de cuenta cumple el patrón obligatorio
     *
     * @param numeroCuenta
     * @return True si es correcto False si es no coincide.
     */
    public boolean comprobarNumeroCuentaValido(String numeroCuenta) {
        return Pattern.compile("^[0-9]{5}[A-Z]{1}").matcher(numeroCuenta).matches();
    }

    /**
     * Comprueba si la cuent existe
     *
     * @param numeroCuenta referencia de cuenta a buscar
     * @return devuelve el indice dentro del ArrayList Cuentas, o -1 si no lo
     * encuentra
     */
    public int buscarCuenta(String numeroCuenta) {
        int resultado = -1;
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getNumero().equals(numeroCuenta)) {
                resultado = i;
            }
        }
        return resultado;
    }

    /**
     * Listado de clientes
     *
     * @return ArrayList de clientes.
     */
    public ArrayList<Cliente> clientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for (Cuenta cuenta : cuentas) {
            for (Cliente cliente : cuenta.getClientes()) {
                if (!clientes.contains(cliente)) {
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }

    /**
     * Guarda el las cuenta en el fichero
     */
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

    /**
     * Carga las cuentas desde el fichero
     */
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
            }
        }
    }

    public void demo() {
        int cuentasEliminadas = cuentas.size();
        cuentas.clear();
        //0
        cuentaBuilder.addCliente(new Cliente("12345678Z", "john doe", "cualquier sitios"));
        cuentaBuilder.setNumero("12345A");
        cuentaBuilder.setSucursal("florida");
        cuentaBuilder.setTipoCuenta(CuentaBuilder.CUENTA_CORRIENTE);
        guardarCuenta();

        //1
        cuentaBuilder.addCliente(new Cliente("53170624Y", "Armando Castro", "calle undostres"));
        cuentaBuilder.setNumero("32345C");
        cuentaBuilder.setSucursal("noruega");
        cuentaBuilder.setIntereses(2.3F);
        cuentaBuilder.setDepositoPlazo(15000L);
        try {
            cuentaBuilder.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy").parse("05/02/2026"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cuentaBuilder.setTipoCuenta(CuentaBuilder.CUENTA_PLAZO);
        guardarCuenta();

        //2
        cuentaBuilder.addCliente(new Cliente("12345670y", "Lorena Abalde", "Vigo"));
        cuentaBuilder.setNumero("12345L");
        cuentaBuilder.setSucursal("florida");
        cuentaBuilder.setTipoCuenta(CuentaBuilder.CUENTA_CORRIENTE);
        guardarCuenta();

        //3
        cuentaBuilder.addCliente(new Cliente("12345679S", "Roberto", "Bruselas"));
        cuentaBuilder.setNumero("55345H");
        cuentaBuilder.setSucursal("noruega");
        cuentaBuilder.setIntereses(5.4F);
        cuentaBuilder.setDepositoPlazo(20000L);
        try {
            cuentaBuilder.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy").parse("21/12/2021"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        cuentaBuilder.setTipoCuenta(CuentaBuilder.CUENTA_PLAZO);
        guardarCuenta();

        ((CuentaCorriente) cuentas.get(2)).ingresar(100);
        ((CuentaCorriente) cuentas.get(2)).retirar(25);
        ((CuentaCorriente) cuentas.get(2)).ingresar(150);
        ((CuentaCorriente) cuentas.get(2)).retirar(130);
        ((CuentaCorriente) cuentas.get(2)).ingresar(400);

        int cuentasCargadas = cuentas.size();
        JOptionPane.showMessageDialog(null, "Cuentas eliminadas: " + cuentasEliminadas + "\nCuentas Demo cargadas: " + cuentasCargadas);

    }

    public void listarCuentas() {
        StringBuilder resutado = new StringBuilder();
        for (Cuenta cuenta : cuentas) {
            resutado.append(cuenta.toString() + "\n\n");
        }

        if (resutado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cuentas disponibles");
        } else {
            JOptionPane.showMessageDialog(null, resutado.toString());
        }

    }

    public void listarDatosCuenta() {
        String numeroCuenta = EntradasGui.pedirString("Indíca un número de cuenta a consultar.");
        int index = buscarCuenta(numeroCuenta);
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "El numero de cuenta No Existe.");
        } else {
            JOptionPane.showMessageDialog(null, cuentas.get(index).toString());
        }
    }

    public void listarCuentasCliente() {
        StringBuilder resultado = new StringBuilder("Cuentas del cliente: \n");
        String dniCliente = EntradasGui.pedirString("Indíca el dni del cliente");
        dniCliente = Utils.validarYFormatearDni(dniCliente);
        if (dniCliente == null) {
            JOptionPane.showMessageDialog(null, "Formato de DNI NO Válido");
        } else {
            Cliente cliente = buscarClientePorDni(dniCliente);
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "El cliente No Existe.");
            } else {
                for (Cuenta cuenta : cuentas) {
                    if (cuenta.getClientes().contains(cliente)) {
                        resultado.append(" - " + cuenta.getNumero() + "\n");
                        //listaCuentas.add(cuenta);
                    }
                }
                JOptionPane.showMessageDialog(null, resultado.toString());
            }
        }
    }

    public void listarMovimientosCuentaEntreFechas() {
        String numeroCuenta = EntradasGui.pedirString("Indíca un número de cuenta para consultar sus movmientos.");
        ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
        StringBuilder listaMovimientos = new StringBuilder();

        int index = buscarCuenta(numeroCuenta);
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "El numero de cuenta No Existe.");
        } else {
            if (cuentas.get(index) instanceof CuentaCorriente) {
                movimientos = ((CuentaCorriente) cuentas.get(index)).getMovimientos();

                if (movimientos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La Cuenta no tiene movmientos.");
                } else {
                    Date fechaInicio = EntradasGui.pedirFecha("Fecha de inicio");
                    Date fechaFin = EntradasGui.pedirFecha("Fecha de fin");
                    Date fo; //TODO . tiempo poner a 00:00:00
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                    for (Movimiento movimiento : movimientos) {
                        try {
                            fo = movimiento.getFechaOperacion();
                            fo = formato.parse(formato.format(fo));

                            if ((fo.before(fechaFin) || fo.equals(fechaFin))
                                    && (fo.after(fechaInicio) || fo.equals(fechaInicio))) {
                                listaMovimientos.append(movimiento.toString() + "\n");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                    if (listaMovimientos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay movimientos en las fechas indicadas");
                    } else {
                        JOptionPane.showMessageDialog(null, "Movimientos: \n" + listaMovimientos.toString());
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "La Cuenta debe ser Corriente para disponer de movimientos .");
            }
        }
    }
}
