package zad3;

public class Producer implements Runnable {
    private final Buffer buffer;
    private final int amount;

    public Producer(Buffer buffer, int amount) {
        this.buffer = buffer;
        this.amount = amount;
    }

    @Override
    public void run() {
        for(int i = 0; i < amount; i++) {
            try {
                String message = "message " + i;
                buffer.put(message);
                System.out.println("Produced: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
