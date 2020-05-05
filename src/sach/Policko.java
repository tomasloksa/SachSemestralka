/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import figurky.Dama;
import vykreslenie.Stvorec;
import vykreslenie.Platno;
import figurky.IFigurka;
import figurky.Jazdec;
import figurky.Strelec;
import figurky.Veza;

/**
 * Políčko na šachovnici
 * @author Tomas
 */
public class Policko {
    private IFigurka figurka;
    private final Stvorec stvorec;
    private final String povodnaFarba;
    private final int riadok;
    private final int stlpec;
     
    /**
     * Konštruktor políčka šachovnice
     * @param farba farba políčka
     * @param riadok riadok, na ktorom sa políčko nachádza
     * @param stlpec stĺpec, na ktorom sa políčko nachádza
     */
    public Policko(String farba, int riadok, int stlpec) {
        this.figurka = null;
        this.stvorec = new Stvorec();
        this.stvorec.zmenFarbu(farba);
        this.povodnaFarba = farba;
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.stvorec.nastavPolohu(riadok, stlpec);
        this.stvorec.zobraz();
    }

    /**
     * Vráti figúrku, ktorá stojí na tomto políčku
     * @return Figurka z tohto políčka
     */
    public IFigurka getFigurka() {
        return this.figurka;
    }

    /**
     * Posunie figúrku na toto políčko a vyhodí figúrku, ktorá sa tu už nachádza
     * @param figurka Figúrka, ktorá sa postaví na toto políčko
     * @return boolean, či sa figúrku podarilo posunúť
     */
    public boolean posunAVyhod(IFigurka figurka) {
        // Posuva prazdne policko
        if (figurka == null) {
            return false;
        }
        
        // ak tu uz je nejaka figurka
        if (!this.jeVolne()) {
            // Nemozem vyhodit krala
            if (this.figurka instanceof figurky.Kral) {
                return false;
            }

            // Ak policko obsahuje figurku rovankej farby
            if (this.figurka.getFarba().equals(figurka.getFarba())) {
                return false;
            }
        }
        
        if (figurka.posunNaPoziciu(this.riadok, this.stlpec)) {
            if (this.figurka != null) {
                this.figurka.vyhod();
            }
            
            // Ak pešiak došiel na koniec šachovnice
            if ((this.riadok == 0 || this.riadok == 7) && figurka instanceof figurky.Pesiak) {
                figurka.vyhod();
                String zvolenaFigura = Platno.dajPlatno().zobrazInput("1 - Dáma, 2 - Veža, 3 - Jazdec, 4 - Strelec");
                switch (zvolenaFigura) {
                    case "1":
                        figurka = new Dama(figurka.getFarba(), this.riadok, this.stlpec);
                        break;
                    case "2":
                        figurka = new Veza(figurka.getFarba(), this.riadok, this.stlpec);
                        break;
                    case "3":
                        figurka = new Jazdec(figurka.getFarba(), this.riadok, this.stlpec);
                        break;
                    case "4":
                        figurka = new Strelec(figurka.getFarba(), this.riadok, this.stlpec);
                        break;
                    default: 
                        figurka = new Dama(figurka.getFarba(), this.riadok, this.stlpec);
                }
            } 
            
            this.figurka = figurka;
            return true;
        }
        return false;
    }
    
    /**
     * Slúži na počiatočnú inicializáciu figúrky
     * @param figurka Figúrka, ktorá sa postaví na toto políčko
     */
    public void setFigurka(IFigurka figurka) {
        this.figurka = figurka;
    }
    
    /**
     * Zmení farbu políčka
     * @param farba farba, akú toto políčko bude mať
     */
    public void setFarba(String farba) {
        this.stvorec.zmenFarbu(farba);
    }
    
    /**
     * Vráti aktuálnu farbu políčka
     * @return String s farbou políčka
     */
    public String getFarba() {
        return this.stvorec.getFarba();
    }
    
    /**
     * Nastaví políčko na pôvodnú farbu
     */
    public void setPovodnaFarba() {
        this.stvorec.zmenFarbu(this.povodnaFarba);
    }
    
    /**
     * Zistí, či je políčko voľné
     * @return true, ak tu stojí nejaká figúrka, inak false
     */
    public boolean jeVolne() {
        return this.figurka == null;
    }
    
    /**
     * Zistí, či políčko bolo kliknuté
     * @return true, ak bolo klinuté, inak false
     */
    public boolean jeAktivne() {
        return !this.stvorec.getFarba().equals(this.povodnaFarba);
    }
}
