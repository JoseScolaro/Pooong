package Pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Window extends JFrame{
    GamePanel window1;
    Window(){
        window1 = new GamePanel();
        this.add(window1);
        this.setTitle("POOONGGG");
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}





