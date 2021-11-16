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
                buffer.take(new Random().nextInt(buffer.getMaxCapacity()/2) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
