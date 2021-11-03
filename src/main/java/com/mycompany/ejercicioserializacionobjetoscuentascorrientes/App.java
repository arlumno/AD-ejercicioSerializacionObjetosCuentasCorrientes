/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicioserializacionobjetoscuentascorrientes;

import ar.csdam.pr.libreriaar.Menu;
import com.mycompany.ejercicioserializacionobjetoscuentascorrientes.GestorCuentas;
import java.util.Scanner;

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
        menu.addOpcion("Crear cuenta");
        menu.addOpcion("Añadir Titular");
        menu.addOpcion("Alta Movimientos");
        menu.addOpcion("Baja Cuenta Corriente");
        menu.addOpcion("Cargar fichero Cuentas");
        menu.addOpcion("Guardar fichero Cuentas");
        

        return menu;
    }

    private static boolean menuAcciones(Menu menu, GestorCuentas gestor) throws Exception {
        boolean continuar = true;
        menu.mostrarGUI();
        switch (menu.getSeleccion()) {
            case 0:
                //salir
                continuar = false;
                System.out.println("Bye Bye!");
                break;
            case 1:
                System.out.println("Crear Cuenta");
                gestor.crearCuenta();
                
                break;
            case 2:
                System.out.println("Ejercicio 02");
             //   gestor.ejercicio02();
                
                break;
            case 3:
                System.out.println("Ejercicio 03");
             //   gestor.ejercicio03();
                break;
           
            case 4:
                System.out.println("Ejercicio 04");
             //   gestor.ejercicio04();                
                break;
           
            case 5:
                System.out.println("Ejercicio 05");
            //    gestor.ejercicio05();
                
                break;
           
            default:
                System.out.println("**Opcion incorrecta**");
                break;
        }
        return continuar;
    }
}
