/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import Figurky.Dama;
import Figurky.Figurka;
import Figurky.Jazdec;
import Figurky.Kral;
import Figurky.Pesiak;
import Figurky.Strelec;
import Figurky.Veza;

/**
 *
 * @author Tomas
 */
public class Sachovnica {
    Policko[][] policka;
    private Policko oznacene;
    
    public Sachovnica() {
        this.policka = new Policko[9][9];
    }
    
    public void nastavSachovnicu() {
        for (int riadky = 0; riadky < 8; riadky++) {
            for(int stlpce = 0; stlpce < 8; stlpce++) {
                if ((riadky * 3 + stlpce) % 2 == 0) {
                    policka[riadky][stlpce] = new Policko("bledosiva", riadky, stlpce);
                } else {
                    policka[riadky][stlpce] = new Policko("tmavosiva", riadky, stlpce);
                }
                if (riadky == 1) {
                    policka[riadky][stlpce].setFigurka(new Pesiak("cierna", riadky, stlpce));
                }
                if (riadky == 6) {
                    policka[riadky][stlpce].setFigurka(new Pesiak("biela", riadky, stlpce));
                }
            }
        }
        
        policka[0][0].setFigurka(new Veza("cierna", 0, 0));
        policka[0][7].setFigurka(new Veza("cierna", 0, 7));
        
        policka[0][1].setFigurka(new Jazdec("cierna", 0, 1));
        policka[0][6].setFigurka(new Jazdec("cierna", 0, 6));
        
        policka[0][2].setFigurka(new Strelec("cierna", 0, 2));
        policka[0][5].setFigurka(new Strelec("cierna", 0, 5));
        
        policka[0][3].setFigurka(new Dama("cierna", 0, 3));
        policka[0][4].setFigurka(new Kral("cierna", 0, 4));
        
        policka[7][0].setFigurka(new Veza("biela", 7, 0));
        policka[7][7].setFigurka(new Veza("biela", 7, 7));
        
        policka[7][1].setFigurka(new Jazdec("biela", 7, 1));
        policka[7][6].setFigurka(new Jazdec("biela", 7, 6));
        
        policka[7][2].setFigurka(new Strelec("biela", 7, 2));
        policka[7][5].setFigurka(new Strelec("biela", 7, 5));
        
        policka[7][3].setFigurka(new Kral("biela", 7, 3));
        policka[7][4].setFigurka(new Dama("biela", 7, 4));
    }
    
    public void oznacPolicko(int riadok, int stlpec) {
        Policko vybrate = policka[riadok][stlpec];
        if (this.oznacene == null) {
            Policko policko = policka[riadok][stlpec];
            policko.setFarba("zlta");
            this.oznacene = policko;
            return;
        } 
        
        if (this.oznacene.jeVolne()) {
            Policko policko = policka[riadok][stlpec];
            this.oznacene.setPovodnaFarba();
            policko.setFarba("zlta");
            this.oznacene = policko;
            return;
        }
        
        Figurka figurka = this.oznacene.getFigurka();
        if (figurka.mozeSaPosunut(riadok, stlpec)) {
            this.oznacene.setFigurka(null);
            vybrate.setFigurka(figurka);
            
        }
        oznacene.setPovodnaFarba();
        this.oznacene = null;
    }
}
