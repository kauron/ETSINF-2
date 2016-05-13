package aplicaciones.municipios;

import java.awt.Panel;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.grafos.Adyacente;

/** Aplicacion de prueba, en modo grafico, del gestor de grafos de municipios
  * @author (profesores EDA) 
  * @version (Curso 2015/16)
  */
public class GuiGrafo extends JFrame 
    implements ActionListener, MouseMotionListener, MouseListener  {
        
    // Nombre del fichero con la imagen del mapa
    private static final String MAP_FILE = "aplicaciones" + File.separator 
        + "municipios" + java.io.File.separator + "spain.jpg";
    
    private static final String NO_MAP_MSG = 
        "No se pudo cargar la imagen del mapa";
    private static final String INTRO_MSG = 
        "Introduzca los municipios origen y destino y pulse el bot\u00f3n "
        + "para mostrar el camino m\u00ednimo entre ambos municipios.";
    
    // Gestor de grafos de municipios
    private GestorGrafoMunicipios gg;
    
    // Municipios principales
    private ArrayList<Municipio> municipiosPrincipales;
    
    // Elementos de la interfaz grafica
    // Paneles
    private Panel panelMunicipios;
    // Campos de texto
    private TextField tfOrigen, tfDestino;
    private JTextArea taMsgs;
    // Botones
    private Button btCalcular;
    // Componente para la gestion del mapa    
    private MapComponent map;
    // Fuentes para el texto    
    private Font fuenteTitulos, fuenteNormal;
    // Coordenadas del raton sobre el mapa
    private int mouseX, mouseY;
   

    /** Inicializa el gestor de municipios y la interfaz grafica */            
    public GuiGrafo() {
        super("Interfaz grafica del grafo de municipios: EDA 2015/2016");
        gg = new GestorGrafoMunicipios();
        municipiosPrincipales = new ArrayList<Municipio>();
        for (int i = 0; i < gg.numMunicipios(); i++) {
            if (gg.getMunicipio(i).getPoblacion() > 300000) {
                municipiosPrincipales.add(gg.getMunicipio(i));
            }
        }
        
        // Configuracion de la ventana
        setLayout(null);
        setBackground(Color.lightGray);
        setSize(1010, 618);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // Fuentes
        fuenteTitulos = new Font("ARIAL", Font.BOLD, 12);
        fuenteNormal = new Font("ARIAL", Font.PLAIN, 11);
        
        // Paneles, definicion de tamanyos, posiciones y caracteristicas
        panelMunicipios = new Panel(); 
        panelMunicipios.setBounds(4, 4, 192, 582);
        panelMunicipios.setLayout(null);
        panelMunicipios.setBackground(Color.GRAY);
        add(panelMunicipios);
        inicializarPanelMunicipios();              
                        
        // Cargar la imagen del mapa
        try {
            BufferedImage mapImage = ImageIO.read(new File(MAP_FILE));
            map = new MapComponent(mapImage);
            map.setBounds(200, 4, 999, 603);            
            map.addMouseMotionListener(this);
            map.addMouseListener(this);            
            add(map);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, NO_MAP_MSG, "Error", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }        
    }
    
    //Inicializa el panel donde se muestra la informacion sobre el camino minimo
    private void inicializarPanelMunicipios() {
        // Etiquetas y campos de texto
        crearEtiqueta("CAMINO MiNIMO", 4, 4, 120, 16, panelMunicipios, true);
        crearEtiqueta("Origen:", 4, 24, 60, 40, panelMunicipios, false);
        tfOrigen = crearCampoTexto(60, 34, 125, 20, panelMunicipios);
        crearEtiqueta("Destino:", 4, 48, 60, 40, panelMunicipios, false);
        tfDestino = crearCampoTexto(60, 58, 125, 20, panelMunicipios);
        crearEtiqueta("RESULTADO:", 4, 130, 120, 16, panelMunicipios, true);
        // Botones
        btCalcular = crearBoton("Calcular", 60, 90, 90, 22, panelMunicipios);
        // Panel de resultados
        taMsgs = new JTextArea(INTRO_MSG);
        taMsgs.setFont(fuenteNormal);
        taMsgs.setEditable(false);
        taMsgs.setLineWrap(true);
        taMsgs.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(taMsgs);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(4, 150, 185, 424);
        panelMunicipios.add(scrollPane);
    }

    // Crea una etiqueta de texto
    // text    Texto a mostrar en la etiqueta
    // @param   x       Coordenada horizontal de la etiqueta
    // @param   y       Coordenada vertical de la etiqueta
    // @param   width   Anchura en pixels de la etiqueta
    // @param   height  Altura en pixels de la etiqueta
    // @param   p       Panel que contendra la etiqueta
    // @param   title   True si es una etiqueta de titulo (fuente mas grande)
    private JLabel crearEtiqueta(String text, int x, int y, int width,  
        int height, Panel p, boolean title) {
        JLabel label = new JLabel(text);
        if (title) { label.setFont(fuenteTitulos); }
        else { label.setFont(fuenteNormal); }
        label.setBounds(x, y, width, height);
        p.add(label);
        return label;
    }

    //Crea un campo de texto
    // @param   x       Coordenada horizontal del campo de texto
    // @param   y       Coordenada vertical del campo de texto
    // @param   width   Anchura en pixels del campo de texto
    // @param   height  Altura en pixels del campo de texto
    // @param   p       Panel que contendra el campo de texto        
    private TextField crearCampoTexto(int x, int y, int width, 
        int height, Panel p) { 
        TextField text = new TextField();
        text.setBounds(x, y, width, height);
        p.add(text);
        return text;
    }
    
    // Crea un boton
    // @param   texto   Texto a mostrar en el boton
    // @param   x       Coordenada horizontal del boton
    // @param   y       Coordenada vertical del boton
    // @param   width   Anchura en pixels del boton
    // @param   height  Altura en pixels del boton
    // @param   p       Panel que contendra el boton   
    private Button crearBoton(String texto, int x, int y, int width, 
        int height, Panel p) { 
        Button b = new Button(texto);        
        b.setBounds(x, y, width, height);
        b.setBackground(Color.LIGHT_GRAY);  
        b.addActionListener(this);
        p.add(b);
        return b;
    }
    
    // Muestra un mensaje en el area de resultados de las acciones realizadas
    // @param   msg     Mensaje a mostrar       
    private void mensaje(String msg) {
        taMsgs.append("\n" + msg);
        taMsgs.setCaretPosition(taMsgs.getDocument().getLength());
    }
    
    /** Evento que ocurre al pulsar un boton: se invoca al metodo asociado
     *  al boton pulsado
     * @param   a   Informacion relativa al evento
     */            
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == btCalcular) {
            taMsgs.setText("");
            Municipio mOrig = new Municipio(tfOrigen.getText().toLowerCase());
            Municipio mDst = new Municipio(tfDestino.getText().toLowerCase());
            if (!gg.existeMunicipio(mOrig)) {
                taMsgs.append("No se encuentra el municipio origen");
            } else if (!gg.existeMunicipio(mDst)) {
                taMsgs.append("No se encuentra municipio destino");
            } else { caminoMinimo(mOrig, mDst); }
        }
    }
    
    // Calcula y muestra el camino minimo entre dos municipios
    // @param  y   a sample parameter for a method
    // @return     the sum of x and y    
    private void caminoMinimo(Municipio mOrig, Municipio mDst) {
        double distancia = 0.0;
        ListaConPI<Municipio> cMin = gg.caminoMinimo(mOrig, mDst);
        int i = 0;
        Municipio ant = null;
        for (cMin.inicio(); !cMin.esFin(); cMin.siguiente(), i++) {
            taMsgs.append((i + 1) + ". " + cMin.recuperar().getNombre() + "\n");
            if (i > 0) { distancia += gg.distancia(ant, cMin.recuperar()); }
            ant = cMin.recuperar();
        }
        distancia = ((int) (distancia * 100)) / 100.0;
        taMsgs.append("---\n" + distancia + " km.");
        map.dibujarCamino(cMin);
    }
    
    /**Evento que ocurre al hacer click con el raton
     * @param   e   Informacion relativa al evento
     */        
    public void mouseClicked(MouseEvent e) {   }

    /**Evento que ocurre cuando el cursor del raton entra en el marco 
     * de la aplicacion
     * @param   e   Informacion relativa al evento
     */    
    public void mouseEntered(MouseEvent e) {   }

    /** Evento que ocurre cuando el cursor del raton sale del marco 
     * de la aplicacion
     * @param   e   Informacion relativa al evento
     */        
    public void mouseExited(MouseEvent e) {    }

    /**Evento que ocurre al pulsar un boton del raton
     * Boton izquierdo: hacemos zoom sobre el mapa si el raton esta sobre los 
     *                  botones de zoom
     * Boton derecho: mostramos la informacion del municipio sobre el que se 
     *                encuentra el raton
     * @param   e   Informacion relativa al evento
     */        
    public void mousePressed(MouseEvent e) {        
        if (e.getButton() == MouseEvent.BUTTON1) {            
            mouseX = e.getX();
            mouseY = e.getY();
            if (mouseX > 8 && mouseX < 28 && mouseY > 553 && mouseY < 573) { 
                map.zoomOut(); 
            } else if (mouseX > 32 && mouseX < 52 
                && mouseY > 553 && mouseY < 573) {
                map.zoomIn(); 
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            Municipio m = null;
            int x = e.getX(), y = e.getY();
            for (int i = 1; i <= gg.numMunicipios() && m == null; i++) {
                Municipio aux = gg.getMunicipio(i);
                if (map.municipioSeleccionado(aux, x, y)) { m = aux; }
            }
            if (m != null) {
                JOptionPane.showMessageDialog(this, m.getNombre() 
                    + "\nPob.: " + m.getPoblacion() + " hab.\nExt.: "
                    + m.getExtension() 
                    + " km2", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**Evento que ocurre al soltar un boton del raton
     * @param   e   Informacion relativa al evento
     */        
    public void mouseReleased(MouseEvent e) {   }
    
    /** Evento que ocurre al mover el raton
     * @param   e   Informacion relativa al evento
     */
    public void mouseMoved(MouseEvent e) {     }
    
    /** Evento que ocurre al mover el raton manteniendo un boton 
     * del raton pulsado
     * Nos desplazamos por el mapa
     * @param   e   Informacion relativa al evento
     */
    public void mouseDragged(MouseEvent e) {
        int incX = mouseX - e.getX();
        int incY = mouseY - e.getY();
        mouseX = e.getX();
        mouseY = e.getY();
        map.moveZoom(incX, incY);        
    }
    
         
    /**Metodo principal: crea la interfaz grafica de la aplicacion
     * @param  args   Argumentos de la linea de comandos (no se utiliza)
     */        
    public static void main(String[] args) {
        GuiGrafo guiMunicipios = new GuiGrafo();
        guiMunicipios.setVisible(true);        
    }
    
    /** Componente para la gestion del mapa */
    class MapComponent extends JComponent {
        
        // Camino a mostrar
        Municipio[] camino;
        // Imagen que contiene el mapa
        private BufferedImage map;
        // Muestra todas las aristas del grafo si esta a true
        private boolean drawAll;
        // Nivel de zoom
        private double zoom;
        // Coordenadas origen actuales del mapa
        private int zoomX, zoomY;
        
        /**Constructor: inicializa el mapa
         * @param  map   Imagen que contiene el mapa
         */        
        MapComponent(BufferedImage m) {            
            this.map = m;
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            drawAll = true;
            zoomX = 0; zoomY = 0;
            zoom = 1.0;
            camino = null;
        }

        /**Devuelve las dimensiones del mapa
         * @return      Dimensiones preferidas del componente
         */
        public Dimension getPreferredSize() {
            Insets insets = getInsets();
            int w = insets.left + insets.right + map.getWidth();
            int h = insets.top + insets.bottom + map.getHeight();
            return new Dimension(w, h);
        }

        /** Incrementa el nivel de zoom del mapa */        
        public void zoomOut() {
            if (zoom > 1.0) {
                zoom = zoom / 2.0;
                if (zoom < 1.0) { zoom = 1.0; }
                int w = (int) (map.getWidth() / zoom);
                int h = (int) (map.getHeight() / zoom);
                if (zoomX + w > map.getWidth()) {
                    zoomX = map.getWidth() - w - 1;
                } else if (zoomX < 0) { zoomX = 0; }
                if (zoomY + h > map.getHeight()) {
                    zoomY = map.getHeight() - h - 1;
                } else if (zoomY < 0) { zoomY = 0; }
                repaint();
            }
        }        
        
        /** Reduce el nivel de zoom del mapa */
        public void zoomIn() {
            if (zoom < 5.0) {
                zoom = zoom + 1.0;
                if (zoom > 5.0) { zoom = 5.0; }
                int w = (int) (map.getWidth() / zoom);
                int h = (int) (map.getHeight() / zoom);
                if (zoomX + w > map.getWidth()) {
                    zoomX = map.getWidth() - w - 1;
                } else if (zoomX < 0) { zoomX = 0; }
                if (zoomY + h > map.getHeight()) {
                    zoomY = map.getHeight() - h - 1;
                } else if (zoomY < 0) { zoomY = 0; }
                repaint();
            }
        }
        
        /** Desplaza el mapa */
        public void moveZoom(int incX, int incY) {
            if (zoom != 1.0) {
                zoomX += incX;
                zoomY += incY;
                int w = (int) (map.getWidth() / zoom);
                int h = (int) (map.getHeight() / zoom);
                if (zoomX + w > map.getWidth()) {
                    zoomX = map.getWidth() - w - 1;
                } else if (zoomX < 0) { zoomX = 0; }
                if (zoomY + h > map.getHeight()) {
                    zoomY = map.getHeight() - h - 1;
                } else if (zoomY < 0) { zoomY = 0; }
                repaint();
            }
        }
        
        /**Dibuja el camino que recibe como parametro
         * @param camino    Array de municipios que conforman el camino
         */
        public void dibujarCamino(ListaConPI<Municipio> c) {
            int n = 0;
            for (c.inicio(); !c.esFin(); c.siguiente()) { n++; }
            c.inicio();
            camino = new Municipio[n];
            int i = 0;
            for (c.inicio(); !c.esFin(); c.siguiente(), i++) {
                camino[i] = c.recuperar();
            }
            if (c.esVacia()) {
                zoomX = 0; zoomY = 0;
                zoom = 1.0;
                drawAll = true;
            } else {
                drawAll = false;
                int minX, minY, maxX, maxY, w, h;
                minX = camino[0].getPosX(); maxX = minX;
                minY = camino[0].getPosY(); maxY = minY;
                for (i = 1; i < camino.length; i++) {
                    int px = camino[i].getPosX(), py = camino[i].getPosY();
                    if (px < minX) { minX = px; }
                    else if (px > maxX) { maxX = px; }
                    if (py < minY) { minY = py; }
                    else if (py > maxY) { maxY = py; }
                }
                double ar = map.getWidth() / (double) map.getHeight();                
                if (maxX - minX < ar * (maxY - minY)) {
                    h = maxY - minY + 20;
                    w = (int) (h * ar);
                } else {
                    w = maxX - minX + 30;
                    h = (int) (w / ar);
                }
                zoom = map.getWidth() / w;
                if (zoom <= 1.2) {
                    zoom = 1.0;
                    zoomX = 0; zoomY = 0;
                } else {
                    if (zoom > 5.0) {
                        w = (int) ((w / 5.0) * zoom);
                        h = (int) ((h / 5.0) * zoom);
                        zoom = 5.0;
                    }
                    zoomY = (int) ((maxY + minY) / 2.0 - h / 2.0);
                    zoomX = (int) ((maxX + minX) / 2.0 - w / 2.0);
                    if (zoomX < 0) { zoomX = 0; }               
                    if (zoomY < 0) { zoomY = 0; }
                }
            }
            repaint();
        }
        
        // Transforma la coordenada horizontal del mapa en coordenada  
        // horizontal de la pantalla
        // @param  x   Coordenada horizontal del mapa
        // @return     Coordenada horizontal de la pantalla       
        private int posX(int x) { return (int) ((x - zoomX) * zoom); }
           
       // Transforma la coordenada vertical del mapa en coordenada  
       // vertical de la pantalla
       // @param  x   Coordenada vertical del mapa
       // @return     Coordenada vertical de la pantalla
        private int posY(int y) { return (int) ((y - zoomY) * zoom); }
        
        // Dibuja los botones de zoom sobre el mapa
        // @param  g   Superficie de dibujo
        private void paintZoomButtons(Graphics g) {
            g.setColor(Color.blue);
            g.fillRoundRect(8, 553, 20, 20, 6, 6);
            g.fillRoundRect(32, 553, 20, 20, 6, 6);
            g.setColor(Color.white);
            g.drawString("-", 17, 567);
            g.drawString("+", 38, 567);
        }
        
        /** Comprueba si un municipio esta en las coordenadas especificadas
         * @param  m        Municipio a comprobar
         * @param  mouseX   Coordenada horizontal
         * @param  mouseY   Coordenada vertical
         * @return True si el municipio esta en dichas coordenadas
         */
        public boolean municipioSeleccionado(Municipio m, int mX, int mY) {
            boolean sel = false;
            int posX = posX(m.getPosX());
            if (Math.abs(posX - mX) < 6) {
                int posY = posY(m.getPosY());
                sel = Math.abs(posY - mY) < 6;
            }            
            return sel;
        }
        
        // Dibuja un municipio sobre el mapa
        // @param   g   Superficie de dibujo
        // @param   m   Municipio a mostrar
        private void dibujarMunicipio(Graphics g, Municipio m) {
            int posX = posX(m.getPosX());  
            int posY = posY(m.getPosY());
            g.setColor(Color.BLACK);
            g.drawOval(posX - 7, posY - 7, 13, 13); 
            g.setColor(Color.RED);
            g.fillOval(posX - 4, posY - 4, 8, 8);
            g.setColor(Color.BLACK);
            g.drawString(m.getNombre(), posX + 10, posY + 10);
        }
        
        /**Procedimiento de dibujado del mapa
         * @param   g   Superficie de dibujo
         */        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                                 RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            int w = (int) (map.getWidth() / zoom);
            int h = (int) (map.getHeight() / zoom);            
            g.drawImage(map, 0, 0, map.getWidth() - 1, map.getHeight() - 1, 
                zoomX, zoomY, zoomX + w, zoomY + h, null);
            if (drawAll) {
                float red;
                for (int i = 0; i < gg.numMunicipios(); i++) {
                    Municipio m1 = gg.getMunicipio(i);
                    ListaConPI<Adyacente> ady = gg.adyacentesDe(i);
                    for (ady.inicio(); !ady.esFin(); ady.siguiente()) {
                        Municipio m2 = gg.getMunicipio(ady.recuperar().getDestino());
                        red = (m1.getPoblacion() + m2.getPoblacion()) / 10000.0f;
                        if (red > 1.0f) { red = 1.0f; }
                        red /= 4.0f;
                        g.setColor(new Color(0.6f - red, 0.5f - red, 0.6f));
                        g.drawLine(posX(m1.getPosX()), 
                                   posY(m1.getPosY()), 
                                   posX(m2.getPosX()), 
                                   posY(m2.getPosY()));
                    }
                }
                for (int i = 0; i < municipiosPrincipales.size(); i++) {
                    dibujarMunicipio(g, municipiosPrincipales.get(i));
                }
            } else if (camino != null) {                
                int posX, posY;
                g.setColor(Color.BLACK);
                for (int i = 1; i < camino.length; i++) {
                    posX = camino[i - 1].getPosX();
                    posY = camino[i - 1].getPosY();
                    g.drawLine(posX(posX), 
                               posY(posY), 
                               posX(camino[i].getPosX()), 
                               posY(camino[i].getPosY()));
                }
                for (int i = 0; i < camino.length; i++) {
                    dibujarMunicipio(g, camino[i]);
                }
            }
            paintZoomButtons(g);
        }               
    }
}
