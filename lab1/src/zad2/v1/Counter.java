package zad2.v1;

public class Counter {
    private int i;

    public Counter(int i) {
        this.i = i;
    }

    public synchronized void increment(){
        i += 1;
    }

    public synchronized void decrement(){
        i -= 1;
    }

    public void print(){
        System.out.println(i);
    }
}
