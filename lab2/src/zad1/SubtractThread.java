package zad1;

public class SubtractThread implements Runnable  {
    Counter c;

    public SubtractThread(Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++){
            c.decrement();
        }
    }
}
