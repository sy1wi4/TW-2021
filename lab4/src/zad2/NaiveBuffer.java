package zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NaiveBuffer implements Buffer {
    private final int maxCapacity;
    private final Lock lock;
    private final Condition condition;
    private int currentlyOccupied;

    public NaiveBuffer(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        currentlyOccupied = 0;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    @Override
    public void put(int itemsNumber) throws InterruptedException {
        lock.lock();
        // until fits
        while (currentlyOccupied + itemsNumber > maxCapacity) {
            condition.await();
        }
        currentlyOccupied += itemsNumber;
        System.out.println(itemsNumber + " item(s) put,   " + this);

        lock.unlock();
    }

    @Override
    public void take(int itemsNumber) throws InterruptedException {
        lock.lock();
        // until enough elements
        while (currentlyOccupied - itemsNumber < 0) {
            condition.await();
        }

        currentlyOccupied -= itemsNumber;
        System.out.println(itemsNumber + " item(s) taken, " + this);

        lock.unlock();
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public String toString() {
        return "occupied: " + currentlyOccupied + "/" + maxCapacity;
    }
}
