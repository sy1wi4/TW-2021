package zad3;

public class Producer implements Runnable {
    private Buffer buffer;
    private final int amount;
    public Producer(Buffer buffer, int amount) {
        this.buffer = buffer;
        this.amount = amount;
    }

    @Override
    public void run() {
        for(int i = 0; i < amount; i++) {
            buffer.put("message " + i);
        }
    }
}
