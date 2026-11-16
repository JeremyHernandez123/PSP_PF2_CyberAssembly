/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import model.CentralSoldadura;
import model.CintaTransportadora;
import model.EstatRobot;
import model.SupervisorCentral;
import tasks.RobotTask;

/**
 *
 * @author Alumne
 */
public class DashboardTask implements Runnable {
    private final CintaTransportadora cinta; 
    private final CentralSoldadura central; 
    private final SupervisorCentral supervisor; 
    private List<RobotTask> llistaRobots = new ArrayList<>(); 
    
    public DashboardTask(CintaTransportadora cinta, CentralSoldadura central, SupervisorCentral supervisor, ArrayList<RobotTask> llistaRobots){
        this.cinta = cinta; 
        this.central = central; 
        this.supervisor = supervisor;
        this.llistaRobots = llistaRobots; 
    }
    
    @Override
    public void run(){
        try {
            while(!Thread.currentThread().isInterrupted()) {
                String alerta; 
                if(supervisor.isActiva()){
                    alerta = "Si"; 
                }else {
                    alerta = "No"; 
                }   
                
                String robotsTxt = "";
                for (RobotTask r : llistaRobots) {
                    robotsTxt = robotsTxt + "R" + r.getId() + ":" + r.getEstat() + "  ";
                }
                IO.println("Estaciones ocupadas: " + central.getOcupadas() + ", n: " + central.getNumEstacion() + "\n Cinta: " + cinta.getOcupacion() + ", capacitat: " + cinta.getCapacitat() + "\n Alerta: " +  alerta);
                Thread.sleep(1000);
                IO.println(" ---------------------------------------- ");
                IO.println(" ");
                IO.println("   Robots: " + robotsTxt);
                IO.println(" ");
                IO.println(" ---------------------------------------- ");
                
            }   
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
