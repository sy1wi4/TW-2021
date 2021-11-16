package zad1;

import java.util.Random;

public class Processor implements Runnable {
    private final Buffer buffer;
    private final int id;

    public Processor(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < buffer.getSize(); i++) {
                buffer.lock(i);
                // processor 0 is first - value should be set to 0, etc.
                while (buffer.getTapeValue(i) != id) {
                    System.out.println("Processor, czekam se " + i);
                    buffer.conditionAwait(i);
                }
                buffer.process(i);

                System.out.println("Processed on seg [" + i + "], id: " + id + "\n" + buffer);
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
