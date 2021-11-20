package zad2;

public interface Buffer {
    void put(int elementsNumber) throws InterruptedException;

    void take(int elementsNumber) throws InterruptedException;

    int getMaxCapacity();
}
