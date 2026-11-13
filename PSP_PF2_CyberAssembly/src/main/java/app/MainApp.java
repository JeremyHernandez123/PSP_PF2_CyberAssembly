/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import model.CintaTransportadora;
import tasks.AGVTask;
import tasks.RobotTask;

/**
 *
 * @author Alumne
 */
public class MainApp {
    public static void main () throws InterruptedException{
        CintaTransportadora c1 = new CintaTransportadora(3); 
        
        Thread avg1 = new Thread(new AGVTask(1, c1));
        
        Thread rt1 = new Thread(new RobotTask(1, c1));
        Thread rt2 = new Thread(new RobotTask(2, c1));
        
        
        avg1.start();
        rt1.start();
        rt2.start();
        
        Thread.sleep(500);
        
        avg1.interrupt();
        rt1.interrupt();
        rt2.interrupt();
        
        avg1.join();
        rt1.join();
        rt2.join();
    }
    
}
