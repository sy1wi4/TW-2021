package zad2.v2;

public class AddThread extends Thread{
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
