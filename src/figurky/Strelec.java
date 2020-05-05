/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurky;

import sach.Sachovnica;

/**
 * Figúrka strelca
 * @author Tomas
 */
public class Strelec extends Figurka {    
    public Strelec(String farba, int riadok, int stlpec) {
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
        int rozdielRiadok = this.getRiadok() - riadok;
        int rozdielStlpec = this.getStlpec() - stlpec;
        if (Math.abs(rozdielRiadok) != Math.abs(rozdielStlpec)) {
            return false;
        }
        
        Sachovnica sachovnica = Sachovnica.getSachovnica();
        
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
        
        return true;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getNazov() {
        String nazovSuboru;
        if (super.getFarba().equals("biela")) {
            nazovSuboru = "bStrelec.png";
        } else {
            nazovSuboru = "cStrelec.png";
        }
        return nazovSuboru;
    }
}
