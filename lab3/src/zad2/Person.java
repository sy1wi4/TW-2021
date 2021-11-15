package zad2;

import java.util.Random;

public class Person implements Runnable{
    private int id;
    private PrinterMonitor printerMonitor;

    public Person(int id, PrinterMonitor printerMonitor) {
        this.id = id;
        this.printerMonitor = printerMonitor;
        System.out.println("new person: " + id);
    }

    @Override
    public void run() {
        while(true) {
            try {
                int printerId = printerMonitor.usePrinter();
                System.out.println("ID: " + id + " printing on printer " + printerId);
                Thread.sleep(new Random().nextInt(1000) + 200);
                System.out.println("ID: " + id + " is done!  (Printer " + printerId + ")");
                printerMonitor.freePrinter(printerId);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
