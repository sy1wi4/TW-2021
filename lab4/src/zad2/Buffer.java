package zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int maxCapacity;
    private int currentCapacity;
    private Lock lock;
    private Condition condition;

    public Buffer(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        currentCapacity = maxCapacity;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void put(int itemsNumber) throws InterruptedException {
        lock.lock();
        // until fits
        while(currentCapacity + itemsNumber > maxCapacity){
            condition.await();
        }
        currentCapacity += itemsNumber;
        System.out.println(itemsNumber + " item(s) put,   " + this);

        condition.signal();
        lock.unlock();
    }

    public void take(int itemsNumber) throws InterruptedException {
        lock.lock();
        // until enough elements
        while(currentCapacity - itemsNumber < 0){
            condition.await();
        }

        currentCapacity -= itemsNumber;
        System.out.println(itemsNumber + " item(s) taken, " + this);

        condition.signal();
        lock.unlock();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public String toString() {
        return "occupied: " + currentCapacity + "/" + maxCapacity;
    }
}
