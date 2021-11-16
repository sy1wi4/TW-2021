package zad2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int M = 10;
        int PRODUCERS = 5;
        int CONSUMERS = 7;

        Buffer buffer = new Buffer(M);
        Thread[] threads = new Thread[PRODUCERS + CONSUMERS];

        for (int i = 0; i < PRODUCERS; i++) {
            threads[i] = new Thread(new Producer(buffer));
        }

        for (int i = PRODUCERS; i < PRODUCERS + CONSUMERS; i++) {
            threads[i] = new Thread(new Consumer(buffer));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }
}
