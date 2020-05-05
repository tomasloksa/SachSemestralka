/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import vykreslenie.Platno;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main trieda, ktorá spustí hru
 * @author Tomas
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hra hra = new Hra();
        hra.spustiHru();
    }
}
