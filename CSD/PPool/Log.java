// CSD feb 2013 Juansa Sendra

 

import javax.swing.*;
import java.util.*;

public class Log {
	int K,I,KI,CAP,Kend,Iend;
	Random rd=new Random();
	Vector<State> state; 
	JList text;

	
	public Log(Vector<State> stat, JList txt, int type, int K, int I, int KI, int CAP) {
		state=stat; text=txt; state.clear(); 
		state.addElement(new State(K,I));
		this.K=K; this.I=I; this.KI=KI; this.CAP=CAP;
	}
	private long delay(int n) {return (n==0 || n==2)? 100+rd.nextInt(60): 0;}
	private synchronized long step(int x, int n) {
		state.addElement(new State(state.lastElement(),x,n)); 
		text.setListData(state);
		return delay(n);
	}
	public long begin(int x) 	{return step(x,0);}
	public long end(int x) 		{return step(x,-2);}
	public long swims(int x)	{return step(x,2);}
	public long rests(int x)		{return step(x,0);}
	public long enterWait(int x) 	{return step(x,1);}
	public long leaveWait (int x)  	{return step(x,3);}
	public int  maxKI() 		{return KI;}
	public int  capacity() 		{return CAP;}

}