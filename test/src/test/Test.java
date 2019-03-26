/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Owner
 */
import java.util.Timer; 
import java.util.TimerTask; 


public class Test extends TimerTask
{ 
    static Timer timer;
    
    public static int i = 0; 
	public void run() 
	{ 
            System.out.println("Timer ran " + ++i); 
		timer.cancel();
                timer.purge();
      
	} 
	public static void main(String[] args) 
	{ 
		
		timer = new Timer(); 
		TimerTask task = new Test(); 
		
		timer.schedule(task, 30000); 
                System.out.println("hi");
                
		
	} 
} 
