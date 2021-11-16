package zad2;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // M = 1000, 10k, 100k
        // Config P-C: 10P+10C, 100P+100C, 1000P+1000C

        int M = 1000;
        int PRODUCERS = 10;
        int CONSUMERS = 10;

        Buffer naiveBuffer = new NaiveBuffer(M);
        execute(naiveBuffer, PRODUCERS, CONSUMERS);

//        Buffer fairBuffer = new FairBuffer(M);
//        execute(fairBuffer, PRODUCERS, CONSUMERS);
    }

    private static void execute(Buffer buffer, int PRODUCERS, int CONSUMERS) throws InterruptedException {
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

    public static void test() {

    }
}
