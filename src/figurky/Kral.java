/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurky;

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
     * Zistí, či sa kráľ môže posunúť na danú pozíciu v súlade s pravidlami jeho pohybu
     * @param riadok riadok, na ktorý sa chce posunúť
     * @param stlpec stĺpec na ktorý sa chce posunúť
     * @return true, ak sa posunúť môže, inak false
     */
    @Override
    public boolean mozeSaPosunut(int riadok, int stlpec) {
        int rozdielRiadok = this.getRiadok() - riadok;
        int rozdielStlpec = this.getStlpec() - stlpec;
        
        if (rozdielRiadok < -1 || rozdielRiadok > 1) {
            return false;
        }
        
        if (rozdielStlpec < -1 || rozdielRiadok > 1) {
            return false;
        }
        
        return true;
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
