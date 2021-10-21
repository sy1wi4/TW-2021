package zad1_2.v1;

public class Subtract implements Runnable  {
    Counter c;

    public Subtract(Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++){
            c.decrement();
        }
    }
}
