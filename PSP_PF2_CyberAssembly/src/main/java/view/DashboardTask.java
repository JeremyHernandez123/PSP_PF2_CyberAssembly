/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;

/**
 *
 * @author Alumne
 */
public class DashboardTask implements Runnable {
    private final CintaTransportadora cinta; 
    private final CentralSoldadura central; 
    private final SupervisorCentral supervisor; 
    
    public DashboardTask(CintaTransportadora cinta, CentralSoldadura central, SupervisorCentral supervisor){
        this.cinta = cinta; 
        this.central = central; 
        this.supervisor = supervisor; 
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
                
                IO.println("Estaciones ocupadas: " + central.getOcupadas() + ", n: " + central.getNumEstacion() + "\n Cinta: " + cinta.getOcupacion() + ", capacitat: " + cinta.getCapacitat() + "\n Alerta: " +  alerta);
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
