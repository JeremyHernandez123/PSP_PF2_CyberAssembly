/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.concurrent.Semaphore;

/**
 *
 * @author jerem
 */
public class TaulerRegistre {
    private final Semaphore  mutex = new Semaphore(1); 
    
    
    public void firmarCertificado(int idRobot) throws InterruptedException{
        mutex.acquire(); 
        try {
            IO.println("Robot " + idRobot + "firma el certificado");
            Thread.sleep(200); 
        } finally {
            mutex.release(); 
        }
        
    }   
}
