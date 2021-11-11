/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.csdam.pr.libreriaar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * Imprime un menún según los parametros proporcionados
 *
 * @author Ar
 */
public class Menu {


    private ArrayList<String> opcionesArray = new ArrayList<String>();
    private ArrayList<String> descripcionOpcionesArray = new ArrayList<String>();
    private Scanner lector;
    private StringBuilder menuString = new StringBuilder();
    private String tituloMenu = "";
    private boolean salir = true;
    private boolean descripcion = true;
    private byte nOpciones = 0;
    private byte seleccion;
    private int intentos = 0;
    private boolean pausarEjecucion = false;
    private String textoBotonSalir = "EXIT";

    /**
     * Menú de opciones. Limitado a 127 opciones. Se reserva el 0 para salir.
     *
     * @param lector objeto Scanner para seleccionar opciones
     */
    public Menu(Scanner lector) {
        this.lector = lector;
    }
    /**
     * @return the salir
     */
    public boolean isSalir() {
        return salir;
    }

    /**
     * @return the descripcion
     */
    public boolean isDescripcion() {
        return descripcion;
    }

    /**
     * @param salir Indica si hay la opción de salir del menuString.
     */
    public void setSalir(boolean salir) {
        this.salir = salir;
        borrarMenu();
    }
    /**
     * @param salir Indica si hay la opción de salir del menuString.
     */
    public void setTexoSalir(String salir) {
        this.textoBotonSalir = salir;
        borrarMenu();
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(boolean descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Cambia el titulo del menuString, por defecto no tiene.
     *
     * @param tituloMenu
     */
    public void setTituloMenu(String tituloMenu) {
        this.tituloMenu = tituloMenu;
    }

    /**
     * Añade una opción al menú,
     *
     * @param opcion Nombre o titulo de la opcion
     * @param descripcion descripción de la opcion
     */
    public void addOpcion(String opcion, String descripcion) {
        if (nOpciones == 127) {
            Salidas.errorLimite();
        } else {
            opcionesArray.add(opcion);
            descripcionOpcionesArray.add(descripcion);
            nOpciones = (byte) opcionesArray.size();
            borrarMenu();
        }
    }

    /**
     * Añade una opción al menú,
     *
     * @param opcion Nombre o titulo de la opcion
     */
    public void addOpcion(String opcion) {
        addOpcion(opcion, "");
    }

    /**
     * Carga un conjunto de opciones de tipo Arraylist String
     *
     * @param opciones Arraylist String de opciones
     */
    public void addOpciones(ArrayList<String> opciones) {
        for (int i = 0; i < opciones.size(); i++) {
            addOpcion(opciones.get(i));
        }
    }
    /**
     * Carga un conjunto de opciones de tipo String[]
     *
     * @param opciones Arraylist String de opciones
     */
    public void addOpciones(String[] opciones) {        
        addOpciones(new ArrayList<>(Arrays.asList(opciones)));
    }
   
    /**
     * Carga un conjunto de opciones / descripcion  de tipo String[][]
     * Donde el primer indice indica el conjunto de opciones, en el segundo indice [0] opcion y [1] descripción
     * @param opciones 
     */
    public void addOpciones(String[][] opciones) {
        for(int i = 0; i < opciones.length; i++) {
            addOpcion(opciones[i][0], opciones[i][1]);
        }
    }

    private void delOpcion(byte nOpcion) {
        opcionesArray.remove(nOpcion);
        descripcionOpcionesArray.remove(nOpcion);
        nOpciones = (byte) opcionesArray.size();
        borrarMenu();
    }

    /**
     * Genera el menuString en función de los opciones.
     */
    private void construirMenu() {
        //Construimos y guardamos el menú
        menuString.append(Textos.cabeceraMenu(tituloMenu));
        for (int i = 0; i < nOpciones; i++) {
            menuString.append("\n");
            menuString.append(i + 1); //la opcion es = al indice del array + 1
            menuString.append(".- ");
            menuString.append(opcionesArray.get(i));
            if (isDescripcion() && descripcionOpcionesArray.get(i) != "") {
                menuString.append("\n\t");
                menuString.append(descripcionOpcionesArray.get(i));
            }
        }
        if (isSalir()) {
            menuString.append(Textos.OPCION_SALIR);
        }
        menuString.append(Textos.INDICAR_OPCION);
    }

    private void borrarMenu() {
        menuString = new StringBuilder();
    }

    /**
     * Muestra el menú por consola y pide seleccionar una opción. El menú
     * empieza en 1 y usa 0 para salir. El Indice del array es (return - 1)
     *
     * @return opcion tipo byte, devuelve opción correspondiente a la lista
     * impresa, empezando en 1. Devuelve 0 para salir. El Indice del array es
     * (return - 1)
     */
    private boolean validarMenu() {
        boolean resultado = true;

        if (opcionesArray.isEmpty()) {
            Salidas.errorMenuVacio();
            resultado = false;
        } else {
            if (menuString.length() == 0) {
                construirMenu();
            }
        }
        return resultado;
    }

    /**
     * Imprime el menuString, pide que selecciones una opción y guarda la seleccion.     
     *
     * @throws Exception. Ocurre cuando se intenta imprimir un menú vació, con respuesta obligatoria (que no tiene opción de salir)
     */
    public void mostrarGUI() throws Exception {
        JFrame menuGUI = new JFrame();
        JButton opcion = new JButton();       
        int nBotones;
        if(salir){
            nBotones = nOpciones + 2;
        }else{
            nBotones = nOpciones ;            
        }
        int alturaBoton = 40;
        int anchoBoton = 400;
        int margenBoton = 15;
        
        Toolkit elToolkit = Toolkit.getDefaultToolkit();
    //    Dimension screen = elToolkit.getScreenSize();        

        menuGUI.setTitle(tituloMenu);       
        menuGUI.setLayout(null);
        menuGUI.setResizable(false);
        menuGUI.setSize(margenBoton + anchoBoton + margenBoton +15,20+ margenBoton + (nBotones * (alturaBoton + margenBoton)) + margenBoton);
        menuGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        menuGUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        int y = margenBoton;
        for (int i = 0; i < nOpciones; i++) {   
            final int j = i;
            opcion = new JButton(opcionesArray.get(i));
            opcion.setSize(anchoBoton, alturaBoton);
            opcion.setLocation(margenBoton, y);
            
            opcion.addActionListener( new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    seleccion = (byte) (j +1);
                    menuGUI.dispose();
                    pausarEjecucion = false;
                }
            });            
            menuGUI.add(opcion);
            y += alturaBoton + margenBoton;
        }   
        if(salir){
            opcion = new JButton(textoBotonSalir);
            opcion.setSize(anchoBoton, alturaBoton);
            opcion.setLocation(margenBoton, (y + alturaBoton));
            opcion.setBackground(Color.red);
            
            opcion.addActionListener( new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    seleccion = 0;
                    menuGUI.dispose();
                    pausarEjecucion = false;
                }
            });            
            menuGUI.add(opcion);
        }
        
        menuGUI.setVisible(true);
        intentos++;
        pausarEjecucion = true;
        while(pausarEjecucion){
             //pausamos la ejecución del programa hasta que se asigne una respuesta.
             Thread.sleep(500); //para reducir la carga del bucle se pase.         
        }
               
    }
    public void mostrar() throws Exception {
        //se imprime a partir del primer intento del menú.
        if (validarMenu()) {
            if (intentos > 0) {
                //pausa
                Salidas.repetirMenu();
                //pedimos entrada de texto para que se pause el programa, no es necesario asignar el resultado
                lector.nextLine();
            }
            //imprimimos el menú
            System.out.println(menuString);
            seleccion = Entradas.pedirByte(lector, (byte) 0, nOpciones);

            if (seleccion == 0) {
                System.out.println(Textos.finMenu(tituloMenu));
            } else {
                Utils.limpiarConsola(5);                
                System.out.println(Textos.opcionSeleccionada(opcionesArray.get(seleccion - 1)));
            }
            intentos++;
        } else {
            if (isSalir()) {
                seleccion = 0;
            } else {
                // esto ocurre cuando se intenta imprimir un menú vació, con respuesta obligatoria (que no tiene opción de salir)
                throw new Exception(Textos.EXC_SIN_OPCIONES);
            }
        }

    }

    /**
     * Para obtener la opcion seleccionada en el thisl. mostrar()
     * @return tipo byte, tras mostrar el menú. indice array = (selección - 1)
     * @throws Exception, Cuando no se ha seleccionado anteriormente.
     */
    public byte getSeleccion() throws Exception{
        if (intentos == 0){
            throw new Exception(Textos.NO_SELECCIONADO);
        }
        return seleccion;
    }

}
