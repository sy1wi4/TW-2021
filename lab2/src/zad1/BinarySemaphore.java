package zad1;

public class BinarySemaphore {
    int value;
    public BinarySemaphore() {
        value = 1;
    }

    public synchronized void lock() {
        while(value == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value = 0;
    }

    public synchronized void unlock() {
        value = 1;
        notifyAll();
    }
}
