/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.csdam.pr.libreriaar;

/**
 * Metodos con textos que se utilizan en la libreria.
 * @author Ar
 */
public class Textos {
    /* Menu.java*/    
    static final String INDICAR_OPCION = "\n<< Indica el nº de la opción >>";    
    static final String OPCION_SALIR = "\n0.- Salir";    
    static final String EXC_SIN_OPCIONES = " El menú no contiene opciones y requiere respuesta";    
    static final String NO_SELECCIONADO = "No se ha realizado ninguna selección";    
    static final String FORMATO_FECHA = "\n fecha en formato dia/mes/año";    
    
    static String cabeceraMenu(String tituloMenu){
        return "\n[Menú " + tituloMenu + "]";
    }
    static String finMenu(String tituloMenu){
        return "\n**** Fin del menú " + tituloMenu + " ****";
    }
    static String opcionSeleccionada(String seleccion){
        return "\nSeleccionado -> " + seleccion;
    }
     
    // pendiente: 
        /* Salidas.java*/
}
