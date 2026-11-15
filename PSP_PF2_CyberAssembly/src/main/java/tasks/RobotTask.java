/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import java.util.concurrent.Semaphore;
import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;
import model.TaulerRegistre;

/**
 *
 * @author Alumne
 */
public class RobotTask implements Runnable{
    
    private final int id;
    private final CintaTransportadora cinta; 
    private final SupervisorCentral supervisor;
    private final CentralSoldadura central; 
    private final TaulerRegistre tauler; 
    
    public RobotTask(int id, CintaTransportadora cinta, SupervisorCentral supervisor, CentralSoldadura central, TaulerRegistre tauler) {
        this.id = id;
        this.cinta = cinta; 
        this.supervisor = supervisor; 
        this.central = central; 
        this.tauler = tauler; 
    }
    
    @Override
    public void run(){
        try { 
            while (!Thread.currentThread().isInterrupted()) {
                supervisor.esperarAlerta();
                int rBateria = cinta.retirar(); 
                
                int estacio = central.entrarEstacion(); 
                try{
                    Thread.sleep(300);
                    tauler.firmarCertificado(id); 
                } finally {
                    central.salirEstacion(estacio);
                }
            }
        } catch (InterruptedException  e) {
            Thread.currentThread().interrupt();
        }
    }
}
