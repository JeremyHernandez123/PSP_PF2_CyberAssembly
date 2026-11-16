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
    private final ConsoleView vista;  

    
    public DashboardTask(CintaTransportadora cinta, CentralSoldadura central, SupervisorCentral supervisor, ArrayList<RobotTask> llistaRobots, ConsoleView vista){
        this.cinta = cinta; 
        this.central = central; 
        this.supervisor = supervisor;
        this.llistaRobots = llistaRobots; 
        this.vista = vista; 
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
                vista.mostrar("Estaciones ocupadas: " + central.getOcupades()
                            + ", averiadas: " + central.getAveriades()
                            + ", n: " + central.getNumEstacions()
                            + "\n Cinta: " + cinta.getOcupacion() + ", capacitat: " + cinta.getCapacitat()
                            + "\n Alerta: " + alerta
                            + "\n   Robots: " + robotsTxt);
                Thread.sleep(1000);                
            }   
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
