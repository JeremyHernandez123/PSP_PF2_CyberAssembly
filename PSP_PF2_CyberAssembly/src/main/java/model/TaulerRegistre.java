/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.concurrent.Semaphore;
import view.ConsoleView;

/**
 *
 * @author jerem
 */
public class TaulerRegistre {
    private final Semaphore  mutex = new Semaphore(1); 
    private final ConsoleView vista = new ConsoleView(); 

    
    public void firmarCertificado(int idRobot) throws InterruptedException{
        mutex.acquire(); 
        try {
            vista.mostrar(" ");
            vista.mostrar("Robot " + idRobot + " firma el certificado");
            vista.mostrar(" ");
            Thread.sleep(200); 
        } finally {
            mutex.release(); 
        }
        
    }   
}
