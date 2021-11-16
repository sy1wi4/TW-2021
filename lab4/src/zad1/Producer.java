package zad1;

import java.util.Random;

public class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < buffer.getSize(); i++) {
                buffer.lock(i);
                // wait until resource on tape is consumed (-1)
                while (buffer.getTapeValue(i) != -1) {
                    System.out.println("Producer, czekam se " + i);
                    buffer.conditionAwait(i);
                }

                try {
                    buffer.produce(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("Produced on seg [" + i + "]\n" + buffer);

                try {
                    Thread.sleep(new Random().nextInt(1000) + 200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buffer.conditionSignal(i);
                buffer.unlock(i);
            }
        }
    }
}
