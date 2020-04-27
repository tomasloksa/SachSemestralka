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
public class Veza extends Figurka{    
    public Veza(String farba, int riadok, int stlpec) {
        super(farba, riadok, stlpec);
    }
    
    public boolean mozeSaPosunut(int riadok, int stlpec) {
        return true;
    }
    
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
