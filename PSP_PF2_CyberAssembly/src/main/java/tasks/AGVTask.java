/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import java.time.Duration;
import model.CintaTransportadora;

/**
 *
 * @author Alumne
 */
public class AGVTask implements Runnable{
    private CintaTransportadora cintaTransportadora;  
    private int id; 
    
    public AGVTask(int id, CintaTransportadora cintaTransportadora){
        this.id = id; 
        this.cintaTransportadora = cintaTransportadora; 
    }
    
    @Override
    public void run(){
        int contador = 0; 
        
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(500);
                cintaTransportadora.depositar(contador);
                contador++; 
                System.out.println("AGV " + id + " deposita batería " + contador);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
