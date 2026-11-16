/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import java.util.concurrent.Semaphore;
import model.CentralSoldadura;
import model.CintaTransportadora;
import model.EstatRobot;
import model.SupervisorCentral;
import model.TaulerRegistre;

/**
 *
 * @author Alumne
 */
public class RobotTask implements Runnable{
    
    private final int id;
    private final CintaTransportadora cinta; 
    private final CentralSoldadura central;
    private final TaulerRegistre tauler;
    private final SupervisorCentral supervisor; 
    private EstatRobot estatRobot = EstatRobot.ESPERANT_ALERTA; 
    
    public RobotTask(int id, CintaTransportadora cinta, CentralSoldadura central, TaulerRegistre tauler, SupervisorCentral supervisor) {
        this.id = id;
        this.cinta = cinta; 
        this.central = central;
        this.tauler = tauler;
        this.supervisor = supervisor; 
    }
    
    public EstatRobot getEstat() {
        return estatRobot;
    }
    @Override
    public void run(){
        try { 
            while (!Thread.currentThread().isInterrupted()) {
                
                estatRobot = EstatRobot.ESPERANT_ALERTA; 
                supervisor.esperarAlerta();
                
                estatRobot = EstatRobot.ESPERANT_CINTA; 
                int rBateria = cinta.retirar(); 
                
                estatRobot = EstatRobot.ESPERANT_ESTACIO; 
                int estacio = central.entrarEstacion(); 
                try{
                    estatRobot = EstatRobot.SOLDANT; 
                    Thread.sleep(300);
                    
                    estatRobot = EstatRobot.SIGNANT; 
                    tauler.firmarCertificado(id); 
                } finally {
                    central.salirEstacion(estacio);
                }
            }
        } catch (InterruptedException  e) {
            Thread.currentThread().interrupt();
        } finally {
            estatRobot = EstatRobot.ATURAT; 
        }
        
    }
}
