/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurky;

import sach.Sachovnica;

/**
 * Figúrka Dámy
 * @author Tomas
 */
public class Dama extends Figurka {    

    /**
     * Vytvorí figúrku dámy
     * @param farba farba dámy
     * @param riadok na akom riadku sa dáma nachádza
     * @param stlpec na akom stĺpci sa dáma nachádza
     */
    public Dama(String farba, int riadok, int stlpec) {
        super(farba, riadok, stlpec);
    }
    
    /**
     * Zistí, či sa dáma môže posunúť na danú pozíciu v súlade s pravidlami jej pohybu
     * @param riadok na aký riadok sa má posunúť
     * @param stlpec na aký stĺpec sa má posunúť
     * @return true, ak je ťah platný, inak false
     */
    @Override
    public boolean mozeSaPosunut(int riadok, int stlpec) {        
        Sachovnica sachovnica = Sachovnica.getSachovnica();
        int rozdielRiadok = this.getRiadok() - riadok;
        int rozdielStlpec = this.getStlpec() - stlpec;
        
        // Najprv zistim, ci je pohyb diagonalny
        if (Math.abs(rozdielRiadok) == Math.abs(rozdielStlpec)) {
            if (rozdielRiadok == rozdielStlpec) {
                int maxRiadok = Math.max(this.getRiadok(), riadok) - 1;
                int minRiadok = Math.min(this.getRiadok(), riadok) + 1;
                int maxStlpec = Math.max(this.getStlpec(), stlpec) - 1;
                while (maxRiadok >= minRiadok) {
                    if (!sachovnica.jeVolne(maxRiadok, maxStlpec)) {
                        return false;
                    }
                    maxRiadok--;
                    maxStlpec--;
                }
            } else {
                int maxRiadok = Math.max(this.getRiadok(), riadok) - 1;
                int minRiadok = Math.min(this.getRiadok(), riadok) + 1;
                int minStlpec = Math.min(this.getStlpec(), stlpec) + 1;
                while (maxRiadok >= minRiadok) {
                    if (!sachovnica.jeVolne(maxRiadok, minStlpec)) {
                        return false;
                    }
                    maxRiadok--;
                    minStlpec++;
                }
            }
            // pohnem sa po diagonale a dalej nepokracujem
            return true;
        }
        
        
        // Ak sa chcem pohnut inak ako horiznotalne/vertikalne
        if (this.getRiadok() != riadok && this.getStlpec() != stlpec) {
            return false;
        }
        
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
    
    /**
     * Vráti názov súboru s ikonou figúrky
     * @return String s názvom súboru
     */
    @Override
    public String getNazov() {
        String nazovSuboru;
        if (super.getFarba().equals("biela")) {
            nazovSuboru = "bDama.png";
        } else {
            nazovSuboru = "cDama.png";
        }
        return nazovSuboru;
    }
}
