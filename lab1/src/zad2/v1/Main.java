package zad2.v1;

// synchronized
// version1: via Runnable interface

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter(0);
        Runnable a = new AddThread(c);
        Runnable s = new SubtractThread(c);

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(s);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        c.print();
    }
}
