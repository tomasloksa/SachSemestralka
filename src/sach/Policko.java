/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import Vykreslenie.Stvorec;
import Figurky.Figurka;

/**
 *
 * @author Tomas
 */
public class Policko {
    private Figurka figurka;
    private Stvorec stvorec;
    private String povodnaFarba;
    private int riadok, stlpec;
     
    public Policko(String farba, int riadok, int stlpec) {
        this.figurka = null;
        this.stvorec = new Stvorec();
        this.stvorec.zmenFarbu(farba);
        this.povodnaFarba = farba;
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.stvorec.nastavPolohu(riadok, stlpec);
        this.stvorec.zobraz();
    }

    public Figurka getFigurka() {
        return figurka;
    }

    public void setFigurka(Figurka figurka) {
        if (figurka != null) {
            figurka.posunNaPoziciu(this.riadok, this.stlpec);
        }
        this.figurka = figurka;
    }
    
    public void setFarba(String farba) {
        this.stvorec.zmenFarbu(farba);
        if (figurka != null) {
            this.figurka.posunNaPoziciu(this.riadok, this.stlpec);
        }
    }
    
    public void setPovodnaFarba() {
        this.stvorec.zmenFarbu(povodnaFarba);
    }
    
    public boolean jeVolne() {
        return figurka == null;
    }
    
    public boolean jeAktivne() {
        if(this.stvorec.getFarba() != this.povodnaFarba) {
            return true;
        }
        return false;
    }
}
