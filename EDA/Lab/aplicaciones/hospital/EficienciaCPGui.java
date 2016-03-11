package aplicaciones.hospital;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.lineales.LPIColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.PriorityQColaPrioridad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.util.ArrayList;

/**
 * Test de eficiencia
 * @author Oscar Sapena Vercher
 * @since Febrero 2012
 * // versionado para pasar el checkstyle el 10/02/2016
 **/
public class EficienciaCPGui extends JFrame implements ActionListener {
    /** Atributos de la interfaz */
    private static final int NUM_TICKS = 10;
    private static       int NUM_CPS = 64;
    private static final int NUM_REP = 200;
    private static final int NUM_REP1 = 10, NUM_REP2 = 50;
    private static final int INC_TALLA = 1000;
    
    private Font fontNormal = new Font("TimesRoman", Font.PLAIN, 12);
    private Font fontItalic = new Font("TimesRoman", Font.ITALIC, 12);
    private Font fontNormalBig = new Font("TimesRoman", Font.PLAIN, 14);
    private Font fontBold = new Font("TimesRoman", Font.BOLD, 14);
    private Font fontCode = new Font("Courier New", Font.PLAIN, 14);
    private Font fontBoldCode = new Font("Courier New", Font.BOLD, 14);
    private Font fontComic = new Font("Comic Sans MS", Font.ITALIC, 12);
    private javax.swing.JLabel labelDiscos;
    private javax.swing.JButton[] jButtonCronometrar;
    private Grafica[] grafica;
    
    /**
     * Constructor de la clase
     */         
    public EficienciaCPGui() {
        getContentPane().setLayout(null);
        jButtonCronometrar = new javax.swing.JButton[4];
        for (int i = 0; i < 4; i++) {
            jButtonCronometrar[i] = new javax.swing.JButton("Generar");
        }
        jButtonCronometrar[0].setBounds(270, 2, 100, 24); 
        // 250 era la x 2 es la Y 120 era ancho 24 es alto
        jButtonCronometrar[1].setBounds(670, 2, 100, 24); 
        // 650 era la x 2 es la Y 120 era ancho 24 es alto
        jButtonCronometrar[2].setBounds(270, 360, 100, 24); 
        // 250 era la x 360 es la Y 120 era ancho 24 es alto
        jButtonCronometrar[3].setBounds(670, 360, 100, 24);
        for (int i = 0; i < 4; i++) {
            jButtonCronometrar[i].setFont(fontNormalBig);
            jButtonCronometrar[i].addActionListener(this);
            getContentPane().add(jButtonCronometrar[i]);
        }
        grafica = new Grafica[4];
        grafica[0] = new Grafica(360, 340, Grafica.LINEAL, 
                               "lineal", "logaritmica", "insertar");
        grafica[1] = new Grafica(360, 340, Grafica.CTE, 
                               "logaritmica", "constante", "insertar");
        grafica[2] = new Grafica(360, 340, Grafica.CTE, 
                               "logaritmica", "constante", "eliminarMin");
        grafica[3] = new Grafica(360, 340, Grafica.LOG, 
                               "logaritmica", "constante", "eliminarMin");
        grafica[0].setBounds(20, 20, 360, 340);
        grafica[0].setSerie(0, Grafica.LINEAL);
        grafica[0].setSerie(1, Grafica.LOG);
        grafica[1].setBounds(420, 20, 360, 340);
        grafica[1].setSerie(0, Grafica.LOG);
        grafica[1].setSerie(1, Grafica.CTE);
        grafica[2].setBounds(20, 380, 360, 340);
        grafica[2].setSerie(0, Grafica.LOG);
        grafica[2].setSerie(1, Grafica.CTE);
        grafica[3].setBounds(420, 380, 360, 340);
        grafica[3].setSerie(0, Grafica.LOG);
        grafica[3].setSerie(1, Grafica.CTE);
        for (int i = 0; i < 4; i++) {
            getContentPane().add(grafica[i]);
        }
        JLabel jLabel = new JLabel("insertar de LPIColaPrioridad");
        jLabel.setBounds(10, 2, 300, 18);
        jLabel.setFont(fontBold);
        getContentPane().add(jLabel);
        
        jLabel = new JLabel("insertar de PriorityQColaPrioridad");
        jLabel.setBounds(410, 2, 300, 18);
        jLabel.setFont(fontBold);
        getContentPane().add(jLabel);
        
        jLabel = new JLabel("eliminarMin de LPIColaPrioridad");
        jLabel.setBounds(10, 362, 300, 18);     
        jLabel.setFont(fontBold);
        getContentPane().add(jLabel);
        
        jLabel = new JLabel("eliminarMin de PriorityQColaPrioridad");
        jLabel.setBounds(410, 362, 300, 18);
        jLabel.setFont(fontBold);
        getContentPane().add(jLabel);
        this.setSize(new Dimension(800, 760));
        this.setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Eficiencia de las implementaciones de Cola de Prioridad");
    }
        
