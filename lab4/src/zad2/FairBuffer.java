package zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairBuffer implements Buffer {
    private final int maxCapacity;
    private final Lock lock;
    private final Condition firstProdCondition;
    private final Condition othersProdCondition;
    private final Condition firstConsCondition;
    private final Condition othersConsCondition;
    private int currentlyOccupied;
    private boolean firstProdWorking;
    private boolean firstConsWorking;

    public FairBuffer(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        currentlyOccupied = 0;
        lock = new ReentrantLock();
        firstProdCondition = lock.newCondition();
        othersProdCondition = lock.newCondition();
        firstConsCondition = lock.newCondition();
        othersConsCondition = lock.newCondition();
        firstProdWorking = false;
        firstConsWorking = false;
    }

    @Override
    public void put(int itemsNumber) throws InterruptedException {
        lock.lock();
        while (firstProdWorking) {
            othersProdCondition.await();
        }
        firstProdWorking = true;

        // until fits
        while (currentlyOccupied + itemsNumber > maxCapacity) {
            firstProdCondition.await();
        }
        currentlyOccupied += itemsNumber;
        System.out.println(itemsNumber + " item(s) put,   " + this);

        firstProdWorking = false;
        othersProdCondition.signal();
        firstConsCondition.signal();
        lock.unlock();
    }

    @Override
    public void take(int itemsNumber) throws InterruptedException {
        lock.lock();
        while (firstConsWorking) {
            othersConsCondition.await();
        }

        firstConsWorking = true;

        // until enough elements
        while (currentlyOccupied - itemsNumber < 0) {
            firstConsCondition.await();
        }

        currentlyOccupied -= itemsNumber;
        System.out.println(itemsNumber + " item(s) taken, " + this);

        firstConsWorking = false;
        othersConsCondition.signal();
        firstProdCondition.signal();
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
