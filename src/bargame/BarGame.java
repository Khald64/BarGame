/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bargame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Khouiled
 */
public class BarGame {

    public static JFrame w=new JFrame();
    public static JPanel f;
    public static boolean lose=false;
     public static boolean side=false;
     public static int score=0;
    public static LinkedList<Bar> bars=new LinkedList<>();
    public static Random r=new Random();
    public static int py=0;
    public static Bar ran(){
       return new Bar(-r.nextInt(500), r.nextInt(6), new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
   }
    
    public static void main(String[] args) {
        w.setBounds(10, 10, 500, 500);
        w.setDefaultCloseOperation(3);
        w.setUndecorated(true);
        for(int i=0;i<30;i++){
          bars.add(ran());
        }
    f=new JPanel(){
        @Override
        public void paint(Graphics g2) {
            Graphics2D g=(Graphics2D) g2.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            bars.stream().map((b) -> {
                g.setColor(b.c);
                return b;
            }).forEachOrdered((b) -> {
                
                g.fillRect(b.x, 100+b.y*50, 40, 10);
            });
            g.setFont(new Font("Tahoma", Font.BOLD, 22));
            
            g.drawString("Score : "+score, 10,25);
            if(lose){
             g.setColor(new Color(0, 0, 0, 150));
             g.fillRect(0, 0, 500, 500);
             g.setColor(Color.red);
             g.setFont(new Font("Tahoma", Font.BOLD, 40));
             g.drawString("LOST", 210,230);
             g.setFont(new Font("Tahoma", Font.PLAIN, 18));
             g.drawString("Score : "+score, 210,250);
             g.setColor(new Color(255, 0, 0, 150));
            g.fillRect(0, 400, 500, 100);
            }else{
            g.fillRect(450, py, 15, 30);
            }
            
        }
      
    };
    w.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(lose){
                    if(e.getY()>400){
                        score=0;
                        lose=false;
                        py=e.getY();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    w.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(!lose){
           py=e.getY();
           if(e.getY()<100){
                  if(side){
               score++;
                  side=false;
                  }
           }
           if(e.getY()>400){
               if(!side){
               score++;
                   side=true;
               
               }
           }
                }
            }
        });
    w.add(f);
    w.setVisible(true);
   Thread th=new Thread(() -> {
       for(;;){
           step();
           w.repaint();
           try {
               Thread.sleep(16);
           } catch (InterruptedException ex) {
               Logger.getLogger(BarGame.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    });
   th.start();
    }
    public static void step(){
        bars.forEach((b) -> {
          if(new Rectangle(b.x,100+ b.y*50, 40, 10).intersects(new Rectangle(450,py, 15, 30))){
              lose=true;
              
          }
            if(b.x>500){
              b.rand();
          }else{
            b.x+=5;
          }
          
          }); 
    }
    
}
