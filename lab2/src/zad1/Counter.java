package zad1;

public class Counter {
    private int i;
    private BinarySemaphore binSemaphore;

    public Counter(int i, BinarySemaphore binSemaphore) {
        this.i = i;
        this.binSemaphore = binSemaphore;
    }

    public void increment() {
        binSemaphore.lock();
        i += 1;
        binSemaphore.unlock();
    }

    public void decrement(){
        binSemaphore.lock();
        i -= 1;
        binSemaphore.unlock();
    }

    public void print(){
        System.out.println(i);
    }
}
