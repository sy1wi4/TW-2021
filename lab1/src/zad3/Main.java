package zad3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int AMOUNT = 100;

        Buffer b = new Buffer();
        Producer p = new Producer(b, AMOUNT);
        Consumer c = new Consumer(b, AMOUNT);

        Thread pTh = new Thread(p);
        Thread cTh = new Thread(c);

        pTh.start();
        cTh.start();

        pTh.join();
        cTh.join();

    }
}
