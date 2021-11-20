package zad1;

import java.util.Random;

public class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < buffer.getSize(); i++) {
                buffer.lock(i);
                while (buffer.getTapeValue(i) != buffer.getMaxTapeValue()) {
                    buffer.conditionAwait(i);
                }

                try {
                    buffer.consume(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("Consumed on seg [" + i + "]\n" + buffer);

                try {
                    Thread.sleep(new Random().nextInt(100) + 200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buffer.conditionSignal(i);
                buffer.unlock(i);
            }
        }
    }
}
