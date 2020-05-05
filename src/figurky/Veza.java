/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurky;

import sach.Sachovnica;

/**
 *
 * @author Tomas
 */
public class Veza extends Figurka {    
    public Veza(String farba, int riadok, int stlpec) {
        super(farba, riadok, stlpec);
    }
    
    /**
     *
     * @param riadok
     * @param stlpec
     * @return
     */
    @Override
    public boolean mozeSaPosunut(int riadok, int stlpec) {
        // Ak sa chcem pohnut inak ako horiznotalne/vertikalne
        if (this.getRiadok() != riadok && this.getStlpec() != stlpec) {
            return false;
        }
        Sachovnica sachovnica = Sachovnica.getSachovnica();
        
        // Kontrolujem ci mam volnu cestu po stlpci
        if (this.getRiadok() != riadok) {
            int max = Math.max(this.getRiadok(), riadok) - 1;
            int min = Math.min(this.getRiadok(), riadok) + 1;
            while (max >= min) {
                if (!sachovnica.jeVolne(max, stlpec)) {
                    return false;
                }
                max--;
            }
        }
        
        // Kontrolujem ci mam volnu cestu po riadku
        if (this.getStlpec() != stlpec) {
            int max = Math.max(this.getStlpec(), stlpec) - 1;
            int min = Math.min(this.getStlpec(), stlpec) + 1;
            while (max >= min) {
                if (!sachovnica.jeVolne(riadok, max)) {
                    return false;
                }
                max--;
            }
        }
        
        return true;
    }
    
    @Override
    public String getNazov() {
        String nazovSuboru;
        if (super.getFarba().equals("biela")) {
            nazovSuboru = "bVeza.png";
        } else {
            nazovSuboru = "cVeza.png";
        }
        
        return nazovSuboru;
    }
    
}
