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
    private final Semaphore semafor; 
    private final boolean[] ocupadas; 

    public CentralSoldadura(int numEstacions) {
        this.semafor = new Semaphore(numEstacions); 
        this.ocupadas = new boolean[numEstacions]; 
    }
    
}
