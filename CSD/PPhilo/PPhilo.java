// CSD Mar 2013 Juansa Sendra
package PPhilo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PPhilo extends JPanel implements ActionListener {
    private Vector<State> state;
    private JList text;
    private Box box;
    private StateRenderer sr;
    private boolean theEnd = true;
    private static int delay;

    void simulate(String s, int delay) {
        final int cycles = 4;
        int i, type = Integer.parseInt(s.substring(5));
        System.out.println(type + "");
        Log log = new Log(state, text);
        sr = new StateRenderer();
        Thread[] philo = new Philo[5];
        Table t;
        switch (type) {
            case 0:
                t = new RegularTable(log);
                for (i = 0; i < 5; i++) philo[i] = new Philo(i, cycles, delay, t);
                break;
            case 1:
                System.out.println("Assimetry (last/but last).");
                t = new RegularTable(log);
                for (i = 0; i < 4; i++) philo[i] = new Philo(i, cycles, delay, t);
                philo[4] = new LefthandPhilo(4, cycles, delay, t);
                break;
            case 2:
                System.out.println("Assimetry (even/odd).- TO BE IMPLEMENTED");
                t = new RegularTable(log);
                for (i = 0; i < 5; i++) philo[i] = new Philo(i, cycles, delay, t);
                break;
            case 3:
                System.out.println("Both or None.- TO BE IMPLEMENTED");
                t = new RegularTable(log);
                for (i = 0; i < 5; i++) philo[i] = new Philo(i, cycles, delay, t);
                break;
            case 4:
                System.out.println("Limited capacity Dining Room.- TO BE IMPLEMENTED");
                t = new RegularTable(log);
                for (i = 0; i < 5; i++) philo[i] = new Philo(i, cycles, delay, t);
                break;
        }
        theEnd = false;
        for (i = 0; i < 5; i++) philo[i].start();
        text.setSelectedIndex(0);
        text.setListData(state);
        do {
            Thread.yield();
        } while (!log.endOK && !log.deadlock);
        System.out.println(log.deadlock ? " DEADLOCK" : "   OK");
        text.setListData(state);
        theEnd = true;
    }

    public PPhilo() {
        JRadioButton[] type = {
                new JRadioButton("Deadlock not prevented"),
                new JRadioButton("Assimetry (last/but last)"),
                new JRadioButton("Assimetry (even/odd)"),
                new JRadioButton("Both or None"),
                new JRadioButton("Limited capacity Dining Room")
        };
        ButtonGroup typeTable = new ButtonGroup();
        for (int j = 0; j < type.length; j++) {
            typeTable.add(type[j]);
            type[j].addActionListener(this);
            type[j].setActionCommand("Table" + j);
        }
        JPanel ptype = new JPanel(new GridLayout(0, 1));
        for (int j = 0; j < type.length; j++) {
            ptype.add(type[j]);
        }
        state = new Vector<State>();
        box = new Box();
        text = new JList(state);
        sr = new StateRenderer();
        text.setCellRenderer(sr);
        text.setVisibleRowCount(30);
        text.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int i = text.getSelectedIndex();
                if (i >= 0) state.get(i).draw(box);
            }
        });
        JPanel q = new JPanel();
        q.setLayout(new BorderLayout());
        q.add(ptype, BorderLayout.PAGE_START);
        q.add(box, BorderLayout.PAGE_END);
        setLayout(new BorderLayout());
        add(q, BorderLayout.LINE_START);
        add(new JScrollPane(text), BorderLayout.LINE_END);
    }

    private static int integer(String[] v, int i, int def, int a, int b) {
        int n = (i >= v.length) ? def : Integer.parseInt(v[i]);
        return (n < a || n > b) ? def : n;
    }

    public static void main(String[] args) {
        delay = integer(args, 0, 10, 1, 10);
        JFrame frame = new JFrame("CSD: 5 Philosophers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new PPhilo());
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (theEnd) simulate(e.getActionCommand(), delay);
    }
}