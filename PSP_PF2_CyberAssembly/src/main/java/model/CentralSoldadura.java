/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Alumne
 */
public class CentralSoldadura {
    private final Semaphore semaforo; 
    private final EstatEstacio[] estacions; 

    public CentralSoldadura(int numEstacions) {
        this.semaforo = new Semaphore(numEstacions); 
        this.estacions = new EstatEstacio[numEstacions]; 
        Arrays.fill(estacions, EstatEstacio.LLIURE);

    }
    
    
    public int entrarEstacion() throws InterruptedException{
        semaforo.acquire(); 
        
        synchronized (estacions) {
            for (int i = 0; i < estacions.length; i++){
                if(estacions[i] == EstatEstacio.LLIURE) {
                    estacions[i] = EstatEstacio.OCUPADA; 
                    return i; 
                }
            }
        }  
        throw new IllegalStateException("Cap estació lliure");
    }
    
    public void salirEstacion(int estacio){
        synchronized (estacions){
            estacions[estacio] = EstatEstacio.LLIURE; 
            
        }
        semaforo.release(); 
    }
    
    public int averiarEstacioLliure() {
        if (!semaforo.tryAcquire()) {
            return -1;           // Devuelve -1 si no había ninguna estacion libre
        }
        synchronized (estacions) {
            for (int i = 0; i < estacions.length; i++) {
                if (estacions[i] == EstatEstacio.LLIURE) {
                    estacions[i] = EstatEstacio.AVERIADA;
                    return i;                                   }
            }
        }
        semaforo.release();                 
        return -1;  
    }
    
    public int repararEstacio() {
        int reparada = -1;
        synchronized (estacions) {
            for (int i = 0; i < estacions.length; i++) {
                if (estacions[i] == EstatEstacio.AVERIADA) {
                    estacions[i] = EstatEstacio.LLIURE;
                    reparada = i;
                    break;
                }
            }
        }
        if (reparada != -1) {
            semaforo.release();           
        }
        return reparada;
    }
    
    public int getOcupades() {
        return contar(EstatEstacio.OCUPADA);
    }

    public int getAveriades() {
        return contar(EstatEstacio.AVERIADA);
    }

    public int getNumEstacions() {
        return estacions.length;
    }

    private int contar(EstatEstacio estat) {
        synchronized (estacions) {
            int n = 0;
            for (EstatEstacio e : estacions) {
                if (e == estat) n++;
            }
            return n;
        }
    }
    
}
