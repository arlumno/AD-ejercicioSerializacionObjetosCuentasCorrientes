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
        menu.addOpcion("Demo - Cargar cuentas de ejemplo");
        

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
                gestor.crearCuenta();
                break;
            case 2:
             //   gestor.ejercicio02();
                break;
            case 3:
             //   gestor.ejercicio03();
                break;
           
            case 4:
             //   gestor.ejercicio04();                
                break;
           
            case 5:
            //    gestor.ejercicio05();
                break;
            case 6:
            //    gestor.ejercicio05();
                break;
            case 7:
                gestor.demo();
                break;
           
            default:
                System.out.println("**Opcion incorrecta**");
                break;
        }
        return continuar;
    }
}
