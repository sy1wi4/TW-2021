package zad2;

import java.util.Random;

public class Consumer implements Runnable{
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            try {
                // take 1 to M/2 items
                long time = System.nanoTime();
                buffer.take(new Random().nextInt(buffer.getMaxCapacity()/2) + 1);
                time = System.nanoTime() - time;
                System.out.println("Cons time: " + time + " [ns]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
