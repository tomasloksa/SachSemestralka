package Vykreslenie;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version: 1.6.1 (shapes)
 */


public class Platno {
            
    private static Platno platnoSingleton;

    /**
     * Factory method to get the canvas singleton object.
     */
    public static Platno dajPlatno() {
        if (Platno.platnoSingleton == null) {
            Platno.platnoSingleton = new Platno("Sach", 750, 750, 
                                         Color.white);
        }
        Platno.platnoSingleton.setVisible(true);
        return Platno.platnoSingleton;
    }

    //  ----- instance part -----

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color pozadie;
    private Image canvasImage;
    private Timer timer;
    private List<Object> objekty;
    private HashMap<Object, Ikona> figurky;
    private HashMap<Object, PopisTvaru> tvary;
    
    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgClour  the desired background colour of the canvas
     */
    private Platno(String titulok, int sirka, int vyska, Color pozadie) {
        this.frame = new JFrame();
        this.canvas = new CanvasPane();
        this.frame.setContentPane(this.canvas);
        this.frame.setTitle(titulok);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // PRIDANE
        this.canvas.setPreferredSize(new Dimension(sirka, vyska));
        this.timer = new javax.swing.Timer(25, null);
        this.timer.start();
        this.pozadie = pozadie;
        this.frame.pack();
        this.objekty = new ArrayList<>();
        this.figurky = new HashMap<>();
        this.tvary = new HashMap<>();
    }
    
    public void drawImage(Object object, String cesta, int lavyHornyX, int lavyHornyY, int strana) {
        try {
            boolean redraw = this.objekty.remove(object);
                
            this.objekty.add(object);
            Image image = ImageIO.read(new File(cesta));
            image = image.getScaledInstance(strana, strana, Image.SCALE_DEFAULT);
            figurky.put(object, new Ikona(lavyHornyX, lavyHornyY, image));
            if (redraw) {
                this.redraw();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible) {
        if (this.graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = this.canvas.getSize();
            this.canvasImage = this.canvas.createImage(size.width, size.height);
            this.graphic = (Graphics2D)this.canvasImage.getGraphics();
            this.graphic.setColor(this.pozadie);
            this.graphic.fillRect(0, 0, size.width, size.height);
            this.graphic.setColor(Color.black);
        }
        this.frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  referenceObject  an object to define identity for this shape
     * @param  color            the color of the shape
     * @param  shape            the shape object to be drawn on the canvas
     */
     // Note: this is a slightly backwards way of maintaining the shape
     // objects. It is carefully designed to keep the visible shape interfaces
     // in this project clean and simple for educational purposes.
    public void draw(Object objekt, String farba, Shape tvar) {
        boolean redraw = this.objekty.remove(objekt);   // just in case it was already there
        this.objekty.add(objekt);      // add at the end
        this.tvary.put(objekt, new PopisTvaru(tvar, farba));
        if (redraw) {
            this.redraw();
        }
    }
 
    /**
     * Erase a given shape's from the screen.
     * @param  referenceObject  the shape object to be erased 
     */
    public void erase(Object objekt) {
        this.objekty.remove(objekt);   // just in case it was already there
        this.tvary.remove(objekt);
        this.redraw();
    }

    /**
     * Set the foreground colour of the Canvas.
     * @param  newColour   the new colour for the foreground of the Canvas 
     */
    // UPRAVENE
    public void setForegroundColor(String farba) {
        if (farba.equals("červená")) {
            this.graphic.setColor(Color.red);
        } else if (farba.equals("cierna")) {
            this.graphic.setColor(Color.black);
        } else if (farba.equals("hneda")) {
            this.graphic.setColor(new Color(102,51,0));   
        } else if (farba.equals("bledosiva")) {
            this.graphic.setColor(new Color(232, 235, 239));      
        } else if (farba.equals("tmavosiva")) {
            this.graphic.setColor(new Color(125, 135, 150));
        } else if (farba.equals("zlta")) {
            this.graphic.setColor(Color.yellow.brighter());
        } else if (farba.equals("green")) {
            this.graphic.setColor(Color.green);
        } else if (farba.equals("magenta")) {
            this.graphic.setColor(Color.magenta);
        } else if (farba.equals("biela")) {
            this.graphic.setColor(Color.white);
        } else {
            this.graphic.setColor(Color.black);
        }
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milisekundy) {
        try {
            Thread.sleep(milisekundy);
        } catch (Exception e) {
            System.out.println("Cakanie sa nepodarilo");
        }
    }

    /**
     * * Redraw all shapes currently on the Canvas.
     */
    public void redraw() {
        System.out.println("redrawing");
        this.erase();
        for (var object : this.objekty ) {
            if (!object.getClass().toString().equals("class Vykreslenie.Ikona")) {
                this.tvary.get(object).draw(this.graphic);
            }
        }
        this.canvas.repaint();

        for (var object : this.objekty ) {
            if (object.getClass().toString().equals("class Vykreslenie.Ikona")) {
                this.figurky.get(object).draw();
            }
        }
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase() {
        Color original = this.graphic.getColor();
        this.graphic.setColor(this.pozadie);
        Dimension size = this.canvas.getSize();
        this.graphic.fill(new Rectangle(0, 0, size.width, size.height));
        this.graphic.setColor(original);
    }
    
    public void addMouseListener(MouseListener listener) {
        this.canvas.addMouseListener(listener);
    }
    
    public void addTimerListener(ActionListener listener) {
        this.timer.addActionListener(listener);
    }
    
    public String zobrazInputDialog(String popis) {
        return JOptionPane.showInputDialog(this.frame, popis);
    }

    public static class getPlatno {

        public getPlatno() {
        }
    }


    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel {
        public void paint(Graphics graphic) {
            graphic.drawImage(Platno.this.canvasImage, 0, 0, null);
        }
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class PopisTvaru {
        private Shape tvar;
        private String farba;

        public PopisTvaru(Shape tvar, String farba) {
            this.tvar = tvar;
            this.farba = farba;
        }

        public void draw(Graphics2D graphic) {
            Platno.this.setForegroundColor(this.farba);
            graphic.fill(this.tvar);
        }
    }
    
    private class Ikona {
        private int lavyHornyX, lavyHornyY;
        private Image obrazok;
        
        public Ikona(int lavyHornyX, int lavyHornyY, Image img) {
            this.lavyHornyX = lavyHornyX;
            this.lavyHornyY = lavyHornyY;
            this.obrazok = img;
        }
        
        public void draw() {
            canvas.getGraphics().drawImage(obrazok, lavyHornyX, lavyHornyY, null);
        }
    }
}
