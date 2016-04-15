package aplicaciones.letras;
import librerias.estructurasDeDatos.deDispersion.TablaHash;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.File;

import java.net.URL;
import java.net.URLConnection;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Cursor;

/** Reconocimiento optico de caracteres:
 *  Se muestra un documento digitalizado en el que aparecen letras de  
 *  diferentes colores y estilos; con el cursor se puede seleccionar una letra 
 *  (imagen) y se muestra la identificación realizada de la misma; 
 *  p.e. letra e de color rosa, en cursiva.
 *  @author EDA
 *  @version Febrero 2016
 */
public class TestOCR extends JFrame implements java.awt.event.ActionListener {
    private static final int NUM_IMGS = 22860;
    private static final int ANCHO = 11;
    private static final int ALTO = 13;
    
    private static final String NOM_URL = Imagen.NOM_URL;
        
    private static final String[] COLORES = {"negro", "marr\u00f3n", "verde", 
        "oliva", "azul marino", "violeta", "cian", "gris claro", "gris oscuro", 
        "rojo", "verde claro", "amarillo", "azul", "rosa", "azul claro"};
        
    private static final String[] ESTILOS = {"normal", "en negrita", 
        "en cursiva", "en negrita y cursiva", "subrayada", 
        "en negrita y subrayada", "en cursiva y subrayada", 
        "en negrita,cursiva y subrayada"};

    private JImagen imagen;
    private JZoom zoom;
    private JTextArea descripcion;
    private TablaHash<Imagen, String> tabla;
    private LEG<DatoLEG> listaLetras;
    private JTextField eficiencia;
    private JRadioButton usarHash, usarLista;
    private long tiempo, busquedas, medidas;
    private boolean usarTablaHash;

    public TestOCR() {
        super("Test OCR - Eda 2015/16");
        setLayout(null);
        setSize(900, 638);
        tiempo = 0; busquedas = 0; medidas = 0;
        usarTablaHash = true;
        zoom = new JZoom();
        zoom.setBounds(790, 20, 44, 52);
        this.add(zoom);
        JLabel lzoom = new JLabel("Imagen seleccionada:");
        lzoom.setBounds(650, 20, 150, 18);
        this.add(lzoom);
        try {
            imagen = new JImagen();
            imagen.setBounds(0, 0, 600, 600);
            this.add(imagen);
            setLocationRelativeTo(null);
            if (cargarImagenes()) {
                JLabel label;
                label = new JLabel("Im\u00e1genes cargadas: " + tabla.talla());
                label.setBounds(650, 150, 200, 18);
                this.add(label);
                label = new JLabel("Resultado de la b\u00fasqueda:");
                label.setBounds(650, 170, 200, 18);
                this.add(label);
                descripcion = new JTextArea();
                descripcion.setBounds(675, 190, 180, 48);
                descripcion.setEditable(false);
                descripcion.setLineWrap(true);
                descripcion.setWrapStyleWord(true);
                descripcion.setText("<No hay coincidencias>");
                this.add(descripcion);
                label = new JLabel("B\u00fasquedas por segundo:");
                label.setBounds(650, 270, 200, 18);
                this.add(label);
                eficiencia = new JTextField("-");
                eficiencia.setBounds(675, 290, 180, 18);
                eficiencia.setEditable(false);
                this.add(eficiencia);
                ButtonGroup group = new ButtonGroup();
                usarHash = new JRadioButton("Usar una Tabla Hash", true); 
                usarHash.setBounds(650, 500, 180, 18);
                this.add(usarHash);
                usarLista = new JRadioButton("Usar una Lista", false); 
                usarLista.setBounds(650, 525, 180, 18);
                this.add(usarLista);
                group.add(usarHash);
                group.add(usarLista);
                usarLista.addActionListener(this);
                usarHash.addActionListener(this);
                setVisible(true);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "No se encontro el fichero: Pagina.bmp");
        }
    }
    
