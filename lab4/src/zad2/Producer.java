package zad2;

import java.util.Random;

public class Producer implements Runnable{
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            try {
                // put 1 to M/2 items
                buffer.put(new Random().nextInt(buffer.getMaxCapacity()/2) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
