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

    /**
     * Posunie figúrku na zadanú pozíciu
     * @param riadok riadok, na ktorý sa má posunúť
     * @param stlpec stĺpec, na ktorý sa má posunúť
     * @return true, ak sa figúrka posunula, inak false
     */
    boolean posunNaPoziciu(int riadok, int stlpec);

    /**
     * Zistí, či sa figúrka môže posunúť na danú pozíciu
     * @param riadok riadok, na ktorý sa má posunúť
     * @param stlpec stĺpec, na ktorý sa má posunúť
     * @return true, ak sa posunúť môže, inak false
     */
    boolean mozeSaPosunut(int riadok, int stlpec);

    /**
     * Zistí, či he figúrka vyhodená
     * @return true, ak vyhodená je, inak false
     */
    boolean jeVyhodena();

    /**
     * Vráti farbu figúrky
     * @return String s farbou figúrky
     */
    String getFarba();

    /**
     * Vyhodí figúrku
     */
    void vyhod();
    
    boolean pohlaSa();
}
