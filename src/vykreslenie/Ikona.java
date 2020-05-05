/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vykreslenie;

/**
 * Trieda s ikonou figúrky
 * @author Tomas
 */
public class Ikona extends Zobrazenie {
    private final String baseCesta = "sprites\\";

    /**
     * Nastaví súbor, z ktorého sa ikona načíta
     * @param nazovSuboru String s názvom súboru
     */
    public Ikona(String nazovSuboru) {
        super();
        super.setCesta(this.baseCesta + nazovSuboru);
    }
    
}
