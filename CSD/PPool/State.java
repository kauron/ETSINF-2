// CSD feb 2013 Juansa Sendra

 

public class State {
	private final String[] ERR={
		"","kids alone","too many kids","capacity excedeed","kid entered when instructor waiting to exit", "finished", "out of instructors"};
    final int[] e;  //-1=not exists, 0=rests, 1=enterWait, 2=swims, 3=leaveWait, -2=ended
	final int K,I;
	int nk,ni,ult=-1;

	private boolean iwExit() {
		for (int i=K; i<K+I; i++) if (e[i]==3) return true;
		return false;
	}
	public int test() {
		int MAX=K/I, CAP=(K+I)/2;
		nk=0; 		for (int i=0; 	i<K;   	i++)   	if (e[i]>1) 	nk++;
		ni=0; 		for (int i=K;	i<K+I; 	i++)   	if (e[i]>1) 	ni++;
		int endedk=0; 	for (int i=0; 	i<K;   	i++)   	if (e[i]==-2) 	endedk++;
		int endedi=0; 	for (int i=K; 	i<K+I; 	i++)   	if (e[i]==-2) 	endedi++;
		return (nk>0&&ni==0)?1
		     : (nk>MAX*ni)?2
			 : ((nk+ni)>CAP)?3
			 : (ult>=0 && ult<K && e[ult]==2 && iwExit())?4
			 : endedk==K?5
			 : endedi==I?6
			 : 0;
	}
    public State(int K, int I) {
		this.K=K; this.I=I; this.e=new int[K+I];
        		for (int i=0; i<K+I; i++) e[i]=-1;
    	}
   public State(State stat) {
		this(stat.K,stat.I);
  		for (int i=0; i<K+I; i++) e[i]=stat.e[i];
	}
    	public State(State stat, int idx, int x) {
		this(stat); e[idx]=x; ult=idx;
    	}
    public String toString() {
		StringBuffer sb=new StringBuffer();
		int r=test();
		for (int i=0; i<K;   i++) sb.append(e[i]==0?'-':e[i]==1?'*':e[i]==2?'K':e[i]==3?'k':' ');
		sb.append("  ");
		for (int i=K; i<K+I; i++) sb.append(e[i]==0?'-':e[i]==1?'*':e[i]==2?'I':e[i]==3?'i':' ');
		sb.append("  "); sb.append(nk+","+ni+"  "); sb.append(ERR[r]);
		return sb.toString();
	}
	public void draw(Box b) {b.state(this);}
	public int get(int i) {return e[i];}
}