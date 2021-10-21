package zad3;

import sun.awt.windows.ThemeReader;

public class Main {
    int AMOUNT = 10;
    public static void main(String[] args) throws InterruptedException {
        Buffer b = new Buffer();
        Producer p = new Producer(b, 10);
        Consumer c = new Consumer(b,10);

        Thread pTh = new Thread(p);
        Thread cTh = new Thread(c);

        pTh.start();
        cTh.start();

        pTh.join();
        cTh.join();

    }
}
