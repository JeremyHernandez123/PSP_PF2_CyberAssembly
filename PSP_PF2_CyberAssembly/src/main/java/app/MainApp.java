/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;
import model.TaulerRegistre;
import tasks.AGVTask;
import tasks.RobotTask;
import tasks.SensorAvariesTask;
import tasks.SupervisorTask;
import tasks.TecnicManteniment;
import view.ConsoleView;
import view.DashboardTask;

/**
 *
 * @author Alumne
 */
public class MainApp {
    public static void main (String[] args) throws InterruptedException{
        ConsoleView vista = new ConsoleView(); 
        CintaTransportadora c1 = new CintaTransportadora(3);
        CentralSoldadura central = new CentralSoldadura(3);
        SupervisorCentral supervisor = new SupervisorCentral();  
        List<RobotTask> llistaRobots = new ArrayList<>();     
        TaulerRegistre tauler= new TaulerRegistre(vista);

        
        RobotTask rbt1 = new RobotTask(1, c1, central, tauler, supervisor); 
        RobotTask rbt2 = new RobotTask(2, c1, central, tauler, supervisor); 
        RobotTask rbt3 = new RobotTask(3, c1, central, tauler, supervisor); 
        RobotTask rbt4 = new RobotTask(4, c1, central, tauler, supervisor); 
        RobotTask rbt5 = new RobotTask(5, c1, central, tauler, supervisor); 
       
        llistaRobots.add(rbt1); 
        llistaRobots.add(rbt2); 
        llistaRobots.add(rbt3); 
        llistaRobots.add(rbt4); 
        llistaRobots.add(rbt5); 
        
        Thread avg1 = new Thread(new AGVTask(1, c1, vista));
        Thread avg2 = new Thread(new AGVTask(2, c1, vista));
        Thread sup1 = new Thread(new SupervisorTask(supervisor));
        Thread dash = new Thread(new DashboardTask(c1, central, supervisor, (ArrayList<RobotTask>) llistaRobots, vista));
        Thread sensor = new Thread(new SensorAvariesTask(central, vista)); 
        sensor.setDaemon(true);
        Thread tecnic = new Thread(new TecnicManteniment(central, vista)); 
       
        Thread frt1 = new Thread(rbt1);
        Thread frt2 = new Thread(rbt2);
        Thread frt3 = new Thread(rbt3);
        Thread frt4 = new Thread(rbt4);
        Thread frt5 = new Thread(rbt5);
        
        avg1.start();
        avg2.start();
        sup1.start(); 
        dash.start();
        sensor.start(); 
        tecnic.start(); 
        
        frt1.start();
        frt2.start();
        frt3.start();
        frt4.start();
        frt5.start();
        
        Thread.sleep(10000);
        
        avg1.interrupt();
        avg2.interrupt();
        sup1.interrupt();   
        dash.interrupt();
        tecnic.interrupt();
        
        frt1.interrupt();
        frt2.interrupt();
        frt3.interrupt();
        frt4.interrupt();
        frt5.interrupt();
        
        avg1.join();
        avg2.join();
        sup1.join();
        dash.join();
        tecnic.join();
        
        frt1.join();
        frt2.join();
        frt3.join();
        frt4.join();
        frt5.join();
        
       vista.mostrar("Simulació finalitzada");
    }
    
}
