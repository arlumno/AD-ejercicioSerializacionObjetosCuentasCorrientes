/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.csdam.pr.libreriaar;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author a20armandocb
 */
public class GUI  extends JFrame{
      
    JButton boton;
    public GUI(){
        setLayout(null);
        
        boton = new JButton("Estoy en mi posición");
        boton.setBounds(30,5,160,20);
        add(boton);
        
        setVisible(true);

        Toolkit elToolkit = Toolkit.getDefaultToolkit();
        Dimension laPantalla = elToolkit.getScreenSize();        
        
        int h = 100;
        setBounds(5,laPantalla.height - h -40 ,laPantalla.width - 10,h);
        setTitle("probandoSwing02");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

