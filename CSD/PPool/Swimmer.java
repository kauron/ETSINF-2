// CSD feb 2013 Juansa Sendra

 

public abstract class Swimmer extends Thread {
	int id;
	Pool pool;
	public Swimmer(int id0, Pool p) {id=id0; pool=p;}
	protected void delay(long ms) {try {Thread.sleep(ms);} catch (Exception ex) {}}
	
	public void run() {
		delay(pool.begin(id));
		for (int i=0; i<6; i++) {swims(id); rests(id);}
		pool.end(id);
	}
	abstract void swims(int id);
	abstract void rests(int id);
}
