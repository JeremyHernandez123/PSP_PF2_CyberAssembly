/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

/**
 *
 * @author Alumne
 */
public class RobotTask implements Runnable{
    
    private final int id;
    
    public RobotTask(int id) {
        this.id = id;
    }
    
    
    @Override
    public void run(){
        RobotTask robot = new RobotTask(1); 
        Thread hilo = new Thread(robot); 
        hilo.start();
    }
}
