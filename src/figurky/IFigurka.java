/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figurky;

/**
 * Interface pre prístup k figúrkam
 * @author Tomas
 */
public interface IFigurka {
    boolean posunNaPoziciu(int riadok, int stlpec);
    boolean mozeSaPosunut(int riadok, int stlpec);
    boolean jeVyhodena();
    String getFarba();
}
