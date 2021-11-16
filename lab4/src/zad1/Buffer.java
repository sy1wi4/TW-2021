package zad1;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final int size;
    private final int[] tape;
    private final Lock[] locks;
    private final Condition[] conditions;
    private final int maxTapeValue;

    public Buffer(int size, int processors) {
        this.size = size;
        tape = new int[size];
        locks = new Lock[size];
        conditions = new Condition[size];
        maxTapeValue = processors;

        for (int i = 0; i < size; i++) {
            tape[i] = -1;
            locks[i] = new ReentrantLock();
            conditions[i] = locks[i].newCondition();
        }
    }

    public void produce(int i) throws Exception {
        if (tape[i] != -1) {
            throw new Exception("Cannot produce on not empty tape segment: " + i);
        }
        tape[i] = 0;
    }

    public void process(int i) {
        tape[i] += 1;
    }

    public void consume(int i) throws Exception {
        if (tape[i] != maxTapeValue) {
            throw new Exception("Cannot consume from not full tape segment: " + i);
        }
        tape[i] = -1;
    }

    public void conditionAwait(int i) {
        try {
            conditions[i].await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void conditionSignal(int i) {
        conditions[i].signal();
    }

    public void lock(int i) {
        locks[i].lock();
    }

    public void unlock(int i) {
        locks[i].unlock();
    }

    public int getSize() {
        return size;
    }

    public int getTapeValue(int i) {
        return tape[i];
    }

    public int getMaxTapeValue() {
        return maxTapeValue;
    }

    @Override
    public String toString() {
        return "tape: " + Arrays.toString(tape) + "\n";
    }
}
