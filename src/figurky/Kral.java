/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurky;

import sach.Sachovnica;
import vynimky.DostalMatException;
import vynimky.DostalSachException;

/**
 * Figúrka Kráľa
 * @author Tomas
 */
public class Kral extends Figurka {  
    /**
     * Vytvorí figúrku kráľa so zadanými parametrami
     * @param farba farba kráľa
     * @param riadok riadok, na ktorom sa kráľ zobrazí
     * @param stlpec stĺpec, na ktorom sa kráľ zobrazí
     */
    public Kral(String farba, int riadok, int stlpec) {
        super(farba, riadok, stlpec);
    }
    
    /**
     * Zistí, či sa kráľ môže posunúť na danú pozíciu v súlade s pravidlami 
     * jeho pohybu
     * @param riadok riadok, na ktorý sa chce posunúť
     * @param stlpec stĺpec na ktorý sa chce posunúť
     * @return true, ak sa posunúť môže, inak false
     */
    @Override
    public boolean mozeSaPosunut(int riadok, int stlpec) {
        int rozdielRiadok = this.getRiadok() - riadok;
        int rozdielStlpec = this.getStlpec() - stlpec;
        
        Sachovnica sachovnica = Sachovnica.getSachovnica();
        // rosada vľavo
        if (!super.pohlaSa() && rozdielStlpec == 2 && rozdielRiadok == 0) {
            if (sachovnica.pohlaSa(riadok, 0)) {
                return false;
            }
            
            try {
                sachovnica.oznacPolicko(riadok, 0);
                sachovnica.oznacPolicko(riadok, 0);
                sachovnica.oznacPolicko(riadok, 3);
            } catch (DostalMatException | DostalSachException ex) {
            }
            
            return true;
        }
        
        // rosada vpravo
        if (!this.pohlaSa() && rozdielStlpec == -2 && rozdielRiadok == 0) {
            if (sachovnica.pohlaSa(riadok, 7)) {
                return false;
            }
            
            try {
                sachovnica.oznacPolicko(riadok, 7);
                sachovnica.oznacPolicko(riadok, 7);
                sachovnica.oznacPolicko(riadok, 5);
            } catch (DostalMatException | DostalSachException ex) {
            }
            
            return true;
        }
        
        if (rozdielRiadok < -1 || rozdielRiadok > 1) {
            return false;
        }
        
        return !(rozdielStlpec < -1 || rozdielStlpec > 1);
    }
    
    /**
     * Vráti názov súboru s ikonkou kráľa
     * @return String s názvom súboru
     */
    @Override
    public String getNazov() {
        String nazovSuboru;
        if (super.getFarba().equals("biela")) {
            nazovSuboru = "bKral.png";
        } else {
            nazovSuboru = "cKral.png";
        }
        
        return nazovSuboru;
    }
}
