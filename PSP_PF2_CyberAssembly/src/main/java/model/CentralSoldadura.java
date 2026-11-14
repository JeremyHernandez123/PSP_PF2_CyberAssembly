/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Alumne
 */
public class CentralSoldadura {
    private final Semaphore semaforo; 
    private final boolean[] ocupadas; 

    public CentralSoldadura(int numEstacions) {
        this.semaforo = new Semaphore(numEstacions); 
        this.ocupadas = new boolean[numEstacions]; 
    }
    
    
    public int entrarEstacion() throws InterruptedException{
        semaforo.acquire(); 
        
        synchronized (ocupadas) {
            for (int i = 0; i < ocupadas.length; i++){
                if(!ocupadas[i]) {
                    ocupadas[i] = true; 
                    return i; 
                }
            }
        }  
        throw new IllegalStateException("Cap estació lliure");
    }
    
    public void salirEstacion(int estacio){
        synchronized (ocupadas){
            ocupadas[estacio] = false; 
            
        }
        semaforo.release(); 
    }
}
