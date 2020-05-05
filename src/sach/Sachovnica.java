/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import vynimky.DostalMatException;
import vynimky.DostalSachException;
import figurky.Dama;
import figurky.IFigurka;
import figurky.Jazdec;
import figurky.Kral;
import figurky.Pesiak;
import figurky.Strelec;
import figurky.Veza;
import vykreslenie.Platno;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Hlavná plocha hry
 * @author Tomas
 */
public class Sachovnica {

    private static Sachovnica sachovnicaSingleton;

    /**
     * Vráti inštanciu šachovnice
     * @return Sachovnica singleton
     */
    public static Sachovnica getSachovnica() {
        if (Sachovnica.sachovnicaSingleton == null) {
            Sachovnica.sachovnicaSingleton = new Sachovnica();
        }
        return Sachovnica.sachovnicaSingleton;
    }

    private final Policko[][] policka;
    private Policko oznacene;
    private String poslednyTahal;
    private ArrayList<IFigurka> figurky;
    private Kral bielyKral;
    private Kral ciernyKral;
    private boolean bielyMaSach;
    private boolean ciernyMaSach;

    private Sachovnica() {
        this.policka = new Policko[8][8];
        this.poslednyTahal = "cierna";
        this.bielyMaSach = false;
        this.ciernyMaSach = false;
        this.figurky = new ArrayList<>();
    }

    /**
     * Nastaví šachovnicu - zobrazí a položí políčka aj figúrky
     */
    public void nastavSachovnicu() {
        for (int riadky = 0; riadky < 8; riadky++) {
            for (int stlpce = 0; stlpce < 8; stlpce++) {
                if ((riadky * 3 + stlpce) % 2 == 0) {
                    this.policka[riadky][stlpce] = new Policko("bledosiva", riadky, stlpce);
                } else {
                    this.policka[riadky][stlpce] = new Policko("tmavosiva", riadky, stlpce);
                }
                if (riadky == 1) {
                    this.policka[riadky][stlpce].setFigurka(new Pesiak("cierna", riadky, stlpce));
                    this.figurky.add(this.policka[riadky][stlpce].getFigurka());
                }
                if (riadky == 6) {
                    this.policka[riadky][stlpce].setFigurka(new Pesiak("biela", riadky, stlpce));
                    this.figurky.add(this.policka[riadky][stlpce].getFigurka());
                }
            }
        }

        this.policka[0][0].setFigurka(new Veza("cierna", 0, 0));
        this.policka[0][7].setFigurka(new Veza("cierna", 0, 7));

        this.policka[0][1].setFigurka(new Jazdec("cierna", 0, 1));
        this.policka[0][6].setFigurka(new Jazdec("cierna", 0, 6));

        this.policka[0][2].setFigurka(new Strelec("cierna", 0, 2));
        this.policka[0][5].setFigurka(new Strelec("cierna", 0, 5));

        this.policka[0][3].setFigurka(new Dama("cierna", 0, 3));
        
        this.ciernyKral = new Kral("cierna", 0, 4);
        this.policka[0][4].setFigurka(this.ciernyKral);

        this.policka[7][0].setFigurka(new Veza("biela", 7, 0));
        this.policka[7][7].setFigurka(new Veza("biela", 7, 7));

        this.policka[7][1].setFigurka(new Jazdec("biela", 7, 1));
        this.policka[7][6].setFigurka(new Jazdec("biela", 7, 6));

        this.policka[7][2].setFigurka(new Strelec("biela", 7, 2));
        this.policka[7][5].setFigurka(new Strelec("biela", 7, 5));

        this.bielyKral = new Kral("biela", 7, 4);
        this.policka[7][4].setFigurka(this.bielyKral);
        this.policka[7][3].setFigurka(new Dama("biela", 7, 3));
        
        for (int i = 0; i < 8; i++) {
            this.figurky.add(this.policka[0][i].getFigurka());
            this.figurky.add(this.policka[7][i].getFigurka());
        }
    }

