package zad1_2.v1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter(0);
        Add a = new Add(c);
        Subtract s = new Subtract(c);

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(s);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        c.print();
    }
}
