/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Debapriya
 */
public class Gamepanel extends JPanel implements Runnable, KeyListener{
    
    public static final int WIDTH = 640, HEIGHT = 800;
    public static int score=0;
    
    private Thread thread;
    
    private boolean running;
    private boolean right=true, left=false, up=false, down=false;
    
    private Body b;
    private ArrayList<Body> snake;
    
    private Food food;
    private ArrayList<Food> foods;
    
    private Random r;
    
    private int xpos=10, ypos=10, size=5; 
    private int ticks=0;
        
    public Gamepanel(){
        setFocusable(true);
        
        
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        addKeyListener(this);
        
        snake = new ArrayList<Body>();
        foods = new ArrayList<Food>();
        
        r = new Random();
        
        start();
        
    }
    
    public void start(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Gamepanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void tick(){
        if(snake.size()==0)
        {
            b = new Body(xpos,ypos,10);
            snake.add(b);
        }
        ticks++;
        if(ticks>500000)
        {
            if(right) xpos++;
            if(left) xpos--;
            if(up) ypos--;
            if(down) ypos++;
            
            ticks = 0;
            
            b = new Body(xpos, ypos, 10);
            snake.add(b);
            if(snake.size()>size){
                snake.remove(0);
                
            }
        }
        if(foods.size() == 0){
            int xpos =r.nextInt(WIDTH/10);
            int ypos =r.nextInt(HEIGHT/10);
            
            food = new Food(xpos,ypos,10);
            foods.add(food);
        }
        for(int i = 0; i < foods.size();i++)
        {
            if(xpos == foods.get(i).getXpos() && ypos == foods.get(i).getYpos())
            {
                size++;
                score+=10;
                foods.remove(i);
                i++;
                
            }
        }
        for(int i=0; i<snake.size();i++)
        {
            if(xpos==snake.get(i).getXpos() && ypos == snake.get(i).getYpos())
            {
                if(i != snake.size()-1)
                {
                    System.out.println("Game Over !!!");
                    stop();
                }
            }
        }
        /*if(xpos<0 || xpos>WIDTH/10 || ypos<0 || ypos>HEIGHT/10)
        {
            System.out.println("Game Over !!!");
            stop();
            
        }*/
        
       if(xpos>WIDTH/10)
           xpos=0;
       if(xpos<0)
           xpos=WIDTH/10;
       if(ypos<0)
           ypos=HEIGHT/10;
       if(ypos>HEIGHT/10)
           ypos=0;
       
    }
    public void paint(Graphics g){
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
                
        for(int i=0; i< WIDTH/10;i++)
        {
            g.drawLine(i*10, 0, i*10, HEIGHT);
        }
        for(int i=0; i< HEIGHT/10;i++)
        {
            g.drawLine(0, i*10, HEIGHT, i*10);            
        }
        for(int i= 0; i< snake.size();i++)
        {
            snake.get(i).draw(g);
        }
        for (int i= 0; i< foods.size();i++)
        {
            foods.get(i).draw(g);
        }
    }

    @Override
    public void run() {
        
        while(running)
        {
            tick();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left)
        {
            right = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_D && !left)
        {
            right = true;
            up = false;
            down = false;
        }
         if(key == KeyEvent.VK_LEFT && !right)
        {
            left = true;
            up = false;
            down = false;
        }
         if(key == KeyEvent.VK_A && !right)
        {
            left = true;
            up = false;
            down = false;
        }
          if(key == KeyEvent.VK_UP && !down)
        {
            up = true;
            right = false;
            left = false;
        }
          if(key == KeyEvent.VK_W && !down)
        {
            up = true;
            right = false;
            left = false;
        }
           if(key == KeyEvent.VK_DOWN && !up)
        {
            down = true;
            right = false;
            left = false;
        }
           if(key == KeyEvent.VK_S && !up)
        {
            down = true;
            right = false;
            left = false;
        }
           if(key == KeyEvent.VK_Q)
           {
               stop();
           }
    }

    @Override
    public void keyReleased(KeyEvent e) {
  //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
