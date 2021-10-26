package zad1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BinarySemaphore binarySemaphore = new BinarySemaphore();
        Counter c = new Counter(0, binarySemaphore);
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
