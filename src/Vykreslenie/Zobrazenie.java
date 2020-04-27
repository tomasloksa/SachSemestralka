/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vykreslenie;

import java.awt.Rectangle;

/**
 *
 * @author Tomas
 */

public abstract class Zobrazenie {
    private int strana;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;
    private boolean jeViditelny;
    private String cesta;

    /**
     * Vytvor nový štvorec preddefinovanej farby na preddefinovanej pozícii.
     */
    public Zobrazenie() {
        this.strana = 90;
        this.lavyHornyX = 0;
        this.lavyHornyY = 0;
        this.farba = "red";
        this.jeViditelny = false;
    }
    
    public void setCesta(String cesta) {
        this.cesta = cesta;
    }
    
    /**
     * (Štvorec) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * (Štvorec) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }
    
        
    public void zmenFarbu(String farba) {
        this.farba = farba;
        this.nakresli();
    }
    
    public String getFarba() {
        return this.farba;
    }
    
    public void nastavPolohu (int riadok, int stlpec) {
        this.lavyHornyX = stlpec * this.strana;
        this.lavyHornyY = riadok * this.strana;
        
        this.nakresli();
    }
    
    public void zmenStranu(int dlzka) {
        this.strana = dlzka;
        this.nakresli();
    }

    /**
     * (Štvorec) Posuň sa pomaly vodorovne o dĺžku danú parametrom.
     */
    public void pomalyPosunVodorovne(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else  {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.lavyHornyX += delta;
            this.nakresli();
        }
    }

    /**
     * (Štvorec) Posuň sa pomaly vodorovne o dĺžku danú parametrom.
     */
    public void pomalyPosunZvisle(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.lavyHornyY += delta;
            this.nakresli();
        }
    }

    /*
     * Draw the square with current specifications on screen.
     */
    public void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            System.out.println(this.getClass().toString());
            if (this.getClass().toString().equals("class Vykreslenie.Ikona")) {
                // TODO nastavenie polohy
                canvas.drawImage(this, this.cesta, this.lavyHornyX, this.lavyHornyY, this.strana);
            } else {
                canvas.draw(this, this.farba,
                            new Rectangle(this.lavyHornyX, this.lavyHornyY, this.strana, this.strana));
            }
        }
    }

    /*
     * Erase the square on screen.
     */
    protected void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}

