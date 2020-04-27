/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figurky;

import Vykreslenie.Ikona;

/**
 *
 * @author Tomas
 */
public abstract class Figurka implements IFigurka {
    private String farba;
    private Ikona ikona;
    private String cesta;
    private int riadok, stlpec;
    
    public Figurka(String farba, int riadok, int stlpec) {
        this.farba = farba;
        this.cesta = this.getNazov();
        this.ikona = new Ikona(cesta);
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.ikona.nastavPolohu(riadok, stlpec);
        this.ikona.zobraz();
    }
    
    public boolean posunNaPoziciu(int riadok, int stlpec) {
        if (!this.mozeSaPosunut(riadok, stlpec)) {
            return false;
        }
        
        this.ikona.nastavPolohu(riadok, stlpec);
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.prekresli();

        return true;
    }
    
    private void prekresli() {
        this.ikona.zobraz();
    }
    
    abstract public boolean mozeSaPosunut(int riadok, int stlpec);
    abstract String getNazov();
    
    public String getFarba() {
        return this.farba;
    }
    

}
