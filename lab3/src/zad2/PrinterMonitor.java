package zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMonitor {
    private final Boolean[] isAvailable;
    private final Lock lock;
    private final Condition anyPrinterFree;
    int printersNumber;
    int availablePrintersNumber;

    public PrinterMonitor(int printersNumber) {
        lock = new ReentrantLock();
        anyPrinterFree = lock.newCondition();
        isAvailable = new Boolean[printersNumber];
        this.printersNumber = printersNumber;
        availablePrintersNumber = printersNumber;

        for (int i = 0; i < printersNumber; i++) {
            isAvailable[i] = true;
        }

    }

    public int usePrinter() throws InterruptedException {
        lock.lock();

        while (availablePrintersNumber == 0) {
            anyPrinterFree.await();
        }

        // found free printer
        int freePrinterIdx = -1;
        for (int i = 0; i < printersNumber; i++) {
            if (isAvailable[i]) {
                availablePrintersNumber--;
                isAvailable[i] = false;
                freePrinterIdx = i;
                break;
            }
        }

        lock.unlock();
        return freePrinterIdx;
    }

    public void freePrinter(int printerIdx) {
        lock.lock();
        isAvailable[printerIdx] = true;
        availablePrintersNumber++;
        anyPrinterFree.signal();
        lock.unlock();
    }
}
