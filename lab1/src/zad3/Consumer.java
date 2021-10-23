package zad3;

public class Consumer implements Runnable{
    private final Buffer buffer;
    private final int amount;

    public Consumer(Buffer buffer, int amount) {
        this.buffer = buffer;
        this.amount = amount;
    }

    @Override
    public void run() {
        for(int i = 0; i < amount; i++) {
            try {
                String message = buffer.take();
                System.out.println("Consumed: " + message);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