    /** Gestion de los eventos de la ventana */
    public void windowActivated(WindowEvent e) { }            
    public void windowClosed(WindowEvent e) { }
    public void windowDeactivated(WindowEvent e) { }
    public void windowDeiconified(WindowEvent e) { }
    public void windowIconified(WindowEvent e) { }
    public void windowOpened(WindowEvent e) { }
    
    @SuppressWarnings("unchecked")
    private ColaPrioridad<Paciente>[] crearCPs(int talla, boolean lpi,
                                               boolean insertar) {
        ColaPrioridad<Paciente>[] cp = new ColaPrioridad[NUM_CPS];
        for (int i = 0; i < NUM_CPS; i++) {
            if (lpi) { cp[i] = new PriorityQColaPrioridad<Paciente>(); }
            else { cp[i] = new LPIColaPrioridad<Paciente>(); }
            int n = insertar ? talla - NUM_REP / 2 : talla + NUM_REP / 2;
            for (int j = 0; j < n; j++) { cp[i].insertar(new Paciente("")); }
        }
        return cp;
    }
    
    private double medirInsertar(ColaPrioridad<Paciente>[] cp, int numRep) {
        double t = 0.0;
        for (int i = 0; i < NUM_CPS; i++) {
            double medida = 0.0;
            for (int rep = 0; rep < numRep; rep++) {
                long inicio = System.nanoTime();
                for (int j = 0; j < NUM_REP; j++) {                    
                    cp[i].insertar(new Paciente(""));
                }
                long fin = System.nanoTime();
                medida += ((fin - inicio) / (double) NUM_REP);
                for (int j = 0; j < NUM_REP; j++) { cp[i].eliminarMin(); }
            }
            t += medida / (double) numRep;
        }
        return t / NUM_CPS;
    }
    
    private double medirEliminar(ColaPrioridad<Paciente>[] cp) {
        double t = 0.0;
        for (int i = 0; i < NUM_CPS; i++) {
            double medida = 0.0;
            for (int rep = 0; rep < NUM_REP2; rep++) {
                long inicio = System.nanoTime();
                for (int j = 0; j < NUM_REP; j++) {
                    cp[i].eliminarMin();
                }
                long fin = System.nanoTime();
                medida += (fin - inicio) / (double) NUM_REP;
                for (int j = 0; j < NUM_REP; j++) {
                    cp[i].insertar(new Paciente(""));
                }
            }
            t += medida / NUM_REP2;
        }
        return t / NUM_CPS;
    }
    
    /** Gestion de los eventos de la ventana */
    public void actionPerformed(ActionEvent e) {
        ColaPrioridad<Paciente>[] cp;
        Splash sp = new Splash(NUM_TICKS);
        if (e.getSource() == jButtonCronometrar[0]) {
            grafica[0].resetSeries();
            NUM_CPS = NUM_CPS / 8;
            for (int talla = 1; talla <= NUM_TICKS; talla++) {
                int tallaCP = talla * INC_TALLA;
                cp = crearCPs(tallaCP, false, true);
                double t = medirInsertar(cp, NUM_REP1);
                grafica[0].addPoint(t);
                sp.step(tallaCP);
            }
            NUM_CPS = NUM_CPS * 8;
        } else if (e.getSource() == jButtonCronometrar[1]) {
            grafica[1].resetSeries();
            for (int talla = 1; talla <= NUM_TICKS; talla++) {
                int tallaCP = talla * INC_TALLA;
                cp = crearCPs(tallaCP, true, true);
                double t = medirInsertar(cp, NUM_REP2);
                grafica[1].addPoint(t);
                sp.step(tallaCP);
            }
        } else if (e.getSource() == jButtonCronometrar[2]) {
            NUM_CPS = NUM_CPS / 8;
            grafica[2].resetSeries();
            for (int talla = 1; talla <= NUM_TICKS; talla++) {
                int tallaCP = talla * INC_TALLA;
                cp = crearCPs(tallaCP, false, false);
                double t = medirEliminar(cp);
                grafica[2].addPoint(t);
                sp.step(tallaCP);
            }
            NUM_CPS = NUM_CPS * 8;
        } else if (e.getSource() == jButtonCronometrar[3]) {
            grafica[3].resetSeries();
            for (int talla = 1; talla <= NUM_TICKS; talla++) {
                int tallaCP = talla * INC_TALLA;
                cp = crearCPs(tallaCP, true, false);
                double t = medirEliminar(cp);
                grafica[3].addPoint(t);
                sp.step(tallaCP);
            }
        }
        sp.setVisible(false);
    }
        
