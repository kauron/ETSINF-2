// CSD feb 2015 Juansa Sendra
 

public class Pool3  extends Pool2 { //max capacity

    @Override
    public synchronized long kidSwims(int id) {
        while(kids + instructors == log.capacity() ||
                kids + 1 > log.maxKI() * instructors) {
            log.enterWait(id);
            await();
        }
        kids++;
        return log.swims(id);
    }

    @Override
    public synchronized long instructorSwims(int id) {
        while(kids + instructors == log.capacity()) {
            log.enterWait(id);
            await();
        }
        instructors++;
        notifyAll();
        return log.swims(id);
    }

    @Override
    public synchronized long instructorRests(int id) {
        long r = super.instructorRests(id);
        notifyAll();
        return r;
    }
}