// CSD feb 2015 Juansa Sendra

 

public interface Pool {
	void make(Log log0);
	long begin(int id);
	void end(int id);
	long kidSwims(int id);
	long kidRests(int id);
	long instructorSwims(int id);
	long instructorRests(int id);
}