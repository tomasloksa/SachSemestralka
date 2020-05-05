/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vynimky;

/**
 * Výnimka, ktorá sa vyhodí, ak jeden z hráčov dostal mat
 * @author Tomas
 */
public class DostalMatException extends Exception {
    private final String kto;
    
    public DostalMatException(String cierny) {
        this.kto = cierny;
    }
    
    public String kto() {
        return this.kto;
    }
    
}
