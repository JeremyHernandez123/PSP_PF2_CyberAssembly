/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import model.CentralSoldadura;
import view.ConsoleView;

/**
 *
 * @author jerem
 */
public class TecnicManteniment implements Runnable{
    private final CentralSoldadura central; 
    private final ConsoleView vista; 
    
    public TecnicManteniment(CentralSoldadura central, ConsoleView vista) {
        this.central = central;
        this.vista = vista;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(2000); 
                int estacio = central.repararEstacio();
                if (estacio != -1) {
                    vista.mostrar("Tecnic: estació " + estacio + " reparada");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
