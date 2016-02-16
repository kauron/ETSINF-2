// CSD feb 2013 Juansa Sendra

 

public class Instructor extends Swimmer {
	public Instructor(int id, Pool p) {super(id,p);}
	void swims(int id) {delay(pool.instructorSwims(id));}
	void rests(int id) {delay(pool.instructorRests(id));}
}