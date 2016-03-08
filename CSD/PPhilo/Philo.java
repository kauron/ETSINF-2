// CSD Mar 2013 Juansa Sendra
 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class Philo extends Thread { //not prevents deadlocks. Table0
	int id, cycles, msegDelay;
	Table table;

	public Philo(int id, int cycles, int delay0, Table table) {
		this.id=id; this.cycles=cycles; this.table=table; msegDelay=delay0;
	}
	protected void delay(long msec) { 
		try {Thread.sleep(msec);} catch (InterruptedException ex) {}
	}
	public void run() {
		table.begin(id);
		for (int i=0; i<cycles; i++) {
			table.takeR(id); delay(msegDelay); table.takeL(id);
			delay(table.eat(id));
			table.dropR(id); table.dropL(id);
			delay(table.ponder(id));
		}
		table.end(id);
	}
}
