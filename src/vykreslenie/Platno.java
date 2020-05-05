package vykreslenie;

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
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import sach.Hrac;

/**
 * Trieda má na starosti vykresľovanie tvarov/ikon pre užívateľa.
 */


public class Platno {
            
    private static Platno platnoSingleton;

    /**
     * Metóda ktorá vráti inštanciu plátna
     * @return singleton plátna
     */
    public static Platno dajPlatno() {
        if (Platno.platnoSingleton == null) {
            Platno.platnoSingleton = new Platno("Sach", 900, 759, 
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
    private List<String> casy;
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
        this.frame.setPreferredSize(new Dimension(sirka, vyska));
        this.canvas.setPreferredSize(new Dimension(sirka, vyska));
        this.timer = new javax.swing.Timer(25, null);
        this.timer.start();
        this.pozadie = pozadie;
        this.frame.pack();
        this.objekty = new ArrayList<>();
        this.casy = new ArrayList<>();
        this.figurky = new HashMap<>();
        this.tvary = new HashMap<>();
    }
    
    public void drawImage(Object object, String cesta, int lavyHornyX, int lavyHornyY, int strana) {
        try {
            boolean redraw = this.objekty.remove(object);
                
            this.objekty.add(object);
            Image image = ImageIO.read(new File(cesta));
            image = image.getScaledInstance(strana, strana, Image.SCALE_DEFAULT);
            this.figurky.put(object, new Ikona(lavyHornyX, lavyHornyY, image));
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
     * @param  objekt  an object to define identity for this shape
     * @param  farba            the color of the shape
     * @param  tvar           the shape object to be drawn on the canvas
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
     * @param  objekt  the shape object to be erased 
     */
    public void erase(Object objekt) {
        this.objekty.remove(objekt);   // just in case it was already there
        this.tvary.remove(objekt);
        this.redraw();
    }

    /**
     * Set the foreground colour of the Canvas.
     * @param  farba   the new colour for the foreground of the Canvas 
     */
    // UPRAVENE
    public void setForegroundColor(String farba) {
        switch (farba) {
            case "cervena":
                this.graphic.setColor(Color.red);
                break;
            case "cierna":
                this.graphic.setColor(Color.black);
                break;
            case "hneda":
                this.graphic.setColor(new Color(102, 51, 0));
                break;
            case "bledosiva":
                this.graphic.setColor(new Color(232, 235, 239));
                break;
            case "tmavosiva":
                this.graphic.setColor(new Color(125, 135, 150));
                break;
            case "zlta":
                this.graphic.setColor(Color.yellow.brighter());
                break;
            case "green":
                this.graphic.setColor(Color.green);
                break;
            case "magenta":
                this.graphic.setColor(Color.magenta);
                break;
            case "biela":
                this.graphic.setColor(Color.white);
                break;
            default:
                this.graphic.setColor(Color.black);
                break;
        }
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milisekundy  the number 
     */
    public void wait(int milisekundy) {
        try {
            Thread.sleep(milisekundy);
        } catch (InterruptedException e) {
            System.out.println("Cakanie sa nepodarilo");
        }
    }

    /**
     * * Redraw all shapes currently on the Canvas.
     */
    public void redraw() {
        this.erase();
        for (var obj : this.objekty ) {
            if (!(obj instanceof vykreslenie.Ikona)) {
                this.tvary.get(obj).draw(this.graphic);
            }
        }

        for (var obj : this.objekty ) {
            if (obj instanceof vykreslenie.Ikona) {
                this.figurky.get(obj).draw();
            }
        }
        
        this.zobrazCasy();
        this.canvas.repaint();
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
    
    public String zobrazInput(String popis) {
        return JOptionPane.showInputDialog(this.frame, popis);
    }
    
    public void zobrazWarning(String popis) {
        JOptionPane.showMessageDialog(this.frame, popis, "Vystraha", JOptionPane.WARNING_MESSAGE);
    }
    
    public void zobrazCasy() {
        Color original = this.graphic.getColor();
        this.graphic.setColor(Color.white);
        this.graphic.fill(new Rectangle(750, 50, 150, 100));
        this.graphic.setColor(Color.black);
        for (int i = 0; i < this.casy.size(); i++) {
            this.graphic.drawString(this.casy.get(i), 750, 50 + i * 30);
        }
        this.graphic.setColor(original);
        this.canvas.repaint();
    }
    
    public void upravCasy(Hrac hrac1, Hrac hrac2) {
        this.casy.clear();
        this.casy.add(hrac1.getMeno() + " (" + hrac1.getFarba() + ")");
        this.casy.add("Čas: " + hrac1.getCas());
        this.casy.add(hrac2.getMeno() + " (" + hrac2.getFarba() + ")");
        this.casy.add("Čas: " + hrac2.getCas());
        this.zobrazCasy();
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

        PopisTvaru(Shape tvar, String farba) {
            this.tvar = tvar;
            this.farba = farba;
        }

        void draw(Graphics2D graphic) {
            Platno.this.setForegroundColor(this.farba);
            graphic.fill(this.tvar);
        }
    }
    
    private class Ikona {
        private int lavyHornyX;
        private int lavyHornyY;
        private final Image obrazok;
        
        Ikona(int lavyHornyX, int lavyHornyY, Image img) {
            this.lavyHornyX = lavyHornyX;
            this.lavyHornyY = lavyHornyY;
            this.obrazok = img;
        }
        
        void draw() {
            Platno.this.graphic.drawImage(this.obrazok, this.lavyHornyX, this.lavyHornyY, null);
        }
    }
}