    /** Grafica */
    private class Grafica extends JPanel {
        private static final int LINEAL = 0;
        private static final int LOG = 1;
        private static final int CTE = 2;
        private final Color[] serieCOLOR = 
        {Color.blue, Color.red, Color.black, Color.green, Color.pink};
        private Font fontLeyenda = new Font("TimesRoman", Font.BOLD, 12);
        private Font fontSmall = new Font("TimesRoman", Font.PLAIN, 10);
        private int minX = 1, maxX = NUM_TICKS, minY = 0, maxY = 1;
        private double incY = 1;
        private int gx1, gy1, gx2, gy2, w, h;
        private ArrayList<Double>[] series;
        private int[] tipoSerie;
        private int tipo;
        
        @SuppressWarnings("unchecked")
        Grafica(int ancho, int alto, int tipo, String coste1, 
                String coste2, String coste3) {
            setLayout(null);
            JLabel label;
            this.tipo = tipo;
            label = new JLabel(coste1);
            label.setBounds(ancho / 5, alto - 35, 80, 22);
            label.setFont(fontLeyenda);
            label.setForeground(serieCOLOR[0]);
            add(label);
            label = new JLabel(coste2);
            label.setBounds(ancho / 2 - 14, alto - 35, 80, 22);
            label.setFont(fontLeyenda);
            label.setForeground(serieCOLOR[1]);
            add(label);
            label = new JLabel(coste3);
            label.setBounds(4 * ancho / 5, alto - 35, 80, 22);
            label.setFont(fontLeyenda);
            label.setForeground(serieCOLOR[2]);
            add(label);
            
            series = new java.util.ArrayList[3];
            resetSeries();
            
            tipoSerie = new int[3];
            for (int i = 0; i < series.length; i++) { tipoSerie[i] = -1; }
        }
        
        void setSerie(int serie, int tipo) {
            tipoSerie[serie] = tipo;
        }
        
        void resetSeries() {
            for (int i = 0; i < series.length; i++) {
                series[i] = new java.util.ArrayList<Double>();
            }
        }
        
        double log(double n) {
            return Math.log(n) / Math.log(2);
        }
        
        void ajusteLineal() {
            double t = 0;
            for (int j = 1; j <= series[2].size(); j++) {
                t += series[2].get(j - 1) / j;
            }
            t /= series[2].size();
            for (int i = 0; i < 2; i++) {
                series[i] = new java.util.ArrayList<Double>();    
                double valor = t;
                for (int j = minX; j <= maxX; j++) {
                    series[i].add(valor);
                    switch (tipoSerie[i]) {
                        case LINEAL: 
                            valor += t; break;
                        case CTE:
                            break;
                        case LOG:
                            valor += log(t * (j - minX + 1)); break;
                        default: 
                    }
                }
            }
        }
        
        void ajusteConstante() {
            double t = 0;
            for (int j = 1; j <= series[2].size(); j++) {
                t += series[2].get(j - 1);
            }
            t /= series[2].size();
            for (int i = 0; i < 2; i++) {
                series[i] = new java.util.ArrayList<Double>();    
                double valor = t;
                for (int j = minX; j <= maxX; j++) {
                    series[i].add(valor);
                    switch (tipoSerie[i]) {
                        case LINEAL: 
                            valor += t; break;
                        case CTE: 
                            break;
                        case LOG: 
                            valor += log(t * (j - minX + 1)); break;
                        default:
                    }
                }
            }
        }
        
        void ajusteLogaritmico() {
            double t = series[2].get(0), 
                       ult = series[2].get(series[2].size() - 1);
            double a = 1.0, valor;
            ult *= 1.25; 
            if (series[2].size() > 1) {
                do {
                    valor = t;
                    for (int i = 2; i <= series[2].size(); i++) {
                        valor += a * log(t * (i - 1) * INC_TALLA);
                    }
                    if (valor < ult) { a *= 1.25; }
                } while (valor < ult);
                for (int i = 0; i < 2; i++) {
                    series[i] = new ArrayList<Double>();
                    valor = t;
                    for (int j = minX; j <= maxX; j++) {
                        series[i].add(valor);
                        switch (tipoSerie[i]) {
                            case LINEAL: 
                                valor += t; break;
                            case CTE: 
                                break;
                            case LOG: 
                                valor += a * log(t * (j - minX + 1) 
                                         * INC_TALLA); 
                                break;
                            default:
                        }
                    }
                }
            }
        }
        
