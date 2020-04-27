/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vykreslenie;

/**
 *
 * @author Tomas
 */
public class Ikona extends Zobrazenie {
    // zmeniť na relatívnu...
    private String baseCesta = "C:\\Users\\Tomas\\Documents\\Skola\\Informa\\Sach\\sprites\\";

    public Ikona(String nazovSuboru) {
        super();
        super.setCesta(baseCesta + nazovSuboru);
    }
}
