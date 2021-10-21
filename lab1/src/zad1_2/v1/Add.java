package zad1_2.v1;

public class Add implements Runnable{
    Counter c;

    public Add(Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++){
            c.increment();
        }
    }
}
