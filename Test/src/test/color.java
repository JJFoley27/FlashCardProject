/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.awt.Color;
import java.awt.Font;
/**
 *
 * @author Owner
 */
public class color {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Color[] colors = {Color.WHITE, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED};
        System.out.println(colors[0].getRed());
        System.out.println(colors[0].getGreen());
        System.out.println(colors[0].getBlue());
        System.out.println(colors[0]);
//        Color s = new Color(int, int, int);
    }
    
}
