package zad2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int BASKETS = 3;
        Semaphore sem = new Semaphore(BASKETS);

        Customer c1 = new Customer(1, sem);
        Customer c2 = new Customer(2, sem);
        Customer c3 = new Customer(3, sem);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

}
