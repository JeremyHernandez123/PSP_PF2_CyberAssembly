/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import java.util.Random;
import model.CentralSoldadura;
import view.ConsoleView;

/**
 *
 * @author Alumne
 */
public class SensorAvariesTask implements Runnable{
    
    private final CentralSoldadura central; 
    private final ConsoleView vista; 
    private final Random random = new Random(); 

    public SensorAvariesTask(CentralSoldadura central, ConsoleView vista) {
        this.central = central;
        this.vista = vista;
    }
    
    @Override
    public void run(){
        
    }
}
