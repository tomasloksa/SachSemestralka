/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figurky;

/**
 *
 * @author Tomas
 */
public class Jazdec extends Figurka {
    private String nazovSuboru;
    
    public Jazdec(String farba, int riadok, int stlpec) {
        super(farba, riadok, stlpec);
    }
    
    public boolean mozeSaPosunut(int riadok, int stlpec) {
        return true;
    }
    
    public String getNazov() {
        if (super.getFarba().equals("biela")) {
            this.nazovSuboru = "bJazdec.png";
        } else {
            this.nazovSuboru = "cJazdec.png";
        }
        
        return nazovSuboru;
    }
}
