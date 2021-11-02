package zad2;

public class Semaphore {
    int value;
    public Semaphore(int value) {
        this.value = value;
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
}
