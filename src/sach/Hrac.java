/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

/**
 *
 * @author Tomas
 */
public class Hrac {
    private String farba;
    private String meno;
    
    public Hrac() {
        this.farba = new String();
        this.meno = new String();
    }

    public String getFarba() {
        return farba;
    }

    public void setFarba(String farba) {
        this.farba = farba;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }
}
