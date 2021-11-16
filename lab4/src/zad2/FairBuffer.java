package zad2;

import java.util.concurrent.Semaphore;

public class FairBuffer implements Buffer {
    private int maxCapacity;
    private Semaphore semaphore;

    public FairBuffer(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        semaphore = new Semaphore(maxCapacity, true);
    }

    @Override
    public void put(int itemsNumber) throws InterruptedException {
        semaphore.acquire(itemsNumber);
//        System.out.println(itemsNumber + " item(s) put");
    }

    @Override
    public void take(int itemsNumber) {
        semaphore.release(itemsNumber);
//        System.out.println(itemsNumber + " item(s) taken");
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }
}
