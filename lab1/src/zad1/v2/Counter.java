package zad1.v2;

public class Counter {
    private int i;

    public Counter(int i) {
        this.i = i;
    }

    public void increment(){
        this.i += 1;
    }

    public void decrement(){
        this.i -= 1;
    }

    public void print(){
        System.out.println(i);
    }
}
