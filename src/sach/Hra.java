/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import Vykreslenie.Platno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import javax.swing.JOptionPane;

/**
 *
 * @author Tomas
 */
public class Hra {
    private Sachovnica sachovnica;
    private Hrac bielyHrac;
    private Hrac ciernyHrac;
    private Timer bielyTimer;
    private Timer ciernyTimer;
    private Platno platno;
    
    public Hra() {
        this.sachovnica = new Sachovnica();
        this.bielyHrac = new Hrac();
        this.ciernyHrac = new Hrac();
        this.platno = Platno.dajPlatno();

        this.nastavHru();
        this.hraj();
    }
    
    public void hraj() {
    }
    
    public void nastavHru() {
        this.sachovnica.nastavSachovnicu();
        Platno.dajPlatno().redraw();
        this.klikniPolicko(3, 5);
        this.klikniPolicko(3, 5);
        this.klikniPolicko(0, 0);
        this.klikniPolicko(3, 0);
        /*String meno1 = platno.zobrazInputDialog("Zadaj meno 1. hráča");
        String meno2 = platno.zobrazInputDialog("Zadaj meno 2. hráča");
        try {
            int cas = Integer.parseInt(platno.zobrazInputDialog("Zadaj čas v min."));
        } catch (Exception e) {
            int cas = -1;
        }*/
    }
    
    public void klikniPolicko(int riadok, int stlpec) {
        this.sachovnica.oznacPolicko(riadok, stlpec);
    }
}
