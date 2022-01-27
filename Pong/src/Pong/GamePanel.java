package Pong;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int COSITOS_WIDTH = 20;
    static final int COSITOS_HEIGTH = 85;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Cositos cositos1;
    Cositos cositos2;
    Ball ball;
    Score score;

    GamePanel() {
        newCositos();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER ), BALL_DIAMETER, BALL_DIAMETER);
    }
    public void newCositos() {
        cositos1 = new Cositos(0, (GAME_HEIGHT / 2) - (COSITOS_HEIGTH / 2), COSITOS_WIDTH, COSITOS_HEIGTH, 1);
        cositos2 = new Cositos(GAME_WIDTH - COSITOS_WIDTH, (GAME_HEIGHT / 2) - (COSITOS_HEIGTH / 2), COSITOS_WIDTH, COSITOS_HEIGTH, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g) {
        cositos1.draw(g);
        cositos2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        cositos1.move();
        cositos2.move();
        ball.move();
    }

    public void checkCollition() {

        //bordes top ball

        if (ball.y < 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y > GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //cositos choque

        if (ball.intersects(cositos1)) {
                ball.xVelocity = Math.abs(ball.xVelocity);
                ball.xVelocity++;
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
            if (ball.intersects(cositos2)) {
                ball.xVelocity = Math.abs(ball.xVelocity);
                ball.xVelocity++;
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
                ball.setXDirection(-ball.xVelocity);
                ball.setYDirection(ball.yVelocity);
        }

        // bien - bordes cositos

        if (cositos1.y <= 0)
            cositos1.y = 0;
        if (cositos1.y >= (GAME_HEIGHT - COSITOS_HEIGTH))
            cositos1.y = GAME_HEIGHT - COSITOS_HEIGTH;
        if (cositos2.y <= 0)
            cositos2.y = 0;
        if (cositos2.y >= (GAME_HEIGHT - COSITOS_HEIGTH))
            cositos2.y = GAME_HEIGHT - COSITOS_HEIGTH;

        //point new
        if (ball.x <=0) {
            score.player2= score.player2+1;
            newCositos();
            newBall();
            System.out.println(score.player2);
        }
        if(ball.x >=GAME_WIDTH-BALL_DIAMETER) {
            score.player1 = score.player1+1;
            newCositos();
            newBall();
            System.out.println(score.player1);
        }

    }


    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta = 0;
        while (true){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta>=1){
                move();
                checkCollition();
                repaint();
                delta --;
            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            cositos1.keyPresed(e);
            cositos2.keyPresed(e);
        }
        public void keyReleased(KeyEvent e){
            cositos1.keyReleased(e);
            cositos2.keyReleased(e);
        }
    }
}
