/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.concurrent.Semaphore;
import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;
import model.TaulerRegistre;
import tasks.AGVTask;
import tasks.RobotTask;

/**
 *
 * @author Alumne
 */
public class MainApp {
    public static void main (String[] args) throws InterruptedException{
        CintaTransportadora c1 = new CintaTransportadora(3);
        CentralSoldadura central = new CentralSoldadura(3);
        TaulerRegistre tauler= new TaulerRegistre();
        SupervisorCentral supervisor = new SupervisorCentral();  
        
        Thread avg1 = new Thread(new AGVTask(1, c1));
        
        Thread rt1 = new Thread(new RobotTask(1, c1, central, tauler, supervisor));
        Thread rt2 = new Thread(new RobotTask(2, c1, central, tauler, supervisor));
        Thread rt3 = new Thread(new RobotTask(3, c1, central, tauler, supervisor));
        Thread rt4 = new Thread(new RobotTask(4, c1, central, tauler, supervisor));
        Thread rt5 = new Thread(new RobotTask(5, c1, central, tauler, supervisor));
        
        avg1.start();
        rt1.start();
        rt2.start();
        rt3.start();
        rt4.start();
        rt5.start();
        
        Thread.sleep(5000);
        
        avg1.interrupt();
        rt1.interrupt();
        rt2.interrupt();
        rt3.interrupt();
        rt4.interrupt();
        rt5.interrupt();
        
        avg1.join();
        
        rt1.join();
        rt2.join();
        rt3.join();
        rt4.join();
        rt5.join();
    }
    
}
