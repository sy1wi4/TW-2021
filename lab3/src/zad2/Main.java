package zad2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int PEOPLE = 5;
        int PRINTERS = 3;

        Thread[] threads = new Thread[PEOPLE];
        PrinterMonitor printerMonitor = new PrinterMonitor(PRINTERS);
        for (int i = 0; i < PEOPLE; i++) {
            threads[i] = new Thread(new Person(i, printerMonitor));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }
}
