package zad2.v2;

public class SubtractThread extends Thread{
    Counter c;

    public SubtractThread(Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++){
            c.decrement();
        }
    }
}
