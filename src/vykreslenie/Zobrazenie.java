/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vykreslenie;

import java.awt.Rectangle;

/**
 * Trieda pre zobrazenie Tvarov a Ikon
 * @author Tomas
 */

public abstract class Zobrazenie {
    private final int strana;
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
    
    /**
     * Nastaví cestu k súboru ikony
     * @param cesta cesta k súboru ikony
     */
    public void setCesta(String cesta) {
        this.cesta = cesta;
    }
    
    /**
     * Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }
    
    /**
     * Zmení farbu 
     * @param farba farba, na ktorú sa tvar nastaví
     */
    public void zmenFarbu(String farba) {
        this.farba = farba;
        this.nakresli();
    }
    
    /**
     * Vráti farbu tvaru
     * @return String s farbou tvaru
     */
    public String getFarba() {
        return this.farba;
    }
    
    /**
     * Nastaví polohu tvaru
     * @param riadok riadok, na ktorom sa tvar zobrazí
     * @param stlpec stĺpec na ktorom sa tvar zobrazí
     */
    public void nastavPolohu (int riadok, int stlpec) {
        this.lavyHornyX = stlpec * this.strana;
        this.lavyHornyY = riadok * this.strana;
        
        this.nakresli();
    }

    /*
     * Nakreslí tvar/ikonu
     */
    public void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            if (this instanceof vykreslenie.Ikona) {
                canvas.drawImage(this, this.cesta, this.lavyHornyX, this.lavyHornyY, this.strana);
            } else {
                canvas.draw(this, this.farba,
                            new Rectangle(this.lavyHornyX, this.lavyHornyY, this.strana, this.strana));
            }
        }
    }

    /*
     * Zmaže tvar/ikonu
     */
    protected void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}

