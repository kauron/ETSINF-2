// CSD feb 2013 Juansa Sendra

 

public class Pool4 extends Pool3 { //kids cannot enter if there are instructors waiting to exit
    int waiting = 0;

    @Override
    public synchronized long instructorRests(int id) {
        while(kids > log.maxKI() * (instructors - 1)) {
            log.leaveWait(id);
            waiting++;
            await();
        }
        waiting--;
        instructors--;
        notifyAll();
        return log.rests(id);
    }

    @Override
    public synchronized long kidSwims(int id) {
        while (kids + instructors == log.capacity() ||
                kids + 1 > log.maxKI() * instructors ||
                waiting > 0) {
            log.enterWait(id);
            await();
        }
        kids++;
        return log.swims(id);
    }
}
