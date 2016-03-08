// CSD Mar 2013 Juansa Sendra
package PPhilo;

public class BothNoneTable extends RegularTable { //both or none
    public BothNoneTable(Log log) {
        super(log);
    }

    public synchronized void takeLR(int id) {
        while (!(log.leftFree(id) && log.rightFree(id))) {
            log.wtakeLR(id);
            waiting();
        }
        log.takeR(id);
        log.takeL(id);
    }
}
