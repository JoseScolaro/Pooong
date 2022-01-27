package Pong;
import java.awt.*;
import java.awt.event.*;

public class Cositos extends Rectangle {
    int id;
    int yvelocity;
    int speed =10;
    Cositos(int x, int y, int COSITOS_WIDTH,int COSITOS_HEIGHT, int id){
        super(x,y,COSITOS_WIDTH,COSITOS_HEIGHT);
        this.id=id;
    }
    public void keyPresed(KeyEvent e){
        switch (id) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    setYDirecction(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    setYDirecction(speed);
                    move();
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    setYDirecction(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    setYDirecction(speed);
                    move();
                }
            }
        }
        }
    public void keyReleased(KeyEvent e){
        switch (id) {
            case 1 -> {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    setYDirecction(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    setYDirecction(0);
                    move();
                }
            }
            case 2 -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    setYDirecction(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    setYDirecction(0);
                    move();
                }
            }
        }
    }
    public void setYDirecction(int yDirecction){
        yvelocity = yDirecction;
    }
    public void move(){
        y = y + yvelocity;
    }
    public void draw(Graphics g){
    if (id==1)
        g.setColor(Color.red);
    else
        g.setColor(Color.blue);
    g.fillRect(x,y,width,height);
    }
}
