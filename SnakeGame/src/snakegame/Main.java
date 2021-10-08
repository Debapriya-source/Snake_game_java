/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Debapriya
 */
public class Main {
    public Main(){
        JFrame frame = new JFrame();
        Gamepanel gamepanel = new Gamepanel();
        
        frame.add(gamepanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Debapriya's Snake Game");
        
        
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
