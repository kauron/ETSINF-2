// CSD Mar 2013 Juansa Sendra
 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

class Log {
	final int variability=80;
	int ended=0;
	Random x=new Random();
	Vector<State> state; JList text;
	int[] fork; //forks state (for each fork, it has an owner or N==free)
	public boolean endOK=false, deadlock=false;
	
	public Log(Vector<State> est, JList txt) {
		fork= new int[5]; for (int i=0; i<5; i++) fdrop(i); //init. free
		state=est; text=txt; state.clear(); 
		state.addElement(new State());
	}
	private long delay(long msec) {return msec*(100+x.nextInt(variability))/100;}
	private long delay() {return delay(200);}
	private boolean myR(int x) {return fork[R(x)]==x;}
	private boolean myL(int x) {return fork[L(x)]==x;}
	
	private int R(int x) {return (4+x)%5;} //right fork of x
	private int L(int x) {return x;} //left fork of x
	private void fdrop(int x)  {fork[x]=5;}
	private void ftakeL(int x)  {fork[L(x)]=x;}
	private void ftakeR(int x)  {fork[R(x)]=x;}	
	private boolean free(int x){return fork[x]==5;}

	private void pre(String f, boolean cond, String msg) {
		if (cond) return;
		System.out.println("ERROR in "+f+": "+msg+"  STATE="+state.lastElement()); System.exit(1);
	}
	private void noForks(String f, int x) 		{pre(f,!myR(x) && !myL(x),x+" must not have any fork");}
	private void bothForks(String f, int x) 	{pre(f,myR(x) && myL(x),x+" must have both forks");}
	private void freeR(String f, int x) 		{pre(f,free(R(x)),x+" right fork not free");}
	private void freeL(String f, int x) 		{pre(f,free(L(x)),x+" left fork not free");}
	private void usedR(String f, int x) 		{pre(f,!free(R(x)),x+" right fork is free");}
	private void usedL(String f, int x) 		{pre(f,!free(L(x)),x+" left fork is free");}
	private void notMyR(String f, int x) 		{pre(f,!myR(x),x+" right fork is yours");}
	private void notMyL(String f, int x) 		{pre(f,!myL(x),x+" left fork is yours");}
	private void notBothFree(String f, int x) 	{pre(f,!free(R(x))||!free(L(x)),x+" both forks are free");}
	private void ownerR(String f, int x) 		{pre(f,myR(x),x+" is not the owner of right fork");}
	private void ownerL(String f, int x) 		{pre(f,myL(x),x+" is not the owner of left fork");}
	
    //              0        1       2      3     4       5       6        7      8      9           10          11   12    13
	//states philo: inactive ponder, wtalk, talk, wtakeL, wtakeR, wtakeLR, talkR, talkL, wtakeLhasR, wtakeRhasL, eat, rest, restL
	private void step(int x, int n) {
		State e=state.lastElement(), ne; if (e.get(x)==n) return; //skip duplicates
		boolean[] f=new boolean[5]; for(int i=0; i<5; i++) f[i]=free(i);
		ne=new State(e,x,n,f); checkDeadlock(ne); state.addElement(ne);
	}
	private void checkDeadlock(State e) {
		int n=0;
		for (int i=0; i<5;  i++) {int s=e.get(i); if (s==9 || s==10) n++;}
		if (n==5) deadlock=true;
	}
	
	public void begin(int x) 	{noForks("begin",x); step(x,3);}
	public void end(int x) 		{noForks("end",x);ended++; endOK=(ended==5);}
	public long eat(int x)	    {bothForks("eat",x); step(x,11); return delay();}
	public long ponder(int x)	{noForks("ponder",x); step(x,1); return delay();}
	public void wtakeR(int x)	{usedR("wtakeR",x); notMyR("wtakeR",x); step(x,myL(x)?10:5);}
	public void wtakeL(int x)   {usedL("wtakeL",x); notMyL("wtakeL",x); step(x,myR(x)?9:4);}
	public void wtakeLR(int x)	{noForks("wtakeLR",x); notBothFree("wtakeLR",x); step(x,6);}
	public void wenter(int x) 	{step(x,2);}
	public void dropLR(int x)	{bothForks("dropLR",x); fdrop(R(x)); fdrop(L(x)); step(x,12);}
	public void dropR(int x)	{bothForks("dropR",x); fdrop(R(x)); step(x,13);}
	public void dropL(int x)	{ownerL("dropL",x); fdrop(L(x)); step(x,12);}
	public void enter(int x)	{noForks("enter",x); step(x,3);}
	public void exit(int x)		{noForks("exit",x); step(x,1);}
	public void talk(int x)		{noForks("talk",x); step(x,3);}
	public void takeR(int x) 	{freeR("takeR",x); ftakeR(x); step(x,myL(x)?11:7);}
	public void takeL(int x) 	{freeL("takeL",x); ftakeL(x); step(x,myR(x)?11:8);}
	public void takeLR(int x) 	{freeL("takeL",x); ftakeL(x); ftakeR(x); step(x,11);}
	public boolean rightFree(int x)	{return free(R(x));}
	public boolean leftFree(int x) 	{return free(L(x));}
}
