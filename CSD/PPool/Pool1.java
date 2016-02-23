// CSD feb 2015 Juansa Sendra

 
public class Pool1 extends Pool0{//there cannot be kids alone
	int instructors = 0, kids = 0;

    public synchronized long kidSwims(int id) {
        while(instructors < 1) {
            log.enterWait(id);
            await();
        }
        kids++;
        return super.kidSwims(id);
    }

    public synchronized long kidRests(int id) {
        kids--;
        notifyAll();
        return super.kidRests(id);
    }

    public synchronized long instructorSwims(int id) {
        instructors++;
        notifyAll();
        return super.instructorSwims(id);
    }

    public synchronized long instructorRests(int id) {
        while (instructors <= 1 && kids > 0) {
            log.leaveWait(id);
            await();
        }
        instructors--;
        return super.instructorRests(id);
    }


}