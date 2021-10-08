/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Debapriya
 */
public class Body {
    
    private int Xpos,Ypos,width,height;
    
    public Body(int Xpos,int Ypos,int tilesize){
        this.Xpos = Xpos;
        this.Ypos = Ypos;
        width = tilesize;
        height = tilesize;
    }
    
    /*public void tick(){
        
    }*/
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(Xpos*width, Ypos*height, width, height);
    }
    public int getXpos(){
        return Xpos;
    }
    public void setXpos(int Xpos){
        this.Xpos= Xpos;
    }
    public int getYpos(){
        return Ypos;
    }
    public void setYpos(int Ypos){
        this.Ypos= Ypos;
    }

   
}
