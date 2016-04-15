package librerias.estructurasDeDatos.jerarquicos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
  
/**
 * Test de prueba para la clase ABB
 * 
 * @author Profesores de EDA'13-14 
 * @version Marzo 2016
 */

public class TestABB extends JFrame implements ActionListener {
    private ABB<Integer> tree;                                   // ABB
    private HashMap<NodoABB<Integer>, 
                    Rectangle> nodeLocations;  // Posicion de los nodos
    private HashMap<NodoABB<Integer>, 
                    Dimension> subtreeSizes;   // Dimensiones de los nodos
    private boolean dirty = true;              // Recalcular posiciones
    private int parent2child = 15, 
                child2child = 24;              // Espacio entre nodos
    private Dimension empty = new Dimension(0, 0);    // Nodos vacios
    private FontMetrics fm = null;                    // Medidas del texto
    private JMenuBar barra;                           // Barra de menu
    private JButton btnEquilibrado, btnDegenerado, 
            btnAleatorio;                       // Botones del menu
    private JButton btnInsertar, btnElim, btnEMin;
    private JButton btnReconstruir, btnIn, btnPre, btnPost, btnNiv;
    
    /** 
     * Constructor
     */
    public TestABB() {
        super("Test de Arbol Binario de Busqueda");
        this.tree = new ABB<Integer>();
        nodeLocations = new HashMap<NodoABB<Integer>, Rectangle>();
        subtreeSizes = new HashMap<NodoABB<Integer>, Dimension>();
        this.setLayout(new BorderLayout());
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());        
        barra = new JMenuBar();
        barra.setBorder(BorderFactory.createBevelBorder(
            javax.swing.border.SoftBevelBorder.RAISED));
        crearMenuSuperior();
        bottomPanel.add(barra, BorderLayout.NORTH);
        barra = new JMenuBar();
        barra.setBorder(BorderFactory.createBevelBorder(
            javax.swing.border.SoftBevelBorder.RAISED));
        crearMenuInferior();
        bottomPanel.add(barra, BorderLayout.SOUTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        setSize(840, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        repaint();
    }
    
    /** 
     * Menu superior de la aplicacion
     */
    private void crearMenuSuperior() {
        barra.add(new JSeparator(SwingConstants.HORIZONTAL));
        btnEquilibrado = crearBoton("Generar ABB equilibrado", Color.black);
        btnDegenerado = crearBoton("Generar ABB degenerado", Color.black);
        btnAleatorio = crearBoton("Generar ABB aleatorio", Color.black);
        barra.add(new JSeparator(SwingConstants.HORIZONTAL));
        btnElim = crearBoton("Eliminar", Color.red);
        btnEMin = crearBoton("EliminarMin", Color.red);
        btnInsertar = crearBoton("Insertar", Color.darkGray);
        barra.add(new JSeparator(SwingConstants.HORIZONTAL));
    }
    
    /** 
     * Menu inferior de la aplicacion
     */
    private void crearMenuInferior() {
        barra.add(new JSeparator(SwingConstants.HORIZONTAL));
        btnReconstruir = crearBoton("Reconstruir equilibrado", Color.blue);
        barra.add(new JSeparator(SwingConstants.HORIZONTAL));
        btnIn = crearBoton("In-Orden", Color.green);
        btnPre = crearBoton("Pre-Orden", Color.green);
        btnPost = crearBoton("Post-Orden", Color.green);
        btnNiv = crearBoton("Por niveles", Color.green);
        barra.add(new JSeparator(SwingConstants.HORIZONTAL));
    }
    
    /** 
     * Crea un boton del menu
     * @param  texto Etiqueta del boton
     * @param  c     Color del boton
     * @return Nuevo boton creado 
     */
    private JButton crearBoton(String texto, Color c) {
        JButton aux = new JButton(texto);
        aux.setForeground(c);
        aux.addActionListener(this);
        barra.add(aux);
        return aux;
    }

    /** 
     * Pulsacion de los botones
     * @param e Evento
     */
    public void actionPerformed(ActionEvent e) {
        Integer s;
        try {
            if (e.getSource() == btnEquilibrado) {
                generarABBEquilibrado();
            } else if (e.getSource() == btnDegenerado) {
                generarABBDegenerado();
            } else if (e.getSource() == btnAleatorio) {
                generarABBAleatorio();
            } else if (e.getSource() == btnElim) {
                s = leerNodo("Escribe el dato a eliminar");
                if (s != null) { tree.eliminar(s); }
            } else if (e.getSource() == btnEMin) {
                s = tree.eliminarMin();
                JOptionPane.showMessageDialog(this, "M\u00ednimo = " 
                    + s.toString(), "Resultado", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == btnInsertar) { 
                s = leerNodo("Escribe el dato a insertar");
                if (s != null) { tree.insertar(s); }
            } else if (e.getSource() == btnReconstruir) {
                tree.reconstruirEquilibrado();
            } else if (e.getSource() == btnIn) {
                JOptionPane.showMessageDialog(this, tree.toStringInOrden(), 
                    "Recorrido In-Orden", JOptionPane.PLAIN_MESSAGE);
            } else if (e.getSource() == btnPre) {
                JOptionPane.showMessageDialog(this, tree.toStringPreOrden(), 
                    "Recorrido Pre-Orden", JOptionPane.PLAIN_MESSAGE);
            } else if (e.getSource() == btnPost) {
                JOptionPane.showMessageDialog(this, tree.toStringPostOrden(), 
                    "Recorrido Post-Orden", JOptionPane.PLAIN_MESSAGE);
            } else if (e.getSource() == btnNiv) {
                JOptionPane.showMessageDialog(this, tree.toStringPorNiveles(), 
                    "Recorrido por niveles", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex, 
                "Excepci\u00f3n detectada:", JOptionPane.ERROR_MESSAGE);
        }
        dirty = true;
        repaint();
    }
    
    /** 
     * Genera un ABB equilibrado
     */
    private void generarABBEquilibrado() {
        tree = new ABB<Integer>();
        int[] v = generarVectorAleatorio(15);
        Arrays.sort(v);
        insertarEquilibrado(v, 0, v.length - 1);
    }
    
    /** 
     * Genera un ABB degenerado
     */
    private void generarABBDegenerado() {
        tree = new ABB<Integer>();
        int[] v = generarVectorAleatorio(10);
        Arrays.sort(v);
        for (int i = 0; i < v.length; i++) {
            tree.insertar(v[i]);
        }
    }
    
    /** 
     * Genera un ABB aleatrorio
     */
    private void generarABBAleatorio() {
        Random rnd = new Random();
        do {
            tree = new ABB<Integer>();
            int talla = rnd.nextInt(26) + 5;
            int[] v = generarVectorAleatorio(talla);
            for (int i = 0; i < v.length; i++) {
                tree.insertar(v[i]);
            }
        } while (altura(tree.raiz) > 10);
    }
    
    /** 
     * Devuelve la altura de un nodo
     * @param <E>       Tipo genérico del nodo
     * @param actual    Nodo del que se quiere conocer su altura
     * @return Altura del nodo actual, o -1 si actual es null
     */
    private static <E> int altura(NodoABB<E> actual) {
        if (actual == null) { return -1; }
        return 1 + Math.max(altura(actual.izq), altura(actual.der));
    }
    
    /** 
     * Genera un vector aleatorio de numeros enteros
     * @param talla Longitud del vector
     * @return Vector aleatorio de numeros enteros
     */ 
    private int[] generarVectorAleatorio(int talla) {
        Random rnd = new Random();
        boolean[] exists = new boolean[100];
        int[] v = new int[talla];
        int i = 0;
        while (i < v.length) {
            v[i] = rnd.nextInt(100);
            if (!exists[v[i]]) {
                exists[v[i]] = true;
                i++;
            }
        }
        return v;
    }
    
    /** 
     * Inserta los datos del vector ordenado v de forma que 
     * el ABB quede equilibrado
     * @param v Vector ordenado de enteros
     * @param izq   Posicion inicial del intervalo
     * @param der   Posicion final del intervalo
     */
    private void insertarEquilibrado(int[] v, int izq, int der) {
        if (izq <= der) {
            int m = (izq + der) / 2;
            tree.insertar(v[m]);
            insertarEquilibrado(v, izq, m - 1);
            insertarEquilibrado(v, m + 1, der);
        }
    }
    
    /** 
     * Lee un entero
     * @param texto Texto del mensaje
     * @return Numero entero leido
     */
    private Integer leerNodo(String texto) {
        String c;
        Integer n = null;
        do {
            c = JOptionPane.showInputDialog(texto);
            if (c != null) {
                try {
                    n = Integer.parseInt(c);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, 
                        "No has escrito un n \u00FAmero entero v\u00E1lido", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (n == null && c != null);
        return n;
    }

    /** 
     * Calculo de la posicion de los nodos
     */
    private void calculateLocations() {
        nodeLocations.clear();
        subtreeSizes.clear();
        NodoABB<Integer> root = tree.raiz;
        if (root != null) {
            calculateSubtreeSize(root);
            calculateLocation(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
    }

    /** 
     * Calculo del tamanyo del subarbol de raiz n
     * @param n Nodo a calcular
     * @return Dimensiones del nodo
     */
    private Dimension calculateSubtreeSize(NodoABB<Integer> n) {
        if (n == null) { return new Dimension(0, 0); }
        Dimension ld = calculateSubtreeSize(n.izq);
        Dimension rd = calculateSubtreeSize(n.der);
        int h = 2 * fm.getHeight() + parent2child 
            + Math.max(ld.height, rd.height);
        int w = ld.width + child2child + rd.width;
        Dimension d = new Dimension(w, h);
        subtreeSizes.put(n, d);
        return d;
    }
    
    /** 
     * Calculo de la posicion de los nodos en el subarbol de raiz n
     * @param n Nodo a calcular
     * @param left Posicion izquierda
     * @param right Posicion derecha
     * @param top   Posicion superior
     */
    private void calculateLocation(NodoABB<Integer> n, int left, int right, 
        int top) {
        if (n == null) { return; }
        Dimension ld = (Dimension) subtreeSizes.get(n.izq);
        if (ld == null) { ld = empty; }
        Dimension rd = (Dimension) subtreeSizes.get(n.der);
        if (rd == null) { rd = empty; }
        int center = 0;
        if (right != Integer.MAX_VALUE) {
            center = right - rd.width - child2child / 2;
        } else if (left != Integer.MAX_VALUE) {
            center = left + ld.width + child2child / 2;
        }
        int width = fm.stringWidth(n.dato + "");
        Rectangle r = new Rectangle(center - width / 2 - 3, 
            top + 25, width + 6, fm.getHeight());
        nodeLocations.put(n, r);
        calculateLocation(n.izq, Integer.MAX_VALUE, center - child2child / 2, 
            top + 2 * fm.getHeight() + parent2child);
        calculateLocation(n.der, center + child2child / 2, Integer.MAX_VALUE, 
            top + 2 * fm.getHeight() + parent2child);
    }

    /** 
     * Dibujo del arbol
     * @param g Area grafica
     * @param n Nodo a dibujar
     * @param px Posicion horizontal
     * @param py Posicion vertical
     * @param yoffs Incremento vertical
     */
    private void drawTree(Graphics2D g, NodoABB<Integer> n, int px, 
        int py, int yoffs) {
        if (n == null) { return; }
        Rectangle r = (Rectangle) nodeLocations.get(n);
        g.draw(r);
        g.drawString(n.dato + "", r.x + 3, r.y + yoffs);
        if (px != Integer.MAX_VALUE) {
            g.drawLine(px, py, r.x + r.width / 2, r.y);
        }
        drawTree(g, n.izq, r.x + r.width / 2, r.y + r.height, yoffs);
        drawTree(g, n.der, r.x + r.width / 2, r.y + r.height, yoffs);
    }
  
    /**
     * Redibujado del area grafica
     * @param g Area grafica
     */
    public void paint(Graphics g) {
        super.paint(g);
        fm = g.getFontMetrics();
        g.drawString(String.format("Tama\u00f1o = %d", tree.talla()), 10, 78);
        if (dirty) {
            calculateLocations();
            dirty = false;
        }    
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = g2d.getRenderingHints();
        rh.put(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.translate(getWidth() / 2, parent2child);
        drawTree(g2d, tree.raiz, Integer.MAX_VALUE, Integer.MAX_VALUE, 
            fm.getLeading() + fm.getAscent());
        fm = null;    
    }

    /** 
     * Programa principal
     * @param args  Argumentos del programa
     */ 
    public static void main(String[] args) {  
        TestABB app = new TestABB();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}