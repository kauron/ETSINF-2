// CSD Mar 2013 Juansa Sendra
package PPhilo;

public class RegularTable implements Table {    //no prevention
    Log log;
    public RegularTable(Log log) {this.log=log;}
    protected void waiting() {try {wait();} catch (InterruptedException ex) {}}
    protected void illegal(String s) {System.out.println("ILLEGAL: "+s+" invoked"); System.exit(1);}

    public synchronized void begin(int id)  {log.begin(id);}
    public synchronized long ponder(int id) {return log.ponder(id);}
    public synchronized void takeR(int id) {
        while (!log.rightFree(id)) {log.wtakeR(id); waiting();}
        log.takeR(id);
    }
    public synchronized void takeL(int id) {
        while (!log.leftFree(id)) {log.wtakeL(id); waiting();}
        log.takeL(id);
    }
    public synchronized long eat(int id)   {return log.eat(id);}
    public synchronized void dropR(int id) {log.dropR(id); notifyAll();}
    public synchronized void dropL(int id) {log.dropL(id); notifyAll();}
    public synchronized void end(int id)   {log.end(id);}
    public synchronized void takeLR(int id){illegal("RegularTable.takeLR");}
    public synchronized void enter(int id) {illegal("RegularTable.enter");}
    public synchronized void exit(int id)  {illegal("RegularTable.exit");}
}