        /** Ajustar las series calculadas */
        void ajustarSeries() {
            if (tipo == LINEAL) { ajusteLineal(); }
            else if (tipo == CTE) { ajusteConstante(); }
            else { ajusteLogaritmico(); }
        }
        
        /** Anyade un nuevo dato */
        void addPoint(double tiempo) {
            series[2].add(tiempo);
            ajustarSeries();
            updateMaxY();
            this.paintImmediately(0, 0, getWidth(), getHeight());
        }
        
        private void updateMaxY() {
            maxY = 1;
            double min = -1;
            for (int s = 0; s < series.length; s++) {
                for (Double d : series[s]) {
                    if (d.doubleValue() > maxY) { 
                        maxY = ((int) d.doubleValue()) + 1;
                    }
                    if (min == -1 || d.doubleValue() < min) {
                        min = d.doubleValue();
                    }
                }
            }
            if (min > maxY / 3.0) { maxY *= 2; }
            incY = (maxY - minY) / 10.0;
        }
        
        /** Dibujado del componente */
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                                 RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            gx1 = 40;
            gy1 = 10;
            gx2 = getWidth() - 10;
            gy2 = getHeight() - 70;
            w = gx2 - gx1;
            h = gy2 - gy1;
            g2d.setPaint(Color.white);
            g2d.fillRect(gx1, gy1, w, h);
            g2d.setColor(Color.black);
            g2d.drawRect(gx1, gy1, w, h);
            g2d.setFont(fontSmall);
            FontMetrics fm = g2d.getFontMetrics();
            int ticks = maxX - minX;
            for (int i = 0; i <= ticks; i++) {
                int x = gx1 + i * w / ticks;
                g2d.drawLine(x, gy2, x, gy2 + 4);
                String s = "" + (i + minX);
                g2d.drawString(s, x - fm.stringWidth(s) / 2, 
                               gy2 + 4 + fm.getHeight());
            }
            //String s = "discos";
            String s = "Num. Pacientes x 1000";
            g2d.drawString(s, gx1 + (w - fm.stringWidth(s)) / 2, 
                           gy2 + 2 + 2 * fm.getHeight());
            ticks = (int) ((maxY - minY) / incY);
            for (int i = 0; i <= ticks; i++) {
                int y = gy2 - i * h / ticks;
                g2d.drawLine(gx1 - 4, y, gx1, y);
                s = "" + (int) ((i + minY) * incY);
                g2d.drawString(s, gx1 - fm.stringWidth(s) - 6, y + 5);
            }
            AffineTransform orig = g2d.getTransform();
            //s = "Num. movimientos";
            s = "Tiempo Promedio (ns.)";
            g2d.translate(10, (h + fm.stringWidth(s)) / 2);
            g2d.rotate(-Math.PI / 2.0);            
            g2d.drawString(s, 0, 0);
            g2d.setTransform(orig);
            drawSeries(g2d);
        }
        
        private int getPointX(int discos) {
            int ticks = maxX - minX;
            return gx1 + (discos - minX) * w / ticks;
        }
        
        private int getPointY(double dato) {
            double ticks = maxY - minY;
            return gy2 - (int) (dato * h / ticks);
        }
        
        private void drawSeries(Graphics2D g) {
            Shape clip = g.getClip();
            g.setClip(gx1, gy1, w, h);
            for (int serie = 0; serie < series.length; serie++) {
                java.util.ArrayList<Double> datos = series[serie];
                int x = -1, y = -1;
                g.setColor(serieCOLOR[serie]);
                for (int i = 0; i < datos.size(); i++) {
                    int nx = getPointX(i + 1), ny = getPointY(datos.get(i));
                    if (x != -1) { g.drawLine(x, y, nx, ny); }
                    x = nx;
                    y = ny;
                }
            }
            g.setClip(clip);
        }
    }
    /**
     * Clase auxiliar
     */
    public class Splash extends JFrame {
        private JProgressBar pbar;
        
        public Splash(int numPasos) {
            super("Midiendo tiempos...");
            setLayout(null);
            setSize(334, 100);
            pbar = new JProgressBar(0, numPasos);
            pbar.setValue(0);
            pbar.setBounds(10, 20, 300, 18);
            this.add(pbar);
            setLocationRelativeTo(null);
            setVisible(true);
        }
        
        public void step(int talla) {
            this.setTitle("Midiendo tiempos: talla = " + talla);
            pbar.setValue(pbar.getValue() + 1);
            pbar.paintImmediately(0, 0, 300, 18);
        }
    }
    
    /**
    * Metodo principal que crea una instancia de la interfaz grafica
    */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                EficienciaCPGui test = new EficienciaCPGui();
                test.setVisible(true);
            }
        });
    }
}
