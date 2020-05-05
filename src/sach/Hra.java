/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import vykreslenie.Platno;
import vynimky.DostalMatException;
import vynimky.DostalSachException;
import vynimky.CasVyprsalException;

/**
 * Hlavná trieda na správu hry
 * @author Tomas
 */
public final class Hra {
    private final Sachovnica sachovnica;
    private Hrac hrac1;
    private Hrac hrac2;
    private final Platno platno;
    
    /**
     * Vytvorí šachovnicu + plátno a nastaví hru
     */
    public Hra() {
        this.sachovnica = Sachovnica.getSachovnica();
        this.platno = Platno.dajPlatno();
    }
    
    public void spustiHru() {
        this.sachovnica.nastavSachovnicu();
        this.platno.redraw();
        String meno1 = this.platno.zobrazInput("Zadaj meno 1. hráča");
        String meno2 = this.platno.zobrazInput("Zadaj meno 2. hráča");
        double cas;
        try {
            cas = Double.parseDouble(this.platno.zobrazInput("Zadaj čas v minutach, \n -1 pre hru bez časomiery"));
        } catch (NumberFormatException e) {
            cas = -1;
        }
        
        if (Math.random() <= 0.5) {
            this.hrac1 = new Hrac("biela", meno1);        
            this.hrac2 = new Hrac("cierna", meno2);
        } else {
            this.hrac1 = new Hrac("cierna", meno1);        
            this.hrac2 = new Hrac("biela", meno2);
        }
        
        this.hrac1.setCas(cas);
        this.hrac2.setCas(cas);
        
        Timer timer;
        timer = new Timer(1000, (ActionEvent evt) -> {
            this.tick();
        });
        timer.start();
        
        Platno.dajPlatno().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Hra.this.klikniPolicko(e.getY() / 90, e.getX() / 90);
            }
        });
    }
    
    private void klikniPolicko(int riadok, int stlpec) {
        try {
            this.sachovnica.oznacPolicko(riadok, stlpec);
        } catch (DostalMatException ex) {
            this.platno.zobrazWarning(this.getHracMeno(ex.kto().equals("biely") ? "cierny" : "biely") + " Vyhral!");
            System.exit(0);
        } catch (DostalSachException ex) {
            this.platno.zobrazWarning(this.getHracMeno(ex.kto()) + " dostal šach! \n Ak sa z neho nedostane v tomto ťahu, prehrá hru!");
        }
    }

    private void tick() {
        if (this.sachovnica.getNaTahu().equals(this.hrac1.getFarba())) {
            try {
                this.hrac1.tick();
            } catch (CasVyprsalException ex) {
                this.platno.zobrazWarning((this.hrac1.getMeno().equals("") ? this.hrac1.getMeno() : "biely") + " vyhral!");
                System.exit(0);
            }
                
        } else {
            try { 
                this.hrac2.tick();
            } catch (CasVyprsalException ex) {
                this.platno.zobrazWarning((this.hrac2.getMeno().equals("") ? this.hrac2.getMeno() : "cierny") + " vyhral!");
                System.exit(0);

            }
        }
        
        if (this.hrac1.getCas() < 0 || this.hrac2.getCas() < 0) {
            return;
        }
                    
        this.platno.upravCasy(this.hrac1, this.hrac2);

    }
    
    private String getHracMeno(String farba) {
        String meno = "";
        if (farba.equals("biela") || farba.equals("biely")) {
            meno = this.hrac1.getMeno();
        } else if (farba.equals("cierna") || farba.equals("cierny")) {
            meno = this.hrac2.getMeno();
        }
        if (meno.equals("")) {
            return farba;
        }
        return meno;
    }
}
