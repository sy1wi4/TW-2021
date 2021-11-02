package zad2;

public class Semaphore {
    int value;
    int capacity;
    public Semaphore(int capacity) {
        this.value = capacity;
        this.capacity = capacity;
    }

    public synchronized void lock() {
        while(value == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value -= 1;
    }

    public synchronized void unlock() {
        value += 1;
        notifyAll();
    }

    public int getValue() {
        return value;
    }
}
