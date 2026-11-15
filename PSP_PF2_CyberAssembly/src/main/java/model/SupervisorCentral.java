/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Alumne
 */
public class SupervisorCentral {
    private boolean activa = false; 
    
    public synchronized void activarAlerta(){
        activa = true; 
    }
    
    public synchronized void resolverAlerta(){
        activa = false; 
        notifyAll(); 
    }
    
    public synchronized void esperarAlerta() throws InterruptedException{
        while (activa){
            wait(); 
        }
    }
    
    public synchronized boolean isActiva() {
        return activa;
    }
    
}
