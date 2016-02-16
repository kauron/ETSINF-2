// CSD feb 2015 Juansa Sendra
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class PPool extends JPanel implements ActionListener {	
	int TYPE,K,I,MAX,CAP;
	private	Vector<State> state;
	private JList text;
	private Box box;
	private StateRenderer sr;

	private static int integer(String[] v, int i, int def, int a, int b) {
		int n= (i>=v.length)? def: Integer.parseInt(v[i]);
		return (n<a || n>b)?def:n;
	}
	
	private void simulate(String s) {
		Pool p=null; TYPE=Integer.parseInt(s.substring(4)); sr.inic(TYPE);
		try {
			Class c=Class.forName(s);
			p=(Pool)c.newInstance();
			p.make(new Log(state,text,TYPE,K,I,MAX,CAP)); 
			box.state0(K,I);
			System.out.print("Simulation of pool "+TYPE+" ... ");
		}
		catch (Exception e) {}
		Swimmer[] sw= new Swimmer[K+I]; //create and start swimmmers
		for (int i=0; i<K+I; i++) sw[i]= i<K? new Kid(i,p): new Instructor(i,p);
		for (int i=0; i<K+I; i++) sw[i].start();
		text.setSelectedIndex(0); 
		for (int i=K; i<K+I; i++) try {sw[i].join();} catch (Exception e){}
		for (int i=0; i<K; i++) {sw[i].interrupt(); sw[i]=null;}
		System.out.println("done");
	}
	
	
	public PPool(int n, int i) {
		TYPE=0; K=n; I=i; MAX=n/i; CAP=(n+i)/2;
		JRadioButton[] type= {
			new JRadioButton("Pool0: free access"),
			new JRadioButton("Pool1: kids cannot be alone"),
			new JRadioButton("Pool2: max kids/instructor"),
			new JRadioButton("Pool3: max capacity"),
			new JRadioButton("Pool4: kids cannot enter if there are instructors waiting to exit")
		};
		JLabel working=new JLabel("");
		ButtonGroup poolType= new ButtonGroup();
		for (int j=0; j<type.length; j++) {
			poolType.add(type[j]);
			type[j].addActionListener(this);
			type[j].setActionCommand("Pool"+j);
		}
		JPanel ptype= new JPanel(new GridLayout(0,1));
		for (int j=0; j<type.length; j++) {
			ptype.add(type[j]);
		}
		ptype.add(new JLabel(""));
		JPanel etiq= new JPanel(new GridLayout(0,1));
		etiq.add(new JLabel(K+" kids", JLabel.CENTER));
		etiq.add(new JLabel(I+" instructors", JLabel.CENTER));
		etiq.add(new JLabel("Max "+MAX+" kids per instructor", JLabel.CENTER));
		etiq.add(new JLabel("Pool capacity = "+CAP+" Swimmers", JLabel.CENTER));
		etiq.add(working);
		state=new Vector<State>(); 
        		box = new Box(K+I);
       		text = new JList(state);
		sr= new StateRenderer();
		text.setCellRenderer(sr);
		text.setVisibleRowCount(24);
		text.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int i=text.getSelectedIndex();
				if (i>=0) state.get(i).draw(box);
			}
		});
		JPanel p=new JPanel(), q=new JPanel(); 
		p.setLayout(new GridLayout(0,1)); p.add(etiq); p.add(ptype);
		q.setLayout(new GridLayout(0,1)); q.add(p); q.add(box);
		setLayout(new BorderLayout());
		add(q, BorderLayout.LINE_START);
		add(new JScrollPane(text), BorderLayout.LINE_END);
	}

	public void actionPerformed(ActionEvent e) {simulate(e.getActionCommand());}
	public Dimension getPreferredSize() {return new Dimension(1000,800);}
	
	public static void main(String[] args) {
	        JFrame frame = new JFrame("CSD: Shared Pool");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        	// 1st arg 	= number of kids 	(interval [5..20], default 7)
	        	// 2nd arg 	= number of instructors 	(interval [2..5], 	default 3)
	        frame.setContentPane(new PPool(integer(args,0,7,5,20), integer(args,1,3,2,5)));
	        frame.pack(); frame.setVisible(true);
	}
}