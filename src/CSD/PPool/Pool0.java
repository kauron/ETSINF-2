// CSD feb 2015 Juansa Sendra

 

public class Pool0 implements Pool {	//free access
	Log log;
	public void make(Log log0) 			{log=log0;}
	
	public  long begin(int id)			{return log.begin(id);}
	public  void end(int id)	{log.end(id);}
	
	
	public long kidSwims(int id) {
		return log.swims(id);
	}
	
	public long kidRests(int id) {
		return log.rests(id);
	}
	
	public long instructorSwims(int id) {
		return log.swims(id);
	}
	
	public long instructorRests(int id) {
		return log.rests(id);
	}
	
}