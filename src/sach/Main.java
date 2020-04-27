/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import Vykreslenie.Platno;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Tomas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hra hra = new Hra();
        Platno.dajPlatno().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("klikam");
                hra.klikniPolicko(e.getY() / 90, e.getX() / 90);
            }
        });
        hra.hraj();
    }
}
