/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Box;

/**
 *
 * @author Owner
 */
class Helper extends TimerTask 
{ 
    public static int i = 0; 
    @Override
    public void run() 
    { 
        System.out.println("Timer ran " + ++i); 

    } 
} 

public class TimerTest 
{ 
    Component newComp = Box.createRigidArea(new Dimension(0, 12));//this is padding for the buttons its not apart of the timer
    public static void main(String[] args) 
    { 
        Timer timer = new Timer(); 
        TimerTask task = new Helper(); 

        timer.schedule(task, 3000, 5000); 
        System.out.println("hi");

    } 
} 
