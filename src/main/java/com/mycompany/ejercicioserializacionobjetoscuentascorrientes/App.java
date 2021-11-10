/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import ar.csdam.pr.libreriaar.Menu;
import com.mycompany.ejercicioserializacionobjetoscuentascorrientes.GestorCuentas;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author a20armandocb
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean continuar = true;
        Scanner lector = new Scanner(System.in);
        GestorCuentas gestor = new GestorCuentas();
        Menu menu = construirMenuPrincipal(lector);
        do {
            try {
                continuar = menuAcciones(menu, gestor);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } while (continuar);

        lector.close();
    }

    private static Menu construirMenuPrincipal(Scanner lector) {
        Menu menu = new Menu(lector);

        menu.setTituloMenu("Menú para el Gestor de Cuentas Bancarias");
        
        menu.addOpcion("Crear cuenta");//1
        menu.addOpcion("Añadir Titular");//2
        menu.addOpcion("Alta Movimientos");//3
        menu.addOpcion("Eliminar Cuenta Corriente");//4
        menu.addOpcion("Modificar Cliente");//5
        menu.addOpcion("Demo - Guardar cuentas de ejemplo");//6
        menu.addOpcion("Listar Todas las Cuentas");        //7
        menu.addOpcion("Listar Datos de Cuentas");//8
        menu.addOpcion("Listar Cuentas de Clientes");//9
        menu.addOpcion("Buscar Movimientos Cuenta");//9
        
        menu.setTexoSalir("Guardar y Salir");

        return menu;
    }

    private static boolean menuAcciones(Menu menu, GestorCuentas gestor) throws Exception {
        boolean continuar = true;
        menu.mostrarGUI();       
        switch (menu.getSeleccion()) {
            case 0:
                //salir
                continuar = false;
                gestor.guardarFicheroCuentas();
                System.out.println("Bye Bye!");
                break;
            case 1:
                gestor.crearCuenta();
                break;
            case 2:
                //añadir titular             
                gestor.addClienteCuenta();
                break;
            case 3:
                //alta movimientos       
                gestor.altaMovimiento();
                break;
           
            case 4:
                //Baja Cuenta Corriente
                gestor.cuentaEliminar();
                break;
           
            case 5:
                //Modificar Cliente
                gestor.modificarCliente();
                break;
            case 6:
                //Demo - Cargar cuentas de ejemplo
                gestor.demo();
                break;
            case 7:
                //Listar Cuentas              
                gestor.listarCuentas();
                break;
            case 8:
                //Listar Datos de una Cuenta
                 gestor.listarDatosCuenta();
                
                break;
            case 9:
                //Listar Cuentas de un Cliente.
                 gestor.listarCuentasCliente();
                break;
            case 10:
                //Listar Movimientos cuenta
                 gestor.listarMovimientosCuentaEntreFechas();
                break;
           //eliminar usuario de cuenta.
            default:
                System.out.println("**Opcion incorrecta**");
                break;
        }
        return continuar;
    }
}
