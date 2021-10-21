package zad2.v1;

public class AddThread implements Runnable{
    Counter c;

    public AddThread(Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++){
            c.increment();
        }
    }
}
