package zad2;

public class Customer implements Runnable {
    int id;
    Semaphore semaphore;
    public Customer(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            semaphore.lock();
            System.out.println("Customer in: " + id + ". Left baskets: " + semaphore.getValue());
            try {
                Thread.sleep((long)(Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.unlock();
            System.out.println("Customer out: " + id + ". Left baskets: " + semaphore.getValue());

        }
    }
}
