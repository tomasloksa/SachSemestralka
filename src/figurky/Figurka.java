/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurky;

import vykreslenie.Ikona;

/**
 * Predok všetkých figúr
 * @author Tomas
 */
public abstract class Figurka implements IFigurka {
    private String farba;
    private Ikona ikona;
    private String cesta;
    private int riadok;
    private int stlpec;
    private boolean vyhodena;
    
    public Figurka(String farba, int riadok, int stlpec) {
        this.farba = farba;
        this.cesta = this.getNazov();
        this.ikona = new Ikona(this.cesta);
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.ikona.nastavPolohu(riadok, stlpec);
        this.ikona.zobraz();
        this.vyhodena = false;
    }
    
    /**
     * Zistí, či je ťah platný a posunie figúrku
     * @param riadok na aký riadok sa má posunúť
     * @param stlpec na aký stĺpec sa má posunúť
     * @return
     */
    @Override
    public boolean posunNaPoziciu(int riadok, int stlpec) {
        if (!this.mozeSaPosunut(riadok, stlpec)) {
            return false;
        }
        
        this.ikona.nastavPolohu(riadok, stlpec);
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.prekresli();

        return true;
    }
        
    /**
     * Vráti farbu figúrky
     * @return String s názvom farby
     */
    @Override
    public String getFarba() {
        return this.farba;
    }
    
    /**
     * Vyhodí vigúrku zo šachovnice
     */
    public void vyhod() {
        this.vyhodena = true;
        this.ikona.skry();
    }
    
    /**
     * Vráti, na akom riadku sa figúrka nachádza
     * @return int s indexom riadku
     */
    public int getRiadok() {
        return this.riadok;
    }
    
    /**
     * Nastaví riadok figúrky
     * @param riadok
     */
    public void setRiadok(int riadok) {
        this.riadok = riadok;
    }
    
    /**
     * Vráti, na akom stĺpci sa figúrka nachádza
     * @return int s indexom stĺpca
     */
    public int getStlpec() {
        return this.stlpec;
    }
    
    /**
     * Nastaví stĺpec figúrky
     * @param stlpec
     */
    public void setStlpec(int stlpec) {
        this.stlpec = stlpec;
    }
    
    /**
     * Zistí, či je figúrka vyhodená
     * @return true, ak je vyhodena, inak false
     */
    @Override
    public boolean jeVyhodena() {
        return this.vyhodena;
    }
    
    private void prekresli() {
        this.ikona.zobraz();
    }
    
    /**
     * Podmienky, či sa figúrky môžu posunúť na dané políčko
     * @param riadok na aký riadok sa má posunúť
     * @param stlpec na aký stĺpec sa má posunúť
     * @return True, ak je ťah platný, inak false
     */
    @Override
    public abstract boolean mozeSaPosunut(int riadok, int stlpec);
    abstract String getNazov();
}
