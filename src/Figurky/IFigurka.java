/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figurky;

/**
 *
 * @author Tomas
 */
public interface IFigurka {
    public boolean posunNaPoziciu(int riadok, int stlpec);
    public boolean mozeSaPosunut(int riadok, int stlpec);
    public String getFarba();
    
}
