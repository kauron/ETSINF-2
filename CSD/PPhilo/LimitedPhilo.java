// CSD Mar 2013 Juansa Sendra
package PPhilo;

public class LimitedPhilo extends Philo {
    public LimitedPhilo(int id, int cycles, int delay, Table table) {
        super(id, cycles, delay, table);
    }

    public void run() {
        table.begin(id);
        for (int i = 0; i < cycles; i++) {
            table.enter(id);
            table.takeR(id);
            delay(msegDelay);
            table.takeL(id);
            delay(table.eat(id));
            table.dropR(id);
            table.dropL(id);
            table.exit(id);
            delay(table.ponder(id));
        }
        table.end(id);
    }
}