    /**
     * Logika s označením políčka
     * @param riadok riadok políčka, ktoré sa má označiť
     * @param stlpec stĺprec políčka, ktoré sa má označiť
     * @throws DostalMatException ak hráč dostal mat
     * @throws DostalSachException ak hráč dostal šach
     */
    public void oznacPolicko(int riadok, int stlpec) throws DostalMatException, DostalSachException {
        Policko vybrate = this.policka[riadok][stlpec];
        if (this.oznacene == null) {
            vybrate.setFarba("zlta");
            this.oznacene = vybrate;
            return;
        }

        if (this.oznacene.jeVolne()) {
            this.oznacene.setPovodnaFarba();
            vybrate.setFarba("zlta");
            this.oznacene = vybrate;
            return;
        }

        if (this.oznacene.equals(vybrate)) {
            this.oznacene.setPovodnaFarba();
            this.oznacene = null;
            return;
        }

        // Ak je na policku figurka hraca, ktory nie je na tahu
        if (this.oznacene.getFigurka().getFarba().equals(this.poslednyTahal)) {
            Platno.dajPlatno().zobrazWarning("Nepodvadzaj! Na tahu je " + (this.poslednyTahal.equals("biela") ? "cierny." : "biely."));
            this.oznacene.setPovodnaFarba();
            this.oznacene = null;
            return;
        }
        
        this.pohni(vybrate);
    }

    /**
     * Zistí, či je políčko voľné
     * @param riadok riadok políčka
     * @param stlpec stĺpec políčka
     * @return true, ak je voľné, inak false
     */
    public boolean jeVolne(int riadok, int stlpec) {
        return this.policka[riadok][stlpec].jeVolne();
    }
    
    public boolean pohlaSa(int riadok, int stlpec) {
        if (!this.jeVolne(riadok, stlpec)) {
            return this.policka[riadok][stlpec].getFigurka().pohlaSa();
        }
        
        return true;
    }

    /**
     * Pohne figúrku na zadané políčko
     * @param kam políčko, na ktoré sa má figúrka posunúť
     * @throws vynimky.DostalMatException ak hráč dostal mat
     * @throws vynimky.DostalSachException ak hráč dostal šach
     */
    public void pohni(Policko kam) throws DostalMatException, DostalSachException {
        if (kam.posunAVyhod(this.oznacene.getFigurka())) {
            this.poslednyTahal = kam.getFigurka().getFarba();
            try {
                this.oznacene.setFigurka(null);
            } catch (NullPointerException ex) {
                // Toto tu pribudlo kvôli rošáde - posun veže po kráľovi mi this.oznacene nulluje a inak by to spadlo

                if (this.poslednyTahal.equals("biela")) {
                    this.policka[7][4].setFigurka(null);
                } else {
                    this.policka[0][4].setFigurka(null);
                }
            }
        } else if (this.oznacene != kam) {
            Timer timer;
            timer = new Timer(200, (ActionEvent evt) -> {
                if (!kam.equals(this.oznacene)) {
                    kam.setPovodnaFarba();
                }
            });
            kam.setFarba("cervena");
            timer.start();
        }
        try {
            this.oznacene.setPovodnaFarba();
            this.oznacene = null;
        } catch (NullPointerException ex) {
            // Toto tu pribudlo kvôli rošáde - posun veže po kráľovi mi this.oznacene nulluje a inak by to spadlo
        }
        
        this.skontrolujKralov();
    }
    
    /**
     * Vráti farbu hráča, ktorý je na ťahu
     * @return String s farbou hráča
     */
    public String getNaTahu() {
        return this.poslednyTahal.equals("biela") ? "cierna" : "biela";
    }   
    
    // Kontrola, či je kráľ v šachu
    private void skontrolujKralov() throws DostalMatException, DostalSachException {
        for (IFigurka figurka : this.figurky) {
            if (figurka.jeVyhodena()) {
                continue;
            }

            if (figurka.getFarba().equals("cierna")) {
                if (figurka.mozeSaPosunut(this.bielyKral.getRiadok(), this.bielyKral.getStlpec())) {
                    if (this.bielyMaSach || this.poslednyTahal.equals("biela")) {
                        throw new DostalMatException("biely");
                    }
                    this.bielyMaSach = true;
                    throw new DostalSachException("biely");
                }
            } else {
                if (figurka.mozeSaPosunut(this.ciernyKral.getRiadok(), this.ciernyKral.getStlpec())) {
                    if (this.ciernyMaSach || this.poslednyTahal.equals("cierna")) {
                        throw new DostalMatException("cierny");
                    }
                    this.ciernyMaSach = true;
                    throw new DostalSachException("cierny");
                }
            }
        }
        this.bielyMaSach = false;
        this.ciernyMaSach = false;
    }
}