    private boolean cargarImagenes() {
        int n = 0;
        boolean ok = false;
        tabla = new TablaHash<Imagen, String>(NUM_IMGS);
        listaLetras = new LEG<DatoLEG>();
        DataInputStream is = null;
        Splash sp = new Splash();
        try {
            URL url = new URL(NOM_URL);
            URLConnection con = url.openConnection();
            is = new DataInputStream(con.getInputStream());
            // is = new DataInputStream(new FileInputStream("aplicaciones"+
            // File.separatorChar+"letras"+File.separatorChar+"Letras.img"));
            // is=new DataInputStream(new FileInputStream(
            // "aplicaciones/letras/Letras.img"));
            for (int i = 0; i < NUM_IMGS; i++) {
                Imagen img = new Imagen(is, ALTO, ANCHO, 
                    Imagen.SUMA_PONDERADA_TODOS);
                char letra = (char) is.readUnsignedByte();
                int estilo = is.readUnsignedByte();
                int color = is.readUnsignedByte();
                String desc = descripcion(letra, estilo, color);
                if (tabla.insertar(img, desc) == null) {
                    listaLetras.insertar(new DatoLEG(img, desc));
                }
                sp.step();
                n++;
            }
            is.close();
            ok = true;
        } catch (IOException e2) {
            JOptionPane.showMessageDialog(this, 
                "No se encontro el fichero: Letras.img");
        }
        sp.setVisible(false);
        return ok;
    }
    
