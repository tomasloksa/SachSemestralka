/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import vynimky.CasVyprsalException;

/**
 * Trieda hráča s informáciami o ňom
 * @author Tomas
 */
public class Hrac {
    private String farba;
    private String meno;
    private int cas;
    
    /**
     * Vytvorí hráča a nastaví mu parametre
     * @param farba farba hráčových figúrok
     * @param meno meno hráča
     */
    public Hrac(String farba, String meno) {
        this.farba = farba;
        this.meno = meno;
    }

    /**
     * Vráti farbu hráča
     * @return string s farbou hráča
     */
    public String getFarba() {
        return this.farba;
    }

    /**
     * Vráti meno hráča
     * @return string s menom hráča
     */
    public String getMeno() {
        if (!this.meno.equals("")) {
            return this.meno;
        } else {
            if (this.farba.equals("biela")) {
                return "biely";
            } else if (this.farba.equals("cierna")) {
                return "cierny";
            }
        }
        return "";
    }
    
    /**
     * Vráti zostávajúci čas na hráčových hodinách
     * @return int s počtom zvyšných sekúnd
     */
    public int getCas() {
        return this.cas;
    }
    
    /**
     * Nastaví čas pre hráča
     * @param cas int s časom v minútach
     */
    public void setCas(double cas) {
        this.cas = (int)(cas * 60);
    }
    
    /**
     * Odpočíta sekndu z hráčových hodiniek a vyhodí Exception, ak mu došiel čas
     * @throws CasVyprsalException ak sa čas na hodinkách rovná nule
     */
    public void tick() throws CasVyprsalException {
        this.cas--;
        if (this.cas == 0) {
            throw new CasVyprsalException();
        }
    }
}
