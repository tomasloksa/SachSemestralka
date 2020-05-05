/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurky;

import sach.Sachovnica;

/**
 * Figúrka Pešiaka
 *
 * @author Tomas
 */
public class Pesiak extends Figurka {

    private boolean poholSa;

    /**
     * Vytvorí figúrku pešiaka so zadanými parametrami
     *
     * @param farba farba pešiaka
     * @param riadok riadok, na ktorom sa pešiak nachádza
     * @param stlpec stĺpec, na ktorom sa pešiak nachádza
     */
    public Pesiak(String farba, int riadok, int stlpec) {
        super(farba, riadok, stlpec);
        this.poholSa = false;
    }

    /**
     * Zistí, či sa jazdec môže posunúť na danú pozíciu v súade s pravidlami
     * jeho pohybu
     *
     * @param riadok riadok, na ktorý sa chce posunúť
     * @param stlpec stĺpec, na ktorý sa chce posunúť
     * @return true, ak sa posunúť môže, inak false
     */
    @Override
    public boolean mozeSaPosunut(int riadok, int stlpec) {
        Sachovnica sachovnica = Sachovnica.getSachovnica();

        if (super.getFarba().equals("cierna")) {
            if (super.getRiadok() == riadok - 1) {
                if (stlpec == super.getStlpec() && sachovnica.jeVolne(riadok, stlpec)) {
                    this.poholSa = true;
                    return true;
                } else if (Math.abs(stlpec - super.getStlpec()) == 1 && !sachovnica.jeVolne(riadok, stlpec)) {
                    this.poholSa = true;
                    return true;
                }
            }
            if (!this.poholSa && super.getRiadok() == riadok - 2) {
                if (stlpec == super.getStlpec() && sachovnica.jeVolne(riadok, stlpec)) {
                    this.poholSa = true;
                    return true;
                }
            }
        }
        if (super.getFarba().equals("biela")) {
            if (super.getRiadok() == riadok + 1) {
                if (stlpec == super.getStlpec() && sachovnica.jeVolne(riadok, stlpec)) {
                    this.poholSa = true;
                    return true;
                } else if (Math.abs(stlpec - super.getStlpec()) == 1 && !sachovnica.jeVolne(riadok, stlpec)) {
                    this.poholSa = true;
                    return true;
                }
            } else if (!this.poholSa && super.getRiadok() == riadok + 2) {
                if (stlpec == super.getStlpec() && sachovnica.jeVolne(riadok, stlpec)) {
                    this.poholSa = true;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Vráti názov súboru s ikonkou pešiaka
     *
     * @return String s názvom súboru
     */
    @Override
    public String getNazov() {
        String nazovSuboru;
        if (super.getFarba().equals("biela")) {
            nazovSuboru = "bPesiak.png";
        } else {
            nazovSuboru = "cPesiak.png";
        }
        return nazovSuboru;
    }
}