    private String descripcion(char letra, int estilo, int color) {
        String desc = "Letra '" + letra + "' de color " + COLORES[color];
        if (estilo != 0) { desc += " " + ESTILOS[estilo]; }
        return desc;
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == usarHash) {
            usarTablaHash = true; 
        } else if (e.getSource() == usarLista) {
            usarTablaHash = false;
        }
        tiempo = 0; busquedas = 0; medidas = 0;
        eficiencia.setText("-");
    }
    
    /**
     * Programa principal
     * @param args Argumentos de la linea de comandos (no se utiliza)
     */
    public static void main(String[] args) throws java.io.IOException {
        TestOCR app = new TestOCR();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * 
     */
    private class JImagen extends JPanel implements 
        java.awt.event.MouseMotionListener {
            
        private java.awt.image.BufferedImage image;

        private int lastX, lastY;
        
        private JImagen() throws IOException {
            image = javax.imageio.ImageIO.read(new File("aplicaciones" 
                + File.separatorChar + "letras" + File.separatorChar 
                + "Pagina.bmp"));
            //image = javax.imageio.ImageIO.read(new java.io.File(
            //  "aplicaciones/letras/Pagina.bmp"));
            Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
            BufferedImage cursorImage = new java.awt.image.BufferedImage(32, 
                32, java.awt.image.BufferedImage.TYPE_INT_ARGB_PRE);
            Graphics2D g = (java.awt.Graphics2D) cursorImage.getGraphics();
            g.setColor(java.awt.Color.BLACK);
            g.drawRect(0, 0, 13, 15);
            g.setColor(java.awt.Color.LIGHT_GRAY);
            g.drawRect(1, 1, 11, 13);
            Point cursorHotSpot = new java.awt.Point(0, 0);
            Cursor customCursor = toolkit.createCustomCursor(cursorImage, 
                cursorHotSpot, "Cursor");
            this.setCursor(customCursor);
            this.addMouseMotionListener(this);
            zoom.draw(image, 28, 26);
            lastX = -100; lastY = -100;
        }
        
        protected void paintComponent(java.awt.Graphics g) {
            int x = (getWidth() - 600) / 2;
            int y = (getHeight() - 600) / 2;
            g.drawImage(image, x, y, this);
        }
        
        public void mouseDragged(java.awt.event.MouseEvent e) { }
        
        public void mouseMoved(java.awt.event.MouseEvent e) {
            zoom.draw(image, e.getX(), e.getY());
            if (Math.abs(e.getX() - lastX) > 4
                || Math.abs(e.getY() - lastY) > 4) {
                    
                Imagen[][] img = new Imagen[5][5];
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        img[i][j] = new ImagenOCR(image, e.getX() + i - 2, 
                            e.getY() + j - 2, ALTO, ANCHO, 
                            Imagen.SUMA_PONDERADA_TODOS);
                    }
                }
                
                String desc = null;
                int n = 0;
                long tInicio = System.nanoTime();
                for (int i = 0; i < 5 && desc == null; i++) {
                    for (int j = 0; j < 5 && desc == null; j++) {
                        n++;
                        if (usarTablaHash) { 
                            desc = tabla.recuperar(img[i][j]); 
                        }
                        else {
                            DatoLEG d = listaLetras.buscar(
                                new DatoLEG(img[i][j]));
                            if (d != null) { desc = d.descripcion; }
                        }
                    }
                }
                long tFin = System.nanoTime();
                medidas++;
                if (medidas < 1000 || (medidas < 1500 && medidas % 2 == 0) 
                    || (medidas < 2000 && medidas % 8 == 0)) {
                    tiempo += tFin - tInicio;
                    busquedas += n;
                }
                if (desc == null) {
                    descripcion.setText("<No hay coincidencias>");
                    lastX = -100;
                } else { 
                    descripcion.setText(desc);
                    lastX = e.getX();
                    lastY = e.getY();
                }
                if (tiempo > 0) {
                    double bs = tiempo / (double) busquedas;
                    bs = 1000000000.0 / bs;
                    eficiencia.setText((long) bs + "");
                }
            }
        }
    }
    
    /**
     * 
     */
    private class JZoom extends JPanel {
        private java.awt.image.BufferedImage image;
        
        private JZoom() {
            image = new java.awt.image.BufferedImage(44, 52, 
                java.awt.image.BufferedImage.TYPE_INT_RGB);
        }
        
        protected void paintComponent(java.awt.Graphics g) {
            int x = (getWidth() - 44) / 2;
            int y = (getHeight() - 52) / 2;
            g.drawImage(image, x, y, this);
        }
        
        private void draw(java.awt.Image img, int x, int y) {
            Graphics2D zg = (java.awt.Graphics2D) image.getGraphics();
            zg.drawImage(img, 0, 0, 43, 51, x, y, x + 11, y + 13, null);
            repaint();
        }
    }
    
    /**
     * 
     */
    private class Splash extends JFrame {
        private JProgressBar pbar;
        
        public Splash() {
            super("Cargando im\u00e1genes...");
            setLayout(null);
            setSize(334, 100);
            pbar = new JProgressBar(0, NUM_IMGS);
            pbar.setValue(0);
            pbar.setBounds(10, 20, 300, 18);
            this.add(pbar);
            setLocationRelativeTo(null);
            setVisible(true);
        }
        
        public void step() {
            pbar.setValue(pbar.getValue() + 1);
        }
    }
    
    /**
     * 
     */
    private static class DatoLEG {
        private Imagen imagen;
        private String descripcion;
        
        DatoLEG(Imagen img) { this(img, null); }
        
        DatoLEG(Imagen img, String desc) { 
            imagen = img; descripcion = desc; 
        }
        
        public boolean equals(Object x) { 
            return imagen.equals(((DatoLEG) x).imagen); 
        }
    }
    
    /**
     * 
     */
    private static class NodoLEG<E> {
        private E dato;
        private NodoLEG<E> siguiente;
    
        NodoLEG(E dato, NodoLEG<E> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    
        NodoLEG(E dato) { this(dato, null); }
    }

    /**
     * 
     */
    private static class LEG<E> {
        private NodoLEG<E> primero;
        
        LEG() { primero = null; }

        void insertar(E x) { primero = new NodoLEG<E>(x, primero); }
        
        E buscar(E e) {
            E res = null; 
            for (NodoLEG<E> aux = primero; aux != null; 
                aux = aux.siguiente) {
                    
                if (aux.dato.equals(e)) { res = aux.dato; break; }
            }
            return res;
        }
    }
    
    /**
     * 
     */
    private static class ImagenOCR extends Imagen implements Cloneable {
        /** Metodo para construir una imagen desde una imagen Java, 
         *  a partir de las coordenadas (x,y), 
         *  y de dimension alto x ancho y utilizando como 
         *  funcion de dispersion fhash.
         */   
        public ImagenOCR(java.awt.image.BufferedImage image, int x, int y, 
            int alto, int ancho, int fhash) {
                
            super(alto, ancho);
            java.awt.Color c;
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    try {
                        c = new java.awt.Color(image.getRGB(j + x, i + y));
                    } catch (Exception e) { c = java.awt.Color.BLACK; }
                    pixels[i][j] = 
                        new Pixel(c.getRed(), c.getGreen(), c.getBlue());
                }
            }
            valorHash = obtieneValorHash(fhash);
        }
      
        /** Metodo para devolver una imagen exactamente igual a la actual */
        public Object clone() {
            Imagen img = new Imagen(alto, ancho);
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    img.pixels[i][j] = new Pixel(pixels[i][j].getR(), 
                        pixels[i][j].getG(), pixels[i][j].getB());
                }
            }
            return img;
        }
    }
}