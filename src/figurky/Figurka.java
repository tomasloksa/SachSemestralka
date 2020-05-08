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
public abstract class Figurka {
    private final String farba;
    private final Ikona ikona;
    private final String cesta;
    private int riadok;
    private int stlpec;
    private boolean vyhodena;
    private boolean pohlaSa;
    
    /**
     * Inicializuje figúrku a nastaví parametre
     * @param farba farba figúrky
     * @param riadok riadok, na ktorom sa figúrka nachádza
     * @param stlpec stĺpec, na ktorom sa figúrka nachádza
     */
    Figurka(String farba, int riadok, int stlpec) {
        this.farba = farba;
        this.cesta = this.getNazov();
        this.ikona = new Ikona(this.cesta);
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.ikona.nastavPolohu(riadok, stlpec);
        this.ikona.zobraz();
        this.vyhodena = false;
        this.pohlaSa = false;
    }
    
    /**
     * Zistí, či je ťah platný a posunie + prekreslí figúrku
     * @param riadok na aký riadok sa má posunúť
     * @param stlpec na aký stĺpec sa má posunúť
     * @return true, ak sa môže posunúť, inak false;
     */
    public boolean posunNaPoziciu(int riadok, int stlpec) {
        if (!this.mozeSaPosunut(riadok, stlpec)) {
            return false;
        }
        
        this.ikona.nastavPolohu(riadok, stlpec);
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.prekresli();
        this.pohlaSa = true;

        return true;
    }
    
    /**
     * Vráti farbu figúrky
     * @return String s názvom farby
     */
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
     * @param riadok riadok, na ktorý sa má poloha nastaviť
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
     * @param stlpec stĺpec, na ktorý sa má figúrka položiť
     */
    public void setStlpec(int stlpec) {
        this.stlpec = stlpec;
    }
    
    /**
     * Zistí, či je figúrka vyhodená
     * @return true, ak je vyhodena, inak false
     */
    public boolean jeVyhodena() {
        return this.vyhodena;
    }
    
    public boolean pohlaSa() {
        return this.pohlaSa;
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
    public abstract boolean mozeSaPosunut(int riadok, int stlpec);
    protected abstract String getNazov();
}
