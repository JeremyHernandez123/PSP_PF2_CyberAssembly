/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import model.SupervisorCentral;

/**
 *
 * @author jerem
 */
public class SupervisorTask implements Runnable{
    private final SupervisorCentral supervisor; 
    
    public SupervisorTask(SupervisorCentral supervisor){
        this.supervisor = supervisor; 
    }
    
    @Override 
    public void run(){
        try {
            while(!Thread.currentThread().isInterrupted()) {
                Thread.sleep(2500); 
                supervisor.activarAlerta(); 
                try {
                    Thread.sleep(1500); 
                } finally {
                    supervisor.resolverAlerta(); 
                }
            }
        } catch(InterruptedException e)  {
            Thread.currentThread().interrupt();
        }
    }
}
