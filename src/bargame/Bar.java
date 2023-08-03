/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bargame;

import static bargame.BarGame.r;
import java.awt.Color;

/**
 *
 * @author Khouiled
 */
public class Bar {
    int x;
    int y;
    Color c;

    public Bar(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
    public void rand(){
        //r.nextInt(8)*50, r.nextInt(6), new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255))
        this.x =- r.nextInt(500);
        this.y = r.nextInt(6);
        this.c =  new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        
    }
}
