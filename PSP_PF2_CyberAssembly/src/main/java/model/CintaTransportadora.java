/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Alumne
 */
public class CintaTransportadora {
    
    private ReentrantLock lock = new ReentrantLock(); 
    private final Condition cintaNoPlena = lock.newCondition(); 
    private final Condition  cintaNoBuida = lock.newCondition();
    private int capacitat; 
    Queue<Integer> cola = new LinkedList<>(); 
            
    public CintaTransportadora(int capacitat) {
        this.capacitat = capacitat; 
    }
       
    public void depositar(int bateria) throws InterruptedException{
        lock.lock();
        try {
            while (cola.size() == capacitat) {
                cintaNoPlena.await();
            }
            cola.add(bateria); 
            cintaNoBuida.signal();
        }finally {
            lock.unlock();
        }
    }
  
    public int retirar()throws InterruptedException{
        lock.lock();
        try {
            while (cola.isEmpty()) {
                cintaNoBuida.await();
            }
            int bateria = cola.poll(); 
            cintaNoPlena.signal();
            return bateria; 
        }finally {
            lock.unlock();
        }
    }
    
}
