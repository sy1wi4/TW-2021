package zad1.v1;

public class Counter {
    private int i;

    public Counter(int i) {
        this.i = i;
    }

    public void increment(){
        i += 1;
    }

    public void decrement(){
        i -= 1;
    }

    public void print(){
        System.out.println(i);
    }
}
