package zad1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int PROCESSORS = 8;
        int THREADS = PROCESSORS + 2;

        Buffer buffer = new Buffer(7, PROCESSORS);
        System.out.println(buffer);

        Thread[] threads = new Thread[THREADS];

        threads[0] = new Thread(new Producer(buffer));
        threads[threads.length - 1] = new Thread(new Consumer(buffer));

        for (int i = 0; i < PROCESSORS; i++) {
            threads[i + 1] = new Thread(new Processor(buffer, i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }
}
