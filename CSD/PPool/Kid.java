// CSD feb 2013 Juansa Sendra

 

public class Kid extends Swimmer {
	public Kid(int id, Pool p) {super(id,p);}
	void swims(int id) {delay(pool.kidSwims(id));}
	void rests(int id) {delay(pool.kidRests(id));}
}
