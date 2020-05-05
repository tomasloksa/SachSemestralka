/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurky;

/**
 * Figúrka Jazdca
 * @author Tomas
 */
public class Jazdec extends Figurka {    

    /**
     * Vytvorí figúrku jazdca
     * @param farba farba jazdca
     * @param riadok na akom riadku sa jazdec nachádza
     * @param stlpec na akom stĺpci sa jazdec nachádza
     */
    public Jazdec(String farba, int riadok, int stlpec) {
        super(farba, riadok, stlpec);
    }
    
    /**
     * Zistí, či sa jazdec môže posunúť na danú pozíciu v súlade s pravidlami 
     * jeho pohybu
     * @param riadok na aký riadok sa chce posunúť
     * @param stlpec na aký stĺpec sa chce posunúť
     * @return true, ak je ťah platný, inak false
     */
    @Override
    public boolean mozeSaPosunut(int riadok, int stlpec) {
        int rozdielRiadok = Math.abs(this.getRiadok() - riadok);
        int rozdielStlpec = Math.abs(this.getStlpec() - stlpec);
        
        if (rozdielRiadok > 2 || rozdielRiadok < 1) {
            return false;
        }
        
        if (rozdielStlpec > 2 || rozdielStlpec < 1) {
            return false;
        }
        
        return rozdielRiadok != rozdielStlpec;
    }
    
    /**
     * Vráti názov súboru s ikonou figúrky
     * @return String s názvom súboru
     */
    @Override
    public String getNazov() {
        String nazovSuboru;
        if (super.getFarba().equals("biela")) {
            nazovSuboru = "bJazdec.png";
        } else {
            nazovSuboru = "cJazdec.png";
        }
        
        return nazovSuboru;
    }
}
