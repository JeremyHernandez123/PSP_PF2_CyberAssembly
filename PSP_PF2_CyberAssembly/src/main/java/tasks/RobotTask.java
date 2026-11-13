/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import model.CintaTransportadora;

/**
 *
 * @author Alumne
 */
public class RobotTask implements Runnable{
    
    private final int id;
    private final CintaTransportadora cintaTransportadora;  
    
    public RobotTask(int id, CintaTransportadora cintaTransportadora) {
        this.id = id;
        this.cintaTransportadora = cintaTransportadora; 
    }
    
    @Override
    public void run(){
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(500);
                int rBateria = cintaTransportadora.retirar(); 
                IO.println("Robot " + id + " retira batería " + rBateria);
            }
           
        } catch (InterruptedException  e) {
            Thread.currentThread().interrupt();
        }
    }
}
