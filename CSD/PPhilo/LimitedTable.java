// CSD Mar 2013 Juansa Sendra
package PPhilo;

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    private int count;

    public LimitedTable(Log log) {
        super(log);
        count = 0;
    }

    @Override
    public synchronized void enter(int id) {
        while (count >= 4) {
            log.wenter(id);
            waiting();
        }
        count++;
        log.enter(id);
    }

    @Override
    public synchronized void exit(int id) {
        count--;
        if (count == 4) notifyAll();
        log.exit(id);
    }
}